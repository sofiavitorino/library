import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void registerBook(String title, String author, String isbn, int quantity){
        Book book = new Book(title, author, isbn, quantity);
        books.add(book);
    }

    public User findUserById(String id) {
        for (User user : users){
            if(user.getId().equalsIgnoreCase(id)){
                return user;
            }
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public LoanItem findLoanItemByIdAndTitle(String id, String title) {
        for (Loan loan : loans) {
            if(loan.getUser().getId().equals(id)){
                for (LoanItem loanItem : loan.getLoanItems()) {
                    if (loanItem.getBook().getTitle().equalsIgnoreCase(title)) {
                        return loanItem;
                    }
                }
            }
        }
        return null;
    }

    public List<Loan> getLoan(){
        return loans;
    }

    public Loan findLoanByIdAndTitle(String id, String title){
        for (Loan loan : loans) {
            if(loan.getUser().getId().equals(id)){
                for (LoanItem loanItem : loan.getLoanItems()) {
                    if (loanItem.getBook().getTitle().equalsIgnoreCase(title)) {
                        return loan;
                    }
                }
            }
        }
        return null;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void printBookCatalog(){
        List<Book> books = getBooks();
        if(books.isEmpty()) {
            System.out.println("There are no books registered in the catalog.");
            System.out.println();
        } else {
            for (Book book : books){
                System.out.println("------------------------------------");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println("------------------------------------");
                System.out.println();
            }
        }
    }

    public void registerUser(String name, String id, String email, String phone){
        User user = new User(name, id, email, phone);
        users.add(user);
    }

    public List<Loan> findLoansById(String id){
        List<Loan> foundLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if(loan.getUser().getId().equals(id)){
                foundLoans.add(loan);
            }
        }
        return foundLoans;
    }

    public List<User> getUsers(){
        return users;
    }

    public void printRegisteredUsers() {
        List <User> users = getUsers();
        if (users.isEmpty()) {
            System.out.println("There are no users registered in the system.");
            System.out.println();
        } else {
            for (User user : users) {
                System.out.println("------------------------------------");
                System.out.println("Name: " + user.getName());
                System.out.println("ID: " + user.getId());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("------------------------------------");
                System.out.println();
            }
        }
    }

    public boolean hasBooksToReturn(String userId) {
        for (Loan loan : loans) {
            if (loan.getUser().getId().equals(userId) && !loan.getLoanItems().isEmpty()) {
                return true;
            }
        }
        return false;
    }

}