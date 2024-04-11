/**
 * This class represents the service implementation of the Payment Element and Stripe Checkout options.
 * 
 * @author ROGELIO BELTRAN
 * @version 1.0
 * @since 29/03/2024
 * 
 * Copyright (c) 2024 Gluo. All rights reserved.
 */
package mx.gluo.stripe.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;

import mx.gluo.stripe.service.StripePaymentService;
import mx.gluo.stripe.utils.*;

/**
 * 	@apiNote Payment and Checkout service implementation. 
 *  @category Implementation
 */
@Service
public class StripePaymentServiceImpl implements StripePaymentService {
	
	@Value("${stripe.public.key}")
	public String publicKey;
	
	@Value("${stripe.private.key}")
	public String privateKey;
	
	@Value("${stripe.webhook}")
	public String webhook;
	
	private static Gson gson = new Gson();

	@Override
	public ResponseEntity<Object> paymentIntent() {
		Stripe.apiKey = privateKey;

		// For sample support and debugging, not required for production:
		Stripe.setAppInfo("stripe-samples/accept-a-payment/prebuilt-checkout-page",
				          "0.0.1",
				          "https://github.com/stripe-samples"
				         );
		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
																	.setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
																																				.setEnabled(true)
																																				.build()
																							   ).setCurrency("EUR")
																	                            .setAmount(1999L)
																	                            .build();
		try {
			// Create a PaymentIntent with the order amount and currency
			PaymentIntent intent = PaymentIntent.create(params);
			// Send PaymentIntent details to client
			return ResponseEntity.status(HttpStatus.OK)
					.body(gson.toJson(new CreatePaymentResponse(intent.getClientSecret())));
		} catch (StripeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(new FailureResponse(e.getMessage())));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(e));
		}
	}

	@Override
	public ResponseEntity<String> webhook(String payload, String sigHeader) {
		Stripe.apiKey = privateKey;
		// For sample support and debugging, not required for production:
		Stripe.setAppInfo("stripe-samples/accept-a-payment/prebuilt-checkout-page",
				          "0.0.1",
				          "https://github.com/stripe-samples"
				         );
		Event event = null;
		try {
			event = Webhook.constructEvent(payload, sigHeader, webhook);
		} catch (SignatureVerificationException e) {
			return ResponseEntity.badRequest().body("Invalid signature");
		}		
		switch (event.getType()) {
		 case "payment_intent.succeeded":
	            // Fulfill any orders, e-mail receipts, etc
	            // To cancel the payment you will need to issue a Refund
	            // (https://stripe.com/docs/api/refunds)
	            System.out.println("💰Payment received!");
	            break;
		 case "payment_intent.payment_failed":
	            System.out.println("❌ Payment failed.");
	            break;
		default:
			return ResponseEntity.badRequest().body("Unexpected event type");
		}
		return ResponseEntity.ok("");
	}

	@Override
	public ResponseEntity<Object> config() {
		Stripe.apiKey = privateKey;
		// For sample support and debugging, not required for production:
		Stripe.setAppInfo("stripe-samples/accept-a-payment/prebuilt-checkout-page",
						   "0.0.1",
				           "https://github.com/stripe-samples"
						 );
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ConfigResponse(publicKey)));
	}

}
