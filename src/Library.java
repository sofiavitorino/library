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

    void registerBook(String title, String author, String isbn, int quantity){
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

    public LoanItem findLoanByIdAndTitle(String id, String Title) {
        for (Loan loan : loans) {
            for (LoanItem loanItem : loan.getLoanItems()){

            }
        }
    }
}


