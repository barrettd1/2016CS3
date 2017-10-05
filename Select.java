//Danyelle Barrett
public class Select
{  
   static int comparison = 0;
    /**
   * Sort an array of integers from smallest to largest, using a selection sort
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
   *   <CODE>data[first] &lt;= data[first+1] &lt; ... &lt;= data[first+n-1]</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that <CODE>first+n-1</CODE> is an index beyond the end of the
   *   array.
   * */
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
}

