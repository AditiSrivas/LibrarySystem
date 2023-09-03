package AP_Assignment1;
import java.util.*;

public class Librarian {
    private List<Book> books;
    private List<Member> members;

    public Librarian() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public List<Book> getBooks(){
        return books;
    }

    public void addMember(String memberName, int age, String phoneNum) {
        Member newMember = new Member(memberName, age, phoneNum);
        members.add(newMember);
        System.out.println("Member Successfully Registered with " + newMember.getMemberID() +"!\n");
        System.out.println("---------------------------------");
    }

    public void removeMember(String memberID) {
        Member memberToRemove = null;
        for (Member member : members) {
            if (member.getMemberID() == memberID) {
                memberToRemove = member;
                break;
            }else{
                System.out.println("This member does not exist");
            }
        }
        if (memberToRemove != null) {
            members.remove(memberToRemove);
        }
    }

    public void addBook(int bookID, String bookName, String author, int totalCopies) {
        books.add(new Book(bookID, bookName, author, totalCopies, totalCopies));
    }

    public void removeBook(int bookID) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                bookToRemove = book;
                break;
            }
            else {
                System.out.println("No such book exits");
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
        }
    }

    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the library system");
        }
        else {
            System.out.println("Members are: ");
            for (Member member : members) {
                System.out.println("Name of member: " + member.getMemberName() +", Member ID: " + member.getMemberAge());
                System.out.println("Borrowed books:");
                member.listBorrowedBooks();
                System.out.println("Fine to be paid" + member.getPenaltyAmount() +"\n");
            }
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("List of available books in the library:");
            for (Book book : books) {
                if (book.getAvailableCopies() > 0) {
                    System.out.println("Book ID: " + book.getBookID());
                    System.out.println("Title: " + book.getBookName());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Total Copies: " + book.getTotalCopies());
                    System.out.println("Available Copies: " + book.getAvailableCopies());
                    System.out.println("\n");
                }
            }
        }
    }

    public boolean isMemberPresent(String memberName, String phoneNum) {
        for (Member member : members) {
            if (member.getMemberName().equals(memberName) && member.getPhoneNum().equals(phoneNum)) {
                return true;
            }
        }
        System.out.println("Member with name '" + memberName + "' and phone number '" + phoneNum + "' does not exist");
        return false;
    }

    public Member getMemberByNameAndPhone(String memberName, String phoneNum) {
        for (Member member : members) {
            if (member.getMemberName().equals(memberName) && member.getPhoneNum().equals(phoneNum)) {
                return member;
            }
        }
        return null;
    }
}