======================================================================
HYBRID AUTOMATION FRAMEWORK – SAUCEDEMO
======================================================================

PROJECT OVERVIEW
----------------
This project is a high-performance Hybrid Test Automation Framework 
designed for the SauceDemo web application. It combines Behavior-Driven 
Development (BDD) with TestNG and Page Object Model (POM) to ensure 
scalability, maintainability, and clear reporting.

TECH STACK
----------
- Language: Java 21
- Build Tool: Maven
- Web Automation: Selenium WebDriver
- BDD Framework: Cucumber
- Test Runner: TestNG
- Design Pattern: Page Object Model (POM)
- Apache POI (Excel Data Driven Testing)  
- Extent Reports  
- Log4j  
- GitHub

KEY MODULES & FEATURES
----------------------
1. SECURE LOGIN & LOGOUT
   - Validates authentication and automated session termination.
   - Tags: @smoke, @login, @logout

2. SHOPPING CART MANAGEMENT
   - Dynamic adding of products to the cart.
   - Real-time verification of the cart badge count.
   - Tags: @cart

3. PRODUCT FILTER FUNCTIONALITY
   - Tests sorting by: Name (A to Z), Name (Z to A), Price (Low to High), 
     and Price (High to Low).
   - Tags: @filter, @regression

4. END-TO-END (E2E) CHECKOUT
   - Full journey: Login -> Add to Cart -> Shipping Info -> Order Finish.
   - Tags: @e2e, @checkout

\#\# Framework Type  
Hybrid Framework (BDD \+ TestNG \+ POM \+ Data Driven)

HOW TO RUN TESTS
----------------
1. Via TestNG Runner:
   Right-click 'TestRunner.java' -> Run as TestNG Test.

2. Via Maven Command Line:
   mvn test -Dcucumber.filter.tags="@regression"

\#\# Folder Structure
src/main/java
├── base
├── config
├── drivers
├── listeners
├── utils
└── pages

src/test/java
├── hooks
├── runners
├── stepdefinition
└── tests

src/test/resources
├── features
├── testdata
├── config.properties
└── log4j.xml

## Framework Architecture Diagram
![Hybrid Automation Framework Architecture](docs/HybridFrameworkArchitecture.png)
======================================================================

