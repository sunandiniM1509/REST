package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command to add user
 */
public class AddUserCommand extends Command {

    private User user;

    public AddUserCommand (User user) {
        this.user = user;
    }

    // Save the user remotely to server
    public void execute() {
        ElasticSearchManager.AddUserTask add_user_task = new ElasticSearchManager.AddUserTask();
        add_user_task.execute(user);

        try {
            if(add_user_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
