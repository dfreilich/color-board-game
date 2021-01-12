package com.pagaya.service;

import com.pagaya.elements.ColorPiece;
import com.pagaya.elements.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Scanner;


@Service
// ColorExpanderService defines a Color Expansion game. The rules are detailed in `printUsage()`
public class ColorExpanderService implements GameService {
    private static final int BOARD_SIZE = 18;
    private static final int NUM_TURNS = 21;

    // This enables easy switching of the starting point
    private static final int STARTING_ROW = 0;
    private static final int STARTING_COL = 0;

    @Autowired
    private Board<ColorPiece> board;
    private Scanner scan;

    public ColorExpanderService(){}

    @PostConstruct
    // Initialize the ColorExpansion game
    public void init() {
        scan = new Scanner(System.in);
    }

    @PreDestroy
    // Close any open resources
    public void safeDestroy() {
        scan.close();
    }

    @Override
    public void runGame() {
        printUsage();
        board.print();

        for(int i = 0; i < NUM_TURNS && !board.hasWon(); i++) { // This takes care of case where board is initialized with a winning hand.
            System.out.printf("Turn %s of %s\n",i+1, NUM_TURNS);
            ColorPiece newColor = null;

            while(newColor == null) {
                System.out.println("Please submit either 'r', 'y', 'b', or 'g', to take over the board!");
                newColor = ColorPiece.getByString(scan.nextLine());
                if(newColor == null) {
                    System.out.println("Invalid input submitted.");
                }
            }

            board.change(newColor);
            board.print();
        }

        if(board.hasWon()) {
            System.out.println("You've won! Congratulations!");
        } else {
            System.out.println("Game over. Better luck next time!");
        }
    }

    private void printUsage() {
        System.out.println("You have reached the Color Expansion Game.");
        System.out.println("To win, you must have make the entire board one color");
        System.out.println("Every turn, you will be able to tell a color (either 'r', 'y', 'b', or 'g'.");
        System.out.println("That will transform the color of the same color, starting from the top left point, to that " +
                "color, and thereby absorb the directly adjoining blocks of the requested color.");

        System.out.println("You have a total of " + NUM_TURNS + " turns");
        System.out.println("Good luck!");
    }
}
