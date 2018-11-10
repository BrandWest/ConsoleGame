package application;

import java.security.SecureRandom;
import java.util.Comparator;

public class UniqueFighter extends Fighter implements Comparable<UniqueFighter>
{
	//unique fighter constructor
	public UniqueFighter( String newName, int health, double newScore)
	{
		this._name = newName;
		this._health = health;
		this._score = newScore;
	}
	//gets health for this object
	public int getHealth ()
	{
		return this._health;
	}
	/**
	 *  sets health for the uniqueFighter
	 */
	public void setHealth( int newHealth )
	{
		if ( this.getHealth() > 500 )
		{
			System.out.println( "Unable to get more health, at max!" );
		}
		else
			this._health = newHealth;
	}
	// gets name for the object
	public String getName()
	{
		return this._name;
	}
	
	//sets name for the object
	public void setName ( String newName )
	{
		this._name = newName;
	}
	
	// gets score for the object
	@Override
	public double getScore() 
	{
		return this._score;
	}
	// fight method for the object
	@Override
	protected int fight() 
	{
		// random number generated
		SecureRandom random = new SecureRandom();
		// shoot to find the roll
		int shoot = random.nextInt( 4 );
		
		//shoot is 1, 1 enemy killed
		if ( shoot == 1 )
		{
			System.out.println ( "Commander lands a small hit!" );
			this.setScore( this.getScore(), 1 );
			return ( _smallGun * 3 );
		}
		
		// shoot 2, kills 2 enemies
		else if( shoot == 2 )
		{
			this.setScore( this.getScore(), 2 );
			System.out.println ( "Commander lands a large hit!");
			return ( _bigGun * 4 );
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
	}

	//no support for this class
	@Override
	protected int support() {
		// TODO Auto-generated method stub
		return 0;
	}

	//communicate the class and the name of the Object
	@Override
	protected void communicate() 
	{
		System.out.println( "The " + this.getClass().getSimpleName() + " Class." );		
		System.out.println( this.getName() + " is up!" );
	}
	// sets the score for the object
	@Override
	protected void setScore(double newScore, int shoot) 
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
	// compareTo method for Object UniqueFighter
	public int compareTo(UniqueFighter compareUnique) 
	{
		int compareScore = (int) ( ( UniqueFighter ) compareUnique ).getScore();
		
		return (int) (compareScore - this.getScore());
	}
	//Comparator for unique fighter, score first then name.
	public static Comparator<UniqueFighter>UniqueFighterScoreComparator = new Comparator<UniqueFighter>() 
	{
		public int compare(UniqueFighter uf1, UniqueFighter uf2 ) 
		{
			Double firstScore = uf1.getScore();
			Double secondScore = uf1.getScore();
			
			String name1 = uf1.getName().toUpperCase();
			String name2 = uf1.getName().toUpperCase();
			
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
}