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
            System.out.println("[0] Sair");
            System.out.println();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Digite o nome do livro: ");
                    String title = scanner.nextLine();
                    System.out.println("Digite o nome do autor: ");
                    String author = scanner.nextLine();
                    System.out.println("Digite o ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Digite a quantidade de cópias disponíveis: ");
                    int quantity = scanner.nextInt();
                    library.registerBook(title, author, isbn, quantity);
                    System.out.println("Livro cadastrado com sucesso!" + "\n");
                break;
                case 2:
                    System.out.println("Digite o ID do usuário");
                    String id = scanner.nextLine();
                    User user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("Usuário não cadastrado no sistema." + "\n");
                        break;
                    } else {
                        List<LoanItem> loanItems = new ArrayList<>();
                        int maxBooks = 5;
                        int count = 0;
                        while (count < maxBooks) {
                            System.out.println("Digite o nome do livro: ");
                            title = scanner.nextLine();
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
                        LocalDate startDate = LocalDate.now();
                        LocalDate returnDate = startDate.plusDays(15);
                        Loan loan = new Loan(startDate, returnDate, user, loanItems);
                        library.addLoan(loan);

                        System.out.println(loanItems);
                    }
                break;
                case 3:
                    System.out.println("Digite o ID do usuário: ");
                    id = scanner.nextLine();
                    user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("Usuário não cadastrado no sistema." + "\n");
                        break;
                    }
                    System.out.println("Digite o título do livro a ser devolvido:");
                    title = scanner.nextLine();
                    Book book = library.findBookByTitle(title);
                    if (book == null) {
                        System.out.println("Livro não encontrado. Tente novamente.");
                    } else {
                        LoanItem loanItems = library.findLoanItemByIdAndTitle(id, title);
                        if (loanItems != null){
                            loanItems.getBook().increaseQuantity();
                            Loan loan = library.findLoanByIdAndTitle(id, title);
                            if (loan != null) {
                                double fine = loan.calculateFine();
                                if (fine > 0){
                                    System.out.printf("Livro devolvido com atraso. Multa: R$ %.2f%n", fine);
                                } else {
                                    System.out.println("Livro dentro do prazo.");
                                }
                                loan.getLoanItems().remove(loanItems);
                                if (loan.getLoanItems().isEmpty()){
                                    library.getLoan().remove(loan);
                                }
                                System.out.println("Livro devolvido com sucesso!.");
                            } else {
                                System.out.println("Empréstimo não encontrado.");
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
                    System.out.println("Digite o CPF do usuário: ");
                    id = scanner.nextLine();
                    System.out.println("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    System.out.println("Digite o número de celular: ");
                    String phone = scanner.nextLine();
                    library.registerUser(name, id, email, phone);
                    System.out.println("Usuário cadastrado com sucesso!" + "\n");
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
                            System.out.println("O usuário não possui empréstimo ativos no momento.");
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
                default:
                    System.exit(0);
            }
        }
    }
}