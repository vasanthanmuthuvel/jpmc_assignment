Feature: Test Comments Endpoint
Description: The Purpose of this API to test Comments

Background: 
Given Base URL
  
@smoke
Scenario: Validate Comments endpoint HTTP Code
  When I hit Comments endpoint "/comments"
  Then API should return Http code as 200

@regression
Scenario: Search Comments based on id
  When I search comments endpoint with id as "100"
  Then API should return Http code as 200
  And Validate id as displayed as "100"
  
@regression
Scenario Outline: Search Comments based on postid
  When I search comments endpoint with postid as "<postid>"
  Then API should return Http code as 200
  And Validate postid as displayed as "<postid>"
  Examples:
  |postid|
  |7|

@regression
Scenario Outline: Create Comments by postid
  When I provide details as postid "<postid>" And title "<name>" And email "<email>" body "<body>"
  Then API should return Http code as 201
  And Validate postid displayed as "<postid>"
  Examples:
  |postid|name|email|body|
  |7|John|john@email.com|New John Comment|
  |5|Kevin|Kevin@email.com|New Kevin Comment|