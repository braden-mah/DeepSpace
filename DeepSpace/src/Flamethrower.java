
public class Flamethrower extends Weapon{
	private int damage;
	private int burnDamage;
	private int burnCounter=0;
	/**
	 * Set the base stats for the flamethrower
	 */
	public Flamethrower() {
		super("heavy","Flamethrower", 3);
	}
	/**
	 * Attack the enemy
	 * @return the damage to deal to the enemhy
	 */
	public int attack() {
		damage=(-1)*((int)(Math.random()*10)+40);
		System.out.println("The flamethrower deals "+damage+" to the enemy and applies burn!");
		burnCounter=3;
		return damage;
	}
	/**
	 * Deal burn damage to the enemy
	 * @return the amount of burn damage to deal to the enemy
	 */
	public int burn() {
			burnDamage=(-1)*((int)(Math.random()*10)+7);
			System.out.println("The enemy takes "+burnDamage+" burn damage!");
			burnCounter-=1;
			return burnDamage;
		
	}
	/**
	 * Get the number of turns of burn damage left
	 * @return the number of turns of damage over time left applied through burn
	 */
	public int getBurnCounter() {
		return burnCounter;
	}
	/**
	 * Reset the burn counter to 0
	 */
	public void resetBurnCounter() {
		burnCounter=0;
	}
}
