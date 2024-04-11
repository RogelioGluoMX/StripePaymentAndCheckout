# StripePaymentAndCheckout
This repository contains an example Spring Boot application that integrates Stripe for payment processing using Payment Elements and Checkout. The application uses JavaScript for client-side interaction and Thymeleaf to generate dynamic HTML views. Maven is used as the dependency management system.

Prerequisites
JDK 17 installed
Maven 3.6.x or higher installed
Stripe developer account (https://stripe.com)
Configuration
Before running the application, you need to configure your Stripe credentials in the application.properties file. Make sure to provide your stripe values.

Copy code
stripe.public.key=your_stripe_public_key
stripe.private.key=your_stripe_secret_key
stripe.price.id=your_stripe_price_id
stripe.webhook=yout_stripe_webhook_id

Execution
Clone this repository to your local machine:
bash
Copy code
git clone https://github.com/RogelioGluoMX/StripePaymentAndCheckout.git
Navigate to the project directory:
bash
Copy code
cd example-stripe-spring-boot
Build the application using Maven:
mvn clean install package
Copy code
mvn spring-boot:run
The application will be available at http://localhost:4242.

Usage
Once the application is up and running, you can access the following functionalities:

Payment Page: Access the payment page where you can make purchases using Stripe Payment Elements and Checkout.
Google Pay, Apple Pay, and Checkout Link: Experiment with different payment options available, including Google Pay, Apple Pay wallets, and Checkout link.
Contribution
If you want to contribute to this project, you are welcome to! If you have any improvement ideas, found a bug, or want to add a new feature, please create a pull request or open an issue.

Support
If you need help or have any questions, feel free to open an issue in this repository.

License
This project is licensed under the  Apache License.