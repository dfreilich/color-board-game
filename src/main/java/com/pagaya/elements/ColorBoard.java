package com.pagaya.elements;

import com.pagaya.colors.Colors;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColorBoard implements Board<ColorPiece> {
    private static final int BOARD_SIZE = 18;
    // This enables easy switching of the starting point
    private static final int STARTING_ROW = 0;
    private static final int STARTING_COL = 0;

    private List<List<ColorPiece>> board;

    public ColorBoard(){
        board = new ArrayList<>();
        init();
    }

    // Seeds the board with random variables
    private void init(){
        for(int i = 0; i < BOARD_SIZE; i++) {
            List<ColorPiece> row = new ArrayList<>();
            for(int j = 0; j < BOARD_SIZE; j++) {
                row.add(ColorPiece.getRandom());
            }
            board.add(row);
        }
    }

    public void print() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf(" %s ", board.get(i).get(j));
            }
            System.out.printf("%s\n", Colors.ANSI_RESET);
        }
    }

    public boolean hasWon() {
        ColorPiece orig = board.get(STARTING_ROW).get(STARTING_COL);

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j =0; j < BOARD_SIZE; j++) {
                if(board.get(i).get(j) != orig) return false;
            }
        }
        return true;
    }

    public void change(ColorPiece newColor) {
        ColorPiece starting = board.get(STARTING_ROW).get(STARTING_COL);
        if(starting != newColor) {
            flipFromColorToColor(starting, newColor, STARTING_ROW, STARTING_COL);
        }
    }

    private void flipFromColorToColor(ColorPiece old, ColorPiece newColor, int i, int j) {
        if(outOfBounds(i) || outOfBounds(j)) return;
        ColorPiece current = board.get(i).get(j);
        if(current == old) {
            board.get(i).set(j, newColor);
            flipFromColorToColor(old, newColor, i-1, j);
            flipFromColorToColor(old, newColor, i+1, j);
            flipFromColorToColor(old, newColor, i, j+1);
            flipFromColorToColor(old, newColor, i, j-1);
        }
    }

    private boolean outOfBounds(int i) {
        return i < 0 || i >= BOARD_SIZE;
    }
}
