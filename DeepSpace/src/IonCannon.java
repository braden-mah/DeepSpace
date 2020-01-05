
public class IonCannon extends Weapon {
	private int damage;
	/**
	 * Set the base stats for the ion cannon
	 */
	public IonCannon() {
		super("heavy","Ion Cannon",3);
	}
	/**
	 * Attack the enemy with the ion cannon
	 * @return the amount of damage to deal to the enemy
	 */
	public int attack() {
		damage=(-1)*((int)(Math.random()*50)+20);
		System.out.println("Your ion cannon deals "+damage+" damage to the enemy!");
		return damage;
		
	}
}
