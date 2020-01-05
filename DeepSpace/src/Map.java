public class Map implements java.io.Serializable{
	private int pilotRow=1;
	private int pilotColumn=1;
	private String region;
	private char[][] mapArray = {
	{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	{'.','x','x','x','x','x','x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','.'},
	{'.','x',' ',' ',' ',' ','x','x','x',' ',' ',' ','x',' ',' ',' ',' ','.'},
	{'.','x','x','x',' ',' ','x',' ',' ',' ',' ',' ','x','x','x',' ',' ','.'},
	{'.','x',' ',' ',' ','x','x',' ',' ',' ',' ','x',' ','x','x','x','x','.'},
	{'.','x','x','x','x',' ','x',' ',' ','x','x','x','x','x',' ',' ','x','.'},
	{'.',' ','x',' ','x','x','x',' ',' ','x',' ','x',' ','x','x','x','x','.'},
	{'.',' ','x',' ',' ',' ',' ',' ',' ','x',' ','x','x','x','x',' ','x','.'},
	{'.',' ','x','x','x','x',' ',' ',' ','x',' ',' ','x',' ','x',' ','x','.'},
	{'.',' ',' ',' ',' ','x','x','x',' ','x',' ',' ','x','x','x',' ','x','.'},
	{'.',' ',' ',' ',' ',' ','x',' ',' ','x',' ',' ','x',' ','x','x','x','.'},
	{'.',' ',' ',' ',' ',' ','x',' ',' ','x',' ',' ','x','x','x',' ',' ','.'},
	{'.',' ',' ',' ',' ',' ','x','x','x','x',' ',' ',' ',' ',' ',' ',' ','.'},
	{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	};
	private int randomNumber;
	/**
	 * Displays the game map.
	 */
	public void showMap() {
		System.out.println("----Map----");
		System.out.println(region);
		mapArray[pilotRow][pilotColumn]='o';
		for (int i=0; i<mapArray.length; i++) {
			for (int j=0; j<mapArray[i].length; j++) {
				System.out.print(mapArray[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void setPlanet(int row, int column, String name) {
		mapArray[row][column]=name.charAt(0);
	}
	/**
	 * Makes sure the player isn't traveling into restricted areas
	 * @param changeRow which row to move the player to
	 * @param changeColumn column which column to move the player to
	 * @return whether or not the player can move to that spot
	 */
	public boolean checkMap(int changeRow, int changeColumn) {
		try {
			if (mapArray[pilotRow+changeRow][pilotColumn+changeColumn]=='x') {
				mapArray[pilotRow][pilotColumn]='x';
				pilotRow+=changeRow;
				pilotColumn+=changeColumn;
				return true;
			}
			else {
				return false;
			}
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
	/**
	 * Randomizer that can spawn an enemy upon moving
	 * @return the type of enemy
	 */
	public String spawnEnemy(){
		randomNumber=(int)(Math.random()*10)+1;
		if ((randomNumber>=1)&&(randomNumber<=8)) {
			return "false";
		}
		else {
			randomNumber=(int)(Math.random()*4)+1;
			if ((randomNumber>=1)&&(randomNumber<=3)) {
				return "easy";
			}
			else {
				return "hard";
			}
		}
	}
	/**
	 * Gets the pilots current row number
	 * @return row number of pilot
	 */
	public int getPilotRow() {
		return pilotRow;
	}
	/**
	 * Sets the pilots row
	 * @param pilotRow the new row number of the pilot
	 */
	public void setPilotRow(int pilotRow) {
		this.pilotRow = pilotRow;
	}
	/**
	 * Gets the pilots current column number
	 * @return column number of pilot
	 */
	public int getPilotColumn() {
		return pilotColumn;
	}
	/**
	 * Sets the pilots column
	 * @param pilotColumn the new column number of the pilot
	 */
	public void setPilotColumn(int pilotColumn) {
		this.pilotColumn = pilotColumn;
	}
	/**
	 * Gets the map
	 * @return the map array
	 */
	public char[][] getMapArray() {
		return mapArray;
	}
	/**
	 * Sets the map
	 * @param mapArray the new map
	 */
	public void setMapArray(char[][] mapArray) {
		this.mapArray = mapArray;
	}
	public void getMovementHelp() {
		System.out.println("\n-----Basic Concept-----\nYour Ship = 'o' on map\nGalactic Pathways = 'x' on map\nOut of Bounds = ' ' on map\nPlanets = Outstanding letters on map ('V' letter is planet Veromoz)");
		System.out.println("\nMove your spaceship along the galactic pathways in order to get to different planets and complete various missions!\nWatch out, because enemies are just around the corner!");
		System.out.println("\n-----Basic Controls-----\n'd' or 'right' = move right\n'a' or 'left' = move left\n'w' or 'up' = move up\n's' or 'down' = move down");
		System.out.println("'summary' = stat summary, including gold count, equipped ships, weapons, etc.\n'save' = save your game progress\n'mission' = get a list of all in-game missions\n'track' = track enemy criminals (only available after getting to Centurus)\n'help' = game instructions\n");
		
	}
	/**
	 * Set the current region the pilot is in
	 */
	public void setRegion() {
		if (pilotRow<=9 && pilotColumn<=7) {
			region="Gemini Region";
		}
		else if (pilotColumn>=10) {
			region="Apollo Region";
		}
		else {
			region="Reaver's Astrobelt";
		}
	}
	/**
	 * Get the region that the pilot is in
	 * @return the region that the pilot is in
	 */
	public String getRegion() {
		return region;
	}
}
