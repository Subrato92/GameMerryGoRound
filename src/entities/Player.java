package entities;

import exceptions.InvalidTrigger;

import java.util.UUID;

public class Player {
    private String id;
    private Seats seatRef;
    private Game game;

    public Player(){
        id = UUID.randomUUID().toString();
    }

    public void setSeatRef(Seats seat){
        seatRef = seat;
        System.out.println("Player"+id+" Allocated to Seat"+seat.getSeqNo());
    }

    public void setGame(Game game){
        this.game = game;
    }

    public synchronized void pullHandle(int handleSeqNo){
        if(game==null){
            System.out.println("Invalid Call on Player "+id);
            return;
        }

        System.out.println("   Player :"+seatRef.getSeqNo()+" pulled handle :"+handleSeqNo);
        try {
            game.pullHandleEvent(new Event(seatRef.getSeqNo(), handleSeqNo));
        }catch (InvalidTrigger ex){
            System.out.println("Invalid Trigger by Player" +id);
        }
    }
}
