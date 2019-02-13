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
 * The Goggles class extends the InGameObject class. The Goggles class represents the Night Vision Goggles within
 * the game. The Goggles class receives two positions, which represents where the player decides to look when
 * using the goggles. 
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */

public class Goggle extends InGameObject {
    
    /**
     * This field represents the column of the second position the night vision goggles allows the player to look in.
     */
	private int secondColumn;
	
	/**
	 * This field represents the row of the second position the night vision goggles allows the player to look in.
	 */
	private int secondRow;
	
	/**
	 * This field represents whether or not the night vision goggles are turned on or off.
	 */
	private boolean isOn;
	
	/**
	 * The default constructor for the goggle class. The constructor will use the InGameObject constructor to set the
	 * position of the goggles, and then will set the goggles to be turned on.
	 */
	
	public Goggle(){
		super(-1,-1);
		isOn = true;
	}
	
	/**
	 * Set the goggle to be turned on.
	 */
	
	public void goggleOn(){
		isOn = true;
	}
	
	/**
	 * Set the goggle to be turned off.
	 */
	
	public void goggleOff(){
		isOn = false;
	}
	
	/**
	 * Returns if the goggle is on or not.
	 * 
	 * @return boolean value of isOn.
	 */
	
	public boolean getGoggle() {
		return isOn;
	}
	
	/**
	 * This method will set the positions where the goggle makes it so it is visable.
	 * 
	 * @param firstRow the position row of the first square seen by the goggle.
	 * @param firstColumn the position column of the first square seen by the goggle.
	 * @param secondRow the position row of the second square seen by the goggle.
	 * @param secondColumn the position column of the second square seen by the goggle.
	 */
	
	public void setGogglePosition(int firstRow, int firstColumn, int secondRow, int secondColumn){
		super.setPositionColumn(firstColumn);
		super.setPositionRow(firstRow);
		this.secondColumn = secondColumn;
		this.secondRow = secondRow;
	}
	
	/**
	 * This method will return the row position of first square seen by goggle.
	 *  
	 * @return the row position of the object.
	 */
	
	public int getGoggleFirstRow(){
		return super.getPositionRow();
	}

	/**
	 * This method will return the column position of first square seen by goggle.
	 * 
	 * @return the column position of the object.
	 */
	
	public int getGoggleFirstColumn() {
		return super.getPositionColumn();
	}
	
	/**
	 * This method will return the row position of second square seen by goggle.
	 * 
	 * @return the row position of the object.
	 */
	
	public int getGoggleSecondRow() {
		return secondRow;
	}
	
	/**
	 * This method will return the column position of second square seen by goggle.
	 * 
	 * @return the column position of the object.
	 */
	
	public int getGoggleSecondColumn() {
		return secondColumn;
	}
}
