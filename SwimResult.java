
import java.text.SimpleDateFormat;
import java.lang.IndexOutOfBoundsException;
public class SwimResult 
{
   private static final String[] DISCIPLINE_NAMES = new String[]{"Crawl", "Butterfly", "RygCrawl", "HundeSvømning", "Bryst"};

   private int id;
	private String name;
	private int result;
	private SimpleDateFormat date;
	private int discipline;
	private String event;
	private int placement;
   
   // Constructors
   public SwimResult()
   {
   
   }
   
   public SwimResult(int id, String name, int result, SimpleDateFormat date, int discipline)
   {
      setName(name);
      setResult(result);
      setDateFormat(date);
      setDisciplineIndex(discipline);
   }
   
   public SwimResult(int id, String name, int result, String date, int discipline)
   {
      setName(name);
      setResult(result);
      setDate(date);
      setDisciplineIndex(discipline);
   }
   
   public SwimResult(int id, String name, int result, SimpleDateFormat date, int discipline, String event, int placement)
   {
      this(id,name,result,date,discipline);
      setEvent(event);
      setPlacement(placement);
   }
   
   public SwimResult(int id, String name, int result, String date, int discipline, String event, int placement)
   {
      this(id,name,result,date,discipline);
      setEvent(event);
      setPlacement(placement);
   }

   // Accessors
   public int getId() 
   {
		return this.id;
	}
	

	public String getName() {
		return this.name;
	}

	
	public int getResult() 
   {
		return this.result;
	}


   public SimpleDateFormat getDateFormat() 
   {
		return this.date;
	}

   
	public String getDate() 
   {
      try
      {
		   return this.date.toPattern();
      }
      catch (Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return null;
	}


   public String getDiscipline() 
   {
		return DISCIPLINE_NAMES[this.discipline];
	}


	public int getDisciplineIndex() 
   {
		return this.discipline;
	}
	

	public String getEvent() {
		return this.event;
	}


	public int getPlacement() {
		return this.placement;
	}


   public String toString()
   {
      return String.format("%d;%s;%d;%s;%s", this.getId(), this.getName(), this.getResult(), this.getDate(), this.getDiscipline());
   }
   
   public static int getDisciplineAmount()
   {
      return DISCIPLINE_NAMES.length;
   }
   
   // Mutators
   public void setId(int id) 
   {
		this.id = id;
	}
   
   
   public void setName(String name) 
   {
		this.name = name;
	}
   
   
   public void setResult(int result) 
   {
		this.result = result;
	}
   
   
	public void setDateFormat(SimpleDateFormat date) 
   {
		this.date = date;
	}
   
   
	public void setDate(String date) 
   {
		this.date = new SimpleDateFormat(date);
	}
   
   
   public void setDisciplineIndex(int discipline) throws IndexOutOfBoundsException
   {
      if (discipline >= 0 && discipline < getDisciplineAmount())
      {
		   this.discipline = discipline;
      }
      else
      {
         throw new IndexOutOfBoundsException("The discipline index is out of bounds. The index pased was " + discipline + ", but the size of the disciplines is " + getDisciplineAmount());
      }
	}
   
   
	public void setEvent(String event) 
   {
		this.event = event;
	}
   
   
	public void setPlacement(int placement) 
   {
		this.placement = placement;
	}
}