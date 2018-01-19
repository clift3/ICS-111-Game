//Class by Clifton Miyamoto
public class Type2 extends Object{
	
	//Speeds in the x and y direction
	private int directionX;
	private int directionY;
	
	//Constructor
	Type2() {
		super();
		//set a random position for the object
		posX = random.nextInt(Main.SCREEN_SIZE_X);
		posY = random.nextInt(Main.SCREEN_SIZE_Y);
		
		objectpic = EZ.addImage("bluebullet.png", posX, posY);
		halfObjectpicW = objectpic.getWidth()/2;
		halfObjectpicH = objectpic.getHeight()/2;
		
		//sets a random speed for x and y. Retries if both = 0
		do{
		directionX = random.nextInt(4) - 4;
		directionY = random.nextInt(4) - 4;
		}while(directionX == 0 && directionY == 0);
		
		active = true;
	}
	
	// function to add Type2 objects into the array list depending on difficulty
	static void patternEasy(){
		int NUMBER = 10;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type2());
		}
	}
	
	static void patternNormal(){
		int NUMBER = 20;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type2());
		}
	}
	
	static void patternHard(){
		int NUMBER = 50;
		for(int i = 0; i < NUMBER; i++){
		arr.add(new Type2());
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
	public void go(){
		if (active){
			posX += directionX;
			posY += directionY;
			objectpic.translateTo(posX, posY);
			
			//if the object reaches the end of the screen, make it reflect off
			if(posX < 0 || posX > Main.SCREEN_SIZE_X){
				directionX = -directionX;
			}
			
			if(posY < 0 || posY > Main.SCREEN_SIZE_Y){
				directionY = -directionY;
			}	
		}
	}
	
	//Function to check if a coordinate is within the object
	public boolean hitbox(int xx, int yy){
		
		if (objectpic.isPointInElement(xx, yy)){
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
