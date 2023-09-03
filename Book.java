package AP_Assignment1;
import java.util.*;

public class Book {
    private int bookID;
    private String bookName;
    private String author;
    private int totalCopies;
    private int copiesAvailable;
    private Date issueDate;
    private Date dueDate;

    public Book(int bookID, String bookName, String author, int totalCopies, int copiesAvailable) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.totalCopies = totalCopies;
        this.copiesAvailable = copiesAvailable;
        this.issueDate = null;
        this.dueDate = null;
    }
    public int getBookID() {
        return bookID;
    }
    public String getBookName() {
        return bookName;
    }
    public String getAuthor() {
        return author;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public int getAvailableCopies() {
        return copiesAvailable;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public void decreaseAvailableCopies() {
        copiesAvailable--;
    }

    public void increaseAvailableCopies() {
        copiesAvailable++;
    }

    public void issue(Date currentDate) {
        if (getAvailableCopies() > 0) {
            issueDate = currentDate;
            dueDate = new Date(currentDate.getTime() +10*24*60*60*1000);
            decreaseAvailableCopies();
        }
    }

    public void returned() {
        issueDate = null;
        dueDate = null;
        increaseAvailableCopies();
    }
}