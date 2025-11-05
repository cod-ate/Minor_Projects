import java.util.*;
public class SearchEngine
{
    public static void main(String[] args)
    {
        BrowsingHistory bh= new BrowsingHistory();
        int MaxTab= 5;
        Sites_LL[] sitesList= new Sites_LL[MaxTab];
        for(int i=0; i<sitesList.length; i++)
            sitesList[i]= new Sites_LL();
//        Sites_LL sitesList= new Sites_LL();

        Scanner sc= new Scanner(System.in);
        String currentSite;
        int idx= -1, target= 0, counter= 0;

        while(true)
        {
            if(counter>4) break;
            System.out.println("Press '0' to add new site  '-1' to move backward\n      '1' to move forward  '2' to clear history\n      '3' to search site   '4' to open new tab\n      'X' to exit: ");
            String choice= sc.next().toUpperCase();
            if(choice.equals("X")) break;
            switch(choice)
            {
                case "0":
                    System.out.print("Type URL: ");
                    String newSite= sc.next();
                    idx+=1;
                    bh.setSite(newSite);
                    currentSite= bh.getSite();
                    System.out.println("Current Site: "+currentSite.toUpperCase());
                    sitesList[counter].pushSite(bh.getSite(), idx, target);
//                    System.out.println("Idx case 0: "+idx);
                    target= 0;
                    sitesList[counter].display();
                    break;
                case "1":
                    idx+=1;
                    String fwd= nextSite(sitesList[counter], idx);
                    if(fwd.equals("None"))
                    {
                        idx-= 1;
                        System.out.println("Forward Site: "+fwd);
                    }
                    else
                    {
                        currentSite= fwd;
                        System.out.println("Current Site: "+currentSite.toUpperCase());
                    }
                    target= 1;
//                    System.out.println("Idx case 1 : "+idx);
                    break;
                case "-1":
                    idx-=1;
                    String bwd= prevSite(sitesList[counter], idx);
                    if(bwd.equals("None"))
                    {
                        idx+=1;
                        System.out.println("Backward Site: "+bwd);
                    }
                    else
                    {
                        currentSite= bwd;
                        System.out.println("Current Site: "+currentSite.toUpperCase());
                    }
                    target= 1;
//                    System.out.println("Idx case -1 : "+idx);
                    break;
                case "2":
                    System.out.println("History Deleted");
                    idx= -1;
                    target= 0;
                    sitesList[counter].clearHistory();
                    break;
                case "3":
                    System.out.print("Type URL: ");
                    System.out.println(sitesList[counter].searchSite(sc.next()));
                    break;
                case "4":
                    counter++;
                    if(counter>4) break;
                    System.out.println("Opened new tab:");
                    target= 0;
                    idx= -1;
                    break;
                default:
                    System.out.println("Invalid input..");
            }
            System.out.println();
        }
    }
    private static String nextSite(Sites_LL sitesList, int idx)
    {
        return sitesList.forward(idx);
    }
    private static String prevSite(Sites_LL sitesList, int idx)
    {
        return sitesList.backward(idx);
    }
}

class BrowsingHistory
{
    private String site;
    public void setSite(String site)
    {
        this.site= site;
    }
    public String getSite()
    {
        return site;
    }
}

class Sites_LL
{
    private Node head, tail;
    public void pushSite(String str, int idx, int target)
    {
        Node node= new Node(str);
        if(target==1)
        {
            head= tail;
            for(int i=0; i<idx-1; i++)
                head= head.prev;
            head.prev= null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        else if(head==null)
            head= tail= node;
        else
        {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public int getSize()
    {
        Node temp= head;
        int size= 0;
        while(temp!=null)
        {
            size++;
            temp= temp.next;
        }
        return size;
    }

    public String forward(int idx)
    {
        if(idx<=0 || idx>=getSize())
            return "None";
        Node temp= tail;
        for(int i=1; i<=idx; i++)
        {
            temp= temp.prev;
            if(i==idx)
                return temp.str;
        }
        return "None";
    }

    public String backward(int idx)
    {
        if(idx<0 || idx>=getSize())
            return "None";
        Node temp= head;
        int x= getSize()-idx;
        for(int i=1; i<x; i++)
        {
            temp= temp.next;
            if(i==x-1)
                return temp.str;
        }
        return "None";
    }

    public void display()
    {
        Node temp= head;
        System.out.print("null <--> ");
        while(temp!=null)
        {
            System.out.print(temp.str+" <--> ");
            temp= temp.next;
        }
        System.out.println("null");
    }

    public void clearHistory()
    {
        head= tail= null;
    }

    public String searchSite(String site)
    {
        Node temp= head;
        while(temp!=null)
        {
            if(temp.str.equalsIgnoreCase(site))
                return temp.str.toUpperCase();
            temp= temp.next;
        }
        return "Site not Found";
    }
}

class Node
{
    public String str;
    public Node next= null, prev= null;
    public Node(String str)
    {
        this.str= str;
    }
}