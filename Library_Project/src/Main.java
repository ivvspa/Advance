import java.util.Arrays;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);

        Book[] books = {
                new Book("Circe", "Madeline Miller", 2018, null),
                new Book("Ariadne", "Jennifer Saint", 2021, null),
                new Book("Percy Jackson: The Lightning Thief", "Rick Riordan", 2005, null),
                new Book("The Alchemist", "Paulo Coelho", 1988, null),
                new Book("The Housemaid", "Freida McFadden", 2022, null)
        };

        do {
                System.out.println("---Library Management System---");
                System.out.println("1  -  Display All Books");
                System.out.println("2  -  Borrow Book");
                System.out.println("3  -  Return Book");
                System.out.println("4  -  Check Book Number");
                System.out.println("5  -  Exit");

                int option = scan.nextInt();

                switch (option) {

                    case 1:
                        displayLibrary(books);
                        break;

                    case 2:
                        borrowBook(books, scan);
                        break;

                    case 3:
                        System.out.println("Which book would you like to return? (by title): ");
                        scan.nextLine();
                        String searchTitle = scan.nextLine();
                        returnBook(books, searchTitle);
                        break;

                    case 4:
                        System.out.println("Which book would you like to check? (by title): ");
                        scan.nextLine();
                        String titleCheck = scan.nextLine();
                        System.out.println(findBookByTitle(books, titleCheck));
                        break;

                    case 5:
                        System.out.println("Goodbye");
                        System.exit(0);
                        break;

            }
        } while(true);

    }

    static void displayLibrary(Book[] books){
        for(Book book : books){
            book.getDetails();
            System.out.println("....................\n");
        }
    }

    static void borrowBook(Book[] books, Scanner scanner){
        System.out.print("Which book would you like to borrow? (by number): ");
        int choice = scanner.nextInt();
        for(int i = 0; i < books.length; i++){
            if (books[i].getBorrowerName() == null) {
                if (i + 1 == choice) {
                    System.out.println("What is the name of the borrower?");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    books[i].borrowBook(name);
                    System.out.println(name + " borrowed " + books[i].getTitle() + " successfully");
                }
            }
            else {
                System.out.println("Book already borrowed.");
            }
        }
    }

    static void returnBook(Book[] books, String searchTitle){
        char[] searchChar = searchTitle.toUpperCase().toCharArray();
        boolean found = false;
            for (Book book : books) {
                if (book.getBorrowerName() != null) {
                    for (Book booka : books) {
                        char[] bookChar = (booka.getTitle().toUpperCase()).toCharArray();
                        if (Arrays.equals(bookChar, searchChar)) {
                            booka.returnBook();
                            System.out.println("Successfully returned!");
                        }
                    }
                    break;
                } else {
                    found = true;
                }
            }
            if (found == true) {
                System.out.println("Book is in stock. Try borrowing it instead.");
            }


    }

    static int findBookByTitle(Book[] books, String title){
        char[] searchChar = title.toUpperCase().toCharArray();
        int index = 0;
        for(int i = 0; i<books.length; i++){
            char[] bookChar = (books[i].getTitle().toUpperCase()).toCharArray();
            if(Arrays.equals(bookChar, searchChar)){
                index = i;
            }
        }
        return index+1;
    }

}

class Book{

    private String title;
    private String author;
    private int yearPublished;
    private String borrowerName;

    public Book(String title, String author, int yearPublished, String borrowerName) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = borrowerName;
    }

    void getDetails(){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year Published: " + yearPublished);
        System.out.println("Borrower Name: " + borrowerName);
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished){
        this.title = newTitle;
        this.author = newAuthor;
        this.yearPublished = newYearPublished;
    }

    public void borrowBook(String borrowerName){
        this.borrowerName = borrowerName;
    }

    public String returnBook(){
        return borrowerName = null;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYearPublished() {
        return yearPublished;
    }
    public String getBorrowerName(){
        return borrowerName;
    }
}