import java.awt.Color;

public class menu {
int clickX, clickY;
int diffsetting;
int active2;
EZImage menu1, diffmenu;
EZRectangle play, difficulty, credits, exit;
EZRectangle easy, normal, hard;
EZSound mainmusic, playbutton, otherbutton;
boolean active;
	menu(){
		diffmenu = EZ.addImage("diffmenu.png", Main.SCREEN_SIZE_X/2, Main.SCREEN_SIZE_Y/2);
		menu1= EZ.addImage("menu.png", Main.SCREEN_SIZE_X/2, Main.SCREEN_SIZE_Y/2);
		diffmenu.hide();
		//diffmenu.hide();
		//add the rectangles for each button
		play = EZ.addRectangle(485, 360, 383, 88, Color.WHITE, false);
		difficulty = EZ.addRectangle(485, 456, 383, 88, Color.WHITE, false);
		credits = EZ.addRectangle(485, 550, 383, 88, Color.WHITE, false);
		exit = EZ.addRectangle(485,644, 383, 88, Color.WHITE, false);

	
		//main menu music
		mainmusic = EZ.addSound("8bit Menu Music.wav");
		playbutton = EZ.addSound("play.wav");
		otherbutton = EZ.addSound("other.wav");
		//variables
		clickX = 0;
		clickY = 0;
		
		//sets the menu to active, difficulty menu to inactive
		active = true;
		active2 = 0;
		//main menu music to play, and loops it
		mainmusic.play();
		mainmusic.loop();
		
	}
	//hides all menu elements i.e. turns off menu
	private void hidemenu(){
		menu1.hide();
		play.hide();
		difficulty.hide();
		credits.hide();
		exit.hide();
		active = false;
	}
	//shows all menu elements i.e. turns on menu
	public void showmenu(){
		menu1.show();
		play.show();
		difficulty.show();
		credits.show();
		exit.show();
		active = true;
	}
	
	public void interact() {
		//while loop to keep running it
		while(active){
			//variables to get the x and y of the mouse
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();
			
			//determines if main menu or difficulty menu is active 
			switch(active2){
			//main menu is active
			case 0:
				//if you click the left button , and it is in the point elements of one of the buttons it will proceed 
				if (EZInteraction.wasMouseLeftButtonReleased()){
					//if play is clicked the game will begin
					if (play.isPointInElement(clickX, clickY)) {		    	  
						playbutton.play();
						hidemenu();
					}
					
					//brings up the difficulty menu
					else if (difficulty.isPointInElement(clickX, clickY)){
						otherbutton.play();
						active2 = 1;
						diffmenu.show();
						menu1.hide();
					}
					//if credits is clicked screen of credits will appear and sound will plays
					else if (credits.isPointInElement(clickX, clickY)) {	
						otherbutton.play();
							
						//makes the credit screen just black with our names in white
						EZ.initialize(800,532);
						EZ.setBackgroundColor(new Color(0,0,0));
						Color c = new Color(255,255, 255);
						EZ.addText(400, 50, "Credits", c, 50);
						EZ.addText(400, 200, "Clifton Miyamoto", c, 40);
						EZ.addText(400, 300, "Trevor Virga", c, 40);
					}
					
					//if exit is clicked the program will end
					else if (exit.isPointInElement(clickX, clickY)) {	
						otherbutton.play();
						System.exit(0);
					}			
				}

				break;
			//difficulty menu is active
			case 1:
				diffMenu();
				break;
			}
		EZ.refreshScreen();
		}
	}
	
	//controls for difficulty menu. 
	public void diffMenu() {
		if(EZInteraction.wasMouseLeftButtonReleased()){
			//changes difficulty to easy
			if(play.isPointInElement(clickX, clickY)){
				otherbutton.play();
				Main.chngdiff(Main.EASY);
				diffmenu.hide();
				menu1.show();
				active2 = 0;
			}
			//changes difficulty to normal
			if(difficulty.isPointInElement(clickX, clickY)){
				otherbutton.play();
				Main.chngdiff(Main.NORMAL);
				diffmenu.hide();
				menu1.show();
				active2 = 0;
			}
			//changes difficulty to hard
			if(credits.isPointInElement(clickX, clickY)){
				otherbutton.play();
				Main.chngdiff(Main.HARD);
				diffmenu.hide();
				menu1.show();
				active2 = 0;
			}
			//exits the difficulty menu 
			if(exit.isPointInElement(clickX, clickY)){
				otherbutton.play();
				diffmenu.hide();
				menu1.show();
				active2 = 0;
			}
		}
	}
}
