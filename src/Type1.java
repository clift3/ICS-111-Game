//Class by Clifton Miyamoto
public class Type1 extends Object{
	private int spawnY;
	
	//Constructor
	Type1(){
		super();
		//Sets the position of the object and rotates it by a random integer between 0 and 180 
		spawnY = Main.SCREEN_SIZE_Y/20 * 9;
		posX = Main.SCREEN_SIZE_X/2;
		posY = Main.SCREEN_SIZE_Y/2 - spawnY;
		
		objectpic = EZ.addImage("redbullet.png", posX, posY);
		halfObjectpicW = objectpic.getWidth()/2;
		halfObjectpicH = objectpic.getHeight()/2;
		objectpic.rotateBy(random.nextInt(180));
	}
	
	// function to add Type1 objects into the array list
	static void patternEasy(){
		int NUMBER = 10;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type1());
		}
	}
	
	static void patternNormal(){
		int NUMBER = 30;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type1());
		}
	}
	
	static void patternHard(){
		int NUMBER = 80;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type1());
		}
	}
	
	//hides all objects and clears array list
	static void clear(){
		for(int i = 0; i<arr.size(); i++){
			arr.get(i).hide();
		}
		arr.clear();
		
	}
	
	//Function to move the object
	void go(){
		if(active){
		objectpic.moveForward(speed);
		posX = objectpic.getXCenter();
		posY = objectpic.getYCenter();
		}
		
		// When the object moves off the screen, move the object to the vertically mirrored spawn point
		if(objectpic.getXCenter() < -100 || objectpic.getXCenter() > Main.SCREEN_SIZE_X + 100 || 
			objectpic.getYCenter() < -100 || objectpic.getYCenter() > Main.SCREEN_SIZE_Y + 100){
			spawnY = -spawnY;
			posY = Main.SCREEN_SIZE_Y/2 - spawnY;
			objectpic.translateTo(posX, posY);
			objectpic.rotateTo(0);
			
			//Rotate the object by a random amount, taking into account where it spawns
			if(spawnY > 0){
				objectpic.rotateBy(random.nextInt(180));
			}else{
				objectpic.rotateBy(random.nextInt(180) - 180);
			}
		}
	}
	
	//Function to check if a coordinate is within the object
	boolean hitbox(int xx, int yy){
		
		
		if(objectpic.isPointInElement(xx, yy)){
			if(player.state == player.NORMAL){
				player.state = player.DEAD;
				return true;
			}
			return false; //why is this needed?
		}
		else{
			return false;
		}

	}
	
}
