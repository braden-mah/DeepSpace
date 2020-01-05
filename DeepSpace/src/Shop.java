
public class Shop implements java.io.Serializable{
	private int row;
	private int column;
	private int items;
	private int limit;
	private String fullShopDialogue=("\n---Shop---\n[L] Leave \n[1] Ship Repair: Free \n[2] Health Potion: 280 \n[3] Juggernaut Ship: 10000 \n[4] Sentinel (LW): 5000 \n[5] Flamethrower (HW): 8000 \n[6] Falconer Ship: 15000 \n[7] Mega Potion: 1000 \n[8]");
	private String shopDialogue;

	public Shop(int row, int column, int num) {
		this.row=row;
		this.column=column;
		items=num;
		
	}
	public boolean checkShop(int pilotRow, int pilotColumn) {
		if (pilotRow==row && pilotColumn==column) {
			return true;
		}
		return false;
	}
	public void displayShop() {
		limit=fullShopDialogue.indexOf("["+(items+1));
		shopDialogue=fullShopDialogue.substring(0,limit);
		System.out.println(shopDialogue);
	}
	public int getItems() {
		return items;
	}
}
