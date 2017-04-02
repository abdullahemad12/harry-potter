package harrypotter.exceptions;


@SuppressWarnings("serial")
public abstract class InvalidActionException extends Exception {
	
	public InvalidActionException()
	{
		super();
	}
	public InvalidActionException(String message)
	{
		super(message);
	}
	
	
}
