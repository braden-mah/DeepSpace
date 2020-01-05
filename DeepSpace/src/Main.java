import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		/*	------------------------------
		 * 	VARIABLE & OBJECT DECLARATION
		 * 	------------------------------
		 */
		
		Scanner sc = new Scanner(System.in);
		String fileName="";
		Map map = new Map();
		map.setPlanet(2, 8, "Veromoz");
		map.setPlanet(3, 3, "Shariff");
		map.setPlanet(8,7,"Polaris");
		map.setPlanet(1,12,"Centurus");
		
		/*  ARRAY LISTS
		 *  criminals    	- stores the coordinates of the criminals, as well as other important values
		 *  saveData  		- object arrayList used to save important objects and values
		 *  loadData  		- object arrayList used to load saved files
		 *  ship      		- arrayList used to store your currently equipped ship, which changes throughout the game
		 *  enemy     		- arrayList used to store the data of the enemy when in combat
		 *  weapon    		- arrayList used to store your currently equipped light weapon and heavy weapon
		 *  shops     		- arrayList used to store the different shops that you will be able to access throughout the game
		 *  missions  		- arrayList used to store the coordinates which you must travel to in order to complete a mission
		 *  missionPlanet   - arrayList used to store the name of the mission (names based on the planets)
		 *  missionShopItems- arrayList used to store the number of items which are accessible in each shop (new shop opens up once a mission is complete)
		 *  bossCoords      - arrayList used to store the coordinates of the different bosses
		 */
		
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();
		ArrayList<Object> saveData = new ArrayList<Object>();
		ArrayList<Object> loadData = new ArrayList<Object>();
		ArrayList<Ship> ship = new ArrayList<Ship>();
		ArrayList<Enemy> enemy = new ArrayList<Enemy>();
		ArrayList<Weapon> weapon = new ArrayList<Weapon>();
		ArrayList <Shop> shops = new ArrayList<Shop>();
		ArrayList<int[]> missions=new ArrayList<int[]>();
		ArrayList<String> missionPlanet=new ArrayList<String>();
		ArrayList<Integer> missionShopItems=new ArrayList<Integer>();
		ArrayList<int[]> bossCoords = new ArrayList<int[]>();
		bossCoords.add(new int[] {0,0});
		
		/*
		 *  MISSION DECLARATION
		 *  The three arrayLists missions, missionPlanet, and missionShopItems are all interconnected.
		 *  Once one mission is completed, the corresponding element from each arrayList (at index 0) for that mission will perform a task and then be removed.
		 *  Afterwards, the next mission will be available, as it will be bumped up to index 0.
		 */
		
		missions.add(new int[] {2,7});
		missionPlanet.add("Veromoz");
		missionShopItems.add(4);
		missions.add(new int[] {3,2});
		missionPlanet.add("Shariff");
		missionShopItems.add(5);
		missions.add(new int[] {9,7});
		missionPlanet.add("Polaris");
		missionShopItems.add(6);
		missions.add(new int[] {2,12});
		missionPlanet.add("Centurus");
		missionShopItems.add(7);
		missions.add(new int[] {-1,-1});
		missionPlanet.add("Filler");
		missionShopItems.add(1);
		
		Bag bag = new Bag();
		Pilot pilot = new Pilot();
		weapon.add(new RapidRepeater());
		weapon.add(new IonCannon());
		ship.add(new Rusty());
		
		/*
		 * IMPORTANT VALUES
		 * response 			- records the value you input at the start of the game
		 * command  			- records the value you input throughout the game
		 * counter				- used in combat to determine who gets to attack first
		 * weaponDamage			- store the damage dealt by your weapon
		 * damage				- store the overall damage dealt (damage from ship + damage from weapon)
		 * pilotName			- store the name of the pilot
		 * enemyType 			- store the difficulty of the enemy you will be facing
		 * attackType			- store the type of attack used (similar to command but for combat)
		 * bossName				- store the name of the boss you will be facing
		 * numItems				- store the number of items available in the shop
		 * fightEnemy			- determine whether or not you are in combat
		 * turn					- determine whose turn it is in combat
		 * attack				- determine whose turn it is to attack
		 * goAgain				- determine whether or not you get to attack again (i.e., an invalid response will prompt you to go again, and type in an actual attack)
		 * move					- determine if you can move to a specific coordinate, or if that coordinate is out of bounds
		 * heavyAttack			- determine if a heavy attack was used
		 * createShop			- create a new shop after a mission has been completed
		 * drinkPotion			- determine whether or not a potion has been drunk
		 * spawnBoss			- determine whether or not to spawn the boss
		 * hmsDown				- determine whether or not the HMS Warrior boss has been defeated
		 * skipIntro			- determine whether or not to skip the intro (e.g., if you load saved data)
		 * releasePrisoners		- determine whether or not to start the generation of criminals in the game (begins after 'Centurus' mission has been completed)
		 * newPrisoner			- determine whether or not to generate a new criminal (e.g., after you have captured one)
		 * prisonerCaught		- determine whether or not the criminal has been caught
		 * regex				- hold all possible digit entries
		 * tracker				- states the direction that the criminal is in relative to you
		 * priceArray			- store the prices of the different items in the shop
		 */
		
		String response=""; 
		int counter=0;
		int weaponDamage=0;
		int damage;
		String pilotName;
		String command="";
		String enemyType;
		String attackType;
		String bossName="";
		int numItems=0;
		boolean fightEnemy=false;
		boolean turn=false;
		boolean attack=false;
		boolean goAgain=false;
		boolean move=false;
		boolean heavyAttack=false;
		boolean createShop=false;
		boolean drinkPotion=false;
		boolean spawnBoss=false;
		boolean hmsDown=false;
		boolean skipIntro=false;
		boolean releasePrisoners=false;
		boolean newPrisoner=true;
		boolean prisonerCaught=false;
		String regex="\\d+";
		String tracker="";
		int[] priceArray = {0,280,10000,4000,8000,15000,1000};

		/* -----------------------------------
		 * 		CODE FOR THE GAME
		 * -----------------------------------
		 */
		
		System.out.println("-----------");
		System.out.println("DEEP SPACE");
		System.out.println("-----------");
		System.out.println("A text-based space adventure");
		System.out.print(">");
		sc.nextLine();
		System.out.println("[0] New Game");
		System.out.println("[1] Load Game");
		do {
			System.out.print(">");
			command=sc.nextLine();
			if (!command.equals("0") && !command.equals("1")) {
				System.out.println("Not a valid input.");
			}
		}
		while (!command.equals("0") && !command.equals("1"));
		
		/*	CODE TO LOAD SAVED DATA
		 * 	Load serialized files and extract the data from the object arrayList
		 */
		
		if (command.equals("1")) {
			try {
				System.out.println("What is your file called?");
				System.out.print(">");
				command=sc.nextLine();
				fileName=command+".ser";
				FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream (file);
				loadData=(ArrayList<Object>)in.readObject();
				pilot=(Pilot)loadData.get(0);
				bag=(Bag)loadData.get(1);
				ship=(ArrayList<Ship>)loadData.get(2);
				weapon=(ArrayList<Weapon>)loadData.get(3);
				shops=(ArrayList<Shop>)loadData.get(4);
				missions=(ArrayList<int[]>)loadData.get(5);
				missionPlanet=(ArrayList<String>)loadData.get(6);
				missionShopItems=(ArrayList<Integer>)loadData.get(7);
				bossCoords=(ArrayList<int[]>)loadData.get(8);
				map=(Map)loadData.get(9);
				releasePrisoners=(boolean)loadData.get(10);
				bossName=(String)loadData.get(11);
				hmsDown=(boolean)loadData.get(12);
				in.close();
				file.close();
				System.out.println("Data from "+command+" file has been loaded!");
				skipIntro=true;
				
			}
			catch(IOException i) {
				System.out.println("Sorry, you don't have saved data.");
				System.out.print(">");
				sc.nextLine();
				skipIntro=false;
			}
			catch(ClassNotFoundException e) {
			}	
		}
		
		/*	INTRO TEXT CODE
		 * 	Skipped if you load saved data!
		 */
		
		if (skipIntro==false) {
			System.out.println("---Incoming message---\nAttention all Deep Space Aviators. We have been overrun.\nHostile extraterrestrials control Xanderian, Gemini, Scorch, and Apollean trade routes.\nWithout access to these routes, we cannot send resources to our colonized planets.\nThese planets will perish.\nWe need you to use your spaceship to go behind enemy lines to deliver food and water to our isolated colonies.\nGood luck, aviator.\n----------------------");
			sc.nextLine();
			System.out.println("???: Aviator, can you hear me?");
			do {
				if (counter!=0) {
					System.out.println("Not a valid statement.");
				}
				System.out.print("> ");
				response=sc.nextLine();
				counter+=1;
			} while (!response.equals("yes")&&!response.equals("no"));
			counter=0;
			if (response.equals("yes")) {
				System.out.println("???: What a relief! The name's Hugo. What's your name, pilot?");
			}
			else if (response.equals("no")) {
				System.out.println("???: Uhh... ok. You're talking, so I'll take that as a yes.  The name's Hugo.  And you, pilot?");
			}
			System.out.print(">");
			pilotName=sc.nextLine();
			pilot.setName(pilotName);
			System.out.println("Hugo: Nice to meet you, "+pilotName+"! I work for the Department of Colony Provision on Earth.\n      As you already know, our colonies are in trouble.\n      Without any food, billions of people on different planets are going to die.\n      We need you to deliver supplies to our colonies.\n      Please, if you can, get to planet Veromoz, and drop off food and drinks.");
			System.out.println("\n(Press 'enter' to continue)");
			System.out.print(">");
			sc.nextLine();
			System.out.println("(Type in \"help\" for instructions)");
		}
		
		/*	-----------------------------
		 * 			MAIN GAME CODE
		 * 	code that is run while moving
		 * 	around the map, fighting,
		 * 	buying items from shops, etc.
		 * 	-----------------------------
		 */
		
		while (true) {
			
			/*	INDIVIDUAL MISSION CODE
			 * 	The story line code for each individual mission
			 * 	After a mission is completed, a shop is created at that coordinate
			 */
			
			if (map.getPilotRow()==missions.get(0)[0] && map.getPilotColumn()==missions.get(0)[1]) {
				if (missionPlanet.get(0).equals("Veromoz")) {
					System.out.println("\nYou drop off supplies to the planet of Veromoz!\n");
					System.out.println("Hugo: Great work, aviator!  The people of Veromoz are indebted to you!\n      They've decided to open up their shipyard for you! Go there to buy ship upgrades, weapons, and items!");
					System.out.println("      Oh and by the way, if you could drop off some weaponry at Shariff, that would be great.\n      Their military could really use some help.");
					createShop=true;
				}
				else if (missionPlanet.get(0).equals("Shariff")) {
					System.out.println("\nHugo: Excellent work, "+pilot.getName()+"!  Shariff has opened up their shops for you!\n      This is a military outpust, so expect to see a lot of weaponry!\n      Huh... I think that's enough missions for the day.\n      Polaris is the last planet on the region, but I haven't heard from them in a while, so they should be fine.\n      Have fun exploring, aviator!");
					createShop=true;
				}
				else if (missionPlanet.get(0).equals("Polaris") && hmsDown==false) {
					System.out.println("You touch down on the planet of Polaris.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("...");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Something doesn't seem right.");
					System.out.println("Where is everyone? Polaris is a small colony, but it's daytime, so people should still be going about their daily business.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Suddenly, you hear footsteps behind you.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("You turn around and see an old man.");
					System.out.println("Village Elder: Sir, our people have been kidnapped!");
					System.out.print(">");
					sc.nextLine();
					System.out.println("You: What happened?");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Village Elder: A Geminean ship abducted all of the villagers except for me!  Please, save them!\n               The ship was last spotted in the Reaver's Astrobelt!");
					do {
						System.out.println();
						System.out.println("[0] Hmmm... where is this astrobelt?");
						System.out.println("[1] Did the ship have a name?");
						System.out.println("[2] Don't worry sir.  I'll handle this.");
						System.out.print(">");
						command=sc.nextLine();
						if (command.equals("0")) {
							System.out.println("Village Elder: The Reaver's Astrobelt is a narrow path that connects the Geminean Region to the Appolean Region!");
						}
						else if (command.equals("1")) {
							System.out.println("Village Elder: I'm pretty sure it was called the HMS Warrior...");
						}
						else if (command.equals("2")) {
							System.out.println("Vilage Elder: Thank you so much, aviator.");
							bossCoords.add(0,new int[] {12,6});
							bossName="HMS Warrior";
						}
						else {
							System.out.println("Not a valid command.");
						}
					}
					while (!command.equals("2"));
				}
				else if (missionPlanet.get(0).equals("Polaris") && hmsDown==true) {
					System.out.println("You safely return the people of Polaris to their colony");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Village Elder: Thank you!  I thought I'd never see my friends again! How can we repay you?");
					System.out.println();
					System.out.println("[0] With gold, maybe?");
					System.out.println("[1] Don't worry about it.");
					System.out.print(">");
					do {
						command=sc.nextLine();
						if (command.equals("0")) {
							System.out.println("Village Elder: Fair enough!  Here's 10000 gold to take on your journeys!");
							pilot.setGold(10000);
							System.out.println("You earned 10000 gold.");
						}
						else if (command.equals("1")) {
							System.out.println("Village Elder: You're too kind!  Here, take this new ship I've been building called the Falconer.\n               It has a special attack which deals damage based upon the number of enemies you have defeated, up to a maximum value.\n               Use it well, aviator!");
							ship.clear();
							ship.add(new Falconer());
						}
						else {
							System.out.println("Not a valid command.");
						}
					}
					while (!command.equals("1")&&!command.equals("0"));
					System.out.println("Hugo: Well, we've taken care of all the planets in the Gemini Region.\n      When you're ready, head on over to Centurus in the Apollo Region.\n      People over there have requested for assistance.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("You: The Apollo Region?");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Hugo: It's the region which is just past the Reaver's Astrobelt.  Just keep going right, you'll get there eventually.");
					createShop=true;
				}
				else if (missionPlanet.get(0).equals("Centurus")){
					System.out.println("Mr. President: Welome to Centurus, aviator! My name is Wardell, and I am the leader of the megaplanet, Centurus!" );
					System.out.print(">");
					sc.nextLine();
					System.out.println("You: I have some supplies in my cargo bay. Let me go grab them.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Mr. President: Don't worry about it!  I'll have my guards unload your inventory!");
					sc.nextLine();
					System.out.println("[0] Alright, thanks.");
					System.out.println("[1] What angle are you playing at...");
					do {
						System.out.print(">");
						command=sc.nextLine();
					}
					while (!command.equals("0")&&!command.equals("1"));
					if (command.equals("0")) {
						System.out.println("Mr. President: No problem!");
						System.out.print(">");
						sc.nextLine();
						System.out.println("Mr. President: Actually... I need a few favors.");
						System.out.print(">");
						sc.nextLine();
						System.out.println("Mr. President: A ton of criminals recently escaped our high-security prison, and are out in the loose.\\n               If you can catch them, I will reward you handsomely.");
								
					}
					else if (command.equals("1")) {
						System.out.println("Mr. President: Alright, fine! Listen, I need a few favors.\n               A ton of criminals recently escaped our high-security prison, and are out in the loose.\n               If you can catch them, I will reward you handsomely.");
					}
					System.out.print(">");
					sc.nextLine();
					System.out.println("You: Tell me what I need to do.");
					System.out.print(">");
					sc.nextLine();
					System.out.println("Mr. President: The criminals are scattered throughout the galaxy.\n               If you manage to find them, you'll capture them immediately, and will be rewarded through coins.\n               Here is a tracking device to help you find them.  Type in 'track' in order to activate it.");
					System.out.print(">");
					sc.nextLine();
					createShop=true;
					releasePrisoners=true;
				}
			}
			
			/*	CRIMINAL CATCH MINIGAME CODE
			 * 	Activates upon completing the Centurus Mission
			 * 	if block (#1) - checks to see whether you have captured the criminal (must be at the same coordinates as the criminal)
			 * 	if block (#2) - generates a new criminal on the map (only happens once you have captured a criminal)
			 */
			
			if (releasePrisoners==true && newPrisoner==false) {
				prisonerCaught=criminals.get(0).check(map.getPilotRow(), map.getPilotColumn());
				if (prisonerCaught==true) {
					System.out.println("Criminal caught!");
					System.out.println("You got "+criminals.get(0).getGold()+" for capturing "+criminals.get(0).getName());
					System.out.print(">");
					sc.nextLine();
					pilot.chGold(-1*criminals.get(0).getGold());
					newPrisoner=true;
					prisonerCaught=false;
					criminals.clear();
				}
			}
			if (releasePrisoners==true && newPrisoner==true) {
				criminals.add(new Criminal(map.getMapArray()));
				System.out.println("A new criminal has been detected!\nName: "+criminals.get(0).getName()+"\nBounty: "+criminals.get(0).getGold());
				System.out.print(">");
				sc.nextLine();
				newPrisoner=false;
			}
			
			/*	SHOP CODE
			 * 	if block (#1) - creates a shop at the coordinates outlined in the mission arrayList
			 * 	for loop (#2) - checks to see if any of the shops in the arrayList have the same coordinates as you
			 * 				 	if the coordinates match, the shop is displayed, and you can input different numbers in order to buy weapons, potions, repair your ship, etc.
			 */
			
			if (createShop==true) {
				System.out.println("\n(Press 'enter' to continue)");
				System.out.print(">");
				sc.nextLine();
				shops.add(new Shop(missions.get(0)[0], missions.get(0)[1], missionShopItems.get(0)));
				missions.remove(0);
				missionPlanet.remove(0);
				System.out.println("Shop Items: "+missionShopItems.get(0));
				missionShopItems.remove(0);
				createShop=false;
			}
			for (Shop s: shops) {
				if (s.checkShop(map.getPilotRow(), map.getPilotColumn())==true) {
					do {
						s.displayShop();
						numItems=s.getItems();
						System.out.print(">");
						command=sc.nextLine();
						if (command.matches(regex)) {
							if (Integer.parseInt(command)<=numItems && Integer.parseInt(command)>=1 && pilot.getGold()>=priceArray[Integer.parseInt(command)-1]) {
								if (command.equals("1")) {
									ship.get(0).maxHp();
									System.out.println("You repair your ship!");
								}
								else if (command.equals("2")) {
									System.out.println("How many health potions would you like?");
									System.out.print(">");
									command=sc.nextLine();
									if (command.matches(regex)&&Integer.parseInt(command)>0) {
										if (pilot.getGold()>=(Integer.parseInt(command)*priceArray[1])){
											for (int i=0; i<Integer.parseInt(command); i++) {
												bag.addPotion();
											}
											pilot.chGold(Integer.parseInt(command)*priceArray[1]);
											System.out.println("You buy "+command+" potions!");
											System.out.println("You have "+pilot.getGold()+" gold remaining.");
										}
										else {
											System.out.println("Sorry, you don't have enough gold.");
											System.out.println("Current Gold: "+pilot.getGold());
										}
									}
									else {
										System.out.println("Not a valid input.");
									}
								}
								else if (command.equals("3")) {
									ship.clear();
									System.out.println("You buy the Juggernaut Ship.");
									ship.add(new Juggernaut());
									pilot.chGold(10000);
									System.out.println("You have "+pilot.getGold()+" gold remaining.");
								}
								else if (command.equals("4")) {
									System.out.println("You buy the Sentinel Rifle.");
									weapon.remove(0);
									weapon.add(0, new Sentinel());
									pilot.chGold(4000);
									System.out.println("You have "+pilot.getGold()+" gold remaining.");
								}
								else if (command.equals("5")) {
									System.out.println("You buy the Flamethrower.");
									weapon.remove(1);
									weapon.add(new Flamethrower());
									pilot.chGold(8000);
									System.out.println("You have "+pilot.getGold()+" gold remaining.");
								}
								else if (command.equals("6")) {
									System.out.println("You buy the Falconer Ship");
									ship.clear();
									ship.add(new Falconer());
									pilot.chGold(15000);
									System.out.println("You have "+pilot.getGold()+" gold remaining.");
								}
								else if (command.equals("7")) {
									System.out.println("How many mega potions would you like?");
									System.out.print(">");
									command=sc.nextLine();
									if (command.matches(regex)&&Integer.parseInt(command)>0) {
										if (pilot.getGold()>=(Integer.parseInt(command)*priceArray[6])){
											for (int i=0; i<Integer.parseInt(command); i++) {
												bag.addBigPotion();
											}
											pilot.chGold(Integer.parseInt(command)*priceArray[6]);
											System.out.println("You buy "+command+" potions!");
											System.out.println("You have "+pilot.getGold()+" gold remaining.");
										}
										else {
											System.out.println("Sorry, you don't have enough gold.");
											System.out.println("Current Gold: "+pilot.getGold());
										}
									}
									else {
										System.out.println("Not a valid input.");
									}
								}
							}
							else if (Integer.parseInt(command)<=numItems && Integer.parseInt(command)>=1 && pilot.getGold()<priceArray[Integer.parseInt(command)-1]){
								System.out.println("Sorry, you don't have enough gold.");
								System.out.println("Current Gold: "+pilot.getGold());
								}
							else {
								System.out.println("Not a valid input.");
							}
						}
						else if (!command.equalsIgnoreCase("L")){
							System.out.println("Not a valid input.");
						}
					}
					while (!command.equalsIgnoreCase("L"));
				}
			}
			
			/*-----------------------------
			 * 			MAP CODE
			 * Code that is run while moving
			 * around the map
			 * 
			 * Different commands can be
			 * entered, each executes a diff.
			 * task
			 * ----------------------------
			 */
			
			move=false;
			map.setRegion();
			map.showMap();
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.print(">");
			command=sc.nextLine();
			
			/*	MOVEMENT CODE
			 * 	input diff. values to move in diff. directions
			 */
			
			if (command.equals("right")||command.equalsIgnoreCase("d")) {
				move=map.checkMap(0,1);
				if (move==false) {
					System.out.println("Not valid");
				}
			}
			else if (command.equals("left")||command.equalsIgnoreCase("a")) {
				move=map.checkMap(0,-1);
				if (move==false) {
					System.out.println("Not valid");
				}
			}
			else if (command.equals("up")||command.equalsIgnoreCase("w")) {
				move=map.checkMap(-1,0);
				if (move==false) {
					System.out.println("Not valid");
				}
			}
			else if (command.equals("down")||command.equalsIgnoreCase("s")) {
				move=map.checkMap(1,0);
				if (move==false) {
					System.out.println("Not valid");
				}
			}
			
			/*	CRIMINAL CATCH MINIGAME CODE PART 2
			 * 	This is the code that allows you to track the criminal
			 * 	Can find their position relative to you
			 * 	Only unlocked after beating Centurus mission
			 */
			
			else if (command.equals("track")&& releasePrisoners==true) {
				if (criminals.get(0).getRow()==map.getPilotRow()) {
					if (criminals.get(0).getColumn()>map.getPilotColumn()) {
						tracker="east";
					}
					else if (criminals.get(0).getColumn()<map.getPilotColumn()) {
						tracker="west";
					}
				}
				else if (criminals.get(0).getColumn()==map.getPilotColumn()) {
					if (criminals.get(0).getRow()>map.getPilotRow()) {
						tracker="south";
					}
					else if (criminals.get(0).getRow()<map.getPilotRow()) {
						tracker="north";
					}
				}
				else if (criminals.get(0).getColumn()<map.getPilotColumn()){
					if (criminals.get(0).getRow()<map.getPilotRow()) {
						tracker="northwest";
					}
					else if (criminals.get(0).getRow()>map.getPilotRow()) {
						tracker="southwest";
					}
				}
				else if (criminals.get(0).getColumn()>map.getPilotColumn()){
					if (criminals.get(0).getRow()<map.getPilotRow()) {
						tracker="northeast";
					}
					else if (criminals.get(0).getRow()>map.getPilotRow()) {
						tracker="southeast";
					}
				}
				System.out.println(criminals.get(0).getName()+" is "+tracker+" of you!");
			}
			
			/*	SUMMARY & HELP
			 * 	else if block (#1) - prints out a summary of your ship
			 * 	else if block (#2) - prints out instructions for the game
			 * 	else if block (#3) - prints out in-game missions
			 */
			
			else if (command.equals("summary")) {
				System.out.println(ship.get(0));
				System.out.println("---Weapons---\nLight Weapon: "+weapon.get(0).getName()+"\nHeavy Weapon: "+weapon.get(1).getName());
				System.out.println();
				System.out.println("---Gold---\nYour gold: "+pilot.getGold());
				System.out.println();
				System.out.println("---Inventory---"+bag+"\n");
			}
			else if (command.equals("help")||command.equals("instructions")) {
				map.getMovementHelp();
			}
			else if (command.equals("mission")) {
				System.out.println("---Mission List---");
				System.out.println("Mission 1: Get to planet Veromoz");
				System.out.println("Mission 2: Get to planet Shariff");
				System.out.println("Mission 3: Get to planet Polaris");
				System.out.println("Mission 4: Defeat the HMS Warrior");
				System.out.println("Mission 5: Get to planet Centurus");
			}
			
			/*	HACK CODE
			 * 	different cheat codes that allow you to progress through the game faster
			 * 	else if block (#1) - restores hp to full
			 * 	else if block (#2) - gives you money based on how much you enter
			 * 	used mainly for the purpose of debugging
			 * 	feel free to use these hacks Mr. Lu, or else the game will take too long to grind out
			 */
			
			else if (command.equals("max")) {
				ship.get(0).maxHp();			
				}
			else if (command.equals("money")) {
				System.out.println("How much?");
				System.out.print(">");
				command=sc.nextLine();
				System.out.println("You received "+command+" gold");
				pilot.setGold(Integer.parseInt(command));
			}
			
			/*	SAVE CODE
			 * 	code used to save your game progress through an object arrayList
			 */
			
			else if (command.equals("save")) {
				try {
					saveData.add(pilot);
					saveData.add(bag);
					saveData.add(ship);
					saveData.add(weapon);
					saveData.add(shops);
					saveData.add(missions);
					saveData.add(missionPlanet);
					saveData.add(missionShopItems);
					saveData.add(bossCoords);
					saveData.add(map);
					saveData.add(releasePrisoners);
					saveData.add(bossName);
					saveData.add(hmsDown);
					System.out.println("What is your save file called?");
					System.out.print(">");
					command=sc.nextLine();
					fileName=command+".ser";
					FileOutputStream file = new FileOutputStream(fileName);
					ObjectOutputStream out = new ObjectOutputStream (file);
					out.writeObject(saveData);
					out.close();
					file.close();
					System.out.println(command+" file has been saved!");
					System.out.println("Your data has been saved successfully!");
					System.out.print(">");
					sc.nextLine();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
			
			/*	--------------------------
			 * 		   COMBAT CODE
			 * 	 Code that is run while 
			 * 	 fighting different enemies
			 * 	--------------------------
			 */
			
			if (move==true) {
				
				/*	ENEMY SPAWN CODE
				 * 	if block (#1) - spawns the boss HMS warrior if you are at the same coordinates as it, and have beat Polaris Mission part 1
				 * 	if block (#2) - spawn enemies based on the region you are in and the difficulty of the enemy
				 */
				
				if (map.getPilotRow()==bossCoords.get(0)[0]&&map.getPilotColumn()==bossCoords.get(0)[1]) {
					spawnBoss=true;
					fightEnemy=true;
					if (bossName.equals("HMS Warrior")) {
						enemy.add(new HMS_Warrior());
					}
					
				}
				enemyType=map.spawnEnemy();
				if (!enemyType.equals("false") && spawnBoss==false) {
					fightEnemy=true;
					if (enemyType.equals("easy")) {
						if (map.getRegion().equals("Gemini Region")||map.getRegion().equals("Reaver's Astrobelt")) {
							enemy.add(new GruntShip());
						}
						else if (map.getRegion().equals("Apollo Region")) {
							enemy.add(new Vendetta());
						}
					}
					else if (enemyType.equals("hard")) {
						if (map.getRegion().equals("Gemini Region")||map.getRegion().equals("Reaver's Astrobelt")) {
							enemy.add(new SquadLeaderShip());
						}
						else if (map.getRegion().equals("Apollo Region")) {
							enemy.add(new Zeus());
						}
					}
				}
				if (fightEnemy==true) {
					enemy.get(0).encounter();
					enemy.get(0).printStats();
					while ((enemy.get(0).getHp()>0)&&(ship.get(0).getHp()>0)) {
						if (counter==0) {
							if (enemy.get(0).getSpeed()>ship.get(0).getSpeed()) {
								turn=false;
							}
							else {
								turn=true;
							}
						}
						counter+=1;
						if (turn==false) {
							
							/*	ENEMY ATTACK PHASE CODE
							 * 	if block (#1) - attack code for a regular enemy
							 * 	else if block (#2) - attack code for a boss, a bit more complex due to heavy attacks
							 */
							
							if (enemy.get(0).getType().equals("scrub")) {
								ship.get(0).setHp(enemy.get(0).attack());
								System.out.println(enemy.get(0).getName()+" attacks you!\n");
								System.out.println(ship.get(0));
								System.out.println("Press 'enter' to continue");
								System.out.print(">");
								sc.nextLine();
								turn=true;
							}
							else if (enemy.get(0).getType().equals("boss")){
								if (enemy.get(0).getName().equals("HMS Warrior")) {
									if (((HMS_Warrior)enemy.get(0)).getCooldown()==3) {
										ship.get(0).setHp(((HMS_Warrior)enemy.get(0)).heavyAttack());
										System.out.println(ship.get(0));
										System.out.println("Press 'enter' to continue");
										System.out.print(">");
										sc.nextLine();
										turn=true;
									}
									else if (((HMS_Warrior)enemy.get(0)).getCooldown()!=3) {
										ship.get(0).setHp(enemy.get(0).attack());
										System.out.println(enemy.get(0).getName()+" attacks you!\n");
										System.out.println(ship.get(0));
										System.out.println("Press 'enter' to continue");
										System.out.print(">");
										sc.nextLine();
										turn=true;
									}
									((HMS_Warrior)enemy.get(0)).reduceCooldown();
								}
							}
						}
						else if (turn==true) {
							
							/*	PLAYER ATTACK PHASE CODE
							 * 	L - perform a light attack
							 * 	H - perform a heavy attack
							 * 	P - heal up with a potion (regular potion or mega potion)
							 * 	F - special falcon's fury attack for people with the falconer ship
							 * 	run - easter egg reference I guess lol
							 */
							
							System.out.println("What would you like to do?\n----------------------\n\n[L] Light Attack\n[H] Heavy Attack\n[P] Drink Potion");
							if (ship.get(0).getName().equals("Falconer")) {
								System.out.println("[F] Falcon's Fury");
							}
							System.out.print(">");
							attackType=sc.nextLine();
							if (attackType.equalsIgnoreCase("L")) {
								if (weapon.get(0).getName().equals("Rapid Repeater")) {
									weaponDamage=((RapidRepeater)weapon.get(0)).attack();
								}
								else if (weapon.get(0).getName().equals("Sentinel")) {
									weaponDamage=((Sentinel)weapon.get(0)).attack();
								}
							}
							else if (attackType.equalsIgnoreCase("H")) {
								if (weapon.get(1).getCoolCounter()==0) {
									heavyAttack=true;
									if (weapon.get(1).getName().equals("Ion Cannon")) {
										weaponDamage=((IonCannon)weapon.get(1)).attack();
									}
									else if (weapon.get(1).getName().equals("Flamethrower")) {
										weaponDamage=((Flamethrower)weapon.get(1)).attack();
									}
								}
								else {
									System.out.println("The "+weapon.get(1).getName()+" is on cooldown!\n"+weapon.get(1).getCoolCounter()+" turn(s) remaining!");
									goAgain=true;
								}
							}
							else if (attackType.equalsIgnoreCase("F") && ship.get(0).getName().equals("Falconer")) {
								weaponDamage=((Falconer)ship.get(0)).attack(pilot.getKillCounter());
							}
							else if (attackType.equalsIgnoreCase("P")) {
								System.out.println("[0] Potion");
								System.out.println("[1] Mega Potion");
								do {
									System.out.print(">");
									command=sc.nextLine();
								}
								while (!command.equals("0")&&!command.equals("1"));
								if (command.equals("0")) {
									if (bag.getPotion()>0) {
										bag.drinkPotion();
										ship.get(0).setHp(250);
										System.out.println("You heal for 250 health!");
										System.out.println("You have "+bag.getPotion()+" potions remaining!");
										System.out.println(ship.get(0));
										drinkPotion=true;
									}
									else {
										System.out.println("You have no potions!");
										goAgain=true;
									}
								}
								else if (command.equals("1"))
									if (bag.getBigPotion()>0) {
										bag.drinkBigPotion();
										ship.get(0).setHp(650);
										System.out.println("You heal for 650 health!");
										System.out.println("You have "+bag.getBigPotion()+" mega potions remaining!");
										System.out.println(ship.get(0));
										drinkPotion=true;
									}
									else {
										System.out.println("You have no mega potions!");
										goAgain=true;
									}
							}
							else if (attackType.equalsIgnoreCase("run")) {
								System.out.println("You cannot fast travel when enemies are nearby.");
								goAgain=true;
							}
							else {
								System.out.println("Not a valid command.");
								goAgain=true;
							}
							if (goAgain!=true && drinkPotion!=true) {
								damage=weaponDamage+ship.get(0).attack();
								enemy.get(0).setHp(damage);
								System.out.println("You attack "+enemy.get(0).getName()+"!\n");
								attack=true;
								}
							if (weapon.get(1).getName().equals("Flamethrower")&&goAgain!=true) {
								if (((Flamethrower)weapon.get(1)).getBurnCounter()>0) {
									enemy.get(0).setHp(((Flamethrower)weapon.get(1)).burn());
									attack=true;
								}
							}
							if (attack==true) {
								enemy.get(0).printStats();
								attack=false;
							}
							if (drinkPotion==true) {
								drinkPotion=false;
							}
							if (weapon.get(1).getCoolCounter()!=0 && (goAgain!=true)) {
								weapon.get(1).reduceCoolCounter();
							}
							else if (weapon.get(1).getCoolCounter()==0 && heavyAttack==true){
								weapon.get(1).maxCoolCounter();
								heavyAttack=false;
							}
							if (goAgain==false) {
								turn=false;
							}
							else {
								turn=true;
								goAgain=false;
							}
						}
					}
					counter=0;
					weapon.get(1).setCoolCounter();
					if (ship.get(0).getHp()<=0) {
						System.out.println("You have been defeated."); 
						pilot.chGold(3000);
						System.out.println("You lost 3000 gold!");
						map.setPilotRow(1);
						map.setPilotColumn(1);
						
					}
					else if (enemy.get(0).getHp()<=0) {
						pilot.addKillCounter();
						System.out.println("The "+enemy.get(0).getName()+" has been defeated!");
						pilot.setGold(enemy.get(0).getGold());
						System.out.println("You got "+enemy.get(0).goldStatement()+" gold from the "+enemy.get(0).getName()+"!");
					    System.out.println();
					    if (weapon.get(1).getName().equals("Flamethrower")) {
					    	((Flamethrower)weapon.get(1)).resetBurnCounter();
					    }
					    if (enemy.get(0).getName().equals("HMS Warrior")) {
					    	hmsDown=true;
					    	bossCoords.remove(0);
							System.out.println("You rescue the captives of Polaris!");
							System.out.print(">");
							sc.nextLine();
							System.out.println("Hugo: That was impressive, "+pilot.getName()+". I had no idea they were in danger.\n      Let's bring them back to their planet.");
							spawnBoss=false;
					    }
					}
					enemy.clear();
					fightEnemy=false;
				}
			}	
		}
	}
	
}
