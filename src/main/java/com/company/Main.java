package com.company;

import com.company.task.CommandConsumer;
import com.company.task.CommandProducer;
import com.company.task.CommandQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static final int QUEUE_CAPACITY = 100;

    public static void main(final String args[]) throws IOException {
        new Main(1, 1);
    }

    public Main(final int producers, final int consumers) throws IOException {
        final CommandQueue commandQueue = new CommandQueue(QUEUE_CAPACITY);

        ExecutorService executor = Executors.newFixedThreadPool(consumers);
        createConsumers(consumers, commandQueue).forEach(executor::execute);
        produceCommands(producers, commandQueue);
    }

    private void produceCommands(int producers, CommandQueue commandQueue) throws IOException {
        String fileName = "src/main/resources/data.txt";
        ExecutorService pool = Executors.newFixedThreadPool(producers);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> pool.submit(new CommandProducer(commandQueue, line)));
        }
    }

    private List<CommandConsumer> createConsumers(int numberOfConsumers, CommandQueue commandQueue) {
        return IntStream.range(0, numberOfConsumers)
                .mapToObj(e -> new CommandConsumer(commandQueue))
                .collect(Collectors.toList());
    }
}