package DolhpinSwimClubDCD;

public class Member {

	private boolean[] disciplines;
	private String name;
	private int age;
	private boolean activity;
	private boolean ambitionLevel;
	private double debt;

	public boolean[] getDisciplines() {
		return this.disciplines;
	}

	/**
	 * 
	 * @param disciplines
	 */
	public void setDisciplines(boolean[] disciplines) {
		this.disciplines = disciplines;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public boolean getActivity() {
		return this.activity;
	}

	/**
	 * 
	 * @param activity
	 */
	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public boolean getAmbitionLevel() {
		return this.ambitionLevel;
	}

	/**
	 * 
	 * @param ambitionLevel
	 */
	public void setAmbitionLevel(boolean ambitionLevel) {
		this.ambitionLevel = ambitionLevel;
	}

	public double getDebt() {
		return this.debt;
	}

	/**
	 * 
	 * @param debt
	 */
	public void setDebt(double debt) {
		this.debt = debt;
	}

}