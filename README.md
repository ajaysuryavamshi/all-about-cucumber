# All About Cucumber

A scalable and maintainable Behavior-Driven Development (BDD) framework using **Cucumber**, **Java**, and **TestNG**. This repository illustrates automation best practices for writing expressive and reliable acceptance tests using **Gherkin syntax**. It can serve as a learning aid or a strong foundation for real-world BDD projects.

---

## 📚 Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Writing Scenarios](#writing-scenarios)
- [Running Tests](#running-tests)
- [Test Report](#test-report)
- [Contributing](#contributing)
- [License](#license)

---

## 📌 About

This project aims to:

- Integrate Cucumber with Java and TestNG for structured BDD test automation.
- Demonstrate clean coding practices for scalable test suites.
- Serve as a boilerplate for expanding into UI, API, or hybrid automation frameworks.

---

## ✨ Features

- ✅ Executable Gherkin specifications (`.feature` files)
- ✅ Step Definitions using Java
- ✅ Page Object Model for UI interaction layers
- ✅ Parallel test execution support with TestNG
- ✅ Integrated with **Allure** for rich reporting
- ✅ Tag-based selective test execution
- ✅ Clean and extensible Maven project structure

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java JDK 17+
- Maven 3.6+
- Git

### ⚙️ Setup Instructions

1. Clone the repository:

    ```bash
    git clone https://github.com/ajaysuryavamshi/all-about-cucumber.git
    cd all-about-cucumber
    ```

2. Build the project and install dependencies:

    ```bash
    mvn clean install
    ```

---

## 🗂️ Project Structure

```text
all-about-cucumber/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── config/             # Config utilities
│   │               └── drivers/            # Driver utilities
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── pages/              # Page Object Model classes
│   │               ├── runner/             # TestNG runners
│   │               └── steps/              # Cucumber step definitions & Hooks
│   └── resources/
│       ├── features/                       # Cucumber feature files
│       └── config/                         # Environment/test configuration files
├── testng.xml                              # TestNG suite configuration
├── pom.xml                                 # Maven project definition
├── README.md                               # Project documentation
└── allure-results/                         # Allure test results (output directory)
```

---

## ✍️ Writing Scenarios

Scenarios are written in `.feature` files using [Gherkin](https://cucumber.io/docs/gherkin/) syntax.

**Example:**

```gherkin
Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the dashboard
```

Organize related scenarios within domain-specific feature files under `src/test/resources/features/`.

---

## 🧪 Running Tests

### 🔹 Run All Tests

```bash
mvn clean test
```

### 🔹 Run Using TestNG Suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### 🔹 Run Tests by Tag

Use the `-Dcucumber.filter.tags` system property to execute specific tagged scenarios.

#### Example: Run Regression Suite

```bash
mvn clean test -Dcucumber.filter.tags="@regression"
```

Or, using TestNG suite with tag filter:

```bash
mvn clean test -DsuiteXmlFile=testng.xml -Dcucumber.filter.tags="@regression"
```

#### Additional Tag Examples:

| Purpose           | Command Example                                           |
|------------------|-----------------------------------------------------------|
| Run Smoke Tests  | `mvn clean test -Dcucumber.filter.tags="@smoke"`          |
| Run Sanity Suite | `mvn clean test -Dcucumber.filter.tags="@sanity"`         |
| Run Multiple Tags| `mvn clean test -Dcucumber.filter.tags="@smoke or @sanity"` |
| Exclude a Tag    | `mvn clean test -Dcucumber.filter.tags="not @wip"`        |

> 📝 **Note**: If no tag is provided, a default tag (like `@regression`) will be used via fallback in the runner.

---

## 📊 Test Report

After execution, you can generate an **Allure Report**:

```bash
allure serve allure-results
```

📎 View the published test report here:  
[🔗 Allure Test Report](https://ajaysuryavamshi.github.io/all-about-cucumber/)

---

## 🤝 Contributing

Contributions, ideas, and improvements are welcome!

Please refer to [CONTRIBUTING.md](CONTRIBUTING.md) before submitting pull requests.

---

## 📄 License

Distributed under the MIT License.  
See [LICENSE](LICENSE) for full licensing information.

---
