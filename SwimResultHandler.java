import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.PrintStream;
import java.lang.Exception;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;


public class SwimResultHandler 
{
   private static final String FILENAME = "swimresults.dat";
   private static final String SAVENAME = "best_swimmers_";
   private static final String DELIMITER = ";";
   private static final int DISCIPLINES = 5;
   private static final int AMOUNT = 5;
   private static final String[] DISCIPLINE_NAMES = new String[]{"Crawl", "Butterfly", "RygCrawl", "HundeSvømning", "Bryst"};

	private SwimResult swimResult;
	private List<SwimResult> swimResults;
   
	public void createSwimResult() 
   {
		// TODO - implement SwimResultHandler.createSwimResult
      
   
      
      
		//throw new UnsupportedOperationException();
	}
   
   private boolean saveToFile(String filename, String formattedText)
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
            if (file.createNewFile())
            {
               output = new PrintStream(file);
               output.print(formattedText);  
               success = true;             
            }
         }
         
      }
      catch(FileNotFoundException ex)
      {
         System.out.println(ex.getMessage());  
      }
      finally
      {
         return success;
      }
   }
   
   //Chance
   private List<SwimResult> reduceToDiscipline(List<SwimResult> results, int discipline)
   {
      List<SwimResult> list;

      list = new ArrayList<SwimResult>();
      
      for (int i = 0; i < results.size(); i++)
      {
         SwimResult res;
         
         res = results.get(i);
         
         if (res.getDiscipline() == discipline)
         {            
            list.add(res);
         }
      }
      
      return list;
   }
   
   
   //Chance
   private List<SwimResult> reduceToBestMemberResults(List<SwimResult> results)
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
   private int findWorstIndex(List<SwimResult> results)
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
   private List<SwimResult> reduceToBestMembers(List<SwimResult> results, int amount)
   {      
      List<SwimResult> list;
      SwimResult worst;
      int worstIndex;
      
      if (results.size() <= amount)
      {
         return results; // returns the same results of if there 
      }
         
      list = new ArrayList<SwimResult>();
      
      worst = results.get(0);
      list.add(worst);     
      worstIndex = 0;   
      
      for (int i = 1; i < amount; i++) // get first 5 and finde worst
      {
         SwimResult result;
         
         result = results.get(i);
      
         if (result.getResult() > worst.getResult())
         {
            worst = result;
            worstIndex = i;
         }
         
         list.add(result);
      }
      
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
 	private List<SwimResult> getTopSwimmers(List<SwimResult> results, int amount, int discipline)
   {
      List<SwimResult> top;
      
      top = reduceToDiscipline(results, discipline);
      
      if (top.isEmpty() != true)
      {
         top = reduceToBestMemberResults(top);
      
         top = reduceToBestMembers(top, amount);
      }
                          
      return top;
	}
   


   //Chance
	private List<SwimResult> getSwimResultsFromFile(String filename) 
   {
      File data;
      Scanner input;
      Scanner row;
      List<SwimResult> results;
      
      results = new ArrayList<SwimResult>();        
      
      try
      {
         data = new File(filename);
         input = new Scanner(data);
         
         while (input.hasNextLine())
         {
            int i;            
            SwimResult result;
            String next;
            
            next = input.nextLine();            
            row = new Scanner(next);
            row.useDelimiter(DELIMITER);
            
            result = new SwimResult();
              
            i = 0;
            
            while (row.hasNext())
            {     
               try
               {     
                  switch (i)
                  {
                     case 0: result.setId(row.nextInt()); break;
                     case 1: result.setName(row.next()); break;
                     case 2: result.setResult(row.nextInt()); break;
                     case 3: result.setDate(new SimpleDateFormat(row.next())); break;
                     case 4: result.setDiscipline(row.nextInt()); break;
                     case 5: result.setEvent(row.next()); break;
                     case 6: result.setPlacement(row.nextInt()); break;
                  }   
               }
               catch (InputMismatchException ex)
               {
                  System.out.println(ex.getMessage());
               }
               
               i++;                  
            }
            
            results.add(result);
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
   private void saveBestSwimmers(List<SwimResult> list)
   {
      // thought for saving in a more iterative save;
   
   }
   
   
   //Chance
   private String presentBestSwimmers(List<SwimResult> list, String discipline)
   {
      String value;
      
      value = discipline + ":\n";
      
      if (list.isEmpty())
      {
         value += "None\n";          
      }
      else
      {
         for (int i = 0; i < list.size(); i++)
         {
            SwimResult result;
            result = list.get(i);
            
            value += String.format("Medlem: %s, Tid i sekunder: %s, Dato sat: %s%n", result.getName(), result.getResult(), result.getDate().toPattern());
         }
      }      
      
      return value;
   }
   
   
   //Chance
   public void findBestSwimmers()
   {       
      List<SwimResult> results;
      List<SwimResult> best;
      String resultStr;
      
      results = getSwimResultsFromFile(FILENAME);
      
      for (int i = 0; i < DISCIPLINES; i++)
      {
          best = getTopSwimmers(results, AMOUNT, i);
          
          resultStr = presentBestSwimmers(best, DISCIPLINE_NAMES[i]);
          
          System.out.println(resultStr);
          
          saveToFile(SAVENAME ,resultStr);                              
      }
   }

    
	public void registerSwimmingResult(int resultInSeconds, SimpleDateFormat date, int discipline, String event, int placement) 
   {
		// TODO - implement SwimResultHandler.registerSwimmingResult
	}
	public void registerSwimmingResult(int resultInSeconds, SimpleDateFormat date, int discipline) 
   {
		// TODO - implement SwimResultHandler.registerSwimmingResult
	}

	public void saveResult() 
   {
		// TODO - implement SwimResultHandler.saveResult
	}


   public static void main(String[] args)
   {
      SwimResultHandler swh = new SwimResultHandler();
      
      swh.findBestSwimmers();
   }
}