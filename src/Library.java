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

    void registerBook(String name, String author, String isbn, int quantity){
        Book book = new Book(name, author, isbn, quantity);
        books.add(book);
    }

    public User findUserById(String id){
        for (User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books){
            if(book.getTitle().toLowerCase().equals(title.toLowerCase())){
                return book;
            }
        }
        return null;
    }
}


