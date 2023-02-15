package exceptions;

public class InvalidTrigger extends Exception{
    public InvalidTrigger(){
        super("Invalid Trigger - Game Stopped");
    }
}
