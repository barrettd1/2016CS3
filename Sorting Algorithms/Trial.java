import java.util.*;
public class Trial
{
    static int comparison = 0;
    public static void main(String[] args)
    {
        int totalComparisons = 0;
        //PART 1
        int [] data = new int[100];
        Random generator = new Random();
    
        //Generates array of 100 random integers
        for(int j = 0; j <100; j++)
          //  data[j] = j+1;
          data[j] = generator.nextInt(100); 
        System.out.println("Quick Sort:");
        //Prints array before sorting
        for(int j = 0; j <100; j++)
            System.out.print(data[j] + " "); 
        System.out.println();
        //Sort array using algorithm
        totalComparisons = mergesort(data, 0, data.length);
        //Prints array after sorting
        for(int j = 0; j <100; j++)
            System.out.print(data[j] + " ");
        
        System.out.println("\nTotal Comparisons: " + totalComparisons);
    }
    
    public static int selectionsort(int[ ] data, int first, int n)
    {
      int i, j; // Loop control variables
      int big;  // Index of largest value in data[first]...data[first+i]
      int temp; // Used during the swapping of two array values
      for (i = n-1; i > 0; i--)
      {  
         // Calculate big as the index of the largest value in data[first]...data[first+i]:
         big = first;
         for (j = first+1; j <= first+i; j++)
         {
            comparison++; 
            if (data[big] < data[j])
               big = j;
         }
      
         // swap data[first+i] with data[big]:
         temp = data[first+i];
         data[first+i] = data[big];
         data[big] = temp;
      }
      return comparison;
    } 
    
    public static int insertionsort(int[ ] data, int first, int n)
   {
      int entry;  // The element that is currently being inserted
      int j;
         
      for (int i = 1; i < n; i++)
      {
         entry = data[first+i];
         for (j = first+i; (j>first) && (data[j-1] > entry); j--) 
         {
                data[j] = data[j-1]; 
                comparison++;
         }
         comparison++;
         data[j] = entry;   
      }
      return comparison;
   } 
   
   public static int mergesort(int[ ] data, int first, int n)
   {
      int n1; // Size of the first half of the array
      int n2; // Size of the second half of the array

      if (n > 1)
      {
         // Compute sizes of the two halves
         n1 = n / 2;
         n2 = n - n1;

         mergesort(data, first, n1);      // Sort data[first] through data[first+n1-1]
         mergesort(data, first + n1, n2); // Sort data[first+n1] to the end

         // Merge the two sorted halves.
         comparison = comparison + merge(data, first, n1, n2);
      }
      return comparison;
   } 
  
   private static int merge(int[ ] data, int first, int n1, int n2)
   // Precondition: data has at least n1 + n2 components starting at data[first]. The first 
   // n1 elements (from data[first] to data[first + n1 – 1] are sorted from smallest 
   // to largest, and the last n2 (from data[first + n1] to data[first + n1 + n2 - 1]) are also
   // sorted from smallest to largest. 
   // Postcondition: Starting at data[first], n1 + n2 elements of data
   // have been rearranged to be sorted from smallest to largest.
   // Note: An OutOfMemoryError can be thrown if there is insufficient
   // memory for an array of n1+n2 ints.
   {
      int[ ] temp = new int[n1+n2]; // Allocate the temporary array
      int copied  = 0; // Number of elements copied from data to temp
      int copied1 = 0; // Number copied from the first half of data
      int copied2 = 0; // Number copied from the second half of data
      int i;           // Array index to copy from temp back into data
      int count = 0;
      
      // Merge elements, copying from two halves of data to the temporary array.
      while ((copied1 < n1) && (copied2 < n2))
      {
         if (data[first + copied1] < data[first + n1 + copied2]) {
            temp[copied++] = data[first + (copied1++)];
            count++; }
         else {
            temp[copied++] = data[first + n1 + (copied2++)]; 
            count++; }
      }

      // Copy any remaining entries in the left and right subarrays.
      while (copied1 < n1) {
         temp[copied++] = data[first + (copied1++)];
         count++; }
      while (copied2 < n2) {
         temp[copied++] = data[first + n1 + (copied2++)];
         count++; }

      // Copy from temp back to the data array.
      for (i = 0; i < n1+n2; i++)
         data[first + i] = temp[i];
      
      return count;
   }
   
    public static int quicksort(int[ ] data, int first, int n)
   {
      int pivotIndex; // Array index for the pivot element
      int n1;         // Number of elements before the pivot element
      int n2;         // Number of elements after the pivot element

      
      if (n > 1)
      {
         // Partition the array, and set the pivot index.
         pivotIndex = partition(data, first, n);

         // Compute the sizes of the two pieces.
         n1 = pivotIndex - first;
         n2 = n - n1 - 1;
        
         // Recursive calls will now sort the two pieces.
        comparison = comparison + quicksort(data, first, n1) + quicksort(data, pivotIndex + 1, n2);
      }
      
      return comparison;
   }
   
   private static int partition(int[ ] data, int first, int n)
   // Precondition: n > 1, and data has at least n elements starting at
   // data[first].
   // Postcondition: The method has selected some "pivot value" that occurs
   // in data[first]. . .data[first+n-1]. The elements of data have then been
   // rearranged and the method returns a pivot index so that
   //   -- data[pivot index] is equal to the pivot;
   //   -- each element before data[pivot index] is <= the pivot;
   //   -- each element after data[pivot index] is > the pivot.
   {
        int pivot = data[first]; // Choose the first element as the pivot
        int tooBigIndex = first + 1; // Index of element after pivot
        int tooSmallIndex = first + n -1; // Index of last element
    
        while (tooBigIndex <= tooSmallIndex) {
          // Search forward from left
          while (tooBigIndex <= tooSmallIndex && data[tooBigIndex] <= pivot) {
            tooBigIndex++;
            comparison++; }
    
          // Search backward from right
          while (data[tooSmallIndex] > pivot) {
            tooSmallIndex--;
            comparison++; }
    
          // Swap two elements in the list
          if (tooBigIndex < tooSmallIndex) {
            int temp = data[tooSmallIndex];
            data[tooSmallIndex] = data[tooBigIndex];
            data[tooBigIndex] = temp;
          }
        }
    
        data[first] = data[tooSmallIndex];
        data[tooSmallIndex] = pivot;
        return tooSmallIndex;       
   }

}

