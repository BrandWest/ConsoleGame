package application;

import java.util.Comparator;
/**
 * This class holds the dead players, when added to the list with score
 * Extra class from the classes required for assignment
 * most methods unused
 * @author Brandon West
 *
 */
public class Deaths extends Fighter
{
	// constructors
	Deaths ()
	{}
	// non-deafault constructor
	public Deaths ( String newName, double efficiency )
	{
		this._name = newName;
		this._score = efficiency;
	}
	// get the name of the object
	protected String getName()
	{
		return this._name;
	}
	// Unused but extends the fighter class
	@Override
	protected int fight() {
		// TODO Auto-generated method stub
		return 0;
	}
	// Unused but extends the fighter class

	@Override
	protected int support() {
		// TODO Auto-generated method stub
		return 0;
	}
	// Unused but extends the fighter class

	@Override
	protected void communicate() {
		// TODO Auto-generated method stub
		
	}
	// Unused but extends the fighter class

	@Override
	protected void setScore(double newScore, int score) 
	{
		// TODO Auto-generated method stub
		
	}
	// Unused but extends the fighter class
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// compare to method
	public int compareTo(Deaths compareDeaths) 
	{
		int compareScore = (int) ( ( Deaths ) compareDeaths ).getScore();
		
		return (int) (compareScore - this.getScore());
	}

	
	// comparator method for comparing objects in a list, first by score then by name.
	public static Comparator<Deaths>DeathsScoreComparator = new Comparator<Deaths>() 
	{
		public int compare(Deaths d1, Deaths d2 ) 
		{
			Double firstScore = d1.getScore();
			Double secondScore = d2.getScore();
			
			String name1 = d1.getName().toUpperCase();
			String name2 = d2.getName().toUpperCase();
			// if the scores are equal, return the names compared.
			if ( firstScore.equals ( secondScore) )
			{
				return name1.compareTo( name2 );
			}
			else
				return secondScore.compareTo( firstScore );
			/*
			 *  un comment for the reverse order
			 */
//			if ( secondScore.equals( firstScore ) )
//				return name2.compareTo( name1 );
//			else 
//				return firstScore.compareTo( secondScore );
		}
	};// end of comparator method

}
