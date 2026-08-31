package week_4.class_problems;

class LibraryBook {
    String title;
    String isbn;

    LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    LibraryBook(String title) {
        this(title, "PENDING");
    }

    void printStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: true");
    }
}

public class M1_LibraryBookCataloguing {
    public static void main(String[] args) {

        LibraryBook[] books = {
            new LibraryBook("Clean Code", "978-0132350884"),
            new LibraryBook("Untitled Draft"),
            new LibraryBook("1984", "9780451524935"),
            new LibraryBook("Notes")
        };

        for (LibraryBook book : books)
            book.printStatus();
    }
}