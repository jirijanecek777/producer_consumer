package com.company.task;

import com.company.command.Command;
import com.company.command.CommandFactory;

public class CommandProducer implements Runnable {
    private final com.company.task.CommandQueue commandQueue;
    private final String data;

    public CommandProducer(final CommandQueue commandQueue, final String data) {
        this.commandQueue = commandQueue;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            Command command = CommandFactory.getCommand(data);
            if (command == null) {
                System.err.println("incorrect command");
                return;
            }

            commandQueue.add(command);

        } catch (final InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
