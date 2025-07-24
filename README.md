# All About Cucumber

A sample Behavior-Driven Development (BDD) framework using Cucumber, Java, and TestNG. This repository demonstrates best practices for writing maintainable, automated acceptance tests using Gherkin syntax, and can be used as a foundation for both learning and scaling up BDD projects.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Writing Scenarios](#writing-scenarios)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## About

This project aims to:
- Illustrate how to integrate Cucumber with Java and TestNG for BDD.
- Showcase best practices for feature file organization, step definition, and test automation.
- Serve as a learning tool or a base framework for extending BDD automation projects.

## Features

- Write executable specifications in Gherkin (.feature files).
- Step definitions in Java for mapping Gherkin steps to code.
- Organized directory structure for scalability.
- Test execution managed by TestNG.
- Easily extensible for UI/API testing.

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Maven 3.6+
- Git

### Setup

1. Clone the repository:
   git clone https://github.com/ajaysuryavamshi/all-about-cucumber.git cd all-about-cucumber 
2. Install dependencies:
   mvn clean install
## Project Structure
<pre>
<code>
all-about-cucumber/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               └── config/              # Config utilities
│   │               └── drivers/             # Driver utilities
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               └── pages/               # Page Object Model classes
│   │               └── runner/              # TestNG or JUnit runners
│   │               └── steps/               # Cucumber step definitions and Hooks
│   └── resources/
│       ├── features/                     # Cucumber feature files
│       └── config/                       # Environment/Test configs (e.g., dev, qa)
├── testng.xml                            # TestNG suite file
├── pom.xml                               # Maven configuration
├── README.md                             # Project documentation
└── allure-results/                       # Allure report output (in .gitignore)
</code>
</pre>

## Writing Scenarios

All user journeys (test cases) are written as `.feature` files using Gherkin syntax. Example:

Feature: User Login

Scenario: Successful login with valid credentials 

Given the user is on the login page

When the user enters valid credentials

Then the user should be redirected to the dashboard

## Running Tests

To run all tests with Maven:
mvn test

To run with TestNG:
mvn test -DsuiteXmlFile=testng.xml

Reports (optionally add HTML reports via plugins) will be generated in the target directory.

## Test Report

The latest Allure test report is published and accessible here:
[Allure Test Report](https://ajaysuryavamshi.github.io/all-about-cucumber/)

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for guidance.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
