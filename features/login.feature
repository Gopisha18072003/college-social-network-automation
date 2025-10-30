Feature: User Login

  Scenario: Successful login with valid credentials 
    Given user is on the login page 
    When user enters credentials (username "admin" and password "admin123")
    And user clicks the login button
    Then the user should be successfully authenticated.
 
 
  Scenario: Login with incorrect password
    Given user is on the login page
    When user enters credentials (username "admin" and password "admin456") 
    And user clicks the login button
    Then it should show "Invalid username/password".


  Scenario: Login with incorrect username
    Given user is on the login page
    When user enters credentials (username "Admin" and password "admin123") 
    And user clicks the login button
    Then it should show "Invalid username/password".


  Scenario: Login with both username and password incorrect
    Given user is on the login page
    When user enters credentials (username "Admin" and password "Admin456")
    And user clicks the login button
    Then it should show "Invalid username/password".

 
  Scenario: Login with blank username
    Given user is on the login page
    When user enters credentials (username "" and password "admin123")
    And user clicks the login button
    Then it should show "Invalid username/password".


  Scenario: Login with blank password
    Given user is on the login page
    When user enters credentials (username "admin" and password "")
    And user clicks the login button
    Then it should show "Invalid username/password".


  Scenario: Login with both fields blank
    Given user is on the login page
    When user enters credentials (username "" and password "")
    And user clicks the login button
    Then it should show "Invalid username/password".