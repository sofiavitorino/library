## Library System

A terminal-based library management system built in Java. Handles book and user registration, loans, returns, and automatic fine calculation for overdue books.

**Features:** register books and users, borrow up to 5 books at once, return with automatic fine calculation, check active loans and the full catalog.

```
------------ Library System ------------
Choose an option:
[1] Register book
[2] Borrow book
[3] Return book
[4] Check book catalog
[5] Register user
[6] Check active loans
[7] List registered users
[0] Exit
```

**Running locally:**
```bash
git clone https://github.com/sofiavitorino/library.git
cd library
javac src/*.java
java -cp src Main
```
Java 17+ required.
