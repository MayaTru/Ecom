@tag
Feature: Check Erro on Landing Page
  I want to verfiy the error
	
	Background:
	Given I landed on Ecommerece Page
	
  @Regression
  Scenario Outline: Positive Test for Submitting Order
    Given I verify the error with <username> and <password>
    Then Verfiy error text "Incorrect email or password."

    Examples: 
      | username  					| password  	|
      | mayatru@mail.com 		| Maya@0811 	|
