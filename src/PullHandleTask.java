import entities.Player;

public class PullHandleTask implements Runnable{

    private Player p;
    private int handleSeqNo;
    PullHandleTask(Player p, int handleSeqNo){
        this.p = p;
        this.handleSeqNo = handleSeqNo;
    }

    @Override
    public void run() {
        p.pullHandle(handleSeqNo);
    }
}
