Feature: User must click on the Request for second opinion button and navigated to the Second opinion page

  Scenario: Validate that the user is navigated to Welcome page
    Given Browser is open
    And User clicks on Welcome Login button

  Scenario: Validate that the user is able to Login with valid data
    When User enters valid phonenumber and password and User click on login

  Scenario: Validate that the user is able to click on 'Request for a Second opinion' button
    When User should be able to click on the Request for second opinion button and navigated to the Second opinion page successfully

  Scenario: Validate that the 'Proceed' button is disabled when user unchecked the check box self and I agree to DocPanels terms and condition
    When User should be not able to click on the Proceed button when the check box self and I agree to DocPanels terms and condition is unchecked

  Scenario: Validate that user should get a validation message on clicking "Continue to add clinical details" button without uploading "Upload your scan page"
    When User should get a validation message when click on Continue to add clinical details button without upload my scans

  Scenario: Validate that user should get validation message on clicking "Continue to add clinical details" button when the check box "I don not have a doctor's reports" is unchecked
    When User should get a validation message when click on Continue to add clinical details button when the check box I don not have a doctors reports is unchecked

  Scenario: Validate that user should not allowed to  click on "Continue to add clinical details" without "upload my reports"
    When User should get a validation message when click on Continue to add clinical details button without upload my reports

  Scenario: Validate that the user is not allowed to leave  check box 'other'  unchecked
    When User should not be allowed to uncheck the other check box

  Scenario: Validate that the user is not allowed to proceed when 'First name' and 'Last name' fields are blank
    When validation messages should be displayed when First name and Last name fields are blank

  Scenario: Validate that the user is not allowed leave blank 'First name' field
    When validation message should be displayed when First name field is blank

  Scenario: Validate that the user is not allowed leave blank 'Last name' field
    When validation message should be displayed when Last name fields are blank

  Scenario: Validate that the user should get a validation message on entering invalid 'First name'  and 'Last name'
    When validation messages should be displayed when First name and Last name fields are invalid

  Scenario: Validate that the user is not allowed to Proceed with invalid 'First name' and valid 'Last name'
    When validation messages should be displayed when invalid First name and valid Last name are given

  Scenario: Validate that the user is not allowed to Proceed with valid 'First name' and invalid 'Last name'
    When validation messages should be displayed when valid First name and invalid Last name are given

  Scenario: Validate that the "Proceed" button is disabled when user unchecked  the check box 'I agree to DocPanels terms and condition'
    When User should be not able to click on the Proceed button when the check box I agree to DocPanels terms and condition is unchecked

  Scenario: Validate that the "Proceed" button is disabled when user unchecked  the check box 'I am the legal guardian or have the necessary consents to order this second opinion'
    When User should be not able to click on the Proceed button when the check box I am the legal guardian or have the necessary consents to order this second opinion" is unchecked
