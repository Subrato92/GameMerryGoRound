package entities;

import exceptions.AllSeatsOccupiedException;
import exceptions.InvalidTrigger;

import java.util.*;

public class Game implements Runnable{

    private List<Handles> handleList ;
    private List<Seats> seatLst;

    private Stack<Event> eventQueues = null;
    private List<Player> players;
    private int totalSeats;
    private int currSeat;

    private boolean gameExecutionStatus = false;

    public Game(int totalSeats){
        this.totalSeats = totalSeats;
        currSeat = 1;
        initialize();
    }

    private void initialize(){
        eventQueues = new Stack<>();
        players = new ArrayList<>();
        seatLst = new ArrayList<>();
        handleList = new ArrayList<>();
        for(int i=1; i<=totalSeats; i++){
            seatLst.add(new Seats(i));
            handleList.add(new Handles(i));
        }
    }

    public synchronized boolean addPlayer(Player player) throws AllSeatsOccupiedException {
        if(!isSeatAvailable())
            throw new AllSeatsOccupiedException();

        player.setGame(this);
        player.setSeatRef(seatLst.get(currSeat-1));
        currSeat++;

        players.add(player);

        if(!isSeatAvailable())
            triggerGameStart();

        return true;
    }

    private void triggerGameStart(){
        gameExecutionStatus = true;
        this.run();
    }

    private void start(){
        System.out.println("Game Started !!!!");
    }

    private void stop(int userSeq){
        gameExecutionStatus = false;
        System.out.println("Game Stopped !!!! - By PlayerSeq:"+userSeq);
    }

    public boolean isSeatAvailable(){
        if(totalSeats>=currSeat)
            return true;
        return false;
    }

    public List<Handles> getHandles(){
        return handleList;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public synchronized void pullHandleEvent(Event event) throws InvalidTrigger {

        if(!gameExecutionStatus){
            throw new InvalidTrigger();
        }

        System.out.println("Received Request: "+event.toString());

        if(eventQueues.isEmpty()) {
            eventQueues.add(event);
            System.out.println("   In 0");
            return;
        }

        Event lstEvent = eventQueues.peek();

        if((lstEvent.getPlayerSeq() == event.getPlayerSeq())){
            int userSeq = lstEvent.getPlayerSeq();
            int minSeatSeqNo = Math.min(lstEvent.getHandleSeq(),event.getHandleSeq());
            int maxSeatSeqNo = Math.max(lstEvent.getHandleSeq(),event.getHandleSeq());

            if((userSeq==minSeatSeqNo || userSeq==maxSeatSeqNo) &&
                    ((userSeq==totalSeats && maxSeatSeqNo-minSeatSeqNo==totalSeats-1) || (userSeq!=totalSeats && maxSeatSeqNo-minSeatSeqNo==1))){
                stop(userSeq);
            }else{
                System.out.println("   In 1");
                eventQueues.add(event);
            }
        }else{
            System.out.println("   In 2");
            eventQueues.pop();
            eventQueues.add(event);
        }
    }

    @Override
    public void run() {
        start();
    }
}
