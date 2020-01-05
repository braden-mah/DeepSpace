
public class Weapon implements java.io.Serializable{
	private String wType;
	private String wName;
	private int cooldown;
	private int damage;
	private int coolCounter;
	public Weapon (String wT, String wN) {
		wType=wT;
		wName=wN;
	}
	public Weapon (String wT, String wN, int cd) {
		wType=wT;
		wName=wN;
		cooldown=cd;
	}
	public String getName() {
		return wName;
	}
	public void reduceCoolCounter() {
		coolCounter-=1;
	}
	public void maxCoolCounter() {
		coolCounter=cooldown;
	}
	public void setCoolCounter() {
		coolCounter=0;
	}
	public int getCoolCounter() {
		return coolCounter;
	}
}
