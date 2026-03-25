import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            System.out.println("------------ Library System ------------");
            System.out.println("Choose an option: ");
            System.out.println("[1] Register book");
            System.out.println("[2] Borrow book");
            System.out.println("[3] Return book");
            System.out.println("[4] Check book catalog");
            System.out.println("[5] Register user");
            System.out.println("[6] Check active loans");
            System.out.println("[7] List registered users");
            System.out.println("[0] Exit");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter book title: ");
                    String title = scanner.nextLine();
                    if (title.trim().isEmpty()) {
                        System.out.println("Title cannot be empty. Please try again.");
                        continue;
                    }
                    System.out.println("Enter book author: ");
                    String author = scanner.nextLine();
                    if (author.trim().isEmpty()) {
                        System.out.println("Author cannot be empty. Please try again.");
                        continue;
                    }
                    System.out.println("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    if (isbn.trim().isEmpty()) {
                        System.out.println("ISBN cannot be empty. Please try again.");
                        continue;
                    }
                    System.out.println("Enter the number of available copies: ");
                    int quantity;
                    while (true) {
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity <= 0) {
                                System.out.println("Quantity must be greater than 0. Please try again.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                        }
                    }
                    library.registerBook(title, author, isbn, quantity);
                    System.out.println("Book registered successfully!" + "\n");
                    break;
                case 2:
                    System.out.println("Enter user ID: ");
                    String id = scanner.nextLine();
                    User user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("User not found in the system." + "\n");
                        break;
                    } else {
                        List<LoanItem> loanItems = new ArrayList<>();
                        int maxBooks = 5;
                        int count = 0;

                        System.out.println("Books available for loan:");
                        library.printBookCatalog();

                        while (count < maxBooks) {
                            System.out.println("Enter book title or '0' to exit: ");
                            title = scanner.nextLine();

                            if (title.equalsIgnoreCase("0")){
                                System.out.println("No books borrowed.");
                                break;
                            } else {
                                Book book = library.findBookByTitle(title);

                                if (book == null) {
                                    System.out.println("Book not found. Please try again.");
                                    continue;
                                }
                                if (book.getQuantity() <= 0) {
                                    System.out.println("No copies available at the moment.");
                                    continue;
                                }
                                LoanItem item = new LoanItem(book);
                                loanItems.add(item);
                                book.decreaseQuantity();
                                count++;
                                if(count >= maxBooks){
                                    System.out.println("You have reached the 5-book limit.");
                                    break;
                                } else {
                                    System.out.println("Would you like to borrow another book? (y/n)");
                                    String response = scanner.nextLine();
                                    if(!response.equalsIgnoreCase("y")){
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

                            System.out.println("Borrowed books: ");
                            for (LoanItem item : loanItems) {
                                System.out.println(item);
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Enter user ID: ");
                    id = scanner.nextLine();
                    user = library.findUserById(id);
                    if (user == null) {
                        System.out.println("User not found in the system." + "\n");
                        break;
                    }

                    boolean hasBooksToReturn = library.hasBooksToReturn(id);
                    if (!hasBooksToReturn) {
                        System.out.println("No books to return.");
                        break;
                    }

                    while (library.hasBooksToReturn(id)){
                        System.out.println("Enter book title or '0' to exit: ");
                        title = scanner.nextLine();
                        if (title.equalsIgnoreCase("0")){
                            break;
                        } else {
                            Book book = library.findBookByTitle(title);

                            if (book == null) {
                                System.out.println("Book not found. Please try again.");
                            } else {
                                LoanItem loanItem = library.findLoanItemByIdAndTitle(id, title);
                                if (loanItem != null) {
                                    loanItem.getBook().increaseQuantity();
                                    Loan loan = library.findLoanByIdAndTitle(id, title);
                                    if (loan != null) {
                                        double fine = loan.calculateFine();
                                        if (fine > 0) {
                                            System.out.printf("Book returned late. Fine: R$ %.2f%n", fine);
                                        } else {
                                            System.out.println("Book returned on time.");
                                        }
                                        loan.getLoanItems().remove(loanItem);
                                        if (loan.getLoanItems().isEmpty()) {
                                            library.getLoan().remove(loan);
                                        }
                                        System.out.println("Book returned successfully!");
                                    } else {
                                        System.out.println("Loan not found.");
                                    }
                                    System.out.println("Would you like to return another book? (y/n)");
                                    String response = scanner.nextLine();
                                    if (!response.equalsIgnoreCase("y")) {
                                        break;
                                    }
                                    if(!library.hasBooksToReturn(id)){
                                        System.out.println("No more books to return.");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Book catalog:");
                    library.printBookCatalog();
                    break;
                case 5:
                    System.out.println("Enter user name: ");
                    String name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty. Please try again.");
                        continue;
                    }
                    System.out.println("Enter user ID: ");
                    id = scanner.nextLine();
                    if (id.trim().isEmpty()) {
                        System.out.println("ID cannot be empty. Please try again.");
                        continue;
                    }
                    if (library.findUserById(id) != null){
                        System.out.println("ID already registered in the system.");
                        break;
                    } else {
                        System.out.println("Enter user email: ");
                        String email = scanner.nextLine();
                        if (email.trim().isEmpty()) {
                            System.out.println("Email cannot be empty. Please try again.");
                            continue;
                        }
                        System.out.println("Enter user phone: ");
                        String phone = scanner.nextLine();
                        if (phone.trim().isEmpty()) {
                            System.out.println("Phone cannot be empty. Please try again.");
                            continue;
                        }
                        library.registerUser(name, id, email, phone);
                        System.out.println("User registered successfully!");
                        System.out.println();
                    }
                    break;
                case 6:
                    System.out.println("Enter user ID: ");
                    id = scanner.nextLine();
                    user = library.findUserById(id);
                    if(user == null) {
                        System.out.println("User not found.");
                    } else {
                        List<Loan> loans = library.findLoansById(id);
                        if (loans.isEmpty()){
                            System.out.println("This user has no active loans.");
                        } else {
                            System.out.println("Active loans for user " + user.getName());
                            for (Loan loan : loans) {
                                System.out.println("------------------------------------");
                                for (LoanItem loanItem : loan.getLoanItems()) {
                                    System.out.println("- Title: " + loanItem.getBook().getTitle());
                                    System.out.println("  Author: " + loanItem.getBook().getAuthor());
                                }
                                System.out.println("Loan date: " + loan.getStartDate());
                                System.out.println("Return date: " + loan.getReturnDate());
                                System.out.println("------------------------------------");
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("Registered users: ");
                    library.printRegisteredUsers();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}