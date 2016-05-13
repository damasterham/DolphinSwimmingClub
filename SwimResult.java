package DolhpinSwimClubDCD;

public class SwimResult {

	private String name;
	private int result;
	private DateTime date;
	private int discipline;
	private String event;
	private int placement;
   
   public SwimResult()
   {
      
   }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public DateTime getDate() {
		return this.date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getDiscipline() {
		return this.discipline;
	}

	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getPlacement() {
		return this.placement;
	}

	public void setPlacement(int placement) {
		this.placement = placement;
	}

}