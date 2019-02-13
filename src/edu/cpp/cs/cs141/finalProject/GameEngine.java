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

import java.io.*;

/**
 * The Game Engine class will be used to keep track of the status of the game, whether it has 
 *  been completed or not. It also provides the medium between the Grid and the UI.
 *  
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *	
 */
public class GameEngine {
	
	/**
     * This field represents the save file of the game. This is what will be used when the user wants to
     * save at a certain point of the game. 
     */
	private final String SAVE_FILE = "gamesave.bin";
	
	/**
	 * This field represents the board. The board will initially be set to null.
	 */
	private Grid board = null;
	
	/**
	 * This field represents whether the game has been completed. 
	 */
	private boolean gameDone = false;
	
	
	/**
	 * This method will draw the board out so that the user will be able to see it, depending 
	 * on whether the parameter is true or false.
	 * 
	 * @param debugMode determines whether the board is drawn or not.
	 * 
	 */
	public String drawBoard(boolean debugMode){
		return board.toString(debugMode);
	}

    /**
     * This method returns whether or not the debug mode is turned on or off.
     * @return whether or not the debug mode is on
     */
    public boolean getDebugMode() {
        return board.getDebugMode();
    }

    /**
     * Save the game.
     * @return A status string.
     */
    public String saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(board);
            
            oos.close();
            fos.close();
        }
        catch(Exception e) {
            /* could not save the game, regardless of the exception type */
            return "Error: could not save the game.";
        }
        
        return "The game was saved.";
    }
    
    /**
     * Load some game save data.
     * @return A status string.
     */
    public String loadGame() {
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            board = (Grid)ois.readObject();
       
            ois.close();
            fis.close();
        }
        catch(FileNotFoundException e) {
            /* the user hasn't saved the game yet */
            return "There is no game save file to load.";
        }
        catch(Exception e) {
            return "Error: could not load the game.";
        }
        
        return "Loaded the game.";
    }
    
	
	/**
	 * This method will return whether the game has been finished or not.
	 * @return whether the game is complete.
	 */
	public boolean gameOver(){
		return gameDone;
	}
	
	/**
	 * This method calls the method in the Grid to move the player in the 'up' direction.
	 * Additionally, it returns the integer value corresponding to what the player encounters.
	 * @return integer corresponding to what the player encounters or doesn't encounter
	 */
	public int movePlayerUp() {
		return board.movePlayerUp();
	}
	
	/**
	 * This method calls the method in the Grid to move the player in the 'down' direction.
	 * Additionally, it returns the integer value corresponding to what the player encounters.
	 * If the method returns '6' as an integer value, then the player has found the briefcase.
	 * @return integer corresponding to what the player encounters or doesn't encounter
	 */
	public int movePlayerDown() {
		int result = board.movePlayerDown();
		if(result == 6)
			gameDone = true;
		return result;
	}
	
	/**
	 * This method calls the method in the Grid to move the player in the 'left' direction.
	 * Additionally, it returns the integer value corresponding to what the player encounters.
	 * @return integer corresponding to what the player encounters or doesn't encounter
	 */
	public int movePlayerLeft() {
		return board.movePlayerLeft();
	}
	
	/**
	 * This method calls the method in the Grid to move the player in the 'right' direction.
	 * Additionally, it returns the integer value corresponding to what the player encounters.
	 * @return integer corresponding to what the player encounters or doesn't encounter
	 */
	public int movePlayerRight() {
		return board.movePlayerRight();
	}
	
	/**
	 * This method calls the method in the Grid to move the ninjas. Additionally, it returns
	 * a boolean value that returns true if the player has been killed due to the
	 * ninja's movement.
	 * @return whether or not the player has been killed
	 */
	public boolean moveEnemies() {
		return board.moveNinjas();
	}
	
	/**
	 * This method calls the method in the Grid to shoot in the 'up' direction. Additionally,
	 * it returns a boolean value that returns true if the player has killed a ninja.
	 * @return whether or not a player has killed a ninja.
	 */
	public boolean shootUp() {
		return board.shootUp();
	}
	
	/**
	 * This method calls the method in the Grid to shoot in the 'down' direction. Additionally,
	 * it returns a boolean value that returns true if the player has killed a ninja.
	 * @return whether or not a player has killed a ninja.
	 */
	public boolean shootDown() {
		return board.shootDown();
	}
	
	/**
	 * This method calls the method in the Grid to shoot in the 'left' direction. Additionally,
	 * it returns a boolean value that returns true if the player has killed a ninja.
	 * @return whether or not a player has killed a ninja.
	 */
	public boolean shootLeft() {
		return board.shootLeft();
	}
	
	/**
	 * This method calls the method in the Grid to shoot in the 'right' direction. Additionally,
	 * it returns a boolean value that returns true if the player has killed a ninja.
	 * @return whether or not a player has killed a ninja.
	 */
	public boolean shootRight() {
		return board.shootRight();
	}
	
	/**
	 * This method calls the method in the Grid to set the amount of ammo in the player's gun
	 * to the integer value that is passed into the parameter.
	 * @param bullet the amount of ammo in the player's gun
	 */
	public void setAmmo(int bullet) {
		board.setAmmo(bullet);
	}
	
	/**
	 * This method calls the method in the Grid that returns the amount of turns the
	 * player is invincible for.
	 * @return amount of turns the player is invincible for
	 */
	public int getInvincTurns() {
		return board.getInvincTurns();
	}
	
	/**
	 * This method calls the method in the Grid that checks if the player is still
	 * invincible or not.
	 */
	public void checkInvincTurns() {
		board.checkInvincTurns();
	}
	
	/**
	 * This method calls the method in the Grid to decrease the amount of turns the
	 * player has been invincible for.
	 */
	public void decreaseInvinc() {
		board.decreaseInvinc();
	}
	
	/**
	 * This method receives and set the positions the player has used the night vision goggles
	 * to look in.
	 * @param firstRow the row of the first position the player looked in
	 * @param firstColumn the column of the first position the player looked in
	 * @param secondRow the row of the second position the player looked in
	 * @param secondColumn the column of the second position the player looked in
	 */
	public void setGogglePosition(int firstRow,int firstColumn, int secondRow, int secondColumn){
		board.setGogglePosition(firstRow, firstColumn, secondRow, secondColumn);
	}
	
	/**
	 * This method returns whether or not the night vision goggles are on or off. If this
	 * method returns true, then the night vision goggles are on.
	 * @return whether or not the night vision goggles are on or off
	 */
	public boolean getGoggle(){
		return board.getGoggle();
	}
	
	/**
	 * This method calls the method in the Grid to turn on the night vision goggles.
	 */
	public void goggleOn(){
		board.goggleOn();
	}
	
	/**
	 * This method calls the method in the Grid to turn off the night vision goggles.
	 */
	public void goggleOff(){
		board.goggleOff();
	}
	
	/**
	 * This method calls the method in the Grid that returns the amount of ammo
	 * in the player's gun
	 * @return the amount of ammo in the player's gun
	 */
	public int getAmmo() {
		return board.getAmmo();
	}
	
	/**
	 * This method calls the method in the Grid that returns the player's position's column.
	 * @return the column of the player's position
	 */
	public int getPlayerPositionColumn(){
		return board.getPlayerPositionColumn();
	}
	
	/**
	 * This method calls the method in the Grid that returns the player's position's row.
	 * @return the row of the player's position
	 */
	public int getPlayerPositionRow(){
		return board.getPlayerPositionRow();
	}
	
	/**
	 * This method calls the method in the Grid that checks if the player still
	 * has lives in the game. If the player has zero lives, then the game ends.
	 * @return the amount of lives the player has
	 */
	public int checkPlayerLives() {
		if(board.getPlayerLives() <= 0) {
			gameDone = true;
		}
		return board.getPlayerLives();
		
	}
	
	/**
	 * This method returns the amount of lives the player has.
	 * @return the amount of lives the player has
	 */
	public int getPlayerLives() {
		return board.getPlayerLives();
	}
	
	/**
	 * This method resets the game.
	 */
	public void reset() {
		gameDone = false;
		board = new Grid();
	}
}
