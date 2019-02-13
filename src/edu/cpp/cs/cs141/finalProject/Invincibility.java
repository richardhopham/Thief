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
 * This class is for the Invincibility power up in the game. The invincibility object will keep the player
 * from being killed by the enemy spies. The invincibility object will first be set outside of the board, with a count
 * of 0, and set to be turned off.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public class Invincibility extends InGameObject {
	
	/**
	 * This field represents the count for the Invincibility object. It will initially be set to 0.
	 */
	
	private int count;
	
	/**
	 * This field represents the turning on of the invincibility.
	 * True will turn on the invincibility and false will turn it off.
	 */
	
	private boolean turnOnInvinc;

	/**
	 * The default constructor for the Invincibility class. It will take from
	 * the parent class InGameObject. The constructor will set the invincibility to have coordinates 
	 * outside the grid, and will set it to be turned off.
	 */
	
	public Invincibility() {
		super(-1,-1);
		count = 0;
		turnOnInvinc = false;
	}
	
	/**
	 * This constructor will take in a row and a column, in order to set the invincibility onto
	 * a specified part of the board.
	 * @param row the row position desired for invincibility.
	 * @param column the column position desired for invincibility.
	 */
	
	public Invincibility(int row,int column){
		super(row, column);
	}
	
	/**
	 * @return the current count.
	 */
	
	public int getCount() {
		return count;
	}
	
	/**
	 * @return the current status of invincibility.
	 */
	
	public boolean getInvincStatus() {
		return turnOnInvinc;
	}
	
	/**
	 * This will set the invincibility to be either on or off, based on the parameter
	 * @param invinc the status of invincibility.
	 */
	
	public void setInvinc(boolean invinc) {
		turnOnInvinc = invinc;
	}
	
	/**
	 * This method will set the count to the desired value.
	 * @param count the desired integer to set the count to.
	 */
	
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * This method will set the current count to be subtracted by one.
	 */
	
	public void decreaseCount() {
		count--;
	}
}
