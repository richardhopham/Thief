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
 * This class represents the rooms of the game. The room will each take one spot on 
 * the board, while one of the will have the briefcase which is the goal of the player to
 * obtain.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public class Room extends InGameObject {
	
	/**
	 * This field represents whether there is a briefcase in the room.
	 */
	
	private boolean hasBriefcase;
	
	/**
	 * The constructor for the Room class. The constructor will take in a specific row 
	 * position and a column position. The constructor will then call the InGameObject constructor
	 * to set the room position. And it will also make it so the room has no briefcase in it.
	 * 
	 * @param row the row position desired for the room.
	 * @param column the column position desired for the room.
	 */
	
	public Room(int row, int column) {
		super(row,column);
		hasBriefcase = false;
	}
	
	/**
	 * This method will set it so that there is a briefcase in the room.
	 */
	
	public void setBriefcase() {
		hasBriefcase = true;
	}
	
	/**
	 * This method returns whether or not the briefcase is in the room.
	 * @return if there is a briefcase in the room or not.
	 */
	public boolean checkBriefcase(){
		return hasBriefcase;
	}
	
}
