
public class Enemy {
	private int hp;
	private int speed;
	private int basePower;
	private String name;
	private String introText;
	private int damage;
	private String text;
	private int gold;
	private int burnDamage;
	private int burnCounter;
	private String type;
	/**
	 * Create and initialize the values for the enemy you will fight
	 * @param h health of the enemy
	 * @param s speed of the enemy
	 * @param bP strength of the enemy
	 * @param name the enemy name
	 * @param iT the text that is displayed when you encounter the enemy
	 * @param g the base amount of gold the enemy drops
	 * @param type the difficulty of the enemy
	 */
	public Enemy(int h, int s, int bP, String name, String iT, int g, String type) {
		gold=g;
		hp=h;
		speed=s;
		basePower=bP;
		this.name=name;
		introText=iT;
		this.type=type;
	}
	/**
	 * Encounter the enemy and display text
	 */
	public void encounter(){
		System.out.println();
		System.out.println("You encountered "+introText);
	}
	/**
	 * Print the current stats of the enemy
	 */
	public void printStats(){
		System.out.println("---"+name+"---\nHp: "+hp+"\nSpeed: "+speed+"\nBase Power: "+basePower+"\n");
	}
	/**
	 * Get the enemy hp
	 * @return the hp of the enemy
	 */
	public int getHp() {
		return hp;
	}
	/**
	 * Get the enemy speed
	 * @return the speed of the enemy
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Attack the hero ship
	 * @return the damage the enemy deals to you
	 */
	public int attack() {
		damage=(int)(-1*((Math.random()*10)+basePower));
		return damage;
	}
	/**
	 * Change the hp of the enemy 
	 * @param chHP the value to change the enemy hp by.
	 */
	public void setHp(int chHP) {
		hp+=chHP;
		if (hp<0) {
			hp=0;
		}
	}
	/**
	 * Get the name of the enemy
	 * @return the enemy name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Generate and get the gold amount of the enemy
	 * @return the amount of gold received for defeating the enemy
	 */
	public int getGold() {
		gold=(int)((Math.random()*100)+gold);
		return gold;
	}
	/**
	 * Get the gold amount of the enemy
	 * @return the amount of gold earned
	 */
	public int goldStatement() {
		return gold;
	}
	/**
	 * Get the difficulty of the enemy
	 * @return the difficulty of the enemy
	 */
	public String getType() {
		return type;
	}
}
