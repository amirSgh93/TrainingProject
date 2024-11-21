Feature: Validating place API's
@AddPlace
  Scenario Outline: Verify if place is being successefully added using AddPlaceAPI
    Given Add place Payload with "<name>" "<language>" "<address>"
    When  user calls "AddPlaceAPI" with "post" http request
    Then  the API call is success with the status code 200
    And "status" in response body is "OK"
    And  "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address             |
      | AAhouse | English  | World cross center  |
   #   | BBhouse | French   | Sea cross center    |
@DeletePlace
  Scenario: verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then  the API call is success with the status code 200
    And "status" in response body is "OK"