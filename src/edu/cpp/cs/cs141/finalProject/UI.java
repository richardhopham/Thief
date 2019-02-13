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

import java.util.Scanner;

public class UI {
	
    /**
    * This object is medium between UI and Grid
    */
	private GameEngine game = null;
	
    /**
    * the boolean variable to on and off for the debug mode
    * if debug mode is on the output will show all the enemies, powerups,rooms and
    * where the briefcase is located.
    * if the debug mode is off the output will only display only rooms and the player.
    */
	private boolean debugMode;
	
	/**
    *need scanner object for accepting input from the user's insturctions
    */
	private Scanner keyboard = new Scanner(System.in);
	
    /**
    * assign the values the user enter in this object.
    */
	private String option;
	
    /**
    * this boolean will say if the game finish or not
    */	
	private boolean quitGame=false;
	
	/**
    * this is the default constructor with parameter game type GameEngine
    * @param game will assign to this.game
    */
	public UI (GameEngine game) {
		this.game = game;
	}
	
	
    /**
    * This method is the main loop for the game 
    * This method will ask the user to choose 3 options which are 
    * New Game , Load Game and Leave game. 
    * According to the user option different methods are called.
    */
	public void startGame(){
		welcomeMessage();
		while(!quitGame){
			System.out.println("1.New Game");
			System.out.println("2.Load Game");
			System.out.println("3.Leave Game");
			System.out.print("Choose Option: ");
			option = keyboard.nextLine();
			switch(option){
			case"1":
				game.reset();
				debugMode = false;
				gameLoop();
				break;
			case"2":
                System.out.println(game.loadGame());
                this.debugMode = game.getDebugMode();
                gameLoop();
				break;
			case"3":
				quitGame = true;
				break;
			default:
					System.out.println("Invalid Input!");
					break;
				
			}
		}
		System.out.println("End of the game!");
	}
	
    /**
    * this method is called when the user choose the option new game.
    * this method handles almost all the interaction between user and the program.
    * In this method the player has option to 
    * 1. Move(take 1 turn).
    * 2. Shoot any direction(take 1 turn).
    * 3. Use Goggle any direction before move or shoot.
    * 4. Saving the game.
    * 5. Quit the game.
    */
	public void gameLoop(){
		while(! ( game.gameOver() || quitGame ) ){
			int move;
			System.out.println(game.drawBoard(debugMode));
			System.out.println("Lives: "+game.getPlayerLives());
			System.out.println("Bullets: "+game.getAmmo());
			System.out.println("Debug Mode(on/off) = t");
			System.out.println("Move Up = w\tMove Down = s\tMove Left = a\tMove Right = d");
			System.out.println("Shoot = e \tUse night vision goggles = g");
			System.out.println("Save game = v");
			System.out.println("Quit game: q.");
			if(game.getInvincTurns() != 0)
				System.out.println("You are invincible for "+game.getInvincTurns()+" turn(s).");
			System.out.print("Enter Option: ");
			option=keyboard.nextLine().toLowerCase();
			switch(option){
			case "t":
				if(debugMode==true){
					debugMode=false;
				}else if(debugMode==false){
					debugMode=true;
				}
				break;
			case "g":
				if(game.getGoggle()){
					System.out.println("Select the direction you want to look in: ");
					System.out.println("Look up = w\tLook Down = s\tLook Left = a\tLook Right = d");
					System.out.print("Enter Option : ");
					option = keyboard.nextLine().toLowerCase();
					switch(option){
					case "w":
					case "a":
					case "s":
					case "d":
						if( (option.equals("w")) && (game.getPlayerPositionRow()-2 >= -1) ){
							game.setGogglePosition(game.getPlayerPositionRow()-1, game.getPlayerPositionColumn(), game.getPlayerPositionRow()-2, game.getPlayerPositionColumn());
							game.goggleOff();
						}else if( (option.equals("a")) && game.getPlayerPositionColumn()-2 >= -1 ){
							game.setGogglePosition(game.getPlayerPositionRow(), game.getPlayerPositionColumn()-1, game.getPlayerPositionRow(), game.getPlayerPositionColumn()-2);
							game.goggleOff();
						}else if( (option.equals("s")) && game.getPlayerPositionRow()+2 <= 9){
							game.setGogglePosition(game.getPlayerPositionRow()+1, game.getPlayerPositionColumn(), game.getPlayerPositionRow()+2, game.getPlayerPositionColumn());
							game.goggleOff();
						}else if( (option.equals("d")) && game.getPlayerPositionColumn()+2 <= 9){
							game.setGogglePosition(game.getPlayerPositionRow(), game.getPlayerPositionColumn()+1, game.getPlayerPositionRow(), game.getPlayerPositionColumn()+2);
							game.goggleOff();
						}else{
							System.out.println("You cannot look in that direction!");
						}
						break;
					default:
							System.out.println("Invalid Option!");
							break;
					}
				}
				else if(!game.getGoggle())
					System.out.println("You have already used your night vision goggles!");
				break;
				
				case "e":
						System.out.println("Select the direction you want to shoot in: ");
						System.out.println("Shoot up = w\tShoot Down = s\tShoot Left = a\tShoot Right = d");
						System.out.print("Enter Option : ");
						option = keyboard.nextLine().toLowerCase();
						switch(option){
						case "w":
						case "a":
						case "s":
						case "d":
							if( (option.equals("w"))){
								if(checkBullet()) {
									playerShoot(game.shootUp());
									if(game.moveEnemies()){
										System.out.println("You have been killed by a ninja!");
									}
									if(game.getPlayerLives() != 0 && !game.gameOver())
										System.out.println("The ninjas have moved..");
									if(game.getInvincTurns() != 0)
										game.decreaseInvinc();
								}
							}else if( (option.equals("a"))){
								if(checkBullet()) {
									playerShoot(game.shootLeft());
									if(game.moveEnemies()){
										System.out.println("You have been killed by a ninja!");
									}
									if(game.getPlayerLives() != 0 && !game.gameOver())
										System.out.println("The ninjas have moved..");
									if(game.getInvincTurns() != 0)
										game.decreaseInvinc();
								}
							}else if( (option.equals("s"))){
								if(checkBullet()) {
									playerShoot(game.shootDown());
									if(game.moveEnemies()){
										System.out.println("You have been killed by a ninja!");
									}
									if(game.getPlayerLives() != 0 && !game.gameOver())
										System.out.println("The ninjas have moved..");
									if(game.getInvincTurns() != 0)
										game.decreaseInvinc();
								}
							}else if( (option.equals("d"))){
								if(checkBullet()) {
									playerShoot(game.shootRight());
									if(game.moveEnemies()){
										System.out.println("You have been killed by a ninja!");
									}
									if(game.getPlayerLives() != 0 && !game.gameOver())
										System.out.println("The ninjas have moved..");
									if(game.getInvincTurns() != 0)
										game.decreaseInvinc();
								}
							}else{
								System.out.println("You cannot shoot in that direction!");
							}
							break;
						default:
								System.out.println("Invalid Option!");
								break;
						}
					break;
			case "q":
				quitGame = true;
				break;
			case "w":
				move = game.movePlayerUp();
				System.out.println(playerMove(move));
				if(move != 0) {
					game.goggleOn();
					if(game.moveEnemies()){
						System.out.println("You have been killed by a ninja!");
					}
					if(game.getPlayerLives() != 0 && !game.gameOver())
						System.out.println("The ninjas have moved..");
					if(game.getInvincTurns() != 0)
						game.decreaseInvinc();
				}
				break;
			case "a":
				move = game.movePlayerLeft();
				System.out.println(playerMove(move));
				if(move != 0) {
					game.goggleOn();
					if(game.moveEnemies()){
						System.out.println("You have been killed by a ninja!");
					}
					if(game.getPlayerLives() != 0 && !game.gameOver())
						System.out.println("The ninjas have moved..");
					if(game.getInvincTurns() != 0)
						game.decreaseInvinc();
				}
				break;
			case "s":
				move = game.movePlayerDown();
				System.out.println(playerMove(move));
				if(move != 0) {
					game.goggleOn();
					if(game.moveEnemies()){
						System.out.println("You have been killed by a ninja!");
					}
					if(game.getPlayerLives() != 0 && !game.gameOver())
						System.out.println("The ninjas have moved..");
					if(game.getInvincTurns() != 0)
						game.decreaseInvinc();
				}
				break;
			case "d":
				move = game.movePlayerRight();
				System.out.println(playerMove(move));
				if(move != 0) {
					game.goggleOn();
					if(game.moveEnemies()){
						System.out.println("You have been killed by a ninja!");
					}
					if(game.getPlayerLives() != 0 && !game.gameOver())
						System.out.println("The ninjas have moved..");
					if(game.getInvincTurns() != 0)
						game.decreaseInvinc();
				}
				break;
            case "v":
                System.out.println(game.saveGame());
                break;
			default:
					System.out.println("Invalid Option!");
					break;
			}
			game.checkInvincTurns();
			game.checkPlayerLives();
		}
		if(game.checkPlayerLives() > 0 && !quitGame)
			System.out.println("You win!");
		else if(game.checkPlayerLives() <= 0 && !quitGame)
			System.out.println("You lose!");
			
	}
	
    /**
    * This is method is called once the program is started.
    * In message, explains how the game works and the tools the player can use.
    * The last thing is the character representation of each object on the display.
    */
	private void welcomeMessage() {
		System.out.println("Welcome to Thief!");
		System.out.println("Thief is a turn-based game that takes place in a pitch-black building that is filled with ninjas!");
		System.out.println("The objective of the game is simple: navigate through the dark building, steal the briefcase (found in one of nine rooms), and avoid the deadly ninjas.");
		System.out.println();
		System.out.println("You have several tools at your disposal in order to accomplish your mission:" +
								"\n\t Night Vision Goggles: allows you to see two spaces ahead of you, can only use once per turn" +
								"\n\t Gun: able to shoot in any direction to kill one enemy, can only hold one bullet");
		System.out.println();
		System.out.println("In addition to this, you have several power-ups you may piok up throughout the building:" +
								"\n\t Bullet: refills your gun with one bullet (will not do anything if you already have a bullet)" +
								"\n\t Invincibility: allows you to pass by ninjas without dying, lasts 5 turns" +
								"\n\t Radar: reveals which room the briefcase is located in");
	    System.out.println("Legend:");
	    System.out.println("Player = P" + "\t Ninja = N" + "\t Briefcase = C");
	    System.out.println("Room = R" + "\t Bullet = B" + "\t Invincibility = I" + "\t Radar = A");
		System.out.println();
		System.out.println("Good luck!");
		System.out.println();
	}
	
    /**
    * This method will print according to
    * @param shoot. if shoot is true, the player killed a ninja.
    * if false, the player hit the wall.
    */
	public void playerShoot(boolean shoot) {
		if(shoot)
			System.out.println("You have killed a ninja!");
		else
			System.out.println("You have hit a wall!");
		game.setAmmo(0);
	}
	
    /**
    * This method check if the player has bullet.
    * @return true if the player has a bullet 
    * and false if there is no bullet
    */
	private boolean checkBullet() {
		if(game.getAmmo() == 0) {
			System.out.println("You have no bulllets!");
			return false;
		}
		else
			return true;
	}
	
    /**
    * This method prints the every possible scenario after the player moves.
    * @param scenario will decide what string should be returned.
    * @return the scenario String according to the parameter.
    */
	public String playerMove(int scenario) {
	    String result = "";
	    
		if(scenario == 0) result = "You have ran into a wall!"; 	//if player attempts to walk into wall
		if(scenario == 1) result = "You have ran into a ninja and died!"; //if player walks into a ninja
		if(scenario == 2) result = "You have received a bullet!"; //if player moves on Bullet power-up
		if(scenario == 3) result = "You have received invincibility!"; //if player moves on Invincibility power-up
		if(scenario == 4) result = "You have receivd a radar!"; //if player moves on Radar power-up
		if(scenario == 5) result = "There is no briefcase in that room."; //if player attempts to move in a room
		if(scenario == 6) result = "You have found the briefcase!"; //if player moves in Briefcase room
		
		return result;
	}
}
