package application;

/*
 * Author: Brandon West
 * Lab 2 - Fighting Game
 * September 20th, 2018.
 */
import java.security.SecureRandom;
import java.util.Comparator;

/*
 * generates, attacks, and supplied allies through random  rolls
 */
public class Enemy extends Fighter implements Comparable<Enemy>
{
	// removed amount, it should be use to create the amount of people
	public Enemy ( String newName, int health, double newScore )
	{
		this._name = newName;
		this._health = health;
		this._score = newScore;

	}
	// sets score for using a certain weapon used for how effective enemy was
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
	// gets health for the object
	public int getHealth ()
	{
		return this._health;
	}
	// sets health for the object
	public void setHealth( int newHealth )
	{
		// cant gain more than 60 hp from the supports
		if ( this.getHealth() > 60 )
		{
			System.out.println( "Unable to get more health, at max!\n" );
		}
		// new health is set
		else 
			this._health = newHealth;
	}
	//returns the name of the object
	public String getName()
	{
		return this._name;
	}
	// sets the name of the object
	public void setName ( String newName )
	{
		this._name = newName;
	}
	
	// overrides fight class
	@Override
	public int fight ()
	{
		// random number generated between 0-4
		SecureRandom random = new SecureRandom();
		int shoot = random.nextInt( 4 );
		
		// if 1, hits 1 soldier or ally
		if ( shoot == 1 )
		{
			// sets the score for a successful hit
			this.setScore( this.getScore(), 1 );
			System.out.println ( "Small Enemy hit!");
			return _smallGun;
		}
		
		// if 2, hits 2 soldiers or allies
		else if( shoot == 2 )
		{
			// sets the score for a successful hit
			this.setScore( this.getScore(), 2 );
			System.out.println ( "Big Enemy hit!");
			return _bigGun;
		}
		// if 3, hits the commander
		else if ( shoot == 3 )
		{
			// sets the score for a successful hit
			this.setScore( this.getScore(), 3 );
			return _leaderAttack;
		}
		
		// sets the score for an unsuccesful hit
		else
		{
			this.setScore( this.getScore(), 0 );
			System.out.println ( "Enemy Miss!");
			return _miss;
		}
	}// end fight method
	
	// Overrides support method from fighter
	@SuppressWarnings("unused")
	@Override
	protected int support() 
	{
		// roll to hold random
		int roll = 0;
		SecureRandom random = new SecureRandom();
		
		return roll = random.nextInt( 5 ) +  1; // returns the amount of support gained
	}// end of support method

	//overrides the communicate method from Fighter, reports which class is currently attacking
	@Override
	protected void communicate()
	{
		System.out.println( "The " + this.getClass().getSimpleName() + " Class.");
		System.out.println( this.getName() + " is up!" );
	}// end 
	// gets score for this object
	public double getScore() 
	{
		return this._score;
	}
	//compareTo method to compare the scores in the objects
	public int compareTo(Enemy compareEnemy) 
	{
		int compareScore = (int) ( ( Enemy ) compareEnemy ).getScore();
		
		return (int) (compareScore - this.getScore());
	}
	//comparator for the objects Enemy, first by score, then by name
	public static Comparator<Enemy>EnemyScoreComparator = new Comparator<Enemy>() 
	{
		public int compare( Enemy e1, Enemy e2 ) 
		{
			Double firstScore = e1.getScore();
			Double secondScore = e2.getScore();
			
			String name1 = e1.getName().toUpperCase();
			String name2 = e2.getName().toUpperCase();
			
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
	};
}// end of enemy class
