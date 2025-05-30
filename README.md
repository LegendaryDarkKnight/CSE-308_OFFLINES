# CSE308: Software Engineering Sessional – Offline Assignments

This repository contains the offline assignment submissions for **CSE308: Software Engineering Sessional** at [Your University]. Each assignment corresponds to a specific topic in software engineering, primarily focused on design patterns and object-oriented programming (OOP).

---

## 📁 Directory Structure Overview

```plaintext
.
├── OFFLINE_1            # Basic Practice of OOP
├── OFFLINE_2            # Creational Design Pattern (Builder)
├── OFFLINE_3            # Structural Design Patterns (Composite, Decorator)
├── OFFLINE_4            # Behavioral Design Pattern (Observer) - IGNORE FOR NOW
├── README.md


---

## 📘 Assignment Specifications

| Assignment     | Topic                          | Specification                                                                 |
|----------------|--------------------------------|-------------------------------------------------------------------------------|
| **OFFLINE_1**  | Basic Practice of OOP          | [CSE308_OOP.pdf](OFFLINE_1/CSE308_OOP.pdf)                                   |
| **OFFLINE_2**  | Creational Design Pattern      | [CSE308Offline2.docx.pdf](OFFLINE_2/CSE308Offline2.docx.pdf)                 |
| **OFFLINE_3**  | Structural Design Patterns     | [CSE308_Structural_Design_Pattern.pdf](OFFLINE_3/CSE308_Structural_Design_Pattern.pdf) |

> 🔒 `OFFLINE_4` has been excluded for now.

---

## 💡 Assignment Details

### 🧮 OFFLINE_1: Basic OOP Practice
- **Topics:** Class design, encapsulation, inheritance, polymorphism.
- **Files:** `Accounts.java`, `Employees.java`, `Bank.java`, `Menu.java`
- **Runner Script:** [`2005033.ps1`](OFFLINE_1/2005033.ps1)

---

### 🏗️ OFFLINE_2: Creational Pattern – Builder
- **Pattern:** Builder Design Pattern
- **Packages:** `Builder`, `Director`, `Shakes`
- **Main File:** `App.java`
- **Runner Script:** [`2005033.ps1`](OFFLINE_2/2005033.ps1)

---

### 🧱 OFFLINE_3: Structural Patterns – Composite & Decorator

#### Composite
- Simulates a file system using the Composite pattern.
- **Files:** `App.java`, `Root.java`, `Directory.java`, `File.java`, etc.
- **Runner Script:** [`2005033.ps1`](OFFLINE_3/2005033.ps1)

#### Decorator
- Adds roles (Imposter, CrewMate) dynamically using the Decorator pattern.
- **Files:** `App.java`, `Crew.java`, `CrewDecorator.java`, etc.
- **Runner Script:** Same as above.

> Run specific folders by passing the subfolder name as a parameter (explained below).

---

## ⚙️ PowerShell Script Usage

Each `OFFLINE_*` folder contains a PowerShell script named `2005033.ps1`.  
This script:

1. **Cleans** old `.class` files
2. **Compiles** all Java files
3. **Runs** the `App` class
4. **Restores** the folder by cleaning up again

### ✅ To run:

```powershell
.\2005033.ps1 -SubfolderName "<Subfolder>"
