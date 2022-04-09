Feature: Check number of opened PR

  @checkOpenedPR
  Scenario Outline: How many open pull requests

    Given User open the browser "<webBrowserName>" with environment "<environment>"
    Given User navigate to the given  "<url>"
    When User checks the number of PR
    Then Number of PR displayed

    Examples:
      | url                                      | webBrowserName | environment |
      | https://github.com/appwrite/appwrite/pulls | chrome         | local       |