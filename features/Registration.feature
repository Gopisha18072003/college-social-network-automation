Feature: Registration Validation
	As an admin, I want to validate the student registration form to ensure accurate and complete data entry.

  	# --- Positive Scenarios ---
	@Registration
	Scenario: Verify registration with all valid inputs
		Given User is logged in and on registration form
	    When User fills all fields with valid data
	      | Student Name    | John                  |
	      | Mobile Number   | 7589908709            |
	      | Email Id        | random123@gmail.com   |
	      | CGPA            | 9.5                   |
	      | Department Name | Civil Engineering     |
	      | Backlog Count   | 1                     |
	    And Clicks on "Register" button
	    Then Registration should be successful with a success message

	@Registration 
	Scenario: Verify registration with CGPA as Zero
		Given User is logged in and on registration form
		When User fills all fields with valid data
	      | Student Name    | Priya Mehra                | 
	      | Mobile Number   | 7894561230                 | 
	      | Email Id        | priya.mehra01@gmail.com    |
	      | CGPA            | 0                          |
	      | Department Name | Mechanical Engineering     |
	      | Backlog Count   | 2                          |
	    And Clicks on "Register" button
	    Then Registration should be successful with a success message

	@Registration
	Scenario: Verify registration with Backlog Count as Zero
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Rohan Das                  | 
	    | Mobile Number   | 9345678123                 | 
	    | Email Id        | rohan.das22@gmail.com      |
	    | CGPA            | 8.6                        |
	    | Department Name | Computer Science           |
	    | Backlog Count   | 0                          |
	  And Clicks on "Register" button
	  Then Registration should be successful with a success message

  # --- Negative Scenarios: Field Validation ---
	@Registration
	Scenario: Verify registration with all blank inputs
    Given User is logged in and on registration form
    When User leaves all fields blank:
      | Student Name    |       |
      | Mobile Number   |       |
      | Email Id        |       |
      | CGPA            |       |
      | Department Name |       |
      | Backlog Count   |       |
    And Clicks on "Register" button
    Then An error message should appear for each required field

	@Registration
	Scenario: Verify registration fails when Student Name contains special characters
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | $@#Vin@&!Kumar               |
	    | Mobile Number   | 9012345678                   |
	    | Email Id        | vinay.kumar12@gmail.com      |
	    | CGPA            | 7.8                          |
	    | Department Name | Electronics and Communication|
	    | Backlog Count   | 2                            |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input


	@Registration
	Scenario: Verify registration fails when Student Name is a long paragraph
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | This is a very long paragraph meant to test the input validation for student name field which should normally not allow such verbose content or paragraph-like inputs in the name field. |
	    | Mobile Number   | 9988776655                                                                                                                                |
	    | Email Id        | deepak.verma88@gmail.com                                                                                                                  |
	    | CGPA            | 8.4                                                                                                                                       |
	    | Department Name | Information Technology                                                                                                                   |
	    | Backlog Count   | 1                                                                                                                                         |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input


	@Registration
	Scenario: Verify registration fails when Student Name field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    |                               |
	    | Mobile Number   | 9876543210                    |
	    | Email Id        | kavita.sharma77@gmail.com     |
	    | CGPA            | 9.2                           |
	    | Department Name | Electronics and Instrumentation|
	    | Backlog Count   | 1                             |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	
	@Registration
	Scenario: Verify registration fails when Email ID is in invalid format
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Neha Reddy                 |
	    | Mobile Number   | 8765432109                 |
	    | Email Id        | neha.reddy[at]gmail        |
	    | CGPA            | 8.7                        |
	    | Department Name | Chemical Engineering       |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Email ID field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Arjun Mehta                |
	    | Mobile Number   | 9098765432                 |
	    | Email Id        |                            |
	    | CGPA            | 9.0                        |
	    | Department Name | Civil Engineering          |
	    | Backlog Count   | 2                          |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	
	
	@Registration
	Scenario: Verify registration fails when Email ID is duplicate
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Karan Malhotra              |
	    | Mobile Number   | 9123678450                  |
	    | Email Id        | karan.malhotra01@gmail.com  |
	    | CGPA            | 8.5                         |
	    | Department Name | Mechanical Engineering      |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then Registration should be successful with a success message
	
	  When User registers another student with the same Email Id
	    | Student Name    | Jane Doe                    |
	    | Mobile Number   | 9876543211                  |
	    | Email Id        | karan.malhotra01@gmail.com  |
	    | CGPA            | 8.2                         |
	    | Department Name | Mechanical Engineering      |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for duplicate email
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number has less than 10 digits
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Sneha Roy                  |
	    | Mobile Number   | 98765432                   |
	    | Email Id        | sneha.roy25@gmail.com      |
	    | CGPA            | 9.1                        |
	    | Department Name | Computer Science           |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number has more than 10 digits
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Rahul Bansal               |
	    | Mobile Number   | 987654321098               |
	    | Email Id        | rahul.bansal20@gmail.com   |
	    | CGPA            | 8.3                        |
	    | Department Name | Civil Engineering          |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number contains alphabets
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Ayesha Khan                |
	    | Mobile Number   | 98AB7632CD                 |
	    | Email Id        | ayesha.khan44@gmail.com    |
	    | CGPA            | 9.0                        |
	    | Department Name | Information Technology     |
	    | Backlog Count   | 2                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number contains special characters
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Manish Kapoor              |
	    | Mobile Number   | 98765@#321!                |
	    | Email Id        | manish.kapoor91@gmail.com  |
	    | CGPA            | 8.8                        |
	    | Department Name | Electronics Engineering     |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Shruti Agarwal              |
	    | Mobile Number   |                             |
	    | Email Id        | shruti.agarwal12@gmail.com  |
	    | CGPA            | 9.3                         |
	    | Department Name | Biotechnology               |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	
	
	@Registration
	Scenario: Verify registration fails when Mobile Number is duplicate
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Varun Mishra               |
	    | Mobile Number   | 9812345678                 |
	    | Email Id        | varun.mishra91@gmail.com   |
	    | CGPA            | 8.6                        |
	    | Department Name | Computer Science           |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then Registration should be successful with a success message
	
	  When User registers another student with the same Mobile Number
	    | Student Name    | Jane Doe                   |
	    | Mobile Number   | 9812345678                 |
	    | Email Id        | jane@example.com           |
	    | CGPA            | 8.9                        |
	    | Department Name | Computer Science           |
	    | Backlog Count   | 1                          |
	  And Clicks on "Register" button
	  Then An error message should appear for duplicate mobile number
	
	
	@Registration
	Scenario: Verify registration fails when CGPA is more than 10
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Meenal Joshi                |
	    | Mobile Number   | 9876123450                  |
	    | Email Id        | meenal.joshi99@gmail.com    |
	    | CGPA            | 10.5                        |
	    | Department Name | Information Technology       |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when CGPA is a negative value
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Rohit Nair                  |
	    | Mobile Number   | 9988123456                  |
	    | Email Id        | rohit.nair12@gmail.com      |
	    | CGPA            | -3.2                        |
	    | Department Name | Mechanical Engineering       |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when CGPA field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Anjali Verma                |
	    | Mobile Number   | 9123456789                  |
	    | Email Id        | anjali.verma98@gmail.com    |
	    | CGPA            |                             |
	    | Department Name | Civil Engineering           |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	
	
	@Registration
	Scenario: Verify registration fails when Department Name field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Tanishq Sharma              |
	    | Mobile Number   | 9876543012                  |
	    | Email Id        | tanishq.sharma11@gmail.com  |
	    | CGPA            | 8.9                         |
	    | Department Name |                             |
	    | Backlog Count   | 1                           |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	
	
	@Registration
	Scenario: Verify registration fails when Department Name is a long paragraph
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Ritika Sen                                |
	    | Mobile Number   | 9876543021                                 |
	    | Email Id        | ritika.sen01@gmail.com                     |
	    | CGPA            | 8.6                                        |
	    | Department Name | This is a very long text meant to check whether the department name field accepts or restricts lengthy paragraph-style input, which it should not in a normal use case. |
	    | Backlog Count   | 1                                          |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Department Name contains alphanumeric characters
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Rajeev Kumar                             |
	    | Mobile Number   | 9090901234                                |
	    | Email Id        | rajeev.kumar22@gmail.com                  |
	    | CGPA            | 8.9                                       |
	    | Department Name | CSE1234                                   |
	    | Backlog Count   | 1                                         |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Department Name contains special characters
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Kavya Rathi                              |
	    | Mobile Number   | 9911223344                                |
	    | Email Id        | kavya.rathi91@gmail.com                   |
	    | CGPA            | 9.1                                       |
	    | Department Name | @Mech#Eng!                                |
	    | Backlog Count   | 2                                         |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Backlog Count is a negative number
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Suman Ghosh                              |
	    | Mobile Number   | 9988776611                                |
	    | Email Id        | suman.ghosh09@gmail.com                   |
	    | CGPA            | 8.4                                       |
	    | Department Name | Electrical Engineering                    |
	    | Backlog Count   | -1                                        |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Backlog Count is a decimal value
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Ritu Jain                                |
	    | Mobile Number   | 9765432100                                |
	    | Email Id        | ritu.jain02@gmail.com                     |
	    | CGPA            | 9.0                                       |
	    | Department Name | Chemical Engineering                      |
	    | Backlog Count   | 1.5                                       |
	  And Clicks on "Register" button
	  Then An error message should appear for invalid input
	
	
	@Registration
	Scenario: Verify registration fails when Backlog Count field is empty
	  Given User is logged in and on registration form
	  When User fills all fields with valid data
	    | Student Name    | Devansh Tripathi                         |
	    | Mobile Number   | 9845612309                                |
	    | Email Id        | devansh.tripathi93@gmail.com              |
	    | CGPA            | 8.7                                       |
	    | Department Name | Electronics and Communication             |
	    | Backlog Count   |                                           |
	  And Clicks on "Register" button
	  Then An error message should appear for the required field
	  
