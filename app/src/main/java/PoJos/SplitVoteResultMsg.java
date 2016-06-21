package PoJos;

/**
 * Created by Jim on 6/21/2016.
 */
public class SplitVoteResultMsg {
    public String state = "";
    public int demVotes = 0;
    public int repVotes = 0;

    public SplitVoteResultMsg(String state, int demVotes, int repVotes) {
        this.state = state;
        this.demVotes = demVotes;
        this.repVotes = repVotes;
    }



}
