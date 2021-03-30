Feature: Test Users API
Description: The Purpose of this API to test Users

Background: 
Given Base URL

@smoke
Scenario: Validate users endpoint HTTP Code
   When I hit User endpoint "/users"
   Then API should return Http code as 200
 
@regression  
Scenario: search user based on id
	When I search user endpoint with id as "/users/10"
	Then API should return Http code as 200
	And Validate id as displayed as "10"
	And Validate name is not null

@regression  
Scenario Outline: search user based on name
	When I search user endpoint with name as "<name>"
	Then API should return Http code as 200
	And Validate name as "<name>"
	
	Examples:-
	|name|
	|Ervin Howell|
	
@createUser  @regression 
Scenario Outline: Create user
	When I provide user details for payload
	|jack|jackUser|jack@email.com|new street|Apt 23|new york|10281|-34.3129|73.3119|1-744-736-8231|hildegard1.gov|elite cinemas|cinemas|realtime|
	
	Then API should return Http code as 201
	And Validate id as displayed as "11"
	And Validate "<field>" should be "<value>"
	
	Examples:
	|field|value|
	|name|jack|


