//Class by Clifton Miyamoto
import java.util.ArrayList;
import java.util.Random;

public class Object {
	int speed;
	int posX, posY;
	EZImage objectpic; 
	static ArrayList<Object> arr = new ArrayList<Object>();

	//Variables to hold values for 1/2 the object's width/height
	int halfObjectpicW;
	int halfObjectpicH;
	boolean active;
	final public static Random random = new Random();
	
	//Constructor 
	Object(){
		//Sets the objects speed to a random integer from 1-3
		speed = random.nextInt(2) + 1;
		active = true;
	}
	
	//hides the object picture
	void hide(){
		objectpic.hide();
	}
	void go() {
		// If object is active, advance posY by speed amount and translate image to posX, posY
		if(active){
			posY += speed;
			objectpic.translateTo(posX, posY);
		}
	}
	
	//Function that returns the active state
	boolean getActive(){
		return active;
	}
	
	boolean hitbox(int xx, int yy){
		return false;
	}
}
