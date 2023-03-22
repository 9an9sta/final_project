# All tests are based on [Presta Shop](https://demo.prestashop.com)
Project contains 8 Test:

* Test case #1 (Subscribe with invalid email)
* Test case #2 (Check languages)
* Test case #3 (Registration with valid data)
* Test case #4 (Registration with invalid data)
* Test case #5 (Check categories)
* Test case #6 (Check popular products)
* Test case #7 (Price drop check)
* Test case #8 (Sorting check)

Command for custom Suit run:
`-Dsuite=“suitFileName”`

Command for running all test(running test with @Test annotation):
`mvn clean test`

Command for deleting tests report :
`mvn clean`

Command for generate allure report:
`allure::report`

Command for watching allure report:
`allure:serve`

Command for using custom window height:
`-DbrowserHeight=height`

Command for using custom window width:
`-DbrowserWidth=width`

Command for using custom browser:
`-Dbrowser=browserName`

Command for using custom thread count:
`-DthreadCount=thredCount`