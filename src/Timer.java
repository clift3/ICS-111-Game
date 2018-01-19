//Class by Clifton Miyamoto
public class Timer {
	private long timeElapsed;
	private static long starttime;
	private static long starttimeWave;
	private int frequency = 3;
	private int score = 0;
	
	Timer(){
		starttime = System.currentTimeMillis();
		starttimeWave = System.currentTimeMillis();
	}
	
	//Function that controls the time between waves
	boolean wave(){
		timeElapsed = System.currentTimeMillis() - starttimeWave;
		if(timeElapsed > frequency*1000){
			starttimeWave = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	void restart(){
		starttime = System.currentTimeMillis();
		starttimeWave = System.currentTimeMillis();
	}
	
	
	//Function that keeps a score equal to seconds elapsed * 10
	public int score(){
		score = (int) (System.currentTimeMillis() - starttime)/100;
		return score;
	}
	
	
	
}
