//Danyelle Barrett
public class PriorityQueue
{
   private int data[];
   private int maxItems;
   private int numberOfItems;
                 
   public PriorityQueue(int size)
   {
      maxItems = size;
      data = new int[maxItems];
      numberOfItems = 0;
   }
   
   public boolean isEmpty()
   {
      return numberOfItems == 0;
   }
   
   public void add(int item)
   {
      data[numberOfItems] = item; //places the new element in the heap in the first available spot
      trickleUp(numberOfItems);
      numberOfItems++;             // increase the number of items in the heap
   }
   
   private void trickleUp(int newPosition)
   {
      int temp = data[newPosition];
      int parent = (newPosition-1)/2;
      while (newPosition > 0 && temp > data[parent])
      {
         data[newPosition] = data[parent];
         newPosition = parent;
         parent = (newPosition-1)/2;
      }
      data[newPosition] = temp;
   }

   public int remove()
   {
      int root = data[0];   //save the root(largest element) so that it can be returned
      numberOfItems--;
      if (numberOfItems > 0)
      {
         data[0] = data[numberOfItems];
         trickleDown(0);
      }
      return root;  //return the element removed
   }

   private void trickleDown(int itemPosition)
   {
      int child;                        // Index of the larger child.
      if (2*itemPosition+1 >= numberOfItems)
      {
         child = itemPosition;          // Leaf node (no children).
      }
      else if (2*itemPosition+2 == numberOfItems)
      {
         child = 2*itemPosition+1;      // One child (at the left).
      }
      else if (data[2*itemPosition+1] > data[2*itemPosition+2])
      {
         child = 2*itemPosition+1;      // Two children w/ larger on the left.
      }
      else
      {
         child = 2*itemPosition+2;      // Two children w/ larger on the right.
      }
      
      if (data[itemPosition] < data[child])
      {
         int temp = data[itemPosition];
         data[itemPosition] = data[child];
         data[child] = temp;
         trickleDown(child);
      }
   }
   
   public void printHeapAsTree() {
      printHeapTreeHelper(0, ""); 
      System.out.println(); 
   }
   
   private void printHeapTreeHelper(int i, String indent) {
      if(i < numberOfItems) 
      { 
         printHeapTreeHelper(rchild(i), indent + "   "); 
         System.out.println(indent + data[i]);
         printHeapTreeHelper(lchild(i), indent + "   "); 
      }
   }
   
   private int parent(int i) 
   { 
       return (i-1) / 2; 
   }
   private int lchild(int i) 
   { 
       return 2 * i + 1; 
   }
   private int rchild(int i) 
   {
       return 2 * i + 2; 
   }
}
