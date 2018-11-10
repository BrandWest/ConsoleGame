package application;
/*
 * Author: Brandon West
 * Lab 2 - Fighting Game
 * September 20th, 2018.
 */
import java.security.SecureRandom;
import java.util.Comparator;

/*
 *  Class that extends fighter and creates the fighters, finds hits, and generates support.
 */
public class Soldier extends Fighter implements Comparable<Soldier>
{
	public Soldier ( String newName, int health, double newScore )
	{
		this._name = newName;
		this._health = health;
		this._score = newScore;
		System.out.println ( "Something ");
	}
	
	public int getHealth ()
	{
		return this._health;
	}
	
	public void setHealth( int newHealth )
	{
		if ( this.getHealth() > 60 )
		{
			System.out.println( "Unable to get more health, at max!\n" );
		}
		else
			this._health = newHealth;
	}
	public String getName()
	{
		return this._name;
	}
	public void setName ( String newName )
	{
		this._name = newName;
	}
	@Override
	public double getScore() 
	{
		return this._score;
	}
	@Override
	protected void setScore( double newScore, int shoot ) 
	{
		if ( shoot == 0 )
			this._score = this._score - 3;
		else if ( shoot == 1 )
			this._score = this._score + 1;
		else if ( shoot == 2 )
			this._score = this._score + 2;
		else 
			this._score = this._score + 3;
	}
	
	
	
	@Override
	protected int fight ()
	{
		// random number generated
		SecureRandom random = new SecureRandom();
		// shoot to find the roll
		int shoot = random.nextInt( 4 );
		
		//shoot is 1, 1 enemy killed
		if ( shoot == 1 )
		{
			System.out.println ( "Soldier Small Hit!");
			this.setScore( this.getScore(), 1 );
			return _smallGun;
		}
		
		// shoot 2, kills 2 enemies
		else if( shoot == 2 )
		{
			this.setScore( this.getScore(), 2 );
			System.out.println ( "Soldier Large Hit!");
			return _bigGun;
		}
		// shoot 3, hits the commander on the other team.
		else if ( shoot == 3 )
		{
			this.setScore( this.getScore(), 3 );
			return _leaderAttack;
		}
		//misses the enemy
		else
		{	
			this.setScore( this.getScore(), 0 );
			System.out.println (  this.getName() + " missed.");
			return _miss;
		}
	}// end of fight method
	
	// override the support method
	@SuppressWarnings("unused")
	@Override
	protected int support( ) 
	{
		// roll holds the roll of support generated
		int roll = 0;
		
		SecureRandom random = new SecureRandom();
		
		return roll = random.nextInt( 3 ) + 1; // returns support of 1-3
	}// end support method

	// overrides the communicate method from Fighter, reports which class is currecntly attacking
	@Override
	protected void communicate()
	{
		System.out.println( "The " + this.getClass().getSimpleName() + " Class.");
		System.out.println( this.getName() + " is up!" );		
	}// end communicate method

	// compare to method
	public int compareTo(Soldier compareSoldier) 
	{
		int compareScore = (int) ( ( Soldier ) compareSoldier ).getScore();
		
		return (int) (compareScore - this.getScore());
	}

	// comparator method for comparing objects in a list, first by score then by name.
	public static Comparator<Soldier>SoldierScoreComparator = new Comparator<Soldier>() 
	{
		public int compare(Soldier s1, Soldier s2 ) 
		{
			Double firstScore = s1.getScore();
			Double secondScore = s2.getScore();
			
			String name1 = s1.getName().toUpperCase();
			String name2 = s2.getName().toUpperCase();
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
} // end of the soldier class
