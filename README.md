# 🚀 RestAssured Cucumber API Automation Framework

A **scalable API automation framework** built from scratch using **Rest Assured + Cucumber + TestNG**, following **Service Object Model** principles with clean separation of concerns.

This framework is designed to demonstrate **real-world API automation practices**, not just demo scripts.

---

## 📌 Tech Stack

| Tool / Library        | Purpose                            |
| --------------------- | ---------------------------------- |
| Java 21               | Programming language               |
| Rest Assured          | API testing                        |
| Cucumber              | BDD (Gherkin)                      |
| TestNG                | Test execution                     |
| Maven                 | Build & dependency management      |
| Jackson               | POJO serialization/deserialization |
| JSON Schema Validator | Schema validation                  |
| SLF4J                 | Logging                            |

---

## 📂 Project Structure

```
RestAssuredFramework
│
├── src/main/java
│   └── org.automation.restassuredframework
│       ├── base
│       │   └── BaseService.java
│       │
│       ├── models
│       │   ├── request
│       │   │   ├── LoginRequest.java
│       │   │   ├── CreateUserRequest.java
│       │   │   └── CreateProductRequest.java
│       │   └── response
│       │       ├── CreateUserResponse.java
│       │       └── CreateProductResponse.java
│       │
│       ├── services
│       │   ├── AuthService.java
│       │   ├── UserService.java
│       │   └── ProductService.java
│       │
│       └── utils
│           ├── AssertionUtils.java
│           └── ConfigReader.java
│
├── src/test/java
│   └── org.automation.restassuredframework
│       ├── hooks
│       │   └── CucumberHooks.java
│       │
│       ├── runner
│       │   ├── CucumberRunner.java
│       │   └── FailedCucumberRunner.java
│       │
│       └── stepdefinitions
│           ├── CreateUserSteps.java
│           ├── CreateProductSteps.java
│           └── GetUserSteps.java
│
├── src/test/resources
│   ├── features
│   │   ├── CreateUser.feature
│   │   ├── CreateProduct.feature
│   │   └── GetUser.feature
│   │
│   ├── schemas
│   │   ├── create_user_schema.json
│   │   ├── create_product_schema.json
│   │   └── get_users_schema.json
│   │
│   └── config
│       └── config.properties
│
├── target
│   └── failed_scenarios.txt
│
├── pom.xml
└── README.md
```

---

## 🧠 Framework Design Pattern

### ✔ Service Object Model (Recommended for API Automation)

* **BaseService** → Common request specification (headers, auth, logging)
* **Service classes** → API endpoints (`AuthService`, `UserService`, `ProductService`)
* **Step Definitions** → Only orchestration & assertions
* **POJOs** → Request & response models
* **Hooks** → Authentication & scenario lifecycle

👉 This avoids putting Rest Assured code inside step definitions.

---

## 🔐 Authentication Handling (`@Authentication`)

Authentication is handled via **Cucumber Hooks**, not duplicated in tests.

```java
@Before("@Authentication")
public void generateToken() {
    Response response = new AuthService().login(loginRequest);
    token = response.jsonPath().getString("token");
}
```

Usage in feature file:

```gherkin
@Authentication
Scenario: Create product with valid token
```

✔ Token is injected automatically
✔ APIs that don’t need auth (GET users, Create user) don’t use it

---

## 🧪 APIs Covered

### ✅ Create User API

* POST `/users`
* Request POJO
* Response POJO
* Schema validation
* Status code validation (201)

### ✅ Get Users API

* GET `/users?page={page}`
* Schema validation
* Status code validation (200)

### ✅ Create Product API (Authenticated)

* POST `/products`
* Token-based auth
* Schema validation
* Response field validation

---

## 📐 Schema Validation

Implemented using **Rest Assured JSON Schema Validator**

```java
response.then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("schemas/create_product_schema.json"));
```

✔ Ensures response structure is correct
✔ Prevents silent API contract breaks

---

## 🧩 Assertions Utility

Centralized assertions to avoid duplication:

```java
public class AssertionUtils {

    public static void verifyStatusCode(Response response, int expected) {
        Assert.assertEquals(response.getStatusCode(), expected);
    }

    public static void verifyNotNull(Response response, String jsonPath) {
        Assert.assertNotNull(response.jsonPath().get(jsonPath));
    }
}
```

---

## 🔁 Failed Scenario Re-Run Support

### ✔ How it works

1. Failed scenarios are written to:

```
target/failed_scenarios.txt
```

2. `FailedCucumberRunner` reruns only failed scenarios.

```java
@CucumberOptions(
    features = "@target/failed_scenarios.txt",
    glue = "org.automation.restassuredframework",
    plugin = {"pretty"}
)
```

3. Maven excludes failed runner from normal execution.

```xml
<exclude>**/*Failed*.java</exclude>
```

---

## ▶️ How to Run Tests

### Run all scenarios

```bash
mvn clean test
```

### Run failed scenarios only

```bash
mvn test -Dtest=FailedCucumberRunner
```

### Run by tag

```bash
mvn test -Dcucumber.filter.tags="@Sanity"
```

---

## 🏷 Tagging Strategy

| Tag               | Purpose                   |
| ----------------- | ------------------------- |
| `@Sanity`         | Smoke / critical tests    |
| `@Authentication` | Scenarios requiring token |
| `@Regression`     | Full regression suite     |

---

## ⚠️ Known API Behavior Notes

* FakeStore API **returns 201** for create operations (not 200)
* Some negative validations are skipped because API accepts invalid data
* Framework still supports negative testing if API enforces validations

---

## 💡 Why This Framework Is Production-Ready

✔ Built from scratch with clear architecture
✔ Follows Service Object Model for API automation
✔ Clean separation of concerns (services, steps, models, hooks)
✔ Centralized authentication handling using Cucumber Hooks
✔ Strong schema validation to catch contract changes
✔ Reusable assertion utilities to avoid duplication
✔ Tag-based execution for sanity, regression, and auth flows
✔ Failed scenario re-execution support
✔ Maven + TestNG compatible with CI pipelines
✔ Easy to extend for new APIs, validations, and environments

---


