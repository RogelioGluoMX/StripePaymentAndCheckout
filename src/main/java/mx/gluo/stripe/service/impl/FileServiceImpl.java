/**
 * This class represents the service of files options.
 * 
 * @author ROGELIO BELTRAN
 * @version 1.0
 * @since 29/03/2024
 * 
 * Copyright (c) 2024 Gluo. All rights reserved.
 */
package mx.gluo.stripe.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.gluo.stripe.service.FileService;

/**
 * 	@apiNote File service implementation. 
 *  @category Implementation
 */
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public ResponseEntity<byte[]> getFile() {
		 try {
	            // Load the file from the resources directory
	            Resource resource = resourceLoader.getResource("classpath:apple-developer-merchantid-domain-association");
	            // Read the file and convert it to bytes
	            InputStream inputStream = resource.getInputStream();
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            int byteRead;
	            while ((byteRead = inputStream.read()) != -1) {
	                outputStream.write(byteRead);
	            }
	            byte[] bytes = outputStream.toByteArray();
	            // Return the bytes of the file in a ResponseEntity with an OK status (200).
	            return ResponseEntity.ok().body(bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the error by returning a ResponseEntity with a server internal error status (500).
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	}
}
