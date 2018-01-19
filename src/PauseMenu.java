import java.awt.Color;
import java.awt.event.KeyEvent;
//Didn't get to finish this class... Clifton Miyamoto
public class PauseMenu {
	EZText ui;
	EZRectangle menubox;
	private final int resume = 0;
	private final int restart = 1;
	private final int home = 2;
	private final int quit = 3;
	private int cursorPos = 0;
	EZImage cursor;
	EZText resumeText, restartText, homeText, quitText;
	private boolean active;
	PauseMenu(){
		menubox = EZ.addRectangle(Main.SCREEN_SIZE_X/2, Main.SCREEN_SIZE_Y/2, 300, 400, Color.BLUE, true);
		active = false;
		resumeText = EZ.addText(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y - 150, "Resume", Color.WHITE);
		restartText = EZ.addText(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y - 50, "Restart", Color.WHITE);
		homeText = EZ.addText(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y +50, "Main Menu", Color.WHITE);
		quitText = EZ.addText(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y + 150, "Quit", Color.WHITE);
		cursor = EZ.addImage("cursor.png", Main.SCREEN_SIZE_X - 40, Main.SCREEN_SIZE_Y -150);
		hideMenu();
		}
	
	void hideMenu(){
		menubox.hide();
		resumeText.hide();
		restartText.hide();
		homeText.hide();
		quitText.hide();
		cursor.hide();
	}
	
	void showMenu(){
		menubox.show();
		resumeText.show();
		restartText.show();
		homeText.show();
		quitText.show();
		cursor.show();
	}
	
	public boolean getActive(){
		return active;
	}
	
	void interact(){
		switch(KeyEvent.KEY_PRESSED){
		case 'w':
			if(cursorPos >0){
				cursorPos++;
			}
			break;
		case 's':
			if(cursorPos < 4){
				cursorPos--;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(cursorPos > 0){
				cursorPos++;
			}
			break;
		case KeyEvent.VK_UP:
			if(cursorPos < 4){
				cursorPos--;
			}
			break;
		case KeyEvent.VK_ESCAPE:
			active = false;
			break;
		
		
		case KeyEvent.VK_BACK_SPACE:
			active = false;
			break;
		case KeyEvent.VK_ENTER:
			switch(cursorPos){
			case 0:
				active = false;
			case 1:
			
			
			case 2: 
				Main.dead = true;
			case 3:
				System.exit(0);
			}
		}
		
				
	}
	
	void go(){
		if(KeyEvent.KEY_PRESSED == KeyEvent.VK_ESCAPE){
			showMenu();
			active = true;
			while(active){
				interact();
			}
		}
	}
}
