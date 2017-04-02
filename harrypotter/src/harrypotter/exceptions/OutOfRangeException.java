package harrypotter.exceptions;

@SuppressWarnings("serial")
public class OutOfRangeException extends InvalidActionException{

	private int allowedRange;
	
	public OutOfRangeException(int allowedRange)
	{
		super("Cannot cast the spell this range. allowed range: " + allowedRange +"cells");
		this.allowedRange = allowedRange;
		
	}
	public int getAllowedRange()
	{
		return allowedRange;
	}
}
