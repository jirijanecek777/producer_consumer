package com.company.task;

import com.company.command.Command;

import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandQueue {

    private final BlockingQueue<Command> queue;

    public CommandQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public void add(Command command) throws InterruptedException {
        queue.put(command);
    }

    public void remove() throws InterruptedException, SQLException {
        Command command = queue.take();
        command.execute();
    }
}
