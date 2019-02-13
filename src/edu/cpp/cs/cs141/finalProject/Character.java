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
 * This class represents a character in the game. This class will implement the Serializable class. 
 * The Character object will be given a row position, column position, and a status of whether it is alive or not.
 * There are two types of characters that are in the game: the player and the enemies.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public abstract class Character implements Serializable {
	
	/**
	 * This field represents the row position in the grid for the Character.
	 */
	private int positionRow;
	
	/**
	 * This field represents the column position in the grid for the Character.
	 */
	private int positionColumn;
	
	/**
	 * This field determines whether the Character is alive or not. True would make the 
	 * Character alive, while false would mean they are not alive.
	 */
	private boolean alive;
	
	/**
	 * The constructor for the Character class. The constructor will take in a row and column, to be able to place
	 * the character onto the grid. The constructor also sets the person to be alive, meaning the character is on the 
	 * grid.
	 * 
	 * @param row the position of the Character on the grid, in terms of which row.
	 * @param column the position of the Character on the grid, in terms of which column.
	 */
	public Character(int row,int column){
		this.positionRow = row;
		this.positionColumn = column;
		alive = true;
	}
	
	/**
	 * This method will return the current row position of the Character object.
	 * 
	 * @return the current row position of the Character object.
	 */
	public int getPositionRow() {
		return this.positionRow;
	}
	
	/**
	 * This method will return the current column position of the Character object.
	 * 
	 * @return the current column position of the Character object.
	 */
	
	public int getPositionColumn() {
		return this.positionColumn;
	}
	
	/**
	 * This method will be used to set the row position of the Character to what is 
	 * put for the parameter.
	 * 
	 * @param row the row position that is to be given to the Character object.
	 */
	
	public void setPositionRow(int row){
		this.positionRow = row;
	}
	
	/**
	 * This method will be used to set the column position of the Character to what is
	 * put for the parameter.
	 * 
	 * @param column the column position that is to be given to the Character object.
	 */
	
	public void setPositionColumn(int column) {
		this.positionColumn = column;
	}
	
	/**
	 * This method will be used to return whether the Character is alive or not. 
	 * 
	 * @return whether the Character is alive or not.
	 */
	
	public boolean isAlive() {
		return alive;
	}

	/**
	 * This method will move the Character up one space in the grid.
	 */
	public void moveUp() {
		this.positionRow--;
	}
	
	/**
	 * This method will move the Character down one space in the grid.
	 */
	public void moveDown() {
		this.positionRow++;
	}
	
	/**
	 * This method will move the Character to the right one space in the grid.
	 */
	
	public void moveRight() {
		this.positionColumn++;
	}
	
	/**
	 * This method will move the Character to the left one space in the grid.
	 */
	
	public void moveLeft() {
		this.positionColumn--;
	}
	
	/**
	 * This method is called when the Character dies, specifically the ninja.
	 * The Character is moved off the grid and is the boolean alive is set to false.
	 */
	public void die() {
		setPositionColumn(-1);
		setPositionRow(-1);
		alive = false;
	}
}
