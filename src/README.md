This project is a **UI Test Automation Framework** built with:
- **Java 17**
- **Selenium WebDriver**
- **Cucumber BDD**
- **Page Object Model (POM)**
- **Maven** (Build + Dependency Management)
- **JUnit/TestNG** (Runner & Assertions)

The framework is designed to automate an **E-commerce web application**, covering functionalities such as:
- Product Search
- Add to Cart
- Checkout
- Validation of Error/Success messages

## Prerequisites

1. **Java 17** installed → verify with:
   ```bash
   java -version

2. **Maven** installed → verify with:
   ```bash
   mvn -v
   
3. Chrome Browser installed.
4. ChromeDriver handled by WebDriverManager (auto-managed, no manual setup required).

## Utilities
1. CommonFunctions.java → Wrapper methods (click, type, waits, moveToElement, etc.)
2. LoggerUtil.java → Centralized logging
3. Config.properties → Environment and test configuration
4. Hooks.java → Cucumber hooks (setup & teardown)

▶️ Running Tests

**Run all scenarios** and **Run tests with a specific tag**
   ```bash
    mvn clean test
    mvn clean test -Dcucumber.filter.tags="@Positive"
    mvn allure:serve