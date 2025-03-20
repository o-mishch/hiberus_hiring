## **API Documentation (Swagger UI)**
Access the **Swagger UI** for API documentation:

 [Swagger UI](http://localhost:8081/swagger-ui/index.html)

- Provides an interactive API explorer.
- Enables testing of endpoints directly from the browser.
- Automatically generated from your **Spring Boot** application.

---

## **H2 Database Console**
The **H2 Console** allows you to interact with the in-memory database:

 [H2 Console](http://localhost:8081/h2-console)

### **JDBC Connection Details**
| **Field**      | **Value**               |
|---------------|-----------------------|
| **JDBC URL**  | `jdbc:h2:mem:testdb`   |
| **User**      | `sa`                   |
| **Password**  | _(Leave blank)_        |

### **Instructions to Access**
1. Open the **H2 Console** link in your browser.
2. Enter the JDBC connection details.
3. Click **Connect** to explore the database.

---

##  **Troubleshooting**
### ** Swagger UI Not Loading?**
- Ensure your application is running on **port 8081**.
- Check if `springdoc-openapi-starter-webmvc-ui` dependency is added:
  ```xml
  <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.3.0</version>
  </dependency>
  ```
- Restart the application and try again.

### ** H2 Console Not Connecting?**
- Ensure `spring.h2.console.enabled=true` is set in `application.yml`.
- Verify that `spring.datasource.url` is correctly set to:
  ```yaml
  spring:
    datasource:
      url: jdbc:h2:mem:testdb
  ```
- Restart the application and try again.

---

## **Additional Resources**
-  **Spring Boot + H2**: [Official Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#data.sql.h2)
-  **Swagger OpenAPI**: [Springdoc GitHub](https://github.com/springdoc/springdoc-openapi)

