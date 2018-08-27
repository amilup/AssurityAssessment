Feature: Catalogue API Testing

  Scenario Outline: Verify Category details
    Given A user access to Categories API
      And I have added <categoryID>
      And I call to Categories Details API
      And I have add query String <queryStringValue>
     When I make a GET request
     Then I verify the status as OK
      And I verify <categoryName> in the API response
      And I verify <canRelist> status
      And I verify Promotions element with <name> has a Description that contains the <text> 
  
    Examples: 
      | categoryID | queryStringValue | categoryName   | canRelist | name    | text            | 
      | 6327       | false            | Carbon credits | true      | Gallery | 2x larger image | 