package com.controller;

import java.io.IOException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.ExcelReadWrite;
import com.models.AddressDetails;

@Controller
public class ReadWriteController {
    Logger logger = LoggerFactory.getLogger(ReadWriteController.class);
    @Autowired
    ExcelReadWrite excelReadWrite;
    int count = 0;


    
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
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }



    
}
