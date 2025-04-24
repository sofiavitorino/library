public class LoanItem {
    private Book book;

    public LoanItem(Book book){
        this.book = book;
    }

    public Book getBook(){
        return book;
    }

    @Override
    public String toString() {
        return  "Título: " + book.getTitle() + "\nAuthor: " + book.getAuthor();
    }

}



