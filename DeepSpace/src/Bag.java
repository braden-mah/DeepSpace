public class Bag implements java.io.Serializable{
	private int potionCounter=2;
	private int bigPotionCounter=1;
	/**
	 * Add a potion to your inventory
	 */
	public void addPotion() {
		potionCounter+=1;
	}
	/**
	 * Add a large potion to your inventory
	 */
	public void addBigPotion() {
		bigPotionCounter+=1;
	}
	/**
	 * Get the number of large potions in your inventory
	 * @return the number of large potions you have
	 */
	public int getBigPotion() {
		return bigPotionCounter;
	}
	/**
	 * Get the number of potions in your inventory
	 * @return the number of potions you have
	 */
	public int getPotion() {
		return potionCounter; 
	}
	/**
	 * Drink a potion from your inventory
	 */
	public void drinkPotion() {
		potionCounter-=1;
	}
	/**
	 * Drink a big potion from your inventory
	 */
	public void drinkBigPotion() {
		bigPotionCounter-=1;
	}
	/**
	 * Summary of what is in your bag
	 */
	public String toString() {
		return "\nPotion Count: "+potionCounter+"\nMega Potion Count: "+bigPotionCounter;
	}
}
