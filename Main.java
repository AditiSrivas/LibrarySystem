package AP_Assignment1;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Library portal initialized...");
        System.out.println("---------------------------------");

        Librarian librarian = new Librarian();
        Member currentMember = null;
        Boolean choiceMenue = true;

        while (choiceMenue) {
            System.out.println("1. Enter as a librarian\n"
                    + "2. Enter as a member\n"
                    + "3. Exit\n");
            System.out.println("---------------------------------");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Boolean libMenue = true;
                    while (libMenue) {
                        System.out.println("1. Register a member\n"
                                + "2. Remove a member\n"
                                + "3. Add a book\n"
                                + "4. Remove a book\n"
                                + "5. View all members along with their books and fines to be paid\n"
                                + "6. View all books\n"
                                + "7. Back\n");
                        System.out.println("---------------------------------");
                        int libChoice = sc.nextInt();
                        sc.nextLine();

                        switch (libChoice) {
                            case 1:
                                System.out.println("Enter the name of the member to be added: ");
                                String addName = sc.nextLine();
                                System.out.println("Enter the age of the member to be added: ");
                                int addAge = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the phone number of the member to be added: ");
                                String addPhoneNo = sc.nextLine();
                                System.out.println("---------------------------------");

                                librarian.addMember(addName, addAge, addPhoneNo);
                                break;

                            case 2:
                                System.out.println("Enter the member ID of the member to be removed: ");
                                String removeID = sc.nextLine();

                                librarian.removeMember(removeID);
                                break;

                            case 3:
                                System.out.println("Enter the name of the book: ");
                                String addBookName = sc.nextLine();
                                System.out.println("Enter the author of the book: ");
                                String addAuthor = sc.nextLine();
                                System.out.println("Enter the book ID of the book: ");
                                int bookID = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the total number of copies of the book: ");
                                int bookCopies = sc.nextInt();
                                sc.nextLine();

                                librarian.addBook(bookID, addBookName, addAuthor, bookCopies);
                                break;

                            case 4:
                                System.out.println("Enter the book ID of the book to be removed: ");
                                int removeBookID = sc.nextInt();
                                sc.nextLine();

                                librarian.removeBook(removeBookID);
                                break;

                            case 5:
                                librarian.listMembers();
                                break;

                            case 6:
                                librarian.listBooks();
                                break;

                            case 7:
                                libMenue = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter your name: ");
                    String memberName = sc.nextLine();
                    System.out.println("Enter your phone number: ");
                    String memberPhoneNo = sc.nextLine();

                    boolean isMemberPresent = librarian.isMemberPresent(memberName, memberPhoneNo);

                    if (isMemberPresent) {
                        currentMember = librarian.getMemberByNameAndPhone(memberName, memberPhoneNo);
                        System.out.println("Logged in as Member: " + memberName);
                        Boolean memMenue = true;

                        while (memMenue) {
                            System.out.println("1. List Available Books\n"
                                    + "2. List My Books\n"
                                    + "3. Issue book\n"
                                    + "4. Return book\n"
                                    + "5. Pay Fine\n"
                                    + "6. Back\n");
                            int memChoice = sc.nextInt();
                            sc.nextLine();
                            System.out.println("---------------------------------");

                            switch (memChoice) {
                                case 1:
                                    currentMember.listAvailableBooks();
                                    break;

                                case 2:
                                    currentMember.listBorrowedBooks();
                                    break;

                                case 3:
                                    System.out.println("Enter the name of the book to issue: ");
                                    String bookNameIssue = sc.nextLine();
                                    System.out.println("Enter the author of the book to issue: ");
                                    String authorIssue = sc.nextLine();

                                    Book bookToIssue = null;
                                    for (Book availableBook : librarian.getBooks()) {
                                        if (availableBook.getBookName().equalsIgnoreCase(bookNameIssue) && availableBook.getAuthor().equalsIgnoreCase(authorIssue)) {
                                            bookToIssue = availableBook;
                                            break;
                                        }
                                    }

                                    if (bookToIssue != null) {
                                        boolean issued = currentMember.issueBook(bookToIssue, new Date());
                                        if (issued) {
                                            System.out.println("Book '" + bookToIssue.getBookName() + "' issued successfully.");
                                        } else {
                                            System.out.println("Failed to issue the book");
                                        }
                                    } else {
                                        System.out.println("Book not found in the library");
                                    }
                                    break;

                                case 4:
                                    System.out.println("Enter the name of the book to return: ");
                                    String bookNameReturn = sc.nextLine();
                                    System.out.println("Enter the author of the book to return: ");
                                    String authorReturn = sc.nextLine();

                                    Book bookToReturn = null;
                                    for (Book borrowedBook : currentMember.getBoorowedBooks()) {
                                        if (borrowedBook.getBookName().equalsIgnoreCase(bookNameReturn) && borrowedBook.getAuthor().equalsIgnoreCase(authorReturn)) {
                                            bookToReturn = borrowedBook;
                                            break;
                                        }
                                    }

                                    if (bookToReturn != null) {
                                        boolean returned = currentMember.returnBook(bookToReturn, new Date());
                                        if (returned) {
                                            System.out.println("Book '" + bookToReturn.getBookName() + "' returned successfully.");
                                        } else {
                                            System.out.println("Failed to return the book");
                                        }
                                    } else {
                                        System.out.println("Book not found in your borrowed books.");
                                    }
                                    break;

                                case 5:
                                    currentMember.payDues();
                                    break;

                                case 6:
                                    memMenue = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Member with Name: " + memberName + " and Phone No: " + memberPhoneNo + " doesn't exist.");
                    }

                case 3:
                    System.out.println("Thanks for visiting!");
                    System.out.println("---------------------------------");
                    sc.close();
                    System.exit(0);
                    choiceMenue= false;

                    break;
            }
        }
    }
}