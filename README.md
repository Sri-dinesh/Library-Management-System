# Library Management System

A robust, console-based Java application designed to manage library operations including book registration, member management, and the issuing/returning process.

## Key Features

### Book Management
- **Registration**: Add new books to the system with unique IDs and titles.
- **Availability Tracking**: Real-time tracking of book status (Available or Issued).
- **Comprehensive Listing**: View all books currently in the repository with their status.

### Member Management
- **Registration**: Register members with unique IDs and names.
- **Record Keeping**: Maintain a directory of all registered library members.

### Circulation System
- **Issue Books**: Assign books to members using unique identifiers.
- **Return Books**: Process book returns and update availability status automatically.
- **Flexible Searching**: Supports case-insensitive lookups for both Book IDs and Member IDs.
- **Validation**: Built-in checks to ensure:
  - Books and members exist before transactions.
  - Books already issued cannot be re-issued until returned.
  - Returns are validated against the member who originally borrowed the book.

### Persistence & Data Management
- **Automatic Saving**: State is preserved using Java Serialization (`library.ser`).
- **Data Restoration**: Automatically loads previous session data on startup.
- **Seed Data**: Comes pre-populated with common academic books and sample members for immediate testing.

---

## Technical Architecture

### Core Components
- **`ILibrary` (Interface)**: Defines the contract for all library operations, ensuring a decoupled design.
- **`Library` (Core Engine)**: Implements the business logic, using `HashMap` for efficient O(1) lookups of books and members.
- **Data Models**:
  - `Person` & `Member`: Utilizes inheritance to manage user data.
  - `Book`: Encapsulates book details and status.
- **Custom Exception Handling**: `BookNotAvailableException` provides specific error feedback for circulation conflicts.

### Design Principles & Concepts
- **Inheritance & Polymorphism**: Used in the `Person` -> `Member` hierarchy.
- **Abstraction**: Provided via the `ILibrary` interface.
- **Collections Framework**: Extensive use of `Map` and `ArrayList` for data management.
- **Serialization**: Implements `Serializable` for persistent storage.
- **Robustness**: Utilizes try-with-resources and explicit exception handling for reliable execution.

---

## Console Interface Commands

1. **Add Book**: Prompts for ID and Title.
2. **Add Member**: Prompts for ID and Name.
3. **Issue Book**: Links a Book ID to a Member ID.
4. **Return Book**: Disconnects a Book ID from a Member ID.
5. **Display Books**: Shows all books and their current status.
6. **Display Members**: Lists all registered members.
7. **Save & Exit**: Commits changes to disk and closes the application.

---

## Requirements
- Java Development Kit (JDK) 8 or higher.

## How to Run

### Using the Bash Script (Recommended)
The project is modularized and requires a specific compilation path. Use the provided script to run it effortlessly:
1. Open a terminal in the project root.
2. Ensure the script is executable:
   ```bash
   chmod +x run.sh
   ```
3. Run the project:
   ```bash
   ./run.sh
   ```

### Manual Compilation
```bash
mkdir -p out
javac -d out -sourcepath src/main/java src/main/java/com/library/LibraryManagementSystem.java
java -cp out com.library.LibraryManagementSystem
```
