/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�ez
 *
 * Final Project
 *
 * Text based dungeon esque game.
 *
 * Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 */
package edu.cpp.cs.cs141.finalProject;

/**
 * This class represents the enemy in the game. This class extends the Character class and will be given 
 * a position on the grid and a status of being alive or not.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */

public class Enemy extends Character {
	
	/**
	 * The default constructor for the Enemy class. It will use the Character constructor to set the position
	 * and to also set the enemy to being alive. 
	 */
	
	public Enemy() {
		super(-1,-1);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This constructor takes in a position for the row and column in the grid. It will go back to using 
	 * the constructor from the Character class.
	 * 
	 * @param row
	 * @param column
	 */
	
	public Enemy(int row,int column){
		super(row,column);
	}
	
}
