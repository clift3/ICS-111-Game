//Class by Clifton Miyamoto
public class Item {
	static EZImage itemPic;
	private int spawnrate; //probability of spawning
	private static boolean isSpawned;
	Item(){
		itemPic = EZ.addImage("star.png", -100, -100);
		isSpawned = false;
		itemPic.scaleTo(.2);
	}
	
	//function that determines if the item spawns or not with a 2/1001 probability
	//translates item to within the screen, but never within 1/10 of screen size from the edge
	void ItemSpawn(){
		if(!isSpawned){
			spawnrate = Object.random.nextInt(1000);
			if (spawnrate < 2){
				itemPic.translateTo((int)(Object.random.nextInt((int)(0.8 * Main.SCREEN_SIZE_X)) + 0.1 * Main.SCREEN_SIZE_X), 
				(int)(Object.random.nextInt((int)(0.8 * Main.SCREEN_SIZE_Y)) + 0.1 * Main.SCREEN_SIZE_Y));
				isSpawned = true;
			}
		}
	}
	
	//resets item for a new game
	public void reset(){
		itemPic.translateTo(-100, -100);
		isSpawned = false;
	}
	
	//function to determine if the player is on the item
	public static boolean getItem(int x, int y){
		if(itemPic.isPointInElement(x, y)){
			//translates item outside screen, allows rerolling of spawn.
			itemPic.translateTo(-100, -100);
			isSpawned = false;
			return true;
		
		}else{
			return false;
		}
	}

}

