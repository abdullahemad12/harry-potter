package harrypotter.exceptions;
@SuppressWarnings("serial")

public class InvalidTargetCellException extends InvalidActionException {
	public InvalidTargetCellException()
	{
		super();
	}
	public InvalidTargetCellException(String message)
	{
		super(message);
	}
}
