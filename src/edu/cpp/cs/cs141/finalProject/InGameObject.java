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

import java.io.Serializable;

/**
 * This abstract class represents the objects in the game that are on the Grid. Items which will be 
 * on the board will inherit this class. All in-game objects will have a position for both row and column
 * and a boolean which states whether the item is still in-play or not. Additionally, this class implements
 * Serializable.
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public abstract class InGameObject implements Serializable {
    
    /**
     * This field represents the row of the position the object is in.
     */
	private int positionRow;
	
	/**
	 * This field represents the column of the position the object is in.
	 */
	private int positionColumn;
	
	/**
	 * This field represents whether or not the object is still on the board or not.
	 * If true, then the item is still on the board. If false, then the item has been used
	 * or is off the board.
	 */
	private boolean onBoard;
	
	/**
	 * This constructor will set the row and column position of the in-game object according to
	 * what is passed into the parameter. Additionally, the onBoard boolean is set to true since
	 * in-game object is created.
	 * @param row the row of the position of the object
	 * @param column the column of the position of the object
	 */
	public InGameObject(int row,int columm){
		this.positionRow = row;
		this.positionColumn = columm;
		onBoard = true;
	}
	
	/**
	 * This method returns the row of the position of the in-game object.
	 * @return the row of the position of the in-game object.
	 */
	public int getPositionRow() {
		return this.positionRow;
	}
	
	/**
	 * This method returns the column of the position of the in-game object.
	 * @return the column of the position of the in-game object.
	 */
	public int getPositionColumn() {
		return this.positionColumn;
	}
	
	/**
	 * This method sets the row position of the in-game object to whatever is 
	 * passed into the parameter.
	 * @param row the new row of the position of the object
	 */
	public void setPositionRow(int row){
		this.positionRow = row;
	}
	
	/**
	 * This method sets the column position of the in-game object to whatever is
	 * passed into the parameter.
	 * @param column the new column of the position of the object
	 */
	public void setPositionColumn(int column) {
		this.positionColumn = column;
	}
	
	/**
	 * This method returns whether or not the in-game object is on the board or not.
	 * If true, then the object is on the board. If false, then the object is not on the
	 * board.
	 * @return whether or not the object is on the board.
	 */
	public boolean isOnBoard() {
		return onBoard;
	}
	
	/**
	 * This method is called when the player moves in the position of the in-game object, such
	 * as a power-up. This method will then turn the onBoard boolean to false and set the positions
	 * of the object to positions that are off the grid (-1,-1).
	 */
	public void use() {
		onBoard = false;
		setPositionColumn(-1);
		setPositionRow(-1);
	}
}
