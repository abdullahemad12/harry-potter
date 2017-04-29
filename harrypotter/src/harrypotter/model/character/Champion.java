package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;

import java.io.IOException;

/*
 * Interface containing the methods available to wizards who participate in the tournament
 * i.e. champions.
 */
public interface Champion{
	
	/*
	 * Method for activating the special traits available to champions of each house
     * during the tasks.
	 */
	public void useTrait() throws IOException, InCooldownException, OutOfBordersException , InvalidTargetCellException  ;
}
