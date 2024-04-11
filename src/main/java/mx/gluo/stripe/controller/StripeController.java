/**
 * This class represents the main controller of the example.
 * 
 * @author ROGELIO BELTRAN
 * @version 1.0
 * @since 29/03/2024
 * 
 * Copyright (c) 2024 Gluo. All rights reserved.
 */
package mx.gluo.stripe.controller;

import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.stripe.exception.StripeException;

import mx.gluo.stripe.service.FileService;
import mx.gluo.stripe.service.StripePaymentService;

/**
 * 	@apiNote Main Stripe controller 
 *  @category Controller
 */
@Controller
public class StripeController {

	@Autowired
	private StripePaymentService stripePaymentService;
	
	@Autowired
	private FileService fileService;


	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/index2")
	public String index2() {
		return "index2";
	}

	@GetMapping("/return")
	public String success() {
		return "return";
	}
	
	@GetMapping("/.well-known/apple-developer-merchantid-domain-association")
	public ResponseEntity<byte[]> obtenerArchivo() {
       return fileService.getFile();
    }
	

	@GetMapping("/config")
	public ResponseEntity<Object> config() throws StripeException {
		return stripePaymentService.config();
	}

	@GetMapping("/create-payment-intent")
	public ResponseEntity<Object> createPaymentIntent() throws StripeException {
		return stripePaymentService.paymentIntent();
	}

	@PostMapping("/webhook")
	public ResponseEntity<String> weebhook(@RequestBody String payload,
										   @RequestHeader("Stripe-Signature") String sigHeader
										   ) throws StripeException {
		return stripePaymentService.webhook(payload, sigHeader);
	}

}
