@tag
Feature: Place Products Order
  I want to place order and verfiy the orders
	
	Background:
	Given I landed on Ecommerece Page
	
 	@Regression @Smoke
  Scenario Outline: Positive Test for Submitting Order
    Given Login Application with <username> and <password>
    When I add product to Cart
    And Checkout and Submit Order
    Then I verify the orderList

    Examples: 
      | username  					| password  	|
      | mayatru@mail.com 		| Maya@0812 	|
