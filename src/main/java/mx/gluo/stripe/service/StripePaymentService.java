/**
 * This class represents the service of the Payment Element and Stripe Checkout options.
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
 * 	@apiNote Payment and Checkout service implementation. 
 *  @category Service
 */
public interface StripePaymentService {
	
	public ResponseEntity<Object> paymentIntent();
	public ResponseEntity<String> webhook(String payload, String sigHeader);
	public ResponseEntity<Object> config();

}
