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
import java.util.Random;
/**
 * @author Oakar
 *
 */
public class Grid implements Serializable{
	
	/**
	 * This field represents the size of the row of the grid.
	 */
	private static final int GRID_SIZE_ROW = 9;
	
	/**
	 * This field represents the size of the column of the grid.
	 */
	private static final int GRID_SIZE_COLUMN = 9;
	
	/**
	 * This field represents the area that surrounds the player's starting position. These areas
	 * are reserved, so that the ninjas do not spawn in these positions.
	 */
	private static final int[][] RESERVED_ROOMS = {{5,0},{6,0},{7,0},{8,1},{8,2},{8,3}};
	
	/**
	 * This field represents whether or not the Debug Mode is turned on.
	 */
	private boolean debugMode;
	
	/**
	 * This field represents an instance of the Player class. In addition to this, the player's 
	 * starting position is passed in as the argument. 
	 */
	 
	private Player user = new Player(8,0);
	
	/**
	 * This field represents an array of instances of the Room class. There are nine rooms
	 * in the 9x9 grid. The instances of the Room class are only initialized, but not assigned 
	 * positions.
	 */
	private Room[] specialRoom = new Room[9];
	
	/**
	 * This field represents an instance of the Goggle class. 
	 */
	private Goggle goggle = new Goggle();
	
	/**
	 * This field represents an array of the Enemy class. There are six enemies that are created
	 * to be used in the game. The instances of the Enemy class are only initialized, but not assigned
	 * positions.
	 */
	private Enemy[] ninjas = new Enemy[6];
	
	/**
	 * This field represents an instance of the Random class. This will allow the Grid class to randomize
	 * the positions of objects' positions, such as the enemies, power-ups, and the briefcase location.
	 */
	private Random rand = new Random();
	
	/**
	 * This field represents an instance of the Bullet class. This class is assigned a null value initially.
	 * Once the player steps on the Bullet power-up, the class will be initialized and created.
	 */
	private Bullet bullet = null;
	
	/**
	 * This field represents an instance of the Invincibility class. This class is assigned a null value initially.
	 * Once the player steps on the Invincibility power-up, the class will be initialized and created.
	 */
	private Invincibility invinc = null;
	
	/**
	 * This field represents an instance of the Radar class. This class is assigned a null value initially.
	 * Once the player steps on the Radar power-up, the class will be initialized and created.
	 */
	private Radar radar = null;
	
	/**
	 * This is the constructor of the Grid class. Once the Grid class is created and initialized in the Game
	 * Engine class, the Grid calls several methods to assign positions to the objects of the game, such as the
	 * rooms, ninjas, power-ups, and randomizes their positions. 
	 */
	public Grid(){

		createRooms();
		createNinjas();
		createPowerUps();
		randomizeBriefcase();
		randomizePowerups();
		randomizeEnemies();	
	}
	
	/**
	 * This method assigns positions of the rooms the briefcases can be located in on the grid. For
	 * every 3x3 grid, there is a room in the middle. 
	 */
	private void createRooms(){
		int roomNumber = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				specialRoom[roomNumber] = new Room((3*i)+1,(3*j)+1);
				roomNumber+=1;
			}
		}
	
	}
	
	/**
	 * This method initializes each instance of the Enemy class in the array. This sets the ninjas to be 
	 * alive and assigns a default position of row and column position: (-1,-1).
	 */
	private void createNinjas(){
		for(int i=0;i<ninjas.length;i++){
			ninjas[i] = new Enemy();
		}
	}
	
	/**
	 * This method initializes each power-up in the game. 
	 */
	private void createPowerUps(){
		bullet = new Bullet();
		invinc = new Invincibility();
		radar = new Radar();
	}

	/**
	 * This method randomly places the briefcase in one of the nine rooms.
	 */
	public void randomizeBriefcase() {
		int random = rand.nextInt(9);
		specialRoom[random].setBriefcase();		
	}
	
	/**
	 * This method randomly assigns each power-up of the game a position on the grid.
	 */
	public void randomizePowerups() {
		int randomNumber = rand.nextInt(81)+1;
		int row = (randomNumber-1)/9;
		int column = (randomNumber-1)%9;
		while(checkRandomAvailability(row,column)){
			randomNumber = rand.nextInt(81)+1;
			row = (randomNumber-1)/9;
			column = (randomNumber-1)%9;
		}
		bullet.setPositionRow((randomNumber-1)/9);
		bullet.setPositionColumn((randomNumber-1)%9);
		
		randomNumber = rand.nextInt(81)+1;
		row = (randomNumber-1)/9;
		column = (randomNumber-1)%9;
		while(checkRandomAvailability(row,column)){
			randomNumber = rand.nextInt(81)+1;
			row = (randomNumber-1)/9;
			column = (randomNumber-1)%9;
		}
		invinc.setPositionRow((randomNumber-1)/9);
		invinc.setPositionColumn((randomNumber-1)%9);
		
		randomNumber = rand.nextInt(81)+1;
		row = (randomNumber-1)/9;
		column = (randomNumber-1)%9;
		while(checkRandomAvailability(row,column)){
			randomNumber = rand.nextInt(81)+1;
			row = (randomNumber-1)/9;
			column = (randomNumber-1)%9;
		}
		radar.setPositionRow((randomNumber-1)/9);
		radar.setPositionColumn((randomNumber-1)%9);
		
	}
	
	/**
	 * This method randomly assigns each ninja in the game a position on the grid. 
	 */
	public void randomizeEnemies(){
		int randomNumber;
		int row;
		int column;
		for(int i = 0; i < ninjas.length; i++){
			randomNumber = rand.nextInt(81)+1;
			row = (randomNumber-1)/9;
			column = (randomNumber-1)%9;
			while(checkRandomAvailability(row,column)){
				
				randomNumber = rand.nextInt(81) + 1;
				row = (randomNumber-1)/9;
				column = (randomNumber-1)%9;
				
			}
			ninjas[i].setPositionRow(row);
			ninjas[i].setPositionColumn(column);
		}
	}
	
	/**
	 * This method receives the positions that the player looks in using the night vision goggles.
	 * The method will receive two positions, thus two rows and two columns, since the player looks
	 * in two spaces ahead of him/her. It then sets the goggle's positions to the positions passed
	 * into the parameter.
	 * @param firstRow the row of the first position the player looks in
	 * @param firstColumn the column of the first position the player looks in
	 * @param secondRow the row of the second position the player looks in
	 * @param secondColumn the column of the second position the player looks in
	 */
	public void setGogglePosition(int firstRow, int firstColumn, int secondRow, int secondColumn) {
		goggle.setGogglePosition(firstRow, firstColumn, secondRow, secondColumn);
	}
	
	/**
	 * This method returns whether or not the night vision goggles are used.
	 * @return whether the Night Vision Goggles is on or off
	 */
	public boolean getGoggle() {
		return goggle.getGoggle();
	}
	
	/**
	 * This method turns on the night vision goggles.
	 */
	public void goggleOn() {
		goggle.goggleOn();
	}
	
	/**
	 * This method turns off the night vision goggles.
	 */
	public void goggleOff() {
		goggle.goggleOff();
	}
	
	/**
	 * This method checks if a space is available/empty, so it can be used. A row and a column
	 * are passed in as parameter in order to check if the respective position is open or not. 
	 * This method is used to determine whether or not an object or entity can be placed or moved there.
	 * @param row the row of the position that is checked for availability 
	 * @param column the column of the position that is checked for availability
	 * @return whether or not the position is available 
	 */
	private boolean checkRandomAvailability(int row,int column){
		if(checkIsReserved(row,column)){
			return true;
		}
		if(checkIsRoom(row,column)){
			return true;
		}
		if(checkIsEnemy(row,column)){
			return true;
		}
		if(checkIsBullet(row,column)){
			return true;
		}
		if(checkIsInvinc(row,column)){
			return true;
		}
		if(checkIsRadar(row,column)){
			return true;
		}
		if(checkIsPlayer(row,column)){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the position that is passed in conflicts with the reserved spaces
	 * or not. This method prohibits ninjas from spawning to close to the starting position of 
	 * the player.
	 * @param row the row of the position that is checked 
	 * @param column the column of the position that is checked
	 * @return whether or not the position conflicts with a reserved space
	 */
	private boolean checkIsReserved(int row,int column){
		
		for(int i=0;i<RESERVED_ROOMS.length;i++){
			
			if(RESERVED_ROOMS[i][0]==row && RESERVED_ROOMS[i][1]==column){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method checks if the position that is passed in is the same position 
	 * as the player's position.
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the position that is passed in is the same as the player's position
	 */
	private boolean checkIsPlayer(int row,int column){
		if(user.getPositionRow()==row && user.getPositionColumn()==column){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the position that is passed in the parameter is the
	 * same position as the bullet power-up's position.
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the position that is passed in is the same as the bullet power-up's position
	 */
	private boolean checkIsBullet(int row, int column) {
		if(bullet.getPositionRow()==row && bullet.getPositionColumn()==column){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the position that is passed in the parameter is the
	 * same position as the invincibility power-up's position.
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the position that is passed in is the same as the invincibility power-up's position
	 */
	private boolean checkIsInvinc(int row, int column) {
		if(invinc.getPositionRow()==row && invinc.getPositionColumn()==column){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the position that is passed in the parameter is the same
	 * position as the radar power-up's position.
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the the passed in position is the same as the radar's position
	 */
	private boolean checkIsRadar(int row, int column) {
		if(radar.getPositionRow()==row && radar.getPositionColumn()==column){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the passed in position is the same as
	 * one of the six ninja's positions
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the passed in position is the same as one of the ninja's positions
	 */
	private boolean checkIsEnemy(int row,int column){
		for(int i=0;i<ninjas.length;i++){
			if(ninjas[i].getPositionRow()==row && ninjas[i].getPositionColumn()==column){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method checks if the passed in position is the same as 
	 * one of the room's positions.
	 * @param row the row of the position that is checked
	 * @param column the column of the position that is checked
	 * @return whether or not the checked position is the same as one of the room's positions
	 */
	private boolean checkIsRoom(int row,int column){
		for(int i=0;i<specialRoom.length;i++){
			if(specialRoom[i].getPositionRow()==row && specialRoom[i].getPositionColumn()==column){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method checks whether or not a position is the same
	 * as the room's position that contains the briefcase.
	 * @param row the row of the position that is checked.
	 * @param column the column of the position that is checked.
	 * @return whether or not the passed-in position is the same as the room's position that contains the briefcase
	 */
	private boolean checkIsBriefcaseRoom(int row, int column) {
		for(int i=0;i<specialRoom.length;i++) {
			if(specialRoom[i].getPositionRow()==row && specialRoom[i].getPositionColumn()==column && specialRoom[i].checkBriefcase())
				return true;
		}
		return false;
	}
	
	/**
	 * This method returns whether or not debug mode is turned on.
	 * @return whether or not debug mode is turned on
	 */
	public boolean getDebugMode() {
	    return debugMode;
	}

	/**
	 * This method is responsible for printing and creating the grid, based on the positions of each object or character. If debug mode
	 * is not on, then empty spaces will be printed as "dark" and will hide whatever is there, unless it is a room or a player. Additionally,
	 * if the player uses the night vision goggles, then this method will show the positions that are specified by the Goggle class. Also,
	 * if debug mode is on, then the position of every object and character in the game will be shown and not be hidden. 
	 * @param debugMode whether or not the user has turned on debug mode or not 
	 * @return the layout of the grid
	 */
	public String toString(boolean debugMode) {
	    this.debugMode = debugMode;
		String result = "";
		for(int i=0;i<GRID_SIZE_ROW;i++){
			for(int j=0;j<GRID_SIZE_COLUMN;j++){
				result+="[";
				if(debugMode){
					if(user.getPositionRow()==i && user.getPositionColumn()==j){
						result+="P";
					}else if(checkIsRoom(i,j)){
						if(checkIsBriefcaseRoom(i,j)) {
							result+="C";
						}
						else {
							result+="R";
						}
					}else if(bullet.getPositionRow()==i && bullet.getPositionColumn()==j && bullet.isOnBoard()){
						result+="B";
					}else if(invinc.getPositionRow()==i && invinc.getPositionColumn()==j && invinc.isOnBoard()){
						result+="I";
					}else if(radar.getPositionRow()==i && radar.getPositionColumn()==j && radar.isOnBoard()){
						result+="A";
					}else if(checkIsEnemy(i,j)){
						result+="N";
					}else{
						result+=" ";
					}
				}else{
					if(user.getPositionRow()==i && user.getPositionColumn()==j){
						result+="P";
					}else if(checkIsRoom(i,j)){
						if(radar.getRadar() && checkIsBriefcaseRoom(i,j))
							result+="C";
						else
							result+="R";
					}else if( !goggle.getGoggle() && (bullet.getPositionRow()==i && bullet.getPositionColumn()==j && bullet.isOnBoard()) && checkGogglePosition(i,j)){
						result += "B";
					}else if( !goggle.getGoggle() && (radar.getPositionRow()==i && radar.getPositionColumn()==j && radar.isOnBoard()) && checkGogglePosition(i,j) ){
						result += "A";
					}
					else if( !goggle.getGoggle() && (invinc.getPositionRow()==i && invinc.getPositionColumn()==j && invinc.isOnBoard()) && checkGogglePosition(i,j) ){
						result += "I";
					}else if(!goggle.getGoggle() && checkIsEnemy(i, j) && checkGogglePosition(i,j) ){
						result += "N";
					}else if(!goggle.getGoggle() && checkGogglePosition(i,j)) {
						result += " ";
					}else{
						result+="*";
					}
				}
				result+="]";
			}
			result +="\n";
		}
		return result;
	}
	
	/**
	 * This method will check if a position is either one of the positions that are in the direction
	 * the player looked in using the night vision goggles.
	 * @param i the row of the position that is checked
	 * @param j the column of the position that is checked
	 * @return whether or not the passed-in position is either one of the positions the player looked in using the night vision goggles
	 */
	public boolean checkGogglePosition(int i,int j){
		if(goggle.getGoggleFirstRow() == i && goggle.getGoggleFirstColumn() == j){
			return true;
		}
		if (goggle.getGoggleSecondRow() == i && goggle.getGoggleSecondColumn() == j ){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the player is able to move in the 'up' direction and moves 
	 * him/her if permitted. Also, this method returns an integer, based on what is in the position
	 * that the player has moved in.
	 * @return integer based on what the player encountered or didn't encounter by moving up one position
	 */
	public int movePlayerUp() {
		if(user.getPositionRow()== 0 || checkIsRoom(user.getPositionRow()-1, user.getPositionColumn()))
			return 0;
		if(checkIsEnemy(user.getPositionRow()-1, user.getPositionColumn())) {
			if(invinc.getInvincStatus()) {
				user.moveUp();
				return -1;
			} else {
				user.setLives(user.getLives()-1);
				user.resetPosition();
				return 1;
			}
		}
		if(checkIsBullet(user.getPositionRow()-1,user.getPositionColumn())) {
			bullet.use();
			if(user.getAmmo() == 0) {
				user.setAmmo(1);
				user.moveUp();
				return 2;
			}
			user.moveUp();
			return -1;
		}
		if(checkIsInvinc(user.getPositionRow()-1, user.getPositionColumn())) {
			invinc.use();
			invinc.setInvinc(true);
			invinc.setCount(6);
			user.moveUp();
			return 3;
		}
		if(checkIsRadar(user.getPositionRow()-1, user.getPositionColumn())) {
			radar.use();
			radar.setRadar();
			user.moveUp();
			return 4;
		}
		user.moveUp();
		return -1;
		
	}
	
	/**
	 * This method checks if the player is able to move in the 'down' direction and moves 
	 * him/her if permitted. Also, this method returns an integer, based on what is in the position
	 * that the player has moved in.
	 * @return integer based on what the player encountered or didn't encounter by moving down one position
	 */
	public int movePlayerDown() {
		if(user.getPositionRow()== 8)
			return 0;
		if(checkIsEnemy(user.getPositionRow()+1, user.getPositionColumn())) {
			if(invinc.getInvincStatus()) {
				user.moveDown();
				return -1;
			} else {
				user.setLives(user.getLives()-1);
				user.resetPosition();
				return 1;
			}
		}
		if(checkIsBullet(user.getPositionRow()+1,user.getPositionColumn())) {
			bullet.use();
			if(user.getAmmo() == 0) {
				user.setAmmo(1);
				user.moveDown();
				return 2;
			}
			user.moveDown();
			return -1;
		}
		if(checkIsInvinc(user.getPositionRow()+1, user.getPositionColumn())) {
			invinc.use();
			invinc.setInvinc(true);
			invinc.setCount(6);
			user.moveDown();
			return 3;
		}
		if(checkIsRadar(user.getPositionRow()+1, user.getPositionColumn())) {
			radar.use();
			radar.setRadar();
			user.moveDown();
			return 4;
		}
		if(checkIsBriefcaseRoom(user.getPositionRow()+1, user.getPositionColumn())) {
			user.moveDown();
			return 6;
		}
		if(checkIsRoom(user.getPositionRow()+1,user.getPositionColumn())) {
			return 5;
		}
		user.moveDown();
		return -1;
		
	}
	
	/**
	 * This method checks if the player is able to move in the 'left' direction and moves 
	 * him/her if permitted. Also, this method returns an integer, based on what is in the position
	 * that the player has moved in.
	 * @return integer based on what the player encountered or didn't encounter by moving left one position
	 */
	public int movePlayerLeft() {
		if(user.getPositionColumn()== 0 || checkIsRoom(user.getPositionRow(), user.getPositionColumn()-1))
			return 0;
		if(checkIsEnemy(user.getPositionRow(), user.getPositionColumn()-1)) {
			if(invinc.getInvincStatus()) {
				user.moveLeft();
				return -1;
			} else {
				user.setLives(user.getLives()-1);
				user.resetPosition();
				return 1;
			}
		}
		if(checkIsBullet(user.getPositionRow(),user.getPositionColumn()-1)) {
			bullet.use();
			if(user.getAmmo() == 0) {
				user.setAmmo(1);
				user.moveLeft();
				return 2;
			}
			user.moveLeft();
			return -1;
		}
		if(checkIsInvinc(user.getPositionRow(), user.getPositionColumn()-1)) {
			invinc.use();
			invinc.setInvinc(true);
			invinc.setCount(6);
			user.moveLeft();
			return 3;
		}
		if(checkIsRadar(user.getPositionRow(), user.getPositionColumn()-1)) {
			radar.use();
			radar.setRadar();
			user.moveLeft();
			return 4;
		}
		user.moveLeft();
		return -1;
	}
	
	/**
	 * This method checks if the player is able to move in the 'right' direction and moves 
	 * him/her if permitted. Also, this method returns an integer, based on what is in the position
	 * that the player has moved in.
	 * @return integer based on what the player encountered or didn't encounter by moving right one position
	 */
	public int movePlayerRight() {
		if(user.getPositionColumn()== 8 || checkIsRoom(user.getPositionRow(), user.getPositionColumn()+1))
			return 0;
		if(checkIsEnemy(user.getPositionRow(), user.getPositionColumn()+1)) {
			if(invinc.getInvincStatus()) {
				user.moveRight();
				return -1;
			} else {
				user.setLives(user.getLives()-1);
				user.resetPosition();
				return 1;
			}
		}
		if(checkIsBullet(user.getPositionRow(),user.getPositionColumn()+1)) {
			bullet.use();
			if(user.getAmmo() == 0) {
				user.setAmmo(1);
				user.moveRight();
				return 2;
			}
			user.moveRight();
			return -1;
		}
		if(checkIsInvinc(user.getPositionRow(), user.getPositionColumn()+1)) {
			invinc.use();
			invinc.setInvinc(true);
			invinc.setCount(6);
			user.moveRight();
			return 3;
		}
		if(checkIsRadar(user.getPositionRow(), user.getPositionColumn()+1)) {
			radar.use();
			radar.setRadar();
			user.moveRight();
			return 4;
		}
		user.moveRight();
		return -1;
	}
	
	/**
	 * This method checks if the ninja is able to move in the 'up' direction and moves 
	 * the ninja if permitted.
	 * @param ninja the ninja that is being moved
	 */
	public void moveNinjaUp(Enemy ninja)
	{
		if(ninja.getPositionRow() == 0)	{
			moveNinjaDown(ninja);
		}
		else if(checkIsRoom(ninja.getPositionRow()-1, ninja.getPositionColumn())) {
			moveNinjaDown(ninja);
		}
		else if(checkIsEnemy(ninja.getPositionRow()-1, ninja.getPositionColumn())) {
			moveNinjaDown(ninja);
		}
		else if(checkIsBullet(ninja.getPositionRow()-1, ninja.getPositionColumn())) {
			moveNinjaDown(ninja);
		}
		else if(checkIsInvinc(ninja.getPositionRow()-1, ninja.getPositionColumn())) {
			moveNinjaDown(ninja);
		}
		else if(checkIsRadar(ninja.getPositionRow()-1, ninja.getPositionColumn())) {
			moveNinjaDown(ninja);
		}
		else {
		ninja.moveUp();
		}
	}
	
	/**
	 * This method checks if the ninja is able to move in the 'down' direction and moves 
	 * the ninja if permitted.
	 * @param ninja the ninja that is being moved
	 */
	public void moveNinjaDown(Enemy ninja)
	{
		if(ninja.getPositionRow() == 8)	{
			moveNinjaLeft(ninja);
		}
		else if(checkIsRoom(ninja.getPositionRow()+1, ninja.getPositionColumn())) {
			moveNinjaLeft(ninja);
		}
		else if(checkIsEnemy(ninja.getPositionRow()+1, ninja.getPositionColumn())) {
			moveNinjaLeft(ninja);
		}
		else if(checkIsBullet(ninja.getPositionRow()+1, ninja.getPositionColumn())) {
			moveNinjaLeft(ninja);
		}
		else if(checkIsInvinc(ninja.getPositionRow()+1, ninja.getPositionColumn())) {
			moveNinjaLeft(ninja);
		}
		else if(checkIsRadar(ninja.getPositionRow()+1, ninja.getPositionColumn())) {
			moveNinjaLeft(ninja);
		}
		else {
		ninja.moveDown();
		}
	}
	
	/**
	 * This method checks if the ninja is able to move in the 'left' direction and moves 
	 * the ninja if permitted.
	 * @param ninja the ninja that is being moved
	 */
	public void moveNinjaLeft(Enemy ninja)
	{
		if(ninja.getPositionColumn() == 0)	{
			moveNinjaRight(ninja);
		}
		else if(checkIsRoom(ninja.getPositionRow(), ninja.getPositionColumn()-1)) {
			moveNinjaRight(ninja);
		}
		else if(checkIsEnemy(ninja.getPositionRow(), ninja.getPositionColumn()-1)) {
			moveNinjaRight(ninja);
		}
		else if(checkIsBullet(ninja.getPositionRow(), ninja.getPositionColumn()-1)) {
			moveNinjaRight(ninja);
		}
		else if(checkIsInvinc(ninja.getPositionRow(), ninja.getPositionColumn()-1)) {
			moveNinjaRight(ninja);
		}
		else if(checkIsRadar(ninja.getPositionRow(), ninja.getPositionColumn()-1)) {
			moveNinjaRight(ninja);
		}
		else {
		ninja.moveLeft();
		}
	}
	
	/**
	 * This method checks if the ninja is able to move in the 'right' direction and moves 
	 * the ninja if permitted.
	 * @param ninja the ninja that is being moved
	 */
	public void moveNinjaRight(Enemy ninja)
	{
		if(ninja.getPositionColumn() == 8)	{
			moveNinjaUp(ninja);
		}
		else if(checkIsRoom(ninja.getPositionRow(), ninja.getPositionColumn()+1)) {
			moveNinjaUp(ninja);
		}
		else if(checkIsEnemy(ninja.getPositionRow(), ninja.getPositionColumn()+1)) {
			moveNinjaUp(ninja);
		}
		else if(checkIsBullet(ninja.getPositionRow(), ninja.getPositionColumn()+1)) {
			moveNinjaUp(ninja);
		}
		else if(checkIsInvinc(ninja.getPositionRow(), ninja.getPositionColumn()+1)) {
			moveNinjaUp(ninja);
		}
		else if(checkIsRadar(ninja.getPositionRow(), ninja.getPositionColumn()+1)) {
			moveNinjaUp(ninja);
		}
		else {
		ninja.moveRight();
		}
	}
	
	/**
	 * This method runs a loop of the 'ninjas' array and checks if the player is near and if not, moves
	 * the ninja in a random direction in either up, down, left, or right. If a ninja is next to a player,
	 * then the ninja kills the player and moves in the position of the player.
	 * @return whether or not the player is killed
	 */
	public boolean moveNinjas() {
		boolean kill=false;
		for (int i = 0; i < ninjas.length ; ++i)
		{
			if (checkPlayerNear(ninjas[i].getPositionRow(),ninjas[i].getPositionColumn()) && ninjas[i].isAlive()) {
				if (user.isAlive() && !invinc.getInvincStatus())
				{
					ninjas[i].setPositionRow(user.getPositionRow());
					ninjas[i].setPositionColumn(user.getPositionColumn());
					user.resetPosition();
					user.setLives(user.getLives()-1);
				}
				kill = true;
			}
			else {
				int random = rand.nextInt(4)+1;	
				if (random == 1 && ninjas[i].isAlive()) {
					moveNinjaUp(ninjas[i]);
				}
				else if (random == 2 && ninjas[i].isAlive()) {
					moveNinjaDown(ninjas[i]);
				}
				else if (random == 3 && ninjas[i].isAlive()) {
					moveNinjaLeft(ninjas[i]);
				}
				else if (random == 4 && ninjas[i].isAlive()) {
					moveNinjaRight(ninjas[i]);
				}
			}
		}
		return kill;
	}
	
	/**
	 * This method checks if a player is near the position that is passed in the parameter. If a player is near the
	 * position, then the method will return true.
	 * @param row the row of the position that is passed in as the argument
	 * @param col the column of the position that is passed in as the argument
	 * @return whether or not the player is near
	 */
	private boolean checkPlayerNear(int row, int col) {
		if (user.getPositionRow() == (row-1) && user.getPositionColumn() == col && !invinc.getInvincStatus()) {
			return true;
		}
		if (user.getPositionRow() == (row+1) && user.getPositionColumn() == col && !invinc.getInvincStatus()) {
			return true;
		}
		if (user.getPositionRow() == row && user.getPositionColumn() == (col-1) && !invinc.getInvincStatus()) {
			return true;
		}
		if (user.getPositionRow() == row && user.getPositionColumn() == (col+1) && !invinc.getInvincStatus()) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns the amount of turns that the player is invincible for.
	 * @return amount of turns the player is invincible for
	 */
	public int getInvincTurns() {
		return invinc.getCount();
	}
	
	/**
	 * This method reduces the amount of turns the player is invincible for by one.
	 */
	public void decreaseInvinc() {
		invinc.decreaseCount();
	}
	
	/**
	 * This method checks if the amount of the turns the player is invincible for is zero.
	 * IF it is zero, then the method turns of the invicibility power-up.
	 */
	public void checkInvincTurns() {
		if(invinc.getCount() == 0)
			invinc.setInvinc(false);
	}
	
	/**
	 * This method returns the amount of ammunition left in the player's gun.
	 * @return the amount of ammunition in the player's gun
	 */
	public int getAmmo() {
		return user.getAmmo();
	}
	
	/**
	 * This method is called when the player decides to shoot his/her gun in the 'up' direction.
	 * The method checks any ninjas are in the direction the player shoots in, and if there is, then
	 * the foremost ninja dies and the method returns true.
	 * @return whether or not a ninja dies.
	 */
	public boolean shootUp() {
		int i = GRID_SIZE_ROW;
		while(!checkIsRoom(i,user.getPositionColumn()) && !checkIsEnemy(i, user.getPositionColumn()) && i!=0){			
			i--;
		}
		if(checkIsEnemy(i,user.getPositionColumn())){
			if(killEnemy(i,user.getPositionColumn())){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is called when the player decides to shoot his/her gun in the 'down' direction.
	 * The method checks any ninjas are in the direction the player shoots in, and if there is, then
	 * the foremost ninja dies and the method returns true.
	 * @return whether or not a ninja dies.
	 */
	public boolean shootDown() {
		int i=0;
		while(!checkIsRoom(i,user.getPositionColumn()) && !checkIsEnemy(i, user.getPositionColumn()) && i!=GRID_SIZE_ROW ){			
			i++;
		}
		if(checkIsEnemy(i,user.getPositionColumn())){
			if(killEnemy(i,user.getPositionColumn())){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method kills the ninja that shares the same position as the position that
	 * is passed in as the argument. If a ninja shares the same position, then the method
	 * return true.
	 * @param row the row of the position that is passed in
	 * @param col the column of the position that is passed in
	 * @return whether or not a ninja dies or not
	 */
	public boolean killEnemy(int row, int col){
		for(int i= 0; i < ninjas.length; i++) {
			if((ninjas[i].getPositionRow() == row) && (ninjas[i].getPositionColumn() == col)){
				ninjas[i].die();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method is called when the player decides to shoot his/her gun in the 'left' direction.
	 * The method checks any ninjas are in the direction the player shoots in, and if there is, then
	 * the foremost ninja dies and the method returns true.
	 * @return whether or not a ninja dies.
	 */
	public boolean shootLeft() {
		int i=0;
		while(!checkIsRoom(user.getPositionRow(),i) && !checkIsEnemy(user.getPositionRow(), i) && i!=GRID_SIZE_COLUMN ){			
			i++;
		}
		if(checkIsEnemy(user.getPositionRow(),i)){
			if(killEnemy(user.getPositionRow(),i)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method is called when the player decides to shoot his/her gun in the 'right' direction.
	 * The method checks any ninjas are in the direction the player shoots in, and if there is, then
	 * the foremost ninja dies and the method returns true.
	 * @return whether or not a ninja dies.
	 */
	public boolean shootRight() {
		int i = GRID_SIZE_COLUMN;
		while(!checkIsRoom(user.getPositionRow(),i) && !checkIsEnemy(user.getPositionRow(), i) && i!=0 ){			
			i--;
		}
		if(checkIsEnemy(user.getPositionRow(),i)){
			if(killEnemy(user.getPositionRow(),i)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method sets the ammo count of the player's gun
	 * @param bullet number of bullets in the player's gun
	 */
	public void setAmmo(int bullet) {
		user.setAmmo(bullet);
	}
	
	/**
	 * This method returns the player's position's row.
	 * @return the row of the position of the player
	 */
	public int getPlayerPositionRow(){
		return user.getPositionRow();
	}
	
	/**
	 * This method returns the player's position's column.
	 * @return the column of the position of the player
	 */
	public int getPlayerPositionColumn(){
		return user.getPositionColumn();
	}
	
	/**
	 * This method returns the amount of lives the player has.
	 * @return the amount of lives the player has
	 */
	public int getPlayerLives() {
		return user.getLives();
	}
}
