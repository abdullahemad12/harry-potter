package harrypotter.model.character;

public class GryffindorWizard extends Wizard implements Champion {
	
	public GryffindorWizard(String name)
	{	
		super(name);
		
	}
    public void useTrait(){
    	if (getListener() != null)
    		getListener().onGryffindorTrait();
	}

}
