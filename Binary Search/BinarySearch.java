//Danyelle Barrett
//Programming Assignment 4: Binary Search
//Due: 10/12

import java.util.Scanner;

public class BinarySearch
{
    static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        System.out.println("We will play a game where I try to guess your number.");
        System.out.println("Think of an integer between 1 and 1,000,000: ");
        int value = kb.nextInt();
       
        //Makes sure the user enters a correct value
        while(value <= 1 || value >= 1000000) {
            System.out.println("Error! Integer must be between 1 and 1,000,000");
            System.out.println("Think of another integer please: ");
            value = kb.nextInt();
        }
        
        System.out.println("I will now guess your number through a series of questions- ");
        System.out.println("Please answer with yes or no");
        guess(1,1000000);
    }
    
    public static void guess(int low, int high)
    {
        //Base case
        if(low == high) {
            System.out.println("I've guessed your number!");
            System.out.println("Your number is: " + low);
        }
        
        int midpoint;
        String answer;
        //General case
        if(low < high) {
            midpoint = (low + high) / 2;
            System.out.println("Is your number " + midpoint + "?");
            answer = kb.next();
            //if the number is the midpoint
            if (answer.equals("yes")) {
                System.out.println("I've guessed your number!");
                System.out.println("Your number is : " + midpoint);
            }
            //if not, 
            else {
                System.out.println("Is your number greater than " + midpoint + "?");
                answer = kb.next();
                if(answer.equals("yes")) {
                    guess(midpoint + 1, high);     //recursive call
                }
                else {
                    guess(low, midpoint - 1);       //recursive call
                }
            }
        }
    }
}