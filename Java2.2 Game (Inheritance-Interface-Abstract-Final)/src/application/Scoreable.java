package application;
/*
 * Author: Brandon West
 * Lab 2 - Fighting Game
 * September 20th, 2018.
 */
public interface Scoreable 
{
	// this is the method that is input by the fighter class
	public double getScore();
	// this sets the default score for the interface.
	public default double setDefaultScore()
	{
		return 0.0;
	}
	
}

