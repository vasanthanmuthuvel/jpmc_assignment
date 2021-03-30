Feature: Test Posts Endpoint
Description: The Purpose of this API to test Posts

Background: 
Given Base URL
  
@smoke
Scenario: Validate Posts endpoint HTTP Code
  When I hit Posts endpoint "/posts"
  Then API should return Http code as 200

@regression
Scenario: Search Posts based on id
  When I search user endpoint with id as "/posts/100"
  Then API should return Http code as 200
  And Validate id as displayed as "100"
  
@regression
Scenario Outline: Search Posts based on userid
  When I search post endpoint with userid as "<userid>"
  Then API should return Http code as 200
  And Validate userid as displayed as "<userid>"
  Examples:
  |userid|
  |7|
 
 @regression
Scenario Outline: Search comments by postid
  When I search post endpoint with postid as "<postid>"
  Then API should return Http code as 200
  And Validate postid as displayed as "<postid>"
  Examples:
  |postid|
  |7|

@regression
Scenario Outline: Create Posts based on userid
  When I provide details as userid "<userid>" And title "<title>" And body "<body>"
  Then API should return Http code as 201
  And Validate userid displayed as "<userid>"
  Examples:
  |userid|title|body|
  |8|JPMORGAN|API-SDET|
  
 @regression @negative
Scenario Outline: Validate 500 internal server error
  When I provide empty values
  Then API should return Http code as 500
  