package application;
/*
 * Author: Brandon West
 * Lab 2 - Fighting Game
 * September 20th, 2018.
 */
import java.security.SecureRandom;

public class Support extends Soldier
{
	// support constructor
	public Support( String newName, int health, double newScore ) 
	{
		super(newName, health, newScore);
	}
	/**
	 * Support will ONLY heal no attacking.
	 */
	public double getScore() 
	{
		return 0;
	}
	//sets score for the object
	@Override
	protected void setScore( double newScore, int shoot ) 
	{
		this._score = newScore;
	}
	
	// generates support from the ally
	@Override
	protected int support() 
	{
		int roll = 0;
		SecureRandom random = new SecureRandom();
		roll = random.nextInt( 3 ) + 1;
		
		return roll;
	}// end of the support method
	
	/**
	 * This method shows which class is currently attacking and doing damage/missing
	 */
	@Override
	protected void communicate()
	{
		System.out.println( "The " + this.getClass().getSimpleName() + " Class.");
		System.out.println( this.getName() + " is up!" );
	}
}// end of ally class