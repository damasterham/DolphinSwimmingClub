import java.util.*;
import java.io.*;

//Chance
public class DolphinSwimmingClub
{            
   private static int getInputInt(Scanner input, String mismatchError)
   {
      int value;
            
      while (true)
      {
         try
         {
            value = input.nextInt();
            return value;          
         }
         catch (InputMismatchException ex)
         {
            System.out.println(mismatchError);
            input.nextLine();
         }
      }      
   }
   
   
   private static int getInputIntWithinRange(Scanner input, int min, int max, String mismatchError, String rangeError)
   {
      int value;
            
      while (true)
      {
         try
         {
            value = input.nextInt();
            
            if (value >= min && value <= max)
            {
               return value;
            }
            else
            {
               System.out.println(rangeError);
            }
            
         }
         catch (InputMismatchException ex)
         {
            System.out.println(mismatchError);
            input.nextLine();
         }
      }      
   } 
   
   
   private static void selectTrainerFunc(Scanner console)
   {
      int selection;
      System.out.println("Hej Træner. Hvad vil du gerne gøre?");
      System.out.println("1:Registrer nyt svømme resultat, 2:Se liste af top 5 svømmere");
      
      selection = getInputIntWithinRange(console, 1, 2, "Du skal indtaste et tal for at vælge", "Dine valgmuligheder er kun mellem 1 og 2");     
      
      switch (selection)    
      {
         //case 1: SwimResultHandler.createNewRestult; break;
         case 2: SwimResultHandler.findBestSwimmers(); break;
      }
   }
   
   
   private static void selectTreasurerFunc(Scanner console)
   {
      int selection; 
      System.out.println("Hej Kasserer. Hvad vil du gerne gøre?");
      System.out.println("1:Se medlemmer i restance");
      
      selection = getInputIntWithinRange(console,1 ,1,  "Du skal indtaste et tal for at vælge", "Der er kun en functionalitet, vælg nu bare 1");
      
      if (selection == 1)
      {
         //KassererFunc.bla();   Jeres handler her
      }   
   }
   
   
   private static void selectChairmanFunc(Scanner console)
   {
      int selection; 
      System.out.println("Hej formand. Hvad vil du gerne gøre?");
      System.out.println("1:Registrer nyt medlem");
      
      selection = getInputIntWithinRange(console,1 ,1,  "Du skal indtaste et tal for at vælge", "Der er kun en functionalitet, vælg nu bare 1");
      
      if (selection == 1)
      {
         //FormandFunc.bla();       Jeres handler her
      }
   }
   
   
   private static void selectUser(Scanner console)
   {
      int selection;
      System.out.println("Hvem bruger systemet?");
      System.out.println("1:Formand, 2:Kasserer, 3:Træner");      
      selection = getInputIntWithinRange(console, 1, 3, "Du skal indtaste et tal for at vælge", "Dine valgmuligheder er kun mellem 1 og 3");     
      
      switch (selection)    
      {
         case 1: selectChairmanFunc(console); break;
         case 2: selectTreasurerFunc(console); break;
         case 3: selectTrainerFunc(console); break;
      }   
      
   }
   
   
   public static void main(String[] args)
   {
         Scanner console;
         
         console = new Scanner(System.in);
         
         selectUser(console);
   }
   
}