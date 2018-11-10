package application;
/**
 * Brandon West
 * Honors Bachelor of Applied Information Security
 * Java 2 - PROG36859
 * Description:  This program uses random number generator to attack, support and, eventually beat the enemy
 * 				 It ranks the players in order of efficiency and alive or dead. 
 */
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/*
 * Game starts by generating the players on both sides, random rolls to make hits or misses until one team has 0
 */

public class Game //implements Comparable<Object>
{
	
	String _firstName;
	String _secondName;
	
	@SuppressWarnings("unused")
	public static void main ( String[] args )
	{
		// Initialize variables for use through the program
		int whichSoldier = 0, hit = 0, whichEnemy = 0, turn = 0, whichCommander = 0,
			index = 0, total = 0, attack = 0, support = 0, winner = 0;

		
		// secure random object to allow for the playing of the game
		SecureRandom random = new SecureRandom();

		// objects for uniqueFighter, soldiers, allies, enemies (names of objects from Blizzard game Overwatch)
		Soldier hammond, tracer, gengi, winston, mcree;
		Enemy widowmaker, reaper, roadhog, moria, junkrat;
		Support mercy, zenyatta;
		UniqueFighter soldier76, doomfist;
		
		//array lists to hold each object created
		List <Soldier> soldiers = new ArrayList<>();
		List <Support> supports = new ArrayList<>();
		List <Enemy> enemies = new ArrayList<>();
		List <UniqueFighter> uniqueFighter = new ArrayList<>();
		List <Deaths> deaths = new ArrayList<>();
		
		/**
		 * The binary search items
		 String[] names = new String [10];
		
		names[ 0 ] = "Perfect Dark";
		names[ 1 ] = "James Bond";
		names[ 2 ] = "Portal Two";
		names[ 3 ] = "Half Life";
		names[ 4 ] = "World Warcraft";
		names[ 5 ] = "Over Watch";
		names[ 6 ] = "Mine Craft";
		names[ 7 ] = "Start Craft";
		names[ 8 ] = "Diablo Two";
		names[ 9 ] = "Blizzard World";
		*/
		// iterates through each object
		Iterator<Enemy> iteratorEnemy = enemies.iterator();
		Iterator<Soldier> iteratorSoldier = soldiers.iterator();
		Iterator<Support> iteratorSupport = supports.iterator();
		Iterator<UniqueFighter> iteratorUnique = uniqueFighter.iterator();
		
		// if there is not enough arguments, exit Right now its set to have the maximum number of players
		if ( args.length != 2 )
		{
			System.out.println( "Must implement the command line arguements for:"  );
			System.out.println( "Health of the Soldiers, Allies, and Enemies, max 60."  );
			System.out.println( "Health of the characters: 100 - 200."  );
			System.exit( 1 );
		}
		
		//enough command line arguments (4) 
		else 
		{	
			//verifies that all Command line arguments are within specified range and creates the objects with specified values
			if (  Integer.parseInt( args [ 0 ] ) > 40 && Integer.parseInt( args [ 0 ] ) <= 60 && 
				 Integer.parseInt( args [ 1 ] ) >= 100 && Integer.parseInt( args [ 1 ] ) <= 500 )
			{
				// add soldier objects to the soldier list
				soldiers.add( hammond = new Soldier( "Hammond", Integer.parseInt( args [ 0 ] ), 0 ) );
				soldiers.add( tracer = new Soldier ( "Tracer", Integer.parseInt( args [ 0 ] ), 0 ) );
				soldiers.add( gengi = new Soldier ( "Gengi", Integer.parseInt( args [ 0 ] ), 0  ) );
				soldiers.add( winston = new Soldier ( "Winston", Integer.parseInt( args [ 0 ] ), 0  ) );
				soldiers.add( mcree = new Soldier ( "Mcree", Integer.parseInt( args [ 0 ] ), 0  ) );
				// add enemy to enemies list
				enemies.add( widowmaker = new Enemy ( "Widowmaker", Integer.parseInt( args [ 0 ] ), 0  ) );
				enemies.add( junkrat = new Enemy ( "Junkrat",  Integer.parseInt( args [ 0 ] ), 0  )  );
				enemies.add( roadhog = new Enemy ( "Road Hog", Integer.parseInt( args [ 0 ] ), 0  ) );
				enemies.add( reaper = new Enemy ( "Reaper", Integer.parseInt( args [ 0 ] ), 0  ) );
				enemies.add( moria = new Enemy ( "Moria",  Integer.parseInt( args [ 0 ] ), 0  )  );
				//add supports to the support list
				supports.add( mercy = new Support ( "Mercy",  Integer.parseInt( args [ 0 ] ), 0 ) );
				supports.add( zenyatta = new Support ( "Zenyatta",  Integer.parseInt( args [ 0 ] ), 0 ) );
				// add the "bosses" to the unique fighter list
				uniqueFighter.add( soldier76 = new UniqueFighter ( "Soldier 76",  Integer.parseInt( args [ 1] ), 0 ) );
				uniqueFighter.add( doomfist = new UniqueFighter ( "Doomfist",  Integer.parseInt( args [ 1 ] ), 0 ) );	
			}

			//if the range is not fulfilled, must restart. And specifies what the command line arguments should be
			else 
			{
				System.out.println( "Command Line arguments out of range!"  );
				System.out.println( "Health of the Soldiers, Supports, and enemies, max 50."  );
				System.out.println( "Health 100 - 500."  );
				System.out.println( "Invalid inputs, exiting." );
				System.out.println( "Your Input - Players Health: " + args [0] + ". Commander Health: " + args [1] );
				System.exit( 0 );
			}// end of unsuccessful else
		}// end of checks on arguments

		/**
		 * 	Prints out the rules, and the team compositions
		 */
		// try and catch print out the roster of the teams.
		try 
		{
			System.out.println( " ----------  The Rules of the Game  -------------" );
			System.out.println( " ================================================" );
			System.out.println( "|  Rule 1: The first team to run out of HP Loses.|" );
			System.out.println( "|  Rule 2: If the Commander Dies, automatic loss.|" );
			System.out.println( "|  Rule 3: All rolls are generated randomly.     |" );
			System.out.println( "|  Rule 4: Have fun!                             |" );
			System.out.println( " ================================================ ");
			System.out.println( " ----The Soldier Team----");
			System.out.println( " ========================");
			// do while loop to  print the soldiers list 
			do
			{
				System.out.printf( "|%15s \t |\n", soldiers.get( index ).getName() );
				index++;
			} while ( soldiers.isEmpty() == false );
			
		}catch ( IndexOutOfBoundsException error )
		{
//			System.out.println( error );			
		}
		// prints out the support and Boss for the "good guys"
		System.out.printf( "|Support: %2s \t |\n", supports.get( 0 ).getName());
		System.out.printf( "|Boss:  %2s \t |\n", uniqueFighter.get( 0 ).getName() );
		// reset index to control the do while loops
		index = 0;
		System.out.println( " ========================" );
		// try and catch for printing enemy team
		try 
		{
			System.out.println(  " -----The Enemy Team-----" );
			System.out.println ( " ========================" );
			// do while to print the enemies until there is no more enemies to print in list.
			do
			{
				System.out.printf( "|%15s \t |\n", enemies.get( index ).getName() );
				index++;
			}while ( enemies.isEmpty() == false );
		}catch ( IndexOutOfBoundsException error )
		{
//			System.out.println( error );
		}
		// prints the suppoort and boss for the enemy team
		System.out.printf( "|Support: %2s \t |\n", supports.get( 1 ).getName());
		System.out.printf( "|Boss:  %2s \t |\n", uniqueFighter.get( 1 ).getName() );
		// reset index to 0.
		index = 0;
		System.out.println( " ========================");
		System.out.println( "\n" );
	
		// while loop to keep track of the number of players that are alive and to roll for attacks, misses
		try 
		{
			// while the enemies is empty() comes back as false keep the game going.
			while ( enemies.isEmpty() != true  )
			{
				// pause for testing and tally
//				try
//				{
//					Thread.sleep(1000);
//				} catch (InterruptedException e) 
//				{
//					e.printStackTrace();
//				}
				total++; // keeps track of the number of turns.
				turn = random.nextInt( 7 ) + 1; // random number for turn between 1-7
				System.out.println ( "Roll: " + turn );
				
				// no more good guys left alive, breaks out of the while loop
				if (  soldiers.isEmpty() == true )
				{
					System.out.println( "It took: " + total + " turns to be defeated by the enemy." );
					System.out.println( "The commander has lost all their health.\n" );
					break;
				}

				// rolls a 1 or a 2: soldiers attacking enemy or commander
				else if ( turn == 1 || turn == 2 )
				{
					// find which soldier, enemy is going to attack/defend based on the size of the array list
					whichSoldier = random.nextInt( soldiers.size() );
					whichEnemy = random.nextInt( enemies.size() );
					// communicate the class and the name of the object
					soldiers.get( whichSoldier ).communicate();
					attack = soldiers.get( whichSoldier ).fight();
					// if the attack value is 15, we're taking health from the commander
					if ( attack == 15)
					{
						System.out.println( uniqueFighter.get( 1 ).getName() + " has been struck!" );
						// hit is used to calcualte the damage of the "boss"
						hit = uniqueFighter.get( 1 ).getHealth() - attack;
						uniqueFighter.get( 1 ).setHealth( hit );
						
						// this controls if the enemies commander has no health
						if ( uniqueFighter.get( 1 ).getHealth() <= 0 )
						{
							// the commander is added to the death list, and the game is over.
							System.out.println( uniqueFighter.get( 1 ).getName() + " has no health left!\n" );
							// the deaths list is used to calculate the total scores at the end of the game.
							deaths.add( new Deaths ( uniqueFighter.get( 1 ).getName(), uniqueFighter.get(1).getScore() ) );
							// removes the boss from the list.
							uniqueFighter.remove( 1 );
							// sets winner to 0, ends loop
							winner = 0;
							break;
						}
						// if the commander is still alive continue to next attack
						else
							System.out.println( uniqueFighter.get( 1 ).getName() + " has " + uniqueFighter.get( 1 ).getHealth() + " health left.\n" );
					} // end of if uniqueFighter has been hit 
					// if the attack value is less than 15, we continue to dwindle health of enemies
					else
					{
						// hit calculates the damage to the specific enemy, attack is the attack damage of the opposition
						hit = enemies.get( whichEnemy ).getHealth() - attack;	
						// sets the enemies health
						enemies.get( whichEnemy ).setHealth( hit );
					
						// if the health pool of a particular enemy is 0, remove them and add them to the death list. 
						if ( enemies.get ( whichEnemy ).getHealth() <= 0 )
						{
							// enmey is called and verified is dead
							System.out.println( "Enemy " + enemies.get ( whichEnemy ).getName() + " has been eliminated." );
							// adds to the death list
							deaths.add( new Deaths ( enemies.get( whichEnemy ).getName(), enemies.get( whichEnemy ).getScore() ) );
							//removes for the enemy list
							enemies.remove ( enemies.get( whichEnemy) );
						}// end of if enemy has no health left
						// else state how much health they have left after the attack
						else
							// specifies how much healt is left.
							System.out.println ( enemies.get( whichEnemy ).getName() + " has " + enemies.get(whichEnemy).getHealth() + " health points left!\n");							
					}// end of else
				}// end of else if for turn 1 or 2
			
				// rolled 3, Support is healing the soldiers
				else if ( turn == 3 )
				{
					// support heals a random soldier based on size of the array list
					whichSoldier = random.nextInt( soldiers.size() );
					supports.get( 0 ).communicate();
					System.out.println( "The soldiers support: " + supports.get( 0 ).getName() + " is up!" );
					// soldier regians health
					System.out.println ( soldiers.get( whichSoldier ).getName() + " is getting healed by: " + supports.get( 0 ).getName() + ".\n");
					// support is how much health is gained
					support = soldiers.get( whichSoldier ).support();
					// adds health to solider
					support = soldiers.get( whichSoldier ).getHealth() + support;
					// sets solider health
					soldiers.get( whichSoldier ).setHealth( support );
				}// end of else if healing soldier
				
				// rolled 4 support healing enemies
				else if ( turn == 4 )
				{
					// support for the enemy based on array list sizez
					whichEnemy = random.nextInt( enemies.size() );
					// support reports which class 
					supports.get( 1 ).communicate();
					System.out.println( "The enemies support: " + supports.get( 1 ).getName() + " is up!" );
					// which enemy is going to get healed by the support
					System.out.println ( enemies.get( whichEnemy ).getName() + " is getting healed by: " + supports.get( 1 ).getName() + ".\n");
					support = enemies.get( whichEnemy ).support();
					support = enemies.get( whichEnemy ).getHealth() + support;
					// set health for the enemy
					enemies.get( whichEnemy ).setHealth( support );
				}// end of healing enemies
				
				// roll 5, commander attacks for more damage.
				else if ( turn == 5 )
				{
					// choose which commander
					whichCommander = random.nextInt( 2 );
					// choose which soldier based on soldier array list size
					whichSoldier = random.nextInt( soldiers.size() );
					// choose which enemy basd on the size of the array list
					whichEnemy = random.nextInt( enemies.size() );
					
					// specifies the enemies commander is attacking
					if ( whichCommander == 1 )
					{
						// Tells the class that is fighting, sets the amount of damage
						uniqueFighter.get( whichCommander ).communicate();
						attack = uniqueFighter.get( whichCommander ).fight();
						
						// if attack is 15, a commander hit
						if ( attack == 15 )
						{
							// hits thes commander, hit is total damage, set health to the total damage
							System.out.println( uniqueFighter.get( 0 ).getName() + " has been struck by the commander!" );
							hit = uniqueFighter.get( 0 ).getHealth() - attack;
							uniqueFighter.get( 0 ).setHealth( hit );
							
							// if the commander dies health less than or equal to 0
							if ( uniqueFighter.get( 0 ).getHealth() <= 0 )
							{
								System.out.println ( uniqueFighter.get( 0 ).getName() + " has no health left!\n" );
								// adds death of the commander and removes from game
								deaths.add( new Deaths ( uniqueFighter.get( 0 ).getName(), uniqueFighter.get( 0 ).getScore() ) );
								uniqueFighter.remove( 0 );
								//winner is set to 1, and breaks out of the loop
								winner = 1;
								break;
							}// end of if unique fighter dies.
							else
								// commander has not sustained full damage and is able to move forward
								System.out.println( uniqueFighter.get( 0 ).getName() + " has " + uniqueFighter.get( 0 ).getHealth() + " health left.\n" );
						}
						// must add content for the commander to hit enemies.
						else
						{
							// commander hits the enemy, tracks how much damage is done
							hit = enemies.get( whichEnemy ).getHealth() - attack;	
							// sets the health of the soldier
							soldiers.get( whichSoldier ).setHealth( hit );
							// if minions health drops below 0, dies, adds to death array
							if ( soldiers.get( whichSoldier ).getHealth() <= 0 )
							{
								// removes the soldier and adds to death list
								System.out.println( "Soldier " + soldiers.get( whichSoldier ).getName() + " has been eliminated." );
								deaths.add( new Deaths ( soldiers.get( whichSoldier ).getName(), soldiers.get( whichSoldier).getScore() ) );
								soldiers.remove( whichSoldier );
							}
							// if soldiers doesn't take critical damage
							else
								System.out.println ( soldiers.get( whichSoldier ).getName() + " has " + soldiers.get( whichSoldier).getHealth() + " health left!\n");
						}
					}
					// if the unique fighter roll is for 0, soldier gets to fight
					else 
					{
						// commander is fighting, attack damage is calculated
						uniqueFighter.get( whichCommander ).communicate();
						attack = uniqueFighter.get( whichCommander ).fight();
						// if attack is 15, attack the other commander
						if ( attack == 15 )
						{
							// hits the comamnder
							System.out.println( uniqueFighter.get( 1 ).getName() + " has been struck by the commander!" );
							hit = uniqueFighter.get( 1 ).getHealth() - attack;
							uniqueFighter.get( 1 ).setHealth( hit );
							// if the commander dies. Health less than or equal to 0. remove and add to death array
							if ( uniqueFighter.get( 1 ).getHealth() <= 0 )
							{
								System.out.println ( uniqueFighter.get( 1 ).getName() + " has no health left!\n" );
								// add the commander to the death list and remove from the fighter list
								deaths.add( new Deaths ( uniqueFighter.get( 1 ).getName(), uniqueFighter.get(1).getScore() ) );
								uniqueFighter.remove( 1 );
								// winner is set to 0 and breaks out of loop
								winner = 0;
								break;
							}
							// if commander surrvived attack, states health
							else
								System.out.println( uniqueFighter.get( 1 ).getName() + " has " + uniqueFighter.get( 1 ).getHealth() + " health left.\n" );
						}// end of commander attack commander
						// commander attacks the minions
						else
						{
							// enemy minion is chosen and damage is calculated
							hit = enemies.get( whichEnemy ).getHealth() - attack;	
							enemies.get( whichEnemy ).setHealth( hit );
						
							// if the health pool of a particular enemy is 0, remove them and add them to the death list. 
							if ( enemies.get ( whichEnemy ).getHealth() <= 0 )
							{
								System.out.println( "Enemy " + enemies.get ( whichEnemy ).getName() + " has been eliminated." );
								deaths.add( new Deaths ( enemies.get( whichEnemy ).getName(), enemies.get( whichEnemy ).getScore() ) );
								enemies.remove ( enemies.get( whichEnemy) );
							}
							// if enemy survives the attack
							else
								System.out.println ( enemies.get( whichEnemy ).getName() + " has " + enemies.get( whichEnemy).getHealth() + " health left!\n");
						}// end of command attack minions
					}// end of the commander attack
				}// end of roll ==5
				
				//roll 6 or 7 enemy attacks
				else 
				{
					// enemy, soldier is selected
					whichEnemy = random.nextInt( enemies.size() );
					whichSoldier = random.nextInt( soldiers.size() );
					// states the enemy
					enemies.get( whichEnemy ).communicate();
					attack = enemies.get( whichEnemy ).fight();	
					// if attack is equal to 15, attacks soldier commander
					if ( attack == 15 )
					{
						// soldier commander is selected (0) and is calculated how much health is left
						System.out.println( uniqueFighter.get( 0 ).getName() + " has been struck!" );
						hit = uniqueFighter.get( 0 ).getHealth() - attack;
						uniqueFighter.get( 0 ).setHealth( hit );
						// if commanders health is 0, removes and adds to death
						if ( uniqueFighter.get( 0 ).getHealth() <= 0 )
						{
							System.out.println ( uniqueFighter.get( 0 ).getName() + " has no health left!\n" );
							// how to add to a death list after the fighter has died?\
							deaths.add( new Deaths ( uniqueFighter.get( 0 ).getName(), uniqueFighter.get( 0 ).getScore() ) );
							uniqueFighter.remove( 0 );
							// winner changed to 1, and breaks out of loop
							winner = 1;
							break;
						}
						// if the commander survives strike
						else
							System.out.println( uniqueFighter.get( 0 ).getName() + " has " + uniqueFighter.get( 0 ).getHealth() + " health left.\n" );
					}
					// else the attack is not 15, attack soldiers minions
					else
					{
						// hit is calculated for the soldier 
						hit = soldiers.get( whichSoldier ).getHealth() - attack;	
						soldiers.get( whichSoldier ).setHealth( hit );
						// if minions health drops below 0, dies, adds to death array
						if ( soldiers.get( whichSoldier ).getHealth() <= 0 )
						{
							// removes from soldiers list and adds to the death list
							System.out.println( "Soldier " + soldiers.get( whichSoldier ).getName() + " has been eliminated." );
							deaths.add( new Deaths ( soldiers.get( whichSoldier ).getName(), soldiers.get( whichSoldier ).getScore() ) );
							soldiers.remove( whichSoldier );
						}
						// if soldiers minion doesn't die
						else
							System.out.println ( soldiers.get( whichSoldier ).getName() + " has " + soldiers.get( whichSoldier).getHealth() + " health left!\n");
					}// end of else statement for attack
				}// end of else for enemies attack
			}// end while
		}catch ( ArrayIndexOutOfBoundsException error ) // used to catch index out of bounds (apart of assignment)
		{
			System.out.println( error );
		}
				
		// if winner is 0 enemy has won, else soldiers won.
		if ( winner == 0 )
			System.out.println( "It took: " + total + " turns to defeat the enemy." );
		else
			System.out.println( "It took: " + total + " turns to be defeated by the enemy." );
		

		/**
		 * sorting the scores at the end, initally based on score value, if same, based on name.
		 * Arrays sorting
		 */
		Collections.sort( soldiers, Soldier.SoldierScoreComparator );
		Collections.sort( enemies, Enemy.EnemyScoreComparator );
		Collections.sort( uniqueFighter, UniqueFighter.UniqueFighterScoreComparator);
		Collections.sort( deaths, Deaths.DeathsScoreComparator );


		Collections.sort( soldiers );
		// prints out the Efficiency of the soldiers in descending order
		index = 0;
		// if soldiers size is less than or equal to 0, no soldiers in this list
		if ( soldiers.size() <= 0 )
			System.out.println ( "\nAll soldiers have died." );
		else
			System.out.println ( "\nSoldiers in order: " );
		// enchanced for loop to loop through the soldiers and calculate the efficiency
		for ( Soldier sol : soldiers )
		{
			System.out.println( "Rank: " + ++index + " - Name: " + sol.getName() + 
								" - Total Efficiency: " + sol.getScore() + 
								" - Health Remaining: " + sol.getHealth() );
		}
		
		// prints to the efficiencies of the enemies in descending order
		index = 0;
		// if enemies size is 0, there are none left.
		if( enemies.size() <= 0 )
			System.out.println( "\nAll enemies have died." );
		else
			System.out.println ( "\nEnemies in order: " );
		// use enhanced for loop to loop through the enemies and calulcate efficiencies
		for ( Enemy enemy : enemies )
		{
			System.out.println( "Rank: " + ++index + " - Name: " + enemy.getName() + 
								" - Total Efficiency: " + enemy.getScore() + 
								" - Health Remaining: " + enemy.getHealth() );
		}
		
		// prints out the unique fighters Efficiency in descending order
		index = 0;
		System.out.println ( "\nCommanders in order: " );
		// uses enhanced for loop to loop through the fighters and calculate efficiencies
		for ( UniqueFighter uniqueF : uniqueFighter )
		{
			System.out.println( "Rank: " + ++index + " - Name: " + uniqueF.getName() + 
								" - Total Efficiency: " + uniqueF.getScore() + 
								" - Health Remaining: " + uniqueF.getHealth() );
		}
		// index set to 0, tracks the amount of dead players
		index = 0;
		System.out.println( "\nDead Players:");
		// enchanced for loop to calculate the efficiecies of the dead players
		for ( Deaths deadPlayers : deaths )
		{
			System.out.println( "Rank: " + ++index + " - Name: " + deadPlayers.getName() + 
								" - Total Efficiency: " + deadPlayers.getScore() + 
								" - Status: Dead" );
		}
	}// end of main
}// end of game class
