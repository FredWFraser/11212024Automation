
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order and message is displayed on confirmation page

    Examples: 
      | name  				 | password  | productName  	 |
      | ffiii1@aol.com | Esther!00 | ADIDAS ORIGINAL |