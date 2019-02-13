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
 * This class represents the gun the Player uses in the game. This class is also serializable.
 * Initially, the Player starts with one bullet in his or her gun. When he or she shoots the gun, the gun 
 * loses its bullet. The only way the Player can receive another bullet is by picking up the Bullet power-up.
 * 
 * @author Richard Pham, Oakar Kyaw, Ethan Liao, Jeremy Anunwah, Bennett Wong.
 *
 */
public class Gun implements Serializable {
    
    /**
     * This field represents the amount of ammo in the Player's gun.
     */
	private int ammo;
	
	/**
	 * This constructor puts one ammo into the player's gun once the Gun class is initialized.
	 */
	public Gun() {
		ammo = 1;
		// TODO Auto-generated constructor stub
	}

    /**
     * This method sets the amount of ammo the player's gun has to whatever is passed into the parameter
     * @param bullet the amount of ammo the player's gun is set to
     */
	public void setAmmo(int bullet) {
		ammo = bullet;
	}
	
	/**
	 * This method returns the amount of ammo the player's gun has.
	 * @return the amount ammo in the player's gun
	 */
	public int getAmmo() {
		return ammo;
	}
}
