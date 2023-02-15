package entities;

import java.util.UUID;

public class Handles {
    private String id;
    private int seqNo;

    Handles(int seqNo){
        this.seqNo = seqNo;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int getSeqNo() {
        return seqNo;
    }
}
