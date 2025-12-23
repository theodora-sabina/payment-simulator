# Payment Simulator Client

A Java-based TCP client framework for testing payment transactions with the MockIso8583Host payment simulator.
This project demonstrates the integration of automated tests with a payment simulator, models complex Point of Sale (POS) interactions, and abstracts test logic from the underlying network protocol.

## Framework Architecture

The framework is built using a layered architecture to ensure that the business logic of the tests is decoupled from the technical implementation of the TCP/IP protocol.

This framework provides:
- **TCP Client**: Connects to MockIso8583Host simulator on port 13131
- **Transaction Model**: Abstracts payment transaction logic from protocol details
- **Automated Tests**: TestNG tests for success and failure scenarios

### Key Features
- Simple string-based message protocol (no full ISO8583 compliance required)
- Connection pooling and state management
- Comprehensive logging with SLF4J
- TestNG-based automated testing
- Isolated, independent test cases

### Automated Tests (TestNG)
The test suite consists of isolated, independent test cases designed to validate the reliability of the POS-Host interaction

---

## Installation & Setup
### 1. Prerequisites
    * Java 18 or higher.
    * Maven 3.6+.
    * Lombok plugin enabled in your IDE.
    * Access to the `MockIso8583Host` simulator on port 13131 (or configured port).

### 2. Simulator Setup (Go)
The project requires the `MockIso8583Host` simulator to be running locally.
1. **Install Go:** Download and install Go (version 1.16+) from [https://go.dev/dl/](https://go.dev/dl/).
2. **Clone Simulator:** ```bash
   git clone [https://github.com/thearistotlemethod/MockIso8583Host.git](https://github.com/thearistotlemethod/MockIso8583Host.git)
   cd MockIso8583Host
3. **Build and run:** ```bash
   * Linux/Mac
   go build
   ./MockIso8583Host
   * Windows
    go build
   .\MockIso8583Host.exe

### 3. Build project
mvn clean install

### 4. Run tests

# Compile
mvn clean compile

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=PaymentTransactionTest
