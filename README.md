# CSE308: Software Engineering Sessional – Offline Assignments

This repository contains the offline assignment submissions for **CSE308: Software Engineering Sessional** at BUET CSE. The assignments demonstrate key concepts in **Object-Oriented Programming (OOP)** and **Design Patterns**, implemented in Java.

---

## ⚙️ PowerShell Script Setup

Before running any PowerShell scripts, you may need to allow script execution:

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

---

## 📁 Assignment Overview

### 🧮 OFFLINE_1 – Basic OOP Practice
- **Concepts:** Encapsulation, Inheritance, Polymorphism
- **Files:** `Accounts.java`, `Employees.java`, `Bank.java`, `Menu.java`
- **To Run:**
  ```powershell
  cd OFFLINE_1
  .\2005033.ps1
  ```

---

### 🏗️ OFFLINE_2 – Creational Pattern (Builder)
- **Pattern:** Builder
- **Packages:** `Builder`, `Director`, `Shakes`
- **Main File:** `App.java`
- **To Run:**
  ```powershell
  cd OFFLINE_2
  .\2005033.ps1
  ```

---

### 🧱 OFFLINE_3 – Structural Patterns (Composite & Decorator)
- **Patterns:** Composite (File System Simulation), Decorator (Dynamic Role Assignment)
- **Folders:** `Composite`, `Decorator`
- **Main File (Both):** `App.java`
- **To Run:**
  ```powershell
  cd OFFLINE_3
  .\2005033.ps1 -SubfolderName "Composite"
  .\2005033.ps1 -SubfolderName "Decorator"
  ```

---

### 🔁 OFFLINE_4 – Behavioral Pattern (Observer)
- **Pattern:** Observer (Stock Market Simulation)
- **Files:** `Server.java`, `Client.java`, `Stock.java`, `User.java`, `Administrator.java`, `Wrapper.java`, `init_stocks.txt`
- **To Run:** Use two separate terminals:
  ```powershell
  cd OFFLINE_4
  .\clean_up.ps1
  .\2005033_server.ps1       # Terminal 1

  # In another terminal
  cd OFFLINE_4
  .\2005033_client.ps1       # Terminal 2
  ```

---

## 📄 Assignment Specs

| Assignment     | Topic                     | Specification File                                                                |
|----------------|---------------------------|------------------------------------------------------------------------------------|
| OFFLINE_1      | Basic OOP                 | [CSE308_OOP.pdf](OFFLINE_1/CSE308_OOP.pdf)                                        |
| OFFLINE_2      | Builder Pattern           | [CSE308Offline2.docx.pdf](OFFLINE_2/CSE308Offline2.docx.pdf)                      |
| OFFLINE_3      | Composite & Decorator     | [CSE308_Structural_Design_Pattern.pdf](OFFLINE_3/CSE308_Structural_Design_Pattern.pdf) |
| OFFLINE_4      | Observer Pattern          | [CSE308_Behavioural_Design_Pattern.pdf](OFFLINE_4/CSE308_Behavioural_Design_Pattern.pdf) |

---
