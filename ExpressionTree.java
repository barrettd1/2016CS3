//Danyelle Barrett
//Programmming Assignment 5: Expression Tree Construction
//Due: 10/26/16

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
public class ExpressionTree
{  
    public static void main(String[] args) throws IOException
    {
        BufferedReader inputStream = new BufferedReader(new FileReader("in.dat"));
        String line = inputStream.readLine();
        Scanner kb = new Scanner(System.in);
        int count = 1;
        BTNode<String> root;
        
        System.out.println("Hi, this is an Expression Tree constructor.");
        while(line != null)                 //loop to read every line in the file
        {
            System.out.println(count + ")");
            System.out.println("Postfix Expression: \n" + line);
            root = treeConstructor(line);    //constructs expression tree
            System.out.println("\nExpression Tree: \n");
            root.print(0);                  //prints out expression tree
            System.out.println("\nFully Parenthesized Infix Form:");
            infixForm(root);                //prints out fully parenthesized infix form
            System.out.println("\n\nValue of Expression: \n" + getValue(root));
            
            System.out.println("\nPress <Enter> to continue...");
            kb.nextLine();
            count++;
            line = inputStream.readLine();
        }
        
        inputStream.close();
        System.out.println("\nThere are no more expressions in file. Bye!");
    }
    
    //treeConstructor(String s) is a method to construct an expression tree. 
    // Input: a postfix expression string. 
    // Output: a reference to the root of the expression tree
    public static BTNode<String> treeConstructor(String s)
    {
        String[] tokens = s.split(" "); 
        Stack<BTNode<String>> nodes = new Stack<BTNode<String>>();
        BTNode<String> next;
        String value;
        
        for(int i = 0; i < tokens.length; i++)
        {
            if(tokens[i].matches("\\d+")) //operand
            {
                next = new BTNode<String> (tokens[i], null, null);
                nodes.push(next);
            }
            else if (tokens[i].equals("$")) //end of expression
            {   
                break;
            }
            else  //operator
            {
                value = tokens[i];
                if(isOperator(value))
                {
                    next = new BTNode<String> (value, null, null);
                    next.setLeft(nodes.pop());
                    next.setRight(nodes.pop());
                    nodes.push(next);
                }
                else
                {
                    next = new BTNode<String> (value, null, null);
                    next.setLeft(nodes.pop());
                    nodes.push(next);
                }
            }
        }
        //return root of expression tree
        return nodes.pop();
    }
            
    //isOperator(String s) is a method that checks to see if a string is an operator 
    // that would require two operands. Returns a true/false value.
    public static boolean isOperator(String s) //doesnt include !
    {
        return   s.equals("^") || s.equals("*") || s.equals("/") ||
                s.equals("%") || s.equals("+") || s.equals("-") || s.equals("<") ||
                s.equals("<=") || s.equals(">") || s.equals(">=") || s.equals("==") ||
                s.equals("!=") || s.equals("&&") || s.equals("||");
    }
    
    //infixForm(BTNode<String> root) is a method to print out the fully parenthesized 
    // infix form of the expression, given the expression tree.
    public static void infixForm(BTNode<String> root)
    {
        if(root != null)
        {  
            if(root.isLeaf())                       //base case
                System.out.print(root.getData());
            else    //not a leaf, add parenthesis....general case
            {
                System.out.print("(");
                infixForm(root.getLeft());
                System.out.print(root.getData());
                infixForm(root.getRight());
                System.out.print(")");
            }
        }
    }
    
    
    // getValue(BTNode<String> root) is a method that returns the value of an
    // expression given its expression tree.
    public static int getValue(BTNode<String> root)
    {   
        if(root == null)
            return 0;
        else if(root.isLeaf())
        
            return Integer.parseInt(root.getData());
        else if(isOperator(root.getData()))
        {
            int left = getValue(root.getLeft());
            int right = getValue(root.getRight());
            return calculate(right, root.getData(), left);
        }
        else
        {   
            int left = getValue(root.getLeft());
            return calculate(left);
        }
    }
    
    public static int calculate(int x, String z, int y)
    {
        int i = 0;
        boolean a, b;
        switch(z)
        {
            case "+": // Addition
                        i = x + y;
                        break;
            case "-": // Subtraction
                        i = x - y;
                        break;
            case "*": // Multiplication
                        i = x * y;
                        break;
            case "/": // Division
                        i = x / y;
                        break;
            case "%": //Remainder
                        i = x % y;
            case "^": //Exponentiation
                        i = (int)(Math.pow(x, y));
                        break;
            case "<":
                        if(x < y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case "<=":
                        if(x <=  y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case ">":
                        if(x > y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case ">=":
                        if(x >= y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case "==":
                        if(x == y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case "!=":
                        if(x != y)
                            i = 1;
                        else
                            i = 0;
                        break;
            case "&&":
                        if(x ==0)
                            a = false;
                        else
                            a = true;
                        if(y ==0)
                            b = false;
                        else
                            b = true;
                        if(a && b)
                            i = 1;
                        else
                            i = 0;
                        break;
            case "||":
                        if(x ==0)
                            a = false;
                        else
                            a = true;
                        if(y ==0)
                            b = false;
                        else
                            b = true;
                        if(a || b)
                            i = 1;
                        else
                            i = 0;
                        break;
            default : // Illegal character
                       throw new IllegalArgumentException("Illegal character");
        }
        return i;
    }
    
    public static int calculate(int x) 
    {
        //logical not
        if(x == 0) //false, return opposite of that.
            return 1;
        else  //integer is a nonzero value, and therefor true. return opposite of that
            return 0;
    }
}