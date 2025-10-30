Feature: View All Students Validation

    Scenario: Display of all student data fields
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then All student attributes (Name, Email, Mobile, CGPA, Department, Backlog Count) should be displayed correctly

    Scenario: Display order of students (e.g., newest first or alphabetical)
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then The students should appear in the expected order (e.g., by roll number)
    Scenario: Column headers are correct and visible
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then Column titles (Name, Email ID, CGPA, etc.) should be clearly visible

    Scenario: Table scrolls properly when there are many students
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then Horizontal or vertical scrollbars should appear as expected
    Scenario: Duplicate entries are shown distinctly or handled
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then The system should handle or display duplicate entries distinctly
	
    Scenario: View all registered students after at least one registration
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then The student list should be displayed

    Scenario: All fields are not blank
        Given At least one student is present
        When Admin navigates to "View all Student"
        Then All fields should not be blank
