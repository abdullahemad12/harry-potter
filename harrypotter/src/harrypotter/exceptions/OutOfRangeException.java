package harrypotter.exceptions;

@SuppressWarnings("serial")
public class OutOfRangeException extends InvalidActionException{

	private int allowedRange;
	
	public OutOfRangeException(int allowedRange)
	{
		super();
		this.allowedRange = allowedRange;
		
	}
	public int getAllowedRange()
	{
		return allowedRange;
	}
}
