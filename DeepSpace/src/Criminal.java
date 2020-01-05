
public class Criminal {
	private int row;
	private int column;
	private int gold;	
	private boolean onMap=false;
	private String name;
	private String[] firstName= {"John","Keanu","Jasper","Max","Evelynn","Brittney"};
	private String[] lastName= {"Henderson","Ayton","Graham","Wu","Okinawa","Paul"};
	/**
	 * Create a criminal and place him/her on the map
	 * @param map 2D map used to determine whether criminal coords are valid
	 */
	public Criminal(char[][] map) {
		while (onMap==false) {
			row=(int)(Math.random()*12)+1;
			column=(int)(Math.random()*16)+1;
			if (map[row][column]=='x') {
				onMap=true;
			}
		}
		name=firstName[(int)(Math.random()*6)]+" "+lastName[(int)(Math.random()*6)];
		gold=(int)(Math.random()*1000)+500;
	}
	/**
	 * Check whether the hero has found the criminal
	 * @param pilotRow the row number of the pilot's location
	 * @param pilotColumn the column number of the pilot's location
	 * @return whether or not the hero has found the criminal
	 */
	public boolean check(int pilotRow, int pilotColumn) {
		if (pilotRow==row && pilotColumn==column) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Get the name of the criminal
	 * @return the name of the criminal
	 */
	public String getName(){
		return name;
	}
	/**
	 * Get the criminal's bounty
	 * @return the criminal's bounty
	 */
	public int getGold() {
		return gold;
	}
	/**
	 * Get the row number of the criminal
	 * @return the row number of the criminal
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Get the column number of the criminal
	 * @return the column number of the criminal
	 */
	public int getColumn() {
		return column;
	}
	
}
