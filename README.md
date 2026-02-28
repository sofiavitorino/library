## Library System

A terminal-based library management system built in Java. Handles book and user registration, loans, returns, and automatic fine calculation for overdue books.

**Features:** register books and users, borrow up to 5 books at once, return with automatic fine calculation, check active loans and the full catalog.

```
------------ Biblioteca ------------
Escolha a opção desejada:
[1] Registrar livro
[2] Emprestar livro
[3] Devolver livro
[4] Checar catálogo de livros
[5] Cadastrar usuário
[6] Conferir empréstimos ativos
[7] Listar usuários cadastrados
[0] Sair
```

**Running locally:**
```bash
git clone https://github.com/sofiavitorino/library.git
cd library
javac src/*.java
java -cp src Main
```
Java 17+ required.
