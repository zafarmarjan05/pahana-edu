# Test Suite for PahanaEdu (JUnit 5 + Mockito)

These tests mock `DBConnect.getConnection()` so you **do not need a real database** to run them.

## How to run (Maven)

1. Ensure your `pom.xml` has these dependencies:

```xml
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.12.0</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-inline</artifactId>
    <version>5.2.0</version>
    <scope>test</scope>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.2.5</version>
      <configuration>
        <includes>
          <include>**/*Test.java</include>
        </includes>
        <useModulePath>false</useModulePath>
      </configuration>
    </plugin>
  </plugins>
</build>
```

2. Run tests:

```bash
mvn -q -Dtest=*Test test
```

If you're using **Gradle**, add `junit-jupiter` and `mockito-inline` and enable JUnit Platform.

## Files created
- `src/test/java/test/BookServiceTest.java` (6 tests)
- `src/test/java/test/CustomerServiceTest.java` (5 tests)
- `src/test/java/test/UserServiceTest.java` (5 tests)
- `src/test/java/test/BillServiceTest.java` (4 tests)
- `src/test/java/test/ModelPojoTest.java` (3 tests)

**Total tests:** 23 methods (some IDEs count parameterized expectations separately).  
If you need **25+**, add additional edge-case tests (e.g., exception paths).

