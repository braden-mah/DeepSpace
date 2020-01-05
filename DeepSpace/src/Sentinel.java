
public class Sentinel extends Weapon{
	private int crit;
	private int damage;
	/**
	 * Set the stats for the Sentinel weapon
	 */
	public Sentinel() {
		super("light","Sentinel");
	}
	/**
	 * Attack the enemy with the Sentinel
	 * @return the amount of damage to deal to the enemy
	 */
	public int attack() {
		crit=(int)(Math.random()*10)+1;
		damage=-10;
		if (crit>=7) {
			damage=damage*2;
			System.out.println("You land a critical hit!");
		}
		System.out.println("You deal "+damage+" damage to the enemy!");
		return damage;
	}
}
