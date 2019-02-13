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
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public class Radar extends InGameObject {
	
	/**
	 * This field represents the switch of radar powerup. It will be initially set to be false or turned off.
	 */
	
	private boolean turnOnRadar;
	
	/**
	 * The default constructor for the radar class. The constructor will call the InGameObject constructor and set 
	 * the position to be outside the board, with it being turned off.
	 */
	
	public Radar() {
		super(-1,-1);
		turnOnRadar = false;
	}
	
	/**
	 * This constructor will take in a row and column position, and will use those in the InGameObject constructor t
	 * set it to a desired position on the board.
	 * 
	 * @param row the desired row position.
	 * @param column the desired column position.
	 */
	
	public Radar(int row,int column){
		super(row, column);
	}
	
	/**
	 * This method will set the radar to be turned on (true).
	 */
	
	public void setRadar() {
		turnOnRadar = true;
	}
	
	/**
	 * This method returns whether or not the radar is turned on or off.
	 * @return whether the radar is currently turned on or not.
	 */
	
	public boolean getRadar() {
		return turnOnRadar;
	}
}