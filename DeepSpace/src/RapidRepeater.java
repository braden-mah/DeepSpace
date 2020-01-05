
public class RapidRepeater extends Weapon {
	private boolean shoot;
	private int damage;
	private int counter;
	private int counter2;
	/**
	 * Set the base stats for the rapid repeater
	 */
	public RapidRepeater() {
		super("light","Rapid Repeater");
	}
	/**
	 * Attack the enemy with the rapid repeater
	 * @return the amont of damage to deal to the enemy
	 */
	public int attack() {
		shoot=true;
		damage=0;
		counter2=0;
		counter=0;
		do {
			damage-=3;
			counter2+=1;
			counter=(int)(Math.random()*2)+1;
			if (counter==2) {
				shoot=true;
			}
			else if (counter==1) {
				shoot=false;
			}
		}
		while (shoot==true);
		System.out.println("You shoot your rapid repeater "+counter2+" time(s)!");
		return damage;
	}

}
