package com.company.task;

import java.sql.SQLException;

public class CommandConsumer implements Runnable {
    private final CommandQueue commandQueue;

    public CommandConsumer(final CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {

                commandQueue.remove();

            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
