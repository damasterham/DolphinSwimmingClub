import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.PrintStream;
import java.lang.Exception;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

// Should probably just be made into a static class
public class SwimResultHandler 
{
   private static final String FILENAME = "swimresults.dat";
   private static final String SAVENAME = "best_swimmers_";
   private static final String DELIMITER = ";";
   private static final int DISCIPLINES = SwimResult.getDisciplineAmount();
   private static final int AMOUNT = 5;
   
/*
	private SwimResult swimResult;
	private List<SwimResult> swimResults;
   
*/   
   // Chance
   private static boolean saveToFile(String filename, String formattedText)
   {
      File file;
      PrintStream output;
      boolean success;            
            
      success = false;
      
      try
      {
         file = new File(filename);
         
         if (file.exists() == false)
         {
            file.createNewFile();                                                   
         }
         
         output = new PrintStream(file);
         output.print(formattedText);  
         success = true; 
         
      }
      catch (FileNotFoundException ex)
      {
         System.out.println(ex.getMessage());  
      }
      finally
      {
         return success;
      }
   }

   
   
   // Chance
   private static boolean saveToFile(String filename, String extension, String formattedText)
   {
      String path;
      File file;
      PrintStream output;
      boolean success;            
            
      path = String.format("%s.%s", filename, extension);            
      success = false;
      
      try
      {
         file = new File(path);
         
         if (file.exists() == false)
         {
            file.createNewFile();                                                   
         }
         
         output = new PrintStream(file);
         output.print(formattedText);  
         success = true; 
         
      }
      catch (FileNotFoundException ex)
      {
         System.out.println(ex.getMessage());  
      }
      finally
      {
         return success;
      }
   }
   
    
   // Chance
   private static void saveBestSwimmers(String formattedString)
   {     
      Date date;
      SimpleDateFormat format;
      String dateString;
      
      date = new Date();      
      format = new SimpleDateFormat("dd-MM-yyyy");      
      dateString = format.format(date);
      
      if (saveToFile(SAVENAME + dateString, "txt", formattedString))
      {
         System.out.println("Resultater også gemt som fil: " + SAVENAME + dateString + ".txt");
      }  
   }   


   // Chance
   private static void saveBestSwimmers(List<SwimResult> list)
   {
      String format;
      
      format = new String();
      
      for (int i = 0; i < list.size(); i++)
      {
         format += list.get(i).toFormatString() + "\n";
      }
      
      saveBestSwimmers(format);      
   }
   
   
   //Chance
   private static String formatBestSwimmersByDisciplines(List<SwimResult> list)
   {
      String value;
      
      value = new String();
      
      if (list.isEmpty() == false)
      {
      
         value = list.get(0).getDiscipline() + ":\n";
         
         for (int i = 0; i < list.size(); i++)
         {
            SwimResult result;
            result = list.get(i);
            
            value += String.format("Medlem: %s, Tid i sekunder: %s, Dato sat: %s%n", result.getName(), result.getResult(), result.getDate());
         }
      }      
      
      return value;
   }
   
   
   //Chance
   private static int findWorstIndex(List<SwimResult> results)
   {
      SwimResult worst;
      int worstIndex;
      
      worst = results.get(0);
      worstIndex = 0;
      
      for (int i = 1; i < results.size(); i++)
      {
         SwimResult result;
         
         result = results.get(i);
      
         if (result.getResult() > worst.getResult())
         {
            worst = result;
            worstIndex = i;
         }
      }
      
      return worstIndex;
   }
   
   
   //Chance
   private static List<SwimResult> reduceToBestMembers(List<SwimResult> results, int amount)
   {      
      List<SwimResult> list;
      SwimResult worst;
      int worstIndex;
      
      if (results.size() <= amount)
      {
         return results; // returns the same results of if there 
      }
         
      list = results.subList(0, amount);//new ArrayList<SwimResult>();
      
      worstIndex = findWorstIndex(list);
      worst = list.get(worstIndex);           
      
      for (int i = amount; i < results.size(); i++) // look throught reamining swappping better results and determining new worst
      {     
         SwimResult result;
         
         result = results.get(i);
       
         if (result.getResult() < worst.getResult()) // if better result
         {            
            list.set(worstIndex, result); // overwrite worst with new time
            
            worstIndex = findWorstIndex(list); // finds the new worst result
            worst = list.get(worstIndex); // sets the worst result                     
         }
      }
      
      return list;
   }   
   
   
   //Chance
   private static List<SwimResult> reduceToBestMemberResults(List<SwimResult> results)
   {
      List<SwimResult> list;
      
      list = new ArrayList<SwimResult>();
      
      list.add(results.get(0));
      
      for (int i = 1; i < results.size(); i++)
      {
         SwimResult result;
         
         result = results.get(i);
                  
         for (int j = 0; j < list.size(); j++)
         {
            SwimResult other;
            
            other = list.get(j);
         
            if (result.getId() == other.getId())
            {
               if (result.getResult() < other.getResult())
               {
                  list.set(j, result); // if member has better result overwrite
                  break;
               }               
            }
            else if (j == list.size() - 1)
            {
               list.add(results.get(i)); // if member is not on list add to list
            }        
         }         
      }
      
      return list;
   }
   
   
   //Chance
   private static List<SwimResult> reduceToDiscipline(List<SwimResult> results, int discipline)
   {
      List<SwimResult> list;

      list = new ArrayList<SwimResult>();
      
      for (int i = 0; i < results.size(); i++)
      {
         SwimResult res;
         
         res = results.get(i);
         
         if (res.getDisciplineIndex() == discipline)
         {            
            list.add(res);
         }
      }
      
      return list;
   }
   
   
   //Chance
 	private static List<SwimResult> getBestSwimmersFromDiscipline(List<SwimResult> results, int amount, int discipline)
   {
      List<SwimResult> top;
      
      top = reduceToDiscipline(results, discipline);
      
      if (top.isEmpty() == false)
      {
         top = reduceToBestMemberResults(top);
      
         top = reduceToBestMembers(top, amount);
      }
                          
      return top;
	}
   

   //Chance
	private static List<SwimResult> getSwimResultsFromFile(String filename) 
   {
      File data;
      Scanner input;
      Scanner row;
      List<SwimResult> results;
      int i;
      
      results = new ArrayList<SwimResult>();        
      
      try
      {
         data = new File(filename); // The file must only contain valid rows, if there is an empty line it will bug
         input = new Scanner(data);
         
         i = 0;
         while (input.hasNextLine())
         {
            int j;            
            SwimResult result;
            String next;
            
            next = input.nextLine();            
            row = new Scanner(next);
            row.useDelimiter(DELIMITER);
            
            result = new SwimResult();
              
            j = 0;
            
            while (row.hasNext())
            {     
               try
               {     
                  switch (j)
                  {
                     case 0: result.setId(row.nextInt()); break;
                     case 1: result.setName(row.next()); break;
                     case 2: result.setResult(row.nextInt()); break;
                     case 3: result.setDate(row.next()); break;
                     case 4: result.setDisciplineIndex(row.nextInt()); break;
                     case 5: result.setEvent(row.next()); break;
                     case 6: result.setPlacement(row.nextInt()); break;
                  }   
               }
               catch (InputMismatchException ex)
               {
                  System.out.println(ex.getMessage());
               }
               catch (IndexOutOfBoundsException ex)
               {
                  System.out.println("Error in row " + i + ": " + ex.getMessage() + "\n");
               }
               
               j++;                  
            }
            
            results.add(result);
            
            i++;
          } 
      }
      catch (FileNotFoundException ex)
      {
         System.out.println(ex.getMessage());
      }  
      finally
      {
         return results;
      }       
	}
   
   
   //Chance
   public static void findBestSwimmers()
   {       
      List<SwimResult> results;
      List<SwimResult> bestByDiscipline;
      List<SwimResult> best;
      String formattedString;
      
      results = getSwimResultsFromFile(FILENAME);
      formattedString = new String();
      best = new ArrayList<SwimResult>();
      
      for (int i = 0; i < DISCIPLINES; i++)
      {
          bestByDiscipline = getBestSwimmersFromDiscipline(results, AMOUNT, i);
          best.addAll(bestByDiscipline);
          
          formattedString += formatBestSwimmersByDisciplines(bestByDiscipline) + "\n";                                             
      }
      
      System.out.println(formattedString);

      if (best.isEmpty() == false)
      {
         saveBestSwimmers(best);
      }
      
   }

    
	// Lasse
   public static void createSwimResult(Scanner console){
      
      
      SwimResult nytresultat;
      List<SwimResult> eksisterenderesultater;
      
      eksisterenderesultater = getSwimResultsFromFile(FILENAME);
      
      nytresultat = promtSwimResultInfo(console);
      
      eksisterenderesultater.add(nytresultat);
      
      String formateretresultat;
      
      formateretresultat = resultListToString(eksisterenderesultater);
      
      saveToFile(FILENAME, formateretresultat);     
   }
   
   //Lasse
   private static String resultListToString(List<SwimResult> list)
   {
      String format;
      
      format = new String();
      
      for (int i = 0; i < list.size(); i++)
      {
         format += list.get(i).toFormatString() + "\n";
      }
      return format;
   }
   
   
   // Lasse
   public static SwimResult promtSwimResultInfo(Scanner console)     
   {
      
      System.out.println("Du vil blive bedt om at indtaste fÃ¸lgende data:");
      System.out.println("ID nr pÃ¥ svÃ¸mmeren");
      System.out.println("Navn pÃ¥ svÃ¸mmeren");
      System.out.println("Tiden i sekunder, ingen hundrededele");
      System.out.println("Dato for resultatet (02-08-16)");
      System.out.println("SvÃ¸mme Diciplin (crawl, brystsvÃ¸mning osv...");
      System.out.println();
      System.out.println("Hvis resultatet er et stÃ¦vne skal fÃ¸lgende ydereligere indtastes");
      System.out.println("StÃ¦vnets navn");
      System.out.println("svÃ¸mmerens placering til stevnet");
      System.out.println("- - - - -");
      

      System.out.print("ID nr:");
      int id = console.nextInt();
      console.nextLine();
  //For at "bruge" det whitespace enter der kommer med ved indtastningen  
      
     
      System.out.print("Navn:");     
      String navn = console.nextLine();      
      
      System.out.print("Tid(sekunder):");     
      int tid = console.nextInt();
      console.nextLine();  //For at "bruge" det whitespace enter der kommer med ved indtastningen
      System.out.print("Dato for resultatet:");     
      String dato = console.nextLine();
      
      System.out.print("SvÃ¸mmediciplin ");
      System.out.print("[0] for " + SwimResult.getDiscipline(0));
      System.out.print("[1] for " + SwimResult.getDiscipline(1));
      System.out.print("[2] for " + SwimResult.getDiscipline(2));
      System.out.print("[3] for " + SwimResult.getDiscipline(3));
      System.out.print("[4] for " + SwimResult.getDiscipline(4));     
      int diciplin = console.nextInt();
      console.nextLine();  //For at "bruge" det whitespace enter der kommer med ved indtastningen
      
      System.out.print("Er det fra et event? tast 0 for nej, 1 for ja: ");
      int jaellernej = console.nextInt();
      console.nextLine();
      
            
      SwimResult nytresultat = new SwimResult();
      nytresultat.setId(id); 
      nytresultat.setName(navn);
      nytresultat.setResult(tid);
      nytresultat.setDate(dato);
      nytresultat.setDisciplineIndex(diciplin);
      
      // IF statement? eller overload en method med event sÃ¦t sammen.
      if(jaellernej == 1){
         //koden for event til
         System.out.print("Event/stÃ¦vne navn: ");     
         String eventNavn = console.nextLine();
      
         System.out.print("Placering til eventet: ");     
         int placering = console.nextInt();
         console.nextLine();  //For at "bruge" det whitespace enter der kommer med ved indtastningen
      
         nytresultat.setEvent(eventNavn);
         nytresultat.setPlacement(placering);
      
      }
      return nytresultat; 
   }   //Chance
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

}