<div align="center">

# 🚆 IRCTC Railway Reservation System

### ✨ Aesthetic Console-Based Train Booking System Built with Java ✨

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/OOP-Concepts-blue?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Console-Application-success?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Status-Completed-purple?style=for-the-badge"/>

</div>

---

# 🌟 Overview

The **IRCTC Railway Reservation System** is a console-based Java application inspired by real-world railway booking platforms.

It allows users to:

✔ Search trains  
✔ Book tickets  
✔ Generate PNR numbers  
✔ Cancel bookings  
✔ Check PNR status  
✔ Manage seat availability  

The project is designed using **Object-Oriented Programming (OOP)** principles with a clean modular structure and aesthetic ANSI terminal UI.

---

# ✨ Features

## 🔍 Train Search System
- Search trains using source & destination
- Search by train number
- View all trains
- View all stations

---

## 🎫 Ticket Booking
- Select train
- Choose seats
- Enter passenger details
- Auto fare calculation
- Instant booking confirmation

---

## 🧾 PNR Management
- Unique PNR generation using UUID
- Check booking status anytime

---

## ❌ Ticket Cancellation
- Cancel booked tickets
- Automatic seat restoration
- Refund display system

---

## 💺 Seat Management
- Real-time seat availability tracking
- Booking limit validation
- Waitlist indication when seats are unavailable

---

## 🎨 Aesthetic Console UI
- ANSI color formatting
- Stylish railway ticket display
- Professional console dashboard

---

# 🛠 Tech Stack

| Technology | Usage |
|------------|------|
| Java | Core Development |
| OOP | Project Architecture |
| Collections Framework | Data Management |
| UUID | PNR Generation |
| ANSI Escape Codes | Colored Console UI |

---

# 📚 OOP Concepts Used

## 🔒 Encapsulation
Private fields with public getters/setters.

## 🧩 Abstraction
Service classes hide internal implementation.

## 🏗 Composition
`Booking` class contains `Train` objects.

## 🔄 Object Interaction
Multiple classes communicate together efficiently.

---

# 📂 Project Structure

```bash
📦 project_1
 ┣ 📜 IRCTCMain.java
 ┣ 📜 Train.java
 ┣ 📜 Booking.java
 ┣ 📜 SearchService.java
 ┣ 📜 BookingService.java
 ┣ 📜 TrainDatabase.java
```

---

# 🚀 How to Run

## 🔧 Compile

```bash
javac src/*.java -d out
```

## ▶ Run

```bash
java -cp out project_1.IRCTCMain
```

---

# 🖥 Sample Console Menu

```text
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
     CONSOLE DASHBOARD
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[1] Search & Discover Trains
[2] Ticket Bookings & PNR Status
[3] System Information
[0] Exit Application
```

---

# 🎟 Sample Ticket Output

```text
╔══════════════════════════════════════════╗
║         OFFICIAL RAILWAY E-TICKET       ║
╠══════════════════════════════════════════╣
║ PNR: AB12CD34      Status: CONFIRMED    ║
║ Passenger : MADHUR                      ║
║ Train     : Rajdhani Express            ║
║ From/To   : DELHI ➜ MUMBAI              ║
║ PAYMENT   : Rs. 2500                    ║
╚══════════════════════════════════════════╝
```

---

# 🎯 Learning Outcomes

This project helped in understanding:

- Real-world Java project structure
- OOP implementation
- Service-based architecture
- Console UI design
- Data handling using lists
- Modular programming

---

# 👨‍💻 Author

## Madhur

🚀 Passionate Java Developer  
💻 Exploring DSA & Backend Development  

---

<div align="center">

### ⭐ If you like this project, give it a star ⭐

</div>