package com.pagaya.elements;

public interface Board<Piece> {
    void print();
    void change(Piece p);
    boolean hasWon();
}
//