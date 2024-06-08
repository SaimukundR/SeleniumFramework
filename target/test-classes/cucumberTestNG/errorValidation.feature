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
Feature: Try to login to ecommerce and fail
  I want to use this template for my feature file
  
  Background:
  Given the user has landed on the Ecommerce page


 @errorValidation
    Scenario Outline: Validating error
    Given user tries to login in with <name> and <password>
    Then validate <errorMsg> is displayed
    Examples: 
      | name                  | password          | errorMsg                   |
      | test4545424@gmail.com | Flashback@1235    |Incorrect email or password.|
