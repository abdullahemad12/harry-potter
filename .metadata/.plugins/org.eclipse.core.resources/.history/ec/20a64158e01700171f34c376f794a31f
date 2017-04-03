package harrypotter.exceptions;

@SuppressWarnings("serial")
public class NotEnoughIPException extends NotEnoughResourcesException{
	
	int requiredIP;// Represents the required ip for casting the spell.
	int remainingIP;// Represents the remaining ip that the champion would have needed to cast the spell without raising an exception.
	
	public NotEnoughIPException(int requiredIP, int remainingIP)
	{
		super("Not Enough IP to cast this spell.... \nRequired IP: " + requiredIP + "\nRemaining: " +  remainingIP);
		// youssef customize this message if you want to. the \n means new line 
		
		this.requiredIP  = requiredIP;
		this.remainingIP =  remainingIP;
		
	}
	
	public int getRequiredIP()
	{
		return requiredIP;
	}

	public int getRemainingIP() {
		return remainingIP;
	}
}
