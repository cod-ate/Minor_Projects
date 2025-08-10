import java.util.*;
class LibraryBookTracker
{
    static Scanner sc= new Scanner (System.in);
    static Library lib= new Library();
    public static void main(String[] args)
    {
        int count;
        while(true)
        {
            count= takeCount();
            if(count==6)
                break;
            choice(count);
            System.out.println();
        }
        System.out.println("Process Finished!!");
    }
    public static void choice(int count)
    {
        int bookId; String status= "";
        switch(count)
        {
            case 1:
                System.out.println("Enter details for book:-");
                bookId = takeBookId();
                sc.nextLine();
                System.out.print("Enter title: ");
                String title = sc.nextLine().toUpperCase();
                System.out.print("Enter author: ");
                String author = sc.nextLine().toUpperCase();
                while(!(status.equals("available") || status.equals("issued")))
                {
                    System.out.print("Enter status(available/issued): ");
                    status = sc.next().toLowerCase();
                }
                lib.addBook(new Book(bookId, title, author, status), bookId);
                break;
            case 2:
                bookId = takeBookId();
                lib.deleteBook(bookId);
                break;
            case 3:
                bookId = takeBookId();
                lib.search(bookId);
                break;
            case 4:
                bookId = takeBookId();
                status= "";
                while(!(status.equals("available") || status.equals("issued") || status.equals("returned")))
                {
                    System.out.print("Enter status(available/issued/returned): ");
                    status = sc.next().toLowerCase();
                }
                lib.updateBookStatus(bookId, status);
                break;
            case 5:
                lib.display();
                break;
            default:
                System.out.println("Wrong input!!\nTry again..");
        }
    }
    public static int takeCount()
    {
        while(true)
        {
            try
            {
                System.out.println("Press:\n1) To add book,      2) To remove book\n3) To search book,   4) To update book status\n5) To display books, 6) To exit");
                System.out.print("Enter choice: ");
                return sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input!!\nTry again..\n");
                sc.nextLine();
            }
        }
    }
    public static int takeBookId()
    {
        while(true)
        {
            try
            {
                System.out.print("Enter book id: ");
                return sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input!!\nTry again..\n");
                sc.nextLine();
            }
        }
    }
}
class Book
{
    private int bookId;
    private String title, author, status;
    public Book(int bookId, String title, String author, String status)
    {
        this.bookId= bookId;
        this.title= title;
        this.author= author;
        this.status= status;
    }
    public int getBookId()
    {
        return bookId;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
    @Override
    public String toString()
    {
        return "Book id: "+getBookId()+", Title: "+getTitle()+", Author: "+getAuthor()+", Status: "+getStatus();
    }
}
class Library
{
    private Node head;
    public void addBook(Book b, int id)
    {
        Node node= new Node(b);
        if(head==null)
            head= node;
        else
        {
            Node temp= head;
            while(true)
            {
                if(temp.b.getBookId()==id)
                {
                    System.out.println("Book id Already Present!!");
                    return;
                }
                if(temp.next==null)
                    break;
                temp = temp.next;
            }
            temp.next= node;
        }
        System.out.println("Added Successfully!!");
    }

    public void deleteBook(int id)
    {
        if(head==null)
        {
            System.out.println("Library is Empty!!");
            return;
        }
        if(head.b.getBookId()==id)
        {
            head = head.next;
            System.out.println("Deleted Successfully!!");
            return;
        }
        else
        {
            Node temp= head;
            while(temp.next!=null)
            {
                if(temp.next.b.getBookId()==id)
                {
                    temp.next= temp.next.next;
                    System.out.println("Deleted Successfully!!");
                    return;
                }
                temp= temp.next;
            }
        }
        System.out.println("Book not found!!");
    }
    public void search(int id)
    {
        if(head==null)
        {
            System.out.println("Library is Empty!!");
            return;
        }
        Node temp= head;
        while(temp!=null)
        {
            if(temp.b.getBookId()==id)
            {
                System.out.println(temp.b);
                return;
            }
            temp= temp.next;
        }
        System.out.println("Book not found!!");
    }
    public void updateBookStatus(int id, String status)
    {
        if(head==null)
        {
            System.out.println("Library is Empty!!");
            return;
        }
        Node temp= head;
        while(temp!=null)
        {
            if(temp.b.getBookId()==id)
            {
                temp.b.setStatus(status);
                System.out.println("Updated Successfully!!");
                return;
            }
            temp= temp.next;
        }
        System.out.println("Book not found!!");
    }
    public void display()
    {
        if(head==null)
            System.out.println("Library is Empty!!");
        else
        {
            Node temp = head;
            while (temp != null)
            {
                System.out.println(temp.b);
                temp = temp.next;
            }
        }
    }
}
class Node
{
    Node next;
    Book b;
    public Node(Book b)
    {
        this.b= b;
    }
}
