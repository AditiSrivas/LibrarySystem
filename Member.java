package AP_Assignment1;

import java.util.*;

public class Member {
    Scanner sc = new Scanner(System.in);
    private String memberName;
    private int age;
    private String phoneNum;
    private double penaltyAmount;
    private String memberID;
    private List<Book> borrowedBooks;
    private List<Book> availableBooks;

    public Member(String memberName, int age, String phoneNum) {
        this.memberName = memberName;
        this.age = age;
        this.phoneNum = phoneNum;
        this.memberID = randomMemberID();
        this.penaltyAmount = 0;
        this.borrowedBooks = new ArrayList<>();
        this.availableBooks = new ArrayList<>();
    }

    private String randomMemberID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public String getMemberName() {
        return memberName;
    }
    public int getMemberAge() {
        return age;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getMemberID() {
        return memberID;
    }
    public double getPenaltyAmount() {
        return penaltyAmount;
    }
    public List<Book> getBoorowedBooks(){
        return borrowedBooks;
    }
    public List<Book> getAvailableBooks(){
        return availableBooks;
    }

    public void increasePenalty(double amount) {
        penaltyAmount += amount;
    }

    public void clearPenalty() {
        penaltyAmount = 0;
    }

    public boolean maxBookLimitReached() {
        return borrowedBooks.size() >= 2;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.decreaseAvailableCopies();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.increaseAvailableCopies();
    }

    public boolean issueBook(Book book, Date currentDate) {
        if (penaltyAmount > 0) {
            System.out.println("Please pay pending fine before borrowing new books");
            return false;
        }
        if (maxBookLimitReached() == true) {
            System.out.println("You already have 2 books borrowed");
            return false;
        }
        if (book.getAvailableCopies() != 0) {
            borrowedBooks.add(book);
            book.issue(currentDate);
            System.out.println("Book '" + book.getBookName() + "' issued successfully");
            return true;
        }
        else {
            System.out.println("Book '" + book.getBookName() + "' is unavialable");
            return false;
        }
    }

    private long calculateDaysLate(Date currentDate, Date dueDate) {
        long currentTimeMilliSec = currentDate.getTime();
        long dueTimeMilliSec = dueDate.getTime();
        return (currentTimeMilliSec - dueTimeMilliSec)/(1000);
    }

    public boolean returnBook(Book book, Date currentDate) {
        if (borrowedBooks.contains(book)) {
            long daysLate = calculateDaysLate(currentDate, book.getDueDate());
            if (daysLate > 0) {
                double amount = daysLate*3;
                increasePenalty(amount);
                System.out.println("Book '" + book.getBookName() +"' returned late. A fine of " + amount + " is applied");
            }
            borrowedBooks.remove(book);
            book.returned();
            return true;
        }
        else {
            System.out.println("Book '" + book.getBookName() + "' is not present in your borrowed books.");
            return false;
        }
    }

    public void listBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books have been borrowed by you");
        }
        else {
            System.out.println("Borrowed books: ");
            for (Book book : borrowedBooks) {
                System.out.println("Book ID: " + book.getBookID() +", Title: " + book.getBookName() + ", author: " + book.getAuthor());
            }
        }
    }

    public void listAvailableBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("There are no available books in the library.");
        } else {
            System.out.println("List of Available Books:");
            for (Book book : availableBooks) {
                System.out.println("Book ID: " + book.getBookID() + ", Title: " + book.getBookName() + ", Author: " + book.getAuthor());
            }
        }
    }

    public void payDues() {
        if (penaltyAmount > 0) {
            System.out.println("Pending fine: " + penaltyAmount);

            System.out.println("Enter the amount to pay: ");
            double paidAmount = sc.nextDouble();
            penaltyAmount -= paidAmount;
            System.out.println("Due amount '" + paidAmount + "' paid successfully");
        }
        else {
            System.out.println("You have no pending fines");
        }
    }

    public double calculateFine(Date currentDate, Book book) {
        long daysLate = calculateDaysLate(currentDate, book.getDueDate());
        if (daysLate > 0) {
            return daysLate * 3;
        }
        return 0;
    }
}