package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.ExcelReadWrite;
import com.models.AddressDetails;
import com.models.Employee;
import com.models.EmployeeDetails;
import com.models.RSAToken;
import com.util.Constants;

@Controller
public class ReadWriteController {
    Logger logger = LoggerFactory.getLogger(ReadWriteController.class);
    @Autowired
    ExcelReadWrite excelReadWrite;
    int count = 0;

    @GetMapping("logTime")
    public String logTime(@RequestParam @NotNull(message = "LID is mandatory") @Pattern(regexp =
            "^L[0-9]*$") @Size(min= 7, max = 7, message = "LID Should be valid length") String lid, @RequestParam
            String time, @RequestParam String eventType) {
        DateFormat dayFormatter = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm");
        long milliSeconds = Long.parseLong(time);
        System.out.println(milliSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        System.out.println(String.valueOf(dayFormatter.format(calendar.getTime())));
        System.out.println(String.valueOf(timeFormatter.format(calendar.getTime())));
        Employee emp = new Employee();
        emp.setLid(lid);
        emp.setDay(String.valueOf(dayFormatter.format(calendar.getTime())));
        if (eventType.equalsIgnoreCase("login")) {
            emp.setLoginTime(String.valueOf(timeFormatter.format(calendar.getTime())));
        } else if (eventType.equalsIgnoreCase("logoff")) {
            emp.setLogoffTime(String.valueOf(timeFormatter.format(calendar.getTime())));
        }
        logger.info("Employee Object: {}", emp);
        try {
            if (fileExists()) {
                excelReadWrite.update(emp, eventType);
            } else {
                excelReadWrite.write(emp);
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
    /**
     * https://localhost:9001/logTimee?lid=lid&rsa=rsa&expirydate=expirydate&name=name&email=email
     * 
     * @param lid
     * @param time
     * @param eventType
     * @return
     */
    @GetMapping("logTokenDetails")
    public String logTokenDetails(
    		@RequestParam 
    			@NotNull(message = "LID is mandatory") 
    			@Size(min= 7, max = 7, message = "LID Should be valid length") String lid, 
    		@RequestParam String rsa, 
   			@RequestParam String expirydate, 
    		@RequestParam String name, 
       		@RequestParam String email) {
    	
    	System.out.println("logTokenDetails Started");

        RSAToken rsaToken = new RSAToken();
        rsaToken.setLid(lid);
        rsaToken.setEmail(email);
        rsaToken.setRsa(rsa);
        rsaToken.setExpirydate(expirydate);
        rsaToken.setName(name);
       
        logger.info("RSAToken Object: {}", rsaToken);
        try {
        	 excelReadWrite.writeRSAToken(rsaToken);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    
    /**
     * 
     * @param lid
     * @param rsa
     * @param expirydate
     * @param name
     * @param email
     * @return
     */
    
    @GetMapping("saveAddressDetails")
    public String saveAddressDetails(
    		@RequestParam 
    			@NotNull(message = "LID is mandatory") 
    			@Size(min= 7, max = 7, message = "LID Should be valid length") String lid, 
    		@RequestParam String phone,
    		@RequestParam String address1,
    		@RequestParam String address2, 
    		@RequestParam String landmark, 
    		@RequestParam String city,
    		@RequestParam String state, 
   			@RequestParam String pin, 
    		@RequestParam String name) {
    	
    	System.out.println("saveAddressDetails Started");

        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setLid(lid);
        addressDetails.setName(name);
        addressDetails.setPhone(phone);
        addressDetails.setAddress1(address1);
        addressDetails.setAddress2(address2);
        addressDetails.setCity(city);
        addressDetails.setLandmark(landmark);
        addressDetails.setPin(pin);
        addressDetails.setState(state);
        
        logger.info("addressDetails Object: {}", addressDetails);
        try {
        	 excelReadWrite.writeAddressDetails(addressDetails);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("getAllLids")
    @ResponseBody
    public List<EmployeeDetails> getAllLids(){
        try {
            return excelReadWrite.readLidDetails();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            logger.info("Unable to fetch LID data");
            return null;
        }
    }

    public boolean fileExists() {
        File file = new File(Constants.FILE_NAME);
        logger.info("Files exists? {}", file.exists());
        return file.exists();
    }
    
    @GetMapping("sprintDetails")
    public void sprintTracker(HttpServletResponse response,@RequestParam String lid, @RequestParam String sprintEmailid, @RequestParam String featureTeamName, @RequestParam String projectName, @RequestParam String sprintNumber) throws EncryptedDocumentException, InvalidFormatException, IOException {
    	File file = new File(Constants.FILE_NAME);
        System.out.println("lid"+ lid+" email"+sprintEmailid);
    	try {
            if (file.exists()) {
                excelReadWrite.writeSprintDetails(lid, sprintEmailid, featureTeamName, projectName, sprintNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    	 byte[] bytes =Files.readAllBytes(file.toPath());
         response.setContentType("application/xlsx");
         response.setHeader("Content-Disposition", "inline; filename=SpringDetails.xlsx");
         response.setContentLength(bytes.length);
         try {
             response.getOutputStream().write(bytes);
             response.getOutputStream().flush();
         } catch (IOException e) {
             logger.error("IOException", e);
         }
    }

}
