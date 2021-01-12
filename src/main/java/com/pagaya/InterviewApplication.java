package com.pagaya;

import com.pagaya.service.ColorExpanderService;
import com.pagaya.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication implements CommandLineRunner {

    @Autowired
    private GameService gameToRun;

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

    // Use SpringBoot's CommandLineRunner to help reading from Stdin and having an interactive game.
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting up the game...");

        try {
            // Use dependency injection for the game to run, using Spring, can help us make this more extensible, and swap out the game, if we so desire.
            gameToRun.runGame();
        } catch (Throwable err) {
            System.out.println(err);
            System.exit(1);
        }

        // We've won! Or at least, exited safely.
        System.exit(0);
    }

}
