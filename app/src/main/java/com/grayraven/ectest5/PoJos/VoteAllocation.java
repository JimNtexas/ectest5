package com.grayraven.ectest5.PoJos;

/**
 * Created by Jim on 6/20/2016.
 */
public class VoteAllocation
{
    public VoteAllocation(){};
    public VoteAllocation(String abv, String votes){
        this.abv = abv;
        this.votes = votes;
    }

    private String abv;

    private String votes;

    public String getAbv ()
    {
        return abv;
    }

    public void setAbv (String abv)
    {
        this.abv = abv;
    }

    public String getVotes ()
    {
        return votes;
    }

    public void setVotes (String votes)
    {
        this.votes = votes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [abv = "+abv+", votes = "+votes+"]";
    }
}