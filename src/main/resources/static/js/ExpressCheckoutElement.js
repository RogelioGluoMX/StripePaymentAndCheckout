document.addEventListener('DOMContentLoaded', async () => {
	// Load the publishable key from the server. The publishable key
	// is set in your .env file.
	const { publishableKey } = await fetch('/config').then((r) => r.json());
	if (!publishableKey) {
		addMessage(
			'No publishable key returned from the server. Please check `.env` and try again'
		);
		alert('Please set your Stripe publishable API key in the .env file');
	}

	const stripe = Stripe(publishableKey, {
		apiVersion: '2020-08-27',
	});


	const appearance = {
		theme: 'stripe',
		variables: {
			borderRadius: '36px',
		}
	}
	const expressCheckoutOptions = {
		buttonHeight: 50,
		buttonTheme: {
			applePay: 'white-outline'
		}
	}
	const elements = stripe.elements({
		mode: 'payment',
		amount: 1099,
		currency: 'usd',
		appearance,
	})
	const expressCheckoutElement = elements.create(
		'expressCheckout',
		expressCheckoutOptions
	)
	expressCheckoutElement.mount('#express-checkout-element')


});