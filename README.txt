PROBLEM:

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products which are exempt.
Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
When I purchase items I receive a receipt that lists the name of all the items and their price (including tax),
finishing with the total cost of the items, and the total amounts of sales taxes paid.
The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

SOLUTION:

-   Implemented Decorator design pattern
-   Here the concrete item has a basic price. Based on whether it is exempted from tax or not, decorators are added
    to the basic item.
-   This gives immense flexibility to add new functionality by changing the behaviour of any decorator if required
    or add new decorators.
-   This follows the principle of open for extension and closed for modification.
-   Decorator has helped me to attach additional responsibilities to the basic item dynamically, which provided me with
    an alternative to inheritance for extending functionality.
-   Which means that tomorrow if say Modi says that GST will be applicable to all the products or specific set of products
    no code changes will be required but we will create a new decorator with the new requirements and
    wrap the existing item with the same.
-   If tomorrow I also have to give a certain discount based on the price or say any other criteria, new decorators can easily
    be added.
-   So the basic idea is we decorate an objects operation with additional functionality by composing the object with the
    decorator, we can add additional functionality to the object by composing multiple decorators.
-   Initially I was thinking of using inheritance, but extending and managing it will be difficult when we will have to create
    classes for each and every product.
-   Have kept the tax attribute outside of Item class as Item is not responsible for calculating the tax.
-   The government sets the tax for each item and is responsible for the same. The item is unaware of the same.
-   For now I have calculated the tax in the cart class only, was planning to have a tax calculator class which will expose
    a method of calculateTax, which can be exposed as a Restful API if required.
-   I have used maven 3.6.0.
-   Once the application grows we can use Spring for dependency injection.
-   Planning to use Mockito eventually for unit testing.
-   The tax rate is as of now added in individual decorators, which later can be externalized.
-   The items which are exempted from tax are right now hardcoded which can be later externalized, so that we wont be required
    to touch/change code if we have to tomorrow add new item to this list.
-   Have declared a SalesTaxException for now, which wraps any exception to a custom exception which can be thrown
    to the client.
-   If we have to connect to db, I would suggest we use JPA as the specification and hibernate as its implementation
    which is a great ORM tool.
-   Loggers (slf4j/log4j) have to be added to all the classes and instead of outputting to console, Loggers will log the output
    to the specified location.
-   This will also be integrated with jenkins/git/docker/openshift for cicd.
-   Also there are no code smells in the code apart from smells like adding logger instead of using Sysout.

HOW TO RUN:

Give the file name / s full path to the jar file and execute below command to run the program.

java -jar salestaxcalculator-1.0-SNAPSHOT.jar /Users/sneha/IdeaProjects/boku4/src/main/resources/input1.txt

Below is the sample run

Snehas-MacBook-Pro:target sneha$ java -jar salestaxcalculator-1.0.jar /Users/sneha/IdeaProjects/boku4/src/main/resources/input1.txt /Users/sneha/IdeaProjects/boku4/src/main/resources/input2.txt
INPUT:
1 chocolate bar at 0.85
1 book at 12.49
1 music CD at 14.99
OUTPUT:
1 chocolate bar : 0.85
1 book : 12.49
1 music CD : 16.49
Sales Tax : 1.50
Total : 29.83
INPUT:
1 imported bottle of perfume at 47.50
1 imported box of chocolates at 10.00
OUTPUT:
1 imported bottle of perfume : 54.65
1 imported box of chocolates : 10.50
Sales Tax : 7.65
Total : 65.15

