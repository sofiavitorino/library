public class Book {
    String title;
    String author;
    String isbn;
    int quantity;

    public Book(String title, String author, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getTitle(){
        return title;
    }
}
