import java.time.LocalDate;
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
            System.out.println("[6] Atualizar usuário");
            System.out.println("[0] Sair");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome do livro: ");
                    String name = scanner.nextLine();
                    System.out.println("Digite o nome do autor: ");
                    String author = scanner.nextLine();
                    System.out.println("Digite o ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Digite a quantidade de cópias disponíveis: ");
                    int quantity = scanner.nextInt();
                    library.registerBook(name, author, isbn, quantity);
                    break;
                case 2:
                    System.out.println("Digite o ID do usuário");
                    String id = scanner.nextLine();
                    User user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("Usuário não cadastrado no sistema.");
                        break;
                    } else {
                        System.out.println("Digite o nome do livro: ");
                        String title = scanner.nextLine();
                        Book book = library.findBookByTitle(title);
                        if (book == null) {
                            System.out.println("Livro não encontrado no sistema");
                            break;
                        } if (book.quantity <= 0) {
                            System.out.println("Não há cópias disponíveis no momento.");
                        }
                        LocalDate startDate = LocalDate.now();
                        LocalDate returnDate = startDate.plusDays(15);
                        Loan loan = new Loan(startDate, returnDate, user, )
                    }
            }
        }
    }
}