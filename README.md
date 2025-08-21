# 📚 Pahana Edu Billing System

Pahana Edu is a **web-based billing system** for a Colombo-based bookshop.  
It automates **customer accounts, book inventory, billing, and user management**, replacing manual processes with a centralized web application.



## ✨ Features
- 👥 **Customer Management** – Add, update, view, and delete customer records.  
- 📚 **Book Management** – Manage books with full CRUD (Create, Read, Update, Delete).  
- 💵 **Billing System** – Generate invoices and track sales.  
- 🔑 **User Management** – Secure login and authentication for staff.  
- 📊 **Reports** – Access records of customers, sales, and users.  
- 🌐 **Java Servlet Application** – Built using Java Servlets, JSP, and JDBC.  


## 🛠️ Tech Stack
- **Java 11+**  
- **Apache Tomcat 8.5+**  
- **Servlets & JSP**  
- **MySQL 8.0+**  
- **Maven** (build automation)  
- **JDBC** (database connection)  
    


## 📂 Project Structure
```
pahanaEdu-main/
 └── pahanaedu/
     ├── src/main/java/
     │   ├── controler/          # Database connection
     │   ├── model/              # Data models (Bill, Book, Customer, User)
     │   ├── services/           # Business logic services
     │   └── servlet/            # Servlets (Add, Edit, Delete, View)
     ├── src/main/webapp/        # JSP views, CSS, JS
     ├── pom.xml                 # Maven configuration
     ├── .classpath
     └── .project


## ⚙️ Installation & Setup Guide

Follow these steps to set up and run the project:

### 1️⃣ Prerequisites
Make sure you have installed:
- [Java JDK 11+](https://adoptium.net/)  
- [Apache Tomcat 8.5+](https://tomcat.apache.org/)  
- [MySQL Server](https://dev.mysql.com/downloads/)  
- [Maven](https://maven.apache.org/)  
- An IDE like **Eclipse** or **IntelliJ IDEA**

Verify installations:
```bash
java -version
mvn -version
mysql --version
```

---

### 2️⃣ Clone the Repository
```bash
git clone https://github.com/zafarmarjan05/pahana-edu.git
cd pahanaEdu-main/pahanaedu


### 3️⃣ Database Setup

1. Login to MySQL:
   ```bash
   mysql -u root -p
   ```
2. Create a database:
   ```sql
   CREATE DATABASE pahanaedu;
   USE pahanaedu;
   ```
3. Create required tables (example):
   ```sql
   CREATE TABLE customers (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100),
       phone VARCHAR(20),
       address TEXT
   );

   CREATE TABLE books (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(150),
       author VARCHAR(100),
       price DECIMAL(10,2),
       stock INT
   );

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50),
       password VARCHAR(255),
       role VARCHAR(50)
   );

   CREATE TABLE bills (
       id INT AUTO_INCREMENT PRIMARY KEY,
       customer_id INT,
       book_id INT,
       quantity INT,
       total DECIMAL(10,2),
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (customer_id) REFERENCES customers(id),
       FOREIGN KEY (book_id) REFERENCES books(id)
   );
   ```
4. Update database credentials in:
   ```
   src/main/java/controler/DBConnect.java
   ```
   Example:
   ```java
   private static String url = "jdbc:mysql://localhost:3306/pahanaedu";
   private static String username = "root";
   private static String password = " ";
   ```

---

### 4️⃣ Build the Project
Using Maven:
```bash
mvn clean install

### 5️⃣ Deploy on Tomcat

#### Option 1: Using Eclipse
1. Import project → **Existing Maven Project**  
2. Right-click project → **Run on Server** → Select **Tomcat**  

#### Option 2: Manually
1. Copy the `.war` file from:
   ```
   target/pahanaedu.war
   ```
   into Tomcat’s `webapps/` folder.  
2. Start Tomcat:
   ```bash
   ./catalina.sh run   # Linux/Mac
   catalina.bat run    # Windows
   ```

---

### 6️⃣ Run the Application
Open in browser:
```
http://localhost:8080/pahanaedu
```

Default login credentials (if no DB records):
```
Username: admin
Password: admin123

