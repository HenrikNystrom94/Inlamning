Feature: RegisterUser

Background:
    Given I am am using "edge" and "chrome" as browsers
    Given I have navigated to the basketball England registration page

  Scenario Outline: Create user
    Given I enter "25/10/2000" in the date of birth field
    And I enter "Nisse" in the first name field
    And I enter "<lastName>" in the last name field
    And I enter "nisse.andersson@hotmail.com" in the email fields
    And I enter "andersson123" in the password field
    And I enter "<confirmPassword>" in the retype password field
    And I click on "<termsAndCondition>"
    And I click on age verification box
    And I click on ethics and conduct box
    When I click on confirm and join button
    Then "<expectedText>" is visible on "<verification>"

    Examples:
      | lastName  | confirmPassword | termsAndCondition       | expectedText                                                              | verification                                                                |
      | Andersson | andersson123    | label[for='sign_up_25'] | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 | h2.bold.gray.text-center.margin-bottom-40                                   |
      |           | andersson123    | label[for='sign_up_25'] | Last Name is required                                                     | span.warning.field-validation-error[data-valmsg-for='Surname']              |
      | Andersson | andersson       | label[for='sign_up_25'] | Password did not match                                                    | span.warning.field-validation-error[data-valmsg-for='ConfirmPassword'] span |
      | Andersson | andersson123    |                         | You must confirm that you have read and accepted our Terms and Conditions | span.warning.field-validation-error[data-valmsg-for='TermsAccept'] span     |


