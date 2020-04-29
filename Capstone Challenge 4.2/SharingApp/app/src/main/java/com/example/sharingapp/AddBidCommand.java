package com.example.sharingapp;

import android.content.Context;

/**
 * Command to add a bid
 */
public class AddBidCommand extends Command {

    
    private Bid bid;
    

    public AddBidCommand( Bid bid) {
        this.bid = bid;
        }

    // Save bid remotely
    public void execute(){
       ElasticSearchManager.AddBidTask add_bid_task = new ElasticSearchManager.AddBidTask();
        add_bid_task.execute(bid);
        try {
            if(add_bid_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
