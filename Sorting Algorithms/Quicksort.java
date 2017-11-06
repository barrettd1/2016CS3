//Danyelle Barrett
public class Quicksort
{
   static int comparison = 0;   
   /**
   * This method cannot be used until the student implements 
   * <CODE>partition</CODE>.
   * Sort an array of integers from smallest to largest, using a quicksort
   * algorithm.
   * @param data
   *   the array to be sorted
   * @param first
   *   the start index for the portion of the array that will be sorted
   * @param n
   *   the number of elements to sort
   * <b>Precondition:</b>
   *   <CODE>data[first]</CODE> through <CODE>data[first+n-1]</CODE> are valid
   *   parts of the array.
   * <b>Postcondition:</b>
   *   If <CODE>n</CODE> is zero or negative then no work is done. Otherwise, 
   *   the elements of <CODE>data</CODE> have been rearranged so that 
   *   <CODE>data[first] &lt;= data[first+1] &lt;= ... &lt;= data[first+n-1]</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that <CODE>first+n-1</CODE> is an index beyond the end of the
   *   array.
   * */
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
         quicksort(data, first, n1);
         quicksort(data, pivotIndex + 1, n2);
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
          while (tooBigIndex <= tooSmallIndex && data[tooBigIndex] <= pivot) 
            tooBigIndex++;
   
          // Search backward from right
          while (data[tooSmallIndex] > pivot) 
            tooSmallIndex--;

    
          // Swap two elements in the list
          if (tooBigIndex < tooSmallIndex) {
            int temp = data[tooSmallIndex];
            data[tooSmallIndex] = data[tooBigIndex];
            data[tooBigIndex] = temp;
          }
          comparison++;
        }
    
        data[first] = data[tooSmallIndex];
        data[tooSmallIndex] = pivot;
        return tooSmallIndex; 
   }
}
