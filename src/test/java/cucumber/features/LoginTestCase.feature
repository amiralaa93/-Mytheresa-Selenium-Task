Feature: Check login functionality of the user

  @checkLogin
  Scenario Outline: Verify I can login to the myTheresa Url

    Given User opens the webbrowser "<webBrowserName>" with environment "<environment>"
    And User navigates to the given url  "<url>"
    When User provides "<username>" and "<password>"
    Then User Login should happened successfully

    Examples:
      | url                                      | webBrowserName | environment | username              | password |
      | https://www.mytheresa.com/en-de/men.html | chrome         | local       | amir.alaa93@gmail.com | Test@123 |
#      | https://www.mytheresa.com/en-de/men.html | chrome         | SauceLabs       | amir.alaa93@gmail.com | Test@123 |
      # | https://staging.mytheresa.com/en-de/men.html | chrome		 |local			 |testdemouser@gmail.com	|Test@123		|
      # | https://local.mytheresa.com/en-de/men.html | chrome				 |local			 |testdemouser@gmail.com	|Test@123		|
      # | https://test.mytheresa.com/en-de/men.html | chrome				 |local			 |testdemouser@gmail.com	|Test@123		|