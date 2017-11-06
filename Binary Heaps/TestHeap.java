//Danyelle Barrett
//Assignment #6: Binary Heaps
//Due: 11/9/16
import java.util.Random;
import java.util.Scanner;
public class TestHeap
{
   public static void main(String[] args)
   {
       PriorityQueue heap = new PriorityQueue(20);
       int i = 1;
       int x;
       Random generator = new Random();
       Scanner kb = new Scanner(System.in);
       
       System.out.println("This is an implementation of a Binary Heap!");
       /* Stage 1: 
        * generates a sequence of 20 random integers (<1000), addds them into a heap,
        * and displays the heap after each addition. */
       while(i <=20)
       {
           x = generator.nextInt(1000);
           heap.add(x);
           System.out.println("\nElement added: " + x + "\nHeap: \n");
           heap.printHeapAsTree();
           
           System.out.println("\nPress <Enter> to continue...");
           kb.nextLine();
           i++;
       }
       
       /* Stage 2:
        * Removes the elements from the heap 1 by 1, and displays the heap after 
        * each removal. */
       while(i>=1)
       {
           x = heap.remove();
           System.out.println("\nElement removed: " + x + "\nHeap: \n");
           heap.printHeapAsTree();
           i--;
           
           System.out.println("Press <Enter> to continue...");
           kb.nextLine();
       } 
       System.out.println("Heap is now empty. Goodbye!");
   }
}
