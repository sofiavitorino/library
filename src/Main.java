import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            System.out.println("------------ Biblioteca ------------");
            System.out.println("Escolha a opção desejada: ");
            System.out.println("[1] Registrar livro");
            System.out.println("[2] Emprestar livro");
            System.out.println("[3] Devolver livro");
            System.out.println("[4] Checar catálogo de livros");
            System.out.println("[5] Cadastrar usuário");
            System.out.println("[6] Conferir empréstimos ativos");
            System.out.println("[7] Listar usuários cadastrados");
            System.out.println("[0] Sair");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Digite o título do livro: ");
                    String title = scanner.nextLine();
                    if (title.trim().isEmpty()) {
                        System.out.println("Título não pode ser vazio. Tente novamente.");
                        continue;
                    }
                    System.out.println("Digite o autor do livro: ");
                    String author = scanner.nextLine();
                    if (author.trim().isEmpty()) {
                        System.out.println("Autor não pode ser vazio. Tente novamente.");
                        continue;
                    }
                    System.out.println("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    if (isbn.trim().isEmpty()) {
                        System.out.println("ISBN não pode ser vazio. Tente novamente.");
                        continue;
                    }
                    System.out.println("Digite a quantidade de livros disponíveis: ");
                    int quantity;
                    while (true) {
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity <= 0) {
                                System.out.println("A quantidade deve ser maior que 0. Tente novamente.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Por favor, digite um número válido.");
                        }
                    }
                    library.registerBook(title, author, isbn, quantity);
                    System.out.println("Livro cadastrado com sucesso!" + "\n");
                break;
                case 2:
                    System.out.println("Digite o CPF do usuário");
                    String id = scanner.nextLine();
                    User user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("Usuário não cadastrado no sistema." + "\n");
                        break;
                    } else {
                        List<LoanItem> loanItems = new ArrayList<>();
                        int maxBooks = 5;
                        int count = 0;

                        System.out.println("Livros disponíveis pra empréstimo:");
                        library.printBookCatalog();

                        while (count < maxBooks) {
                            System.out.println("Digite o nome do livro ou digite '0' para sair: ");
                            title = scanner.nextLine();

                            if (title.equalsIgnoreCase("0")){
                                System.out.println("Nenhum livro emprestado.");
                                break;
                            } else {
                                Book book = library.findBookByTitle(title);

                                if (book == null) {
                                    System.out.println("Livro não encontrado. Tente novamente.");
                                    continue;
                                }
                                if (book.getQuantity() <= 0) {
                                    System.out.println("Não há cópias disponíveis no momento.");
                                    continue;
                                }
                                LoanItem item = new LoanItem(book);
                                loanItems.add(item);
                                book.decreaseQuantity();
                                count++;
                                if(count >= maxBooks){
                                    System.out.println("Você atingiu o limite de 5 livros.");
                                    break;
                                } else {
                                    System.out.println("Deseja emprestar mais um livro? (s/n)");
                                    String response = scanner.nextLine();
                                    if(!response.equalsIgnoreCase("s")){
                                        break;
                                    }
                                }
                            }
                        }
                        if(!loanItems.isEmpty()) {
                            LocalDate startDate = LocalDate.now();
                            LocalDate returnDate = startDate.plusDays(15);
                            Loan loan = new Loan(startDate, returnDate, user, loanItems);
                            library.addLoan(loan);

                            System.out.println("Livros emprestados: ");
                            for (LoanItem item : loanItems) {
                                System.out.println(item);
                            }
                        }
                        System.out.println();
                    }
                break;
                case 3:
                    System.out.println("Digite o CPF do usuário: ");
                    id = scanner.nextLine();
                    user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("Usuário não cadastrado no sistema." + "\n");
                        break;
                    }

                    boolean hasBooksToReturn = library.hasBooksToReturn(id);
                    if (!hasBooksToReturn) {
                        System.out.println("Não há livros a serem devolvidos.");
                        break;
                    }

                    while (library.hasBooksToReturn(id)){
                        System.out.println("Digite o nome do livro ou digite '0' para sair: ");
                        title = scanner.nextLine();
                        if (title.equalsIgnoreCase("0")){
                            break;
                        } else {
                            Book book = library.findBookByTitle(title);

                            if (book == null) {
                                System.out.println("Livro não encontrado. Tente novamente.");
                            } else {
                                LoanItem loanItem = library.findLoanItemByIdAndTitle(id, title);
                                if (loanItem != null) {
                                    loanItem.getBook().increaseQuantity();
                                    Loan loan = library.findLoanByIdAndTitle(id, title);
                                    if (loan != null) {
                                        double fine = loan.calculateFine();
                                        if (fine > 0) {
                                            System.out.printf("Livro devolvido com atraso. Multa: R$ %.2f%n", fine);
                                        } else {
                                            System.out.println("Livro dentro do prazo.");
                                        }
                                        loan.getLoanItems().remove(loanItem);
                                        if (loan.getLoanItems().isEmpty()) {
                                            library.getLoan().remove(loan);
                                        }
                                        System.out.println("Livro devolvido com sucesso!");
                                    } else {
                                        System.out.println("Empréstimo não encontrado.");
                                    }
                                    System.out.println("Deseja devolver mais um livro? (s/n)");
                                    String response = scanner.nextLine();
                                    if (!response.equalsIgnoreCase("s")) {
                                        break;
                                    }
                                    if(!library.hasBooksToReturn(id)){
                                        System.out.println("Não há mais livros a serem devolvidos.");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                break;
                case 4:
                    System.out.println("Catálogo de livros:");
                    library.printBookCatalog();
                break;
                case 5:
                    System.out.println("Digite o nome do usuário: ");
                    String name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("Nome não pode ser vazio. Tente novamente.");
                        continue;
                    }
                    System.out.println("Digite o CPF do usuário: ");
                    id = scanner.nextLine();
                    if (id.trim().isEmpty()) {
                        System.out.println("CPF não pode ser vazio. Tente novamente.");
                        continue;
                    }
                    if (library.findUserById(id) != null){
                        System.out.println("CPF já cadastrado no sistema.");
                        break;
                    } else {
                        System.out.println("Digite o e-mail do usuário: ");
                        String email = scanner.nextLine();
                        if (email.trim().isEmpty()) {
                            System.out.println("E-mail não pode ser vazio. Tente novamente.");
                            continue;
                        }
                        System.out.println("Digite o telefone do usuário: ");
                        String phone = scanner.nextLine();
                        if (phone.trim().isEmpty()) {
                            System.out.println("Telefone não pode ser vazio. Tente novamente.");
                            continue;
                        }
                        library.registerUser(name, id, email, phone);
                        System.out.println("Usuário cadastrado com sucesso!");
                        System.out.println();
                    }
                break;
                case 6:
                    System.out.println("Digite o CPF do usuário: ");
                    id = scanner.nextLine();
                    user = library.findUserById(id);
                    if(user == null) {
                        System.out.println("Usuário não encontrado");
                    } else {
                        List<Loan> loans = library.findLoansById(id);
                        if (loans.isEmpty()){
                            System.out.println("O usuário não possui empréstimos ativos no momento.");
                        } else {
                            System.out.println("Empréstimos ativos do usuário " + user.getName());
                            for (Loan loan : loans) {
                                System.out.println("------------------------------------");
                                for (LoanItem loanItem : loan.getLoanItems()) {
                                    System.out.println("- Título: " + loanItem.getBook().getTitle());
                                    System.out.println("  Autor: " + loanItem.getBook().getAuthor());
                                }
                                System.out.println("Data de empréstimo: " + loan.getStartDate());
                                System.out.println("Data de devolução: " + loan.getReturnDate());
                                System.out.println("------------------------------------");
                            }
                        }
                    }
                break;
                case 7:
                    System.out.println("Usuários cadastrados no sistema: ");
                    library.printRegisteredUsers();
                break;
                default:
                    System.exit(0);
            }
        }
    }
}