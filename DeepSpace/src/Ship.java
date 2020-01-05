public class Ship implements java.io.Serializable{
	private int hp;
	private int maxHp;
	private int speed;
	private int basePower;
	private String shipName;
	private int damage;
	/**
	 * Set the base stats for the ship
	 * @param h the health of the ship
	 * @param s the speed of the ship
	 * @param bP the base power of the ship
	 * @param sN the name of the ship
	 */
	public Ship(int h, int s, int bP, String sN) {
		hp=h;
		maxHp=h;
		speed=s;
		basePower=bP;
		shipName=sN;
	}
	/**
	 * Attack the enemy (base damage from ship adds onto the damage from the weapon)
	 * @return the amount of damage to deal to the enemy (from the ship)
	 */
	public int attack() {
		damage=(int)(-1*((Math.random()*10)+basePower));
		System.out.println("Damage from ship: "+damage);
		return damage;
	}
	/**
	 * Get the stats of the ship (e.g., while in combat)
	 */
	public String toString(){
		return "---Ship Info---\nShip Name: "+shipName+"\nHp: "+hp+"\nSpeed: "+speed+"\nBase Power: "+basePower+"\n";
	}
	/**
	 * Get the hp of the ship
	 * @return the hp of the ship
	 */
	public int getHp() {
		return hp;
	}
	/**
	 * Get the speed of the ship
	 * @return the speed of the ship
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Change the hp of the ship due to enemy attacks or potions
	 * @param chHP the amount of hp lost/gained
	 */
	public void setHp(int chHP) {
		hp+=chHP;
		if (hp>maxHp) {
			hp=maxHp;
		}
		if (hp<0) {
			hp=0;
		}
	}
	/**
	 * Restore hp to full
	 */
	public void maxHp() {
		hp=maxHp;
	}
	/**
	 * Get the name of the ship
	 * @return the name of the ship
	 */
	public String getName() {
		return shipName;
	}
}
