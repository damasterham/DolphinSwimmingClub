import java.io.*;
import java.util.*;

public class MemberHandler
{
	// Fields
	private ArrayList<Member> list;
	
	// Constructor
	public MemberHandler()
	{
		list = new ArrayList<Member>();
	}
	
	// *************************************
	// ACCESSORS - GET MTHODS
	// *************************************
	
	
	
	// *************************************
	// MUTATORS - SET METHODS
	// *************************************
	
	// Read memberlist.txt
	public void readFileToList()
	{
		Scanner input;
		File f;
		
		input = null;
		f = new File("memberlist2.txt"); // Kan File objektet måske rykkes ud til et static field eller en konstant?
		
		try
		{
			input = new Scanner(f);
		}
		catch (FileNotFoundException e) 
		{
			// Print errors message to user
			e.printStackTrace();
		}
		
		while (input.hasNextLine())
		{
			String line;
			
			line = input.nextLine();
			processLine(line);
			
		}
		
	}
	
	// read line from memberlist.txt, create Member object and add it to ArrayList
	public void processLine(String line) 
	{
		Scanner data;
		String name;
		int age;
		String activity;
		String ambition;
		Member member;
		
		data = new Scanner(line);
		
		data.useDelimiter(";");
		
		name = data.next();
		age = data.nextInt();
		activity = data.next();
		ambition = data.next();
		
		if (ambition.equalsIgnoreCase("konkurrence"))
		{	
			String disciplines;
			disciplines = data.next();
			
			member = new Member(name, age, activity, ambition, disciplines);
			list.add(member);
		}
		else
		{
			member = new Member(name, age, activity, ambition);
			list.add(member);
		}
		
	}
	
	// Create new member and add it to ArrayList
	public void promptMemberData()
	{
		Scanner console;
		String name;
		int age;
		String activity;
		String ambition;
		Member newMember;
		
		console = new Scanner(System.in);
		
		System.out.print("Navn: ");
		name = console.nextLine();
		
		System.out.print("Alder: ");
		age = console.nextInt();
		
		System.out.print("Aktivitet: ");
		activity = console.next().toLowerCase();
		
		System.out.print("Ambition: ");
		ambition = console.next().toLowerCase();
		
		if (ambition.equalsIgnoreCase("konkurrence"))
		{
			System.out.print("Discipliner: ");
			String discipliner = console.next();
			
			newMember = new Member(name, age, activity, ambition, discipliner);
			list.add(newMember);
		}
		else
		{
			newMember = new Member(name, age, activity, ambition);
			list.add(newMember);
		}
		
	}
	
	// Creates new memberlist text file
	public void createNewList()
	{
		PrintStream output;
		File f;
		
		output = null;
		f = new File("memberlist2.txt");
		
		try 
		{
			output = new PrintStream(f);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.size(); i++)
		{	
			String member; 
			
			member = list.get(i).toString();
			output.println(member);
		}
		
	}
   
   
   public static void createNewMember()
	{						
				MemberHandler f2 = new MemberHandler();
				
				f2.newMemberIntro();
				f2.readFileToList();
				f2.promptMemberData();
				f2.createNewList();		
	}
   
	
	// **************************************
	// STATIC METHODS
	// **************************************
	
	public static void newMemberIntro()
	{
		System.out.println();
		System.out.println("************************");
		System.out.println("* REGISTRER NYT MEDLEM *");
		System.out.println("************************");
	}
	
}