
public class Falconer extends Ship{
	private int damage;
	/**
	 * Set the base stats for the falconer ship
	 */
	public Falconer(){
		super(2000,80,80,"Falconer");
	}
	/**
	 * Use the Falconer special attack
	 * @param d the amount of damage the attack does
	 * @return the damage to deal to the enemy
	 */
	public int attack(int d) {
		if (d>300) {
			damage=300;
		}
		else {
			damage=(-1)*d;
		}
		System.out.println("Falcon's Fury deals "+damage+" damage to the enemy!");
		return damage;
	}
}
