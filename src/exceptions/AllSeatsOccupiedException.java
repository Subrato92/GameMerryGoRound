package exceptions;

public class AllSeatsOccupiedException extends Exception{

    public AllSeatsOccupiedException(){
        super("All Seats are occupied....");
    }
}
