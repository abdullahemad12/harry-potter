package harrypotter.exceptions;

@SuppressWarnings("serial")
public class InCooldownException extends NotEnoughResourcesException{

	private int remainingTurns;
	
	public InCooldownException(int remainingTurns)
	{
		super("Cannot make a move Yet. remaining Turns: " + remainingTurns);
	}
	public int getRemainingTurns()
	{
		return remainingTurns;
		
	}
	
}
