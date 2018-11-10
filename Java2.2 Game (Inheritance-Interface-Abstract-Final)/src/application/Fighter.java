package application;


/*
 * Author: Brandon West
 * Lab 2 - Fighting Game
 * September 20th, 2018.
 */
public abstract class Fighter implements Scoreable
{
	// values that the enemy, soldier, ally classes
	protected int _smallGun = 3;
	protected int _bigGun = 5;
	protected int _miss = 0;
	protected int _leaderAttack = 15;
	protected int _health;
	protected String _name;
	protected double _score = setDefaultScore();
	
	// abstract methods that are used by the other classes.
	protected abstract int fight ();
	protected abstract int support();
	protected abstract void communicate();
	protected abstract void setScore( double newScore, int shoot );
	public abstract int getHealth();
	// set the default score for the objects. (requirement of assignment)
	public double setDefaultScore()
	{
		return _score = 0.0;
	}
	// get score for the class
	public double getScore()
	{
		return this._score;
	}
	//set health for the class
	public void setHealth( int newHealth )
	{
		this._health = newHealth;
	}
	// compare method
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}// end of fighter abstract class.
