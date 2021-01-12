package com.pagaya.elements;

import com.pagaya.colors.Colors;

public enum ColorPiece implements Piece {
    RED("r", Colors.ANSI_RED),
    BLUE("b", Colors.ANSI_BLUE),
    GREEN("g", Colors.ANSI_GREEN),
    YELLOW("y", Colors.ANSI_YELLOW);

    private String color;
    private String ansiCode;

    ColorPiece(String color, String ansiCode) {
        this.color = color;
        this.ansiCode = ansiCode;
    }

    public static ColorPiece getByString(String str) {
        for(ColorPiece current: ColorPiece.values()) {
            if (current.color.equalsIgnoreCase(str)) {
                return current;
            }
        }

        return null;
    }

    public static ColorPiece getRandom() {
        int idx = (int) (Math.random()*(ColorPiece.values().length));
        return ColorPiece.values()[idx];
    }

    public String toString() {
        return this.ansiCode;
    }
}
