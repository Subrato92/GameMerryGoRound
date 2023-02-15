package entities;

public class Event {
    private int playerSeq;
    private int handleSeq;

    Event(int playerSeq, int handleSeq){
        this.playerSeq = playerSeq;
        this.handleSeq = handleSeq;
    }

    public int getPlayerSeq() {
        return playerSeq;
    }

    public int getHandleSeq() {
        return handleSeq;
    }

    @Override
    public String toString(){
        return "Player-"+playerSeq+", handleSeq:"+handleSeq;
    }
}
