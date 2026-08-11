# 📋 Spring Boot 3 + JDK 21 Student Pre-Class Checklist

> **Target Environment:** Windows 10 / 11 | **IDE:** VS Code | **Java:** OpenJDK 21 (LTS)

---

## ⚙️ Environment Setup Checklist

- [ ] **Install OpenJDK 21**
  - Download Eclipse Temurin JDK 21 or Microsoft OpenJDK 21.
  - Select "Set JAVA_HOME variable" and "Add to PATH" during installation.

- [ ] **Configure VS Code Extensions**
  - Install **Extension Pack for Java** (Microsoft).
  - Install **Spring Boot Extension Pack** (Microsoft).
  - Install **Lombok Annotation Support** (Microsoft / Gabriel BB).

---

## 🏗️ Project Implementation Checklist

- [ ] **Generate Project via Spring Initializr**
  - Open Command Palette (`Ctrl + Shift + P`) -> `Spring Initializr: Create a Maven Project...`
  - Group: `com.bankdki` | Artifact: `helloworld` | Java: `21`
  - Dependencies: `Spring Web`, `Spring Data JDBC`, `H2 Database`, `Lombok`.

- [ ] **Implement `HelloController.java`**
  - Location: `src/main/java/com/bankdki/helloworld/HelloController.java`
  - Use `@RequiredArgsConstructor` for constructor injection (no `@Autowired` fields).
  - Use `@Value` for the inner immutable `Result` class.

---

## 🧪 Local Endpoint Verification Checklist

- [ ] **Run Application**
  - Launch via `HelloworldApplication.java` in VS Code or `./mvnw spring-boot:run` in terminal.

- [ ] **Verify Endpoints in Browser**
  - `http://localhost:8080/` -> Returns `"Hello World!"`
  - `http://localhost:8080/calc?left=100&right=100` -> Returns `{"left":100,"right":100,"answer":200}`

---

## 📤 Submission Checklist (Fork & PR Workflow)

- [ ] **Fork & Push Changes**
  - Fork the repository to your GitHub account.
  - Push completed code to your fork (`git push origin main`).

- [ ] **Open Pull Request**
  - Create a Pull Request pointing back to the main repository.
  - Fill out your Name, Student ID, and check off completed items in the PR body.
  - If stuck or encountering build errors, open a Draft PR / Issue and flag for instructor review.