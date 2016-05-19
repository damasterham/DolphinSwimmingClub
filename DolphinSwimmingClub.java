import java.util.*;
import java.io.*;

public class DolphinSwimmingClub
{

   public static void main(String args)
   {
      Scanner console;
      
      console = new Scanner(System.in);
      
      
   }
   
   public static int selectUser(Scanner console)
   {
      System.out.println("Hvem bruger systemet?");
      System.out.println("1:Formand, 2:");
      return 0;
   }
   
   public static int inputInt(Scanner console)
   {
      int value;
   
      if (console.hasNext())
      {
         try
         {
            value = console.nextInt();
         }
         catch (Exception ex)
         {
            
         }
      }
   }
   
   private static int select(int value, int min, int max)
   {
      
      
      if (value < min || value > max)
      {
         
      }
   }
}