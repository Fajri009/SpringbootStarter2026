# 🚀 Spring Boot 3 + OpenJDK 21 Starter Project & Student Onboarding Guide

Welcome! This repository serves as the baseline project and pre-class environment setup guide. Please follow the instructions below to configure your Windows + VS Code development environment, run the sample controller, and submit your setup verification.

---

## 📋 Student Onboarding Checklist

Complete all steps below before attending the first lecture:

- [ ] Install **OpenJDK 21 (LTS)** on Windows
- [ ] Install **VS Code** with required Java & Spring extension packs
- [ ] Implement `HelloController` using Lombok's `@RequiredArgsConstructor`
- [ ] Test endpoints locally in your browser
- [ ] Fork this repository, commit your setup, and open a **Pull Request (PR)**

---

## 🛠️ Step 1: Environment Setup

### 1. Install OpenJDK 21 (Windows)
* Download and install **Eclipse Temurin JDK 21 (LTS)** or **Microsoft Build of OpenJDK 21**.
* During installation, ensure **"Set or update JAVA_HOME variable"** and **"Add to PATH"** are both selected.

### 2. Install VS Code Extensions
Open VS Code (`Ctrl + Shift + X`) and install:
1. **Extension Pack for Java** (Microsoft)
2. **Spring Boot Extension Pack** (Microsoft)
3. **Lombok Annotation Support** (Microsoft / Gabriel BB)

---

## 💻 Step 2: Implementation Details

### File Location
Create `HelloController.java` inside:  
`src/main/java/com/bankdki/helloworld/HelloController.java`

### Reference Implementation
Use **Constructor Injection** via `@RequiredArgsConstructor` (do not use `@Autowired` directly on private fields) and `@Value` for immutable DTOs:

```java
package com.bankdki.helloworld;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @Value
    public static class Result {
        int left;
        int right;
        long answer;
    }

    @GetMapping("/calc")
    public Result calc(@RequestParam int left, @RequestParam int right) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("left", left)
                .addValue("right", right);

        return jdbcTemplate.queryForObject(
                "SELECT :left + :right AS answer",
                source,
                (rs, rowNum) -> new Result(left, right, rs.getLong("answer"))
        );
    }
}
```

---

## 🧪 Step 3: Local Verification

1. **Run the Application:**
   Open `HelloworldApplication.java` in VS Code and click **Run** above `main()`, or execute `./mvnw spring-boot:run` in the terminal.

2. **Test Endpoints:**
   * Root Endpoint: `http://localhost:8080/` -> Returns `"Hello World!"`
   * Calculation Endpoint: `http://localhost:8080/calc?left=100&right=100` -> Returns:
     ```json
     {
       "left": 100,
       "right": 100,
       "answer": 200
     }
     ```

---

## 📤 Step 4: Submitting Your Work or Asking Questions

### Option A: Submitting Your Setup (Pull Request)
If you have completed the setup successfully and tested all endpoints:

1. Click **Fork** at the top right of this repository page to create a copy under your account.
2. Clone your fork locally, add your code, and push the changes back to your fork:
   ```cmd
   git add .
   git commit -m "feat: setup HelloController and complete pre-class checklist"
   git push origin main
   ```
3. Navigate to your fork on GitHub and click **New Pull Request** pointing back to this original repository.
4. Fill out the PR template with your **Full Name** and **Student ID**, and check off all completed items.

---

### Option B: Need Help or Have Something to Check?
If you encounter errors during setup (e.g., Maven build failures, Lombok processing issues, or database connectivity errors):

1. Open a **Draft Pull Request** or an **Issue** on your fork/this repository.
2. Describe the problem you are experiencing and paste the error trace from your console.
3. Check the **"Need Instructor Assistance / Review"** box in the PR template.
4. I will review your submitted code changes, leave inline feedback on your lines of code, and help you fix the configuration.

---

## 🔧 Step 5: Common Troubleshooting

| Error Message | Root Cause | Solution |
| :--- | :--- | :--- |
| `lombok.Generated cannot be resolved` | Missing Lombok processor cache sync | Run `Ctrl + Shift + P` -> **`Java: Clean Java Language Server Workspace`** -> Select **Restart and Delete**. |
| `For artifact {org.projectlombok:lombok:null:jar}: The version cannot be empty` | Missing `<parent>` block in `pom.xml` | Ensure project inherits from `spring-boot-starter-parent` (generated via Spring Initializr). |
| `Field injection is not recommended` | `@Autowired` used directly on private field | Replace with `@RequiredArgsConstructor` on class and mark injected fields `private final`. |
