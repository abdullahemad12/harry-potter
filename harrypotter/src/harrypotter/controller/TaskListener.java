package harrypotter.controller;

import java.util.EventListener;
import harrypotter.model.character.Champion;
public interface TaskListener extends EventListener {
	/*
	 * After Finishing the First Task it initializes a new instance of the secondTask GUI 
	 */
	public void onFinishingFirstTask(); 
	
	/*
	 * After Finishing the First Task it initializes a new instance of the secondTask GUI 
	 */
	public void onFinishingSecondTask();
	/*
	 * After Finishing the Third it initializes a new instance of the Winners and passes the Winners  
	 */
	public void onFinishingThirdTask(Champion Winner);

}
