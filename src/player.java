//Class by Trevor Virga
import java.awt.Color;
import java.io.IOException;

public class player {
	
//variables
EZImage PlayerPicture, ded;	
int x,y;
static int state = 0;
//Constants for the different states of the player
static final int DEAD = 0;
static final int NORMAL = 1;
static final int INVINCIBLE = 2;
static final int TEST = 3;
static long duration = 0;
EZText textbox, youLose;




//constructor for the player
public player(String filename,int posx,int posy){
		PlayerPicture = EZ.addImage(filename, posx, posy);
		x = posx;
		y = posy;
		state = NORMAL;
		
		//makes the image smaller
		PlayerPicture.scaleTo(.25);
		
		//textbox for the invincibility timer
		textbox = EZ.addText(x + 8, y - 40, "", Color.white);
		textbox.setFontSize(20);
		textbox.hide();
		
		// death graphics that show upon death
		ded = EZ.addImage("ded.png", x, y);
		Color c = new Color(0,0, 150);
		int fontsize = 50;
		youLose = EZ.addText(Main.SCREEN_SIZE_X/2, 250, "YOU LOSE", c, fontsize);
		ded.hide();
		youLose.hide();
	}
	
	//method to retrieve x & y of player
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	public static int getState(){
		return state;
	}
	
	//displays death graphics
	public void showDead(){
		ded.show();
		youLose.show();
	}
	
	//hides death graphics
	public void hideDead(){
		ded.hide();
		youLose.hide();
	}
	
	// function to set the position of player
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setPlayerImagePosition(x, y);
	}
	
	
	//function to make the image go to the x and y position that it should be at
	private void setPlayerImagePosition(int posx, int posy) {
		PlayerPicture.translateTo(posx, posy);
		textbox.translateTo(posx + 8, posy - 40);
		ded.translateTo(posx, posy);
	}
	

	// functions to move the image
	public void moveLeft(int step) {
		x = x - step;
		setPlayerImagePosition(x, y);
	}
	public void moveRight(int step) {
		x = x + step;
		setPlayerImagePosition(x, y);
	}
	public void moveUp(int step) {
		y = y - step;
		setPlayerImagePosition(x, y);
	}
	public void moveDown(int step) {
		y = y + step;
		setPlayerImagePosition(x, y);
	}
	
	public void moveDiagnolUpLeft(int step) {
		y = y - step;
		x = x - step;
		setPlayerImagePosition(x, y);
	}
	public void moveDiagnolUpRight(int step) {
		x = x + step;
		y = y - step;
		setPlayerImagePosition(x, y);
	}
	public void moveDiagnolDownLeft(int step) {
		y = y + step;
		x = x - step;
		setPlayerImagePosition(x, y);
	}
	public void moveDiagnolDownRight(int step) {
		y = y + step;
		x = x + step;
		setPlayerImagePosition(x, y);
	}
	
	//resets the player's position and hides death graphics
	public void reset(){
		setPosition(Main.SCREEN_SIZE_X/2, Main.SCREEN_SIZE_Y/2);
		state = NORMAL;
		hideDead();
	}
	public void Movement() {
		switch(player.getState()){
		//when invincible (after getting item) textbox above shows the time until invincibility runs out
		case INVINCIBLE:
			textbox.setMsg(String.valueOf((int) (duration + 6000 - System.currentTimeMillis())/1000));
			if(System.currentTimeMillis() - duration > 5000){
				state = NORMAL;
				textbox.hide();				
			}
		case TEST:
			// no can die
			
		case NORMAL:
			
			// controls to move player with keyboard, the higher their number the faster he goes
			if (EZInteraction.isKeyDown('a')&& EZInteraction.isKeyDown('w')){
				moveDiagnolUpLeft(2);
			}
			else if (EZInteraction.isKeyDown('d')&& EZInteraction.isKeyDown('w')){
				moveDiagnolUpRight(2);
			}
			else if (EZInteraction.isKeyDown('s')&& EZInteraction.isKeyDown('a')){
				moveDiagnolDownLeft(2);
			}
			else if (EZInteraction.isKeyDown('s')&& EZInteraction.isKeyDown('d')){
				moveDiagnolDownRight(2);
			}
			else if (EZInteraction.isKeyDown('w')) {
				moveUp(3);
			} else if (EZInteraction.isKeyDown('a')) {
				moveLeft(3);
			} else if (EZInteraction.isKeyDown('s')) {
				moveDown(3);
			} else if (EZInteraction.isKeyDown('d')) {
				moveRight(3);
			}
			
			if(Item.getItem(x, y)){
				state = INVINCIBLE;
				duration = System.currentTimeMillis();
				textbox.show();
			}
			break;
		
		case DEAD: 
			//once guy is dead will put a picture of skull on place of death and will pop up text stating that the player lost (Trevor)
			showDead();
			break;
		}
	}
}
