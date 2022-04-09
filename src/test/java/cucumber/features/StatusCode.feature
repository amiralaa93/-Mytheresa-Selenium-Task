Feature: Check the expected status code for all the hyperlinks on the url.

  @checkStatusCode
  Scenario Outline: Validating all hyperlinks on the webpage to return correct status code

    Given User navigates to the url
    And User checks for hyperlink on the page "<url>"
    Then Webpage should return 200 or 30x


    Examples:
      | url                                      |
      | https://www.mytheresa.com/en-de/men.html | 
      
