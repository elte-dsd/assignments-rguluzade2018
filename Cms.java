package bucky;
import java.util.Scanner;
 
class CountMinSketch
{
    private int[] h1;
    private int[] h2;
    private int[] h3;
    private int size;
    private static int DEFAULT_SIZE = 11;
 
    
    public CountMinSketch()
    {
        size = DEFAULT_SIZE;
        h1 = new int[ size ];
        h2 = new int[ size ];
        h3 = new int[ size ];
    }
    
    
    public void insert(int value)
    {
        int hash1 = func1(value);
        int hash2 = func2(value);
        int hash3 = func3(value);
        h1[ hash1 ]++;
        h2[ hash2 ]++;
        h3[ hash3 ]++;
    }

    public int sketchCount(int value)
    {
        int hash1 = func1(value);
        int hash2 = func2(value);
        int hash3 = func3(value);
        return min( h1[ hash1 ], h2[ hash2 ], h3[ hash3 ] );
    }
    private int min(int a, int b, int c)
    {
        int min = a;
        if (b < min)
            min = b;
        if (c < min)
            min = c;
        return min;
    }
    /** Hash function 1 **/
    private int func1(int value)
    {
        return value % size;
    }
    /** Hash function 2 **/
    private int func2(int value)
    {
        return ((value * (value + 3)) % size);
    }
    /** Hash function 3 **/
    private int func3(int value)
    {
        return (size - 1) - value % size;
    }
    
    /** clear counters **/
    public void clear()
    {
        size = DEFAULT_SIZE;
        h1 = new int[ size ];
        h2 = new int[ size ];
        h3 = new int[ size ];
    }
    
    
    public void print()
    {
        System.out.println("\nCounter Tables : \n");
        System.out.print("h1 : ");
        for (int i = 0; i < size; i++)
            System.out.print(h1[i] +" ");
        System.out.print("\nh2 : ");
        for (int i = 0; i < size; i++)
            System.out.print(h2[i] +" ");
        System.out.print("\nh3 : ");
        for (int i = 0; i < size; i++)
            System.out.print(h3[i] +" ");
        System.out.println();
    }    
}
 
public class Cms
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Count Min Sketch Test\n\n");

        CountMinSketch cms = new CountMinSketch();
 
        char ch;
      
        do    
        {    
            System.out.println("\nCount Min Sketch Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. get sketch count");
            System.out.println("3. clear");
 
            int choice = scan.nextInt();   
            System.out.println("Enter int value");
            if(choice==1) {
            	cms.insert(scan.nextInt() );
            } else if(choice==2) {
            	int val = scan.nextInt();
            } else if(choice == 3) {
            	cms.clear();
            } else {
            	 System.out.println("Wrong Entry \n ");
            }
           
            cms.print();  
 
            System.out.println("\nPress c to continue, or s to stop \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'C'|| ch == 'c');
    }
}