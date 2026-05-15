# RestAssured API Test Automation Framework

This is a REST API test automation framework built with Rest Assured and TestNG.
OnlineStoreFakeRest API

---

## What’s inside?

* Rest Assured + TestNG based test structure
* API tests for Product, Cart, User and Login flows
* Request payload creation using POJO and dynamic methods
* JSON & XML response parsing
* Schema validation
* API chaining
* Data-driven testing (JSON / CSV / Excel)
* Request & response logging
* Extent Reports and Allure Reports

---

## Project Structure

* `routes` → API endpoints are defined here
* `pojo` → request/response models
* `payloads` → dynamic request body generation
* `testcases` → test implementations
* `utils` → reusable utilities (data providers, config reader, reporting)

---

## How to run

Using Maven:

```bash id="run01"
mvn clean test
```

Or run directly:

```id="run02"
testng.xml
```

---

## Reports

* Allure report:

```bash id="run03"
allure serve ./allure-results
```

## Jenkins CI/CD Setup

* This project is configured to run with Jenkins for CI/CD. The following plugins are required: 
* Maven Integration, Git & GitHub Plugin, Allure Jenkins Plugin, and HTML Publisher Plugin.

Additionally, the Jenkins global tools must include Maven, JDK, and Git.


## Additional Notes

* Logs are stored under `/logs`
* Test data is located in `/testdata`
* Configuration is managed via `config.properties`
* The framework is designed to be extendable with CI/CD pipelines and additional test scenarios
