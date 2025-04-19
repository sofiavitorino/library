import java.time.LocalDate;
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
            System.out.println("Não há livros cadastrados no catálogo.");
            System.out.println();
        } else {
            for (Book book : books){
                System.out.println("------------------------------------");
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autor: " + book.getAuthor());
                System.out.println("Quantidade: " + book.getQuantity());
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
        for (Loan loan : loans) {
            if(loan.getUser().getId().equals(id)){
                loans.add(loan);
            }
        }
        return null;
    }

}


