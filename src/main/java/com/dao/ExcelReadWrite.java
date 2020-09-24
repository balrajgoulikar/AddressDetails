package com.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.models.AddressDetails;

@Component
public class ExcelReadWrite {
    

   
	private boolean writeToFile(String fileName, String fileData) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(fileData + "\r\n");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
    public void writeAddressDetails(AddressDetails addressDetails) throws IOException {
        String fileData = "";
        
        fileData += addressDetails.getLid()+ "	";
        fileData += addressDetails.getName()+ "	";
        fileData += addressDetails.getPhone()+ "	";
        fileData += addressDetails.getAddress1()+ "	";
        fileData += addressDetails.getAddress2()+ "	";
        fileData += addressDetails.getCity()+ "	";
        fileData += addressDetails.getState()+ "	";
        fileData += addressDetails.getPin()+ "	";
        fileData += addressDetails.getLandmark()+ "	";
        


        writeToFile("AddressDetails.txt", fileData);

    }
    
 
		
}