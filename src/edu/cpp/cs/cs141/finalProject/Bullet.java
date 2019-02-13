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
 * This class represents the Bullet object in the game. The Bullet object will have a position and also a value
 * that determines how much ammo the player will receive if the player moves in the position of the Bullet object 
 * on the grid. The bullet's main use is to replenish the ammo of the player's gun.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */

public class Bullet extends InGameObject {
	
	/**
	 * This field represents the count or amount of ammo that the Bullet object has.
	 */
	private int ammo;
	
	/**
	 * The default constructor for the Bullet class. The Bullet class will default to 
	 * having 0 as its ammo count , and then the position is set to be out of bounds (out of the game grid).
	 */
	public Bullet() {
		super(-1,-1);
		ammo = 0;
	}
	
	/**
	 * This constructor takes in two int values for position of the bullet, and then the ammo count that the bullet
	 * will give if the player moves in the position of the bullet.
	 * 
	 * @param x the row position of the bullet in the grid.
	 * @param y the column position of the bullet in the grid.
	 * @param ammo the amount of ammo wanting to set the bullet object to.
	 */
	
	public Bullet(int x,int y, int ammo){
		super(x, y);
		this.ammo = ammo;
	}
	
	/**
	 * This method will return the current amount of ammo count that the Bullet object will give.
	 * 
	 * @return the amount of ammo of the bullet.
	 */
	
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * This method will set the ammo to whatever the user input is.
	 * @param ammo the amount of ammo that one wants the Bullet object to give.
	 */
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
}
