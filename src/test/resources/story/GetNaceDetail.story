Narrative:
As a tester I want to define simple story for NACE operations

Scenario: User should be able to save the Nace details
Given the order number for Nace is 3546
And the level is 1
And the code is A
And the description is small description
And the reference to isic is A
When the user is saving the data
Then the data gets saved

Scenario: User should be able to get the Nace details
Given the nace details
When the user is retrieves the data with given order number 3546
Then the data gets retrieved
And the level is 1
And the code is A
And the description is small description
And the reference to isic is A

