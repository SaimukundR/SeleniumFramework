#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Place an retail order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given the user has landed on the Ecommerce page

  @Regression
  Scenario Outline: Creating a retail order
    Given user has logged in with <name> and <password>
    When user adds <productName> to the basket
    And checksout <productName> places the order
    Then <orderConfirmation> message is displayed

    Examples: 
      | name                 | password        | productName     | orderConfirmation       |
      | test454424@gmail.com | Flashback@123   | IPHONE 13 PRO   | THANKYOU FOR THE ORDER. |
      | test454424@gmail.com | Flashback@123   | ADIDAS ORIGINAL | THANKYOU FOR THE ORDER. |
      
  