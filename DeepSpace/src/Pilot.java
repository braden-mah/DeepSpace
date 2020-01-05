
public class Pilot implements java.io.Serializable {
	private String name;
	private boolean move;
	private int gold;
	private int killCounter=0;
	/**
	 * Initializes the pilot variables
	 * @param name the name of the pilot
	 */
	public Pilot () {
	}
	/**
	 * Set the gold that the pilot has
	 * @param g
	 */
	public void setGold(int g) {
		gold+=g;
		if (gold <0) {
			gold=0;
		}
	}
	/**
	 * Get the gold that the pilot has
	 * @return the amount of gold the pilot has
	 */
	public int getGold() {
		return gold;
	}
	/**
	 * Adjust the amount of gold the pilot has
	 */
	public void chGold(int ch) {
		gold-=ch;
		if (gold <0) {
			gold=0;
		}
	}
	/**
	 * Get the name of the pilot
	 * @return the name of the pilot
	 */
	public String getName() {
		return name;
	}
	/**
	 * Add to the number of enemy ship kills the pilot has gotten
	 */
	public void addKillCounter() {
		killCounter+=1;
	}
	/**
	 * Get the pilot's number of kills
	 * @return
	 */
	public int getKillCounter() {
		return killCounter;
	}
	/**
	 * Set the name of the pilot
	 * @param name
	 */
	public void setName(String name) {
		this.name=name;
	}
}

