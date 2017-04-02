package harrypotter.exceptions;


public abstract class NotEnoughResourcesException extends InvalidActionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8570818403193096680L;
	public NotEnoughResourcesException(String message)
	{
		super(message);
		
	}
	public NotEnoughResourcesException()
	{
		super();
	}
}
