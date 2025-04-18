public class Book {
    private String title;
    private String author;
    private String isbn;
    private int quantity;

    public Book(String title, String author, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getTitle(){
        return title;
    }

    public int getQuantity(){
        return quantity;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
}
