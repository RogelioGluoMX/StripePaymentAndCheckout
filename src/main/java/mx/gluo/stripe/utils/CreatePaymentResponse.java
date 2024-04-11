package mx.gluo.stripe.utils;

public class CreatePaymentResponse {
	private String clientSecret;

	public CreatePaymentResponse(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}