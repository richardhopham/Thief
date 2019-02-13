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
 * This class represents the Player of the game. Additionally, this class extends the Character class.
 * The player will initially start out in the bottom left of the board, with a gun which has one bullet. 
 * The player will also be given 3 lives to start out.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public class Player extends Character {
    
    /**
	 * This field represents the player's gun. The player's gun will initially have one bullet.
	 */
	private Gun playerGun;
	
	/**
	 * This field represents the amount of lives the player has. Once the player's lives runs to zero,
	 * the game will be over.
	 */
	private int lives;

	
	/**
	 * This constructor will take in a desired row position and a column position. With these two
	 * parameters, it will set the player's position to the parameters.
	 * 
	 * @param row the desired row position of the player.
	 * @param column the desired column position of the player.
	 */
	public Player(int row,int column) {
		// TODO Auto-generated constructor stub
		super(row,column);
		lives = 3;
		playerGun = new Gun();
	}

	/**
	 * This method will set the lives to the parameter.
	 * @param lives the desired amount of lives to set the lives of the player to.
	 */
	public void setLives(int lives){
		this.lives = lives;
	}
	
	/**
	 * This method returns the amount of lives the player has.
	 * @return amount of lives the player has
	 */
	public int getLives(){
		return lives;
	}
	
	/**
	 * This method is called when the player decides to use his or her gun to shoot.
	 * If the player has ammo in his or her gun, then then the method will return true.
	 * Otherwise, the method will return false.
	 * @return whetehr or not the player successfully shot his or her gun
	 */
	public boolean shoot() {
		if(playerGun.getAmmo() != 0) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * This method returns the amount ammo in the player's gun.
	 * @return the amount of ammo in the player's gun.
	 */
	public int getAmmo() {
		return playerGun.getAmmo();
	}
	
	/**
	 * This method sets the amount of ammo in the player's gun to whatever is passed into the parameter.
	 * @param bullet the amount of ammo to set the player's gun to
	 */
	public void setAmmo(int bullet) {
		playerGun.setAmmo(bullet);
	}
	
	/**
	 * This method will set the position of the player to be back to the original position.
	 */
	public void resetPosition() {
		setPositionRow(8);
		setPositionColumn(0);
		
	}
}
