
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.File;

import java.lang.Exception;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;


public class SwimResultHandler 
{
   private static final String FILENAME = "swimresults.dat";
   private static final String DELIMITER = ";";
   private static final int DISCIPLINES = 5;
   private static final int TOP5 = 5;

	private SwimResult swimResult;
	private List<SwimResult> swimResults;
   
	public void createSwimResult() 
   {
		// TODO - implement SwimResultHandler.createSwimResult
      
   
      
      
		//throw new UnsupportedOperationException();
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

   //Chance
	public void getTop5Swimmers() 
   {
		// TODO - implement SwimResultHandler.getTop5Swimmers
      File data;
      Scanner input;
      Scanner row;
      List<SwimResult> results;
      List<SwimResult> topResults;
      
      results = new ArrayList<SwimResult>();        
      topResults = new ArrayList<SwimResult>();

      
      try
      {
         data = new File(FILENAME);
         input = new Scanner(data);
         
         while (input.hasNextLine())
         {
            int i;
            
            SwimResult swimResult;
            String next;
            
            next = input.nextLine();            
            row = new Scanner(next);
            row.useDelimiter(DELIMITER);
            
            swimResult = new SwimResult();
              
            i = 0;         
            while (row.hasNext())
            {     
               try
               {     
                  switch(i)
                  {
                     case 0: swimResult.setName(row.next()); break;
                     case 1: swimResult.setResult(row.nextInt()); break;
                     case 2: swimResult.setDate(new SimpleDateFormat(row.next())); break;
                     case 3: swimResult.setDiscipline(row.nextInt()); break;
                     case 4: swimResult.setEvent(row.next()); break;
                     case 5: swimResult.setPlacement(row.nextInt()); break;
                  }   
               }
               catch (InputMismatchException ex)
               {
                  System.out.println(ex.getMessage());
               }
               
               i++;                  
            }
            
            results.add(swimResult);
          } 
      }
      catch(FileNotFoundException ex)
      {
         System.out.println(ex.getMessage());
      }
      
      for (int i = 0; i < DISCIPLINES; i++)
      {
          List<SwimResult> topDiscipline = getTopSwimmers(results, TOP5, i);
          
          topResult.addAll(topDiscipline);                
      }
      
	}

	private List<SwimResult> getTopSwimmers(List<SwimResult> results, int amount, int discipline) 
   {
      List<SwimResult> top;
      int worstTime;
      
      top = new ArrayList<SwimResult>();
           
      worstTime = results[0].getResult();
      
      for (int i = 1; i >)
      
      
      return results;
	}



   public static void main(String[] args)
   {
      SwimResultHandler swh = new SwimResultHandler();
      
      swh.getTop5Swimmers();
   }
}