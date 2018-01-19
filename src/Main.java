import java.awt.Color;
import java.util.ArrayList;


public class Main {
	
	//function that creates a number objects depending on the difficulty
	static void difficulty(){
		switch(difficultySetting){
		case EASY:
			Type1.patternEasy();
			Type2.patternEasy();
			break;
			
		case NORMAL:
			Type1.patternNormal();
			Type2.patternNormal();
			break;
			
		case HARD:
			Type1.patternHard();
			Type2.patternHard();
			break;
		}
	}
	
	public static void chngdiff(int n){
		difficultySetting = n;
	}
	public static int SCREEN_SIZE_X = 1000;
	public static int SCREEN_SIZE_Y = 1000;
	static final int EASY = 0;
	static final int NORMAL = 1;
	static final int HARD = 2;
	public static int difficultySetting = 1;
	public static boolean dead = false;	

	public static void main(String[] args) {
		EZ.initialize(SCREEN_SIZE_X, SCREEN_SIZE_Y);
		EZ.setBackgroundColor(Color.BLACK);
		Timer timer = new Timer();
		Item star = new Item();
		//text to keep track of score
		EZText score = EZ.addText(SCREEN_SIZE_X - 100, 20, "Score:", Color.white);
		score.fontSize = 20;
		score.hide();
		//Spawn player in the middle of the screen
		player player1 = new player("player.png", SCREEN_SIZE_X/2, SCREEN_SIZE_Y/2);
		//player.state = player.TEST; //for testing purposes, player is invincible
		menu mainmenu = new menu();
		
		while(true){
			mainmenu.interact();
			//Add the objects to the array list
			difficulty();
			score.show();
			//sets timer to 0
			timer.restart();
			//Loop that controls the player and object movements. Ends if the player is hit
			do{
				//Goes through array list to move objects
				for(int i = 0; i<Object.arr.size();i++){
					Object.arr.get(i).go();
					
					//Checks to see if the current object is in contact with the player
					if(Object.arr.get(i).hitbox(player1.getX(), player1.getY())){
						dead = true;
					}
					
				}
				//checks to see if item should spawn
				star.ItemSpawn();
				player1.Movement();
				//Spawn a new Type2 object every 3 seconds
				if(timer.wave()){
					Object.arr.add(new Type2());
				}
		
				//Keeps track of the score (time elapsed)
				score.setMsg("Score: " + timer.score());
				EZ.refreshScreen();
				if(dead){
					//Pause so that you can actually see the death graphics
					EZ.pause(2000);
				}
			}while(!dead);
			//Clears array list of objects, resets player and item position, returns to menu to start new game
			Type1.clear();
			player1.reset();
			star.reset();
			dead = false;
			mainmenu.showmenu();
			
		}
	}

}
