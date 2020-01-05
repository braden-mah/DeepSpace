
public class HMS_Warrior extends Enemy{
	private int damage;
	private int cooldown=3;
	private int maxCooldown=3;
	/**
	 * Set the stats for the HMS Warrior
	 */
	public HMS_Warrior(){
		super(1000,100,150,"HMS Warrior","the HMS Warrior! Prepare to fight, aviator!", 4000, "boss");
	}
	/**
	 * Heavy attack the pilot
	 * @return the damage to deal to the pilot
	 */
	public int heavyAttack() {
		damage = (int)(-1*((Math.random()*50)+250));
		System.out.println("The HMS Warrior uses its plasma cannon! It deals "+damage+" to your ship!");
		return damage;
	}
	/**
	 * Get the cooldown of the heavy attack
	 * @return the cooldown for the heavy attack
	 */
	public int getCooldown() {
		return cooldown;
	}
	/**
	 * Adjust the cooldown of the heavy attack
	 */
	public void reduceCooldown() {
		if (cooldown==0) {
			cooldown=maxCooldown;
		}
		else {
			cooldown-=1;
		}
	}
}
