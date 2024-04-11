/**
 * This class represents the service of Files options.
 * 
 * @author ROGELIO BELTRAN
 * @version 1.0
 * @since 29/03/2024
 * 
 * Copyright (c) 2024 Gluo. All rights reserved.
 */
package mx.gluo.stripe.service;

import org.springframework.http.ResponseEntity;

/**
 * 	@apiNote File service. 
 *  @category Service
 */
public interface FileService {
	public ResponseEntity<byte[]> getFile();
}
