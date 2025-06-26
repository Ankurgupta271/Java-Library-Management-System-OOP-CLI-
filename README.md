# 📚 Java Library Management System (OOP + CLI)

This is a simple **Command-Line Library Management System** built using Java. It demonstrates core **Object-Oriented Programming (OOP)** concepts like:

- Abstraction
- Encapsulation
- Inheritance
- Polymorphism

---

## ✅ Features

- Add new books to the library
- Issue books to a user
- Return issued books
- Track the **previous owner** of each book
- View available books
- View books issued to the current user
- CLI-based interaction using `Scanner`

---

## 📦 Classes Used

| Class            | Description |
|------------------|-------------|
| `Book`           | Represents a book with title, author, issued status, and last owner. |
| `User`           | Inherits from `LibraryMember`, contains issued books list. |
| `Library`        | Implements `LibraryOperations`, manages books and issue/return actions. |
| `LibraryMember`  | Parent class for user (for inheritance example). |
| `LibrarySystem`  | Main class with `Scanner` for CLI interaction. |
| `LibraryOperations` | Interface that defines abstract operations (abstraction). |

---

1. Compile the program:

javac LibrarySystem.java
java LibrarySystem
