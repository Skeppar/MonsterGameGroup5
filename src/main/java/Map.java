import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private char block = '\u2588';
    private ArrayList<Position> positions;

    public Map() {
        this.positions = new ArrayList<>();
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public char getBlock() {
        return block;
    }

    public void map1() {
        for (int a = 0; a < 100; a++) {
            positions.add(new Position(a, 39));
        }
        for (int a = 0; a < 100; a++) {
            positions.add(new Position(a, 1));
        }
        for (int a = 1; a < 40; a++) {
            positions.add(new Position(0, a));
        }
        for (int a = 1; a < 40; a++) {
            positions.add(new Position(99, a));
        }
        for (int a = 6; a < 16; a++) {
            positions.add(new Position(a, 10));
        }
        for (int a = 15; a < 25; a++) {
            positions.add(new Position(a, 5));
        }
        for (int a = 6; a < 23; a++) {
            positions.add(new Position(a, 13));
        }
        for (int a = 5; a < 7; a++) {
            positions.add(new Position(24, a));
        }
        for (int a = 23; a < 38; a++) {
            positions.add(new Position(a, 9));
        }
        for (int a = 9; a < 18; a++) {
            positions.add(new Position(38, a));
        }
        for (int a = 13; a < 20; a++) {
            positions.add(new Position(30, a));
        }
        for (int a = 20; a < 47; a++) {
            positions.add(new Position(a, 20));
        }
        for (int a = 24; a > 10; a--) {
            positions.add(new Position(46, a));
        }
        for (int a = 20; a < 35; a++) {
            positions.add(new Position(20, a));
        }
        for (int a = 30; a < 80; a++) {
            positions.add(new Position(a, 5));
        }
        for (int a = 27; a > 8; a--) {
            positions.add(new Position(52, a));
        }
        for (int a = 58; a < 80; a++) {
            positions.add(new Position(a, 32));
        }
        for (int a = 17; a < 25; a++) {
            positions.add(new Position(13, a));
        }
        for (int a = 7; a < 13; a++) {
            positions.add(new Position(a, 17));
        }
        for (int a = 20; a < 28; a++) {
            positions.add(new Position(7, a));
        }
        for (int a = 7; a < 13; a++) {
            positions.add(new Position(a, 27));
        }
        for (int a = 23; a < 40; a++) {
            positions.add(new Position(27, a));
        }
        for (int a = 27; a < 30; a++) {
            positions.add(new Position(a, 23));
        }
        for (int a = 23; a < 36; a++) {
            positions.add(new Position(40, a));
        }
        for (int a = 33; a < 40; a++) {
            positions.add(new Position(a, 23));
        }
        for (int a = 9; a < 13; a++) {
            positions.add(new Position(59, a));
        }
        for (int a = 59; a < 65; a++) {
            positions.add(new Position(a, 13));
        }
        for (int a = 13; a < 17; a++) {
            positions.add(new Position(65, a));
        }
        for (int a = 65; a < 71; a++) {
            positions.add(new Position(a, 17));
        }
        for (int a = 17; a < 21; a++) {
            positions.add(new Position(71, a));
        }
        for (int a = 71; a < 77; a++) {
            positions.add(new Position(a, 21));
        }
        for (int a = 16; a < 20; a++) {
            positions.add(new Position(59, a));
        }
        for (int a = 59; a < 65; a++) {
            positions.add(new Position(a, 20));
        }
        for (int a = 20; a < 24; a++) {
            positions.add(new Position(65, a));
        }
        for (int a = 65; a < 71; a++) {
            positions.add(new Position(a, 24));
        }
        for (int a = 24; a < 28; a++) {
            positions.add(new Position(71, a));
        }
        for (int a = 71; a < 77; a++) {
            positions.add(new Position(a, 28));
        }
        for (int a = 28; a < 36; a++) {
            positions.add(new Position(a, 26));
        }
        for (int a = 31; a < 40; a++) {
            positions.add(new Position(a, 29));
        }
        for (int a = 28; a < 36; a++) {
            positions.add(new Position(a, 32));
        }
        for (int a = 31; a < 40; a++) {
            positions.add(new Position(a, 35));
        }
        for (int a = 20; a < 23; a++) {
            positions.add(new Position(33, a));
        }
        for (int a = 52; a < 58; a++) {
            positions.add(new Position(a, 27));
        }
        for (int a = 27; a < 33; a++) {
            positions.add(new Position(58, a));
        }
        for (int a = 57; a < 60; a++) {
            positions.add(new Position(a, 24));
        }
        for (int a = 63; a < 66; a++) {
            positions.add(new Position(a, 28));
        }
        for (int a = 8; a < 21; a++) {
            positions.add(new Position(81, a));
        }
        for (int a = 81; a < 90; a++) {
            positions.add(new Position(a, 8));
        }
        for (int a = 87; a < 100; a++) {
            positions.add(new Position(a, 12));
        }
        for (int a = 81; a < 90; a++) {
            positions.add(new Position(a, 17));
        }
        for (int a = 90; a < 100; a++) {
            positions.add(new Position(a, 21));
        }
        for (int a = 21; a < 35; a++) {
            positions.add(new Position(90, a));
        }
        for (int a = 46; a < 55; a++) {
            positions.add(new Position(a, 35));
        }
        for (int a = 28; a < 35; a++) {
            positions.add(new Position(46, a));
        }
        for (int a = 66; a < 73; a++) {
            positions.add(new Position(a, 9));
        }
        for (int a = 9; a < 15; a++) {
            positions.add(new Position(73, a));
        }


    }

    public void printMap(Terminal terminal) throws IOException {
        map1();
        for (Position position : positions) {
             terminal.setCursorPosition(position.getX(), position.getY());
             terminal.putCharacter(block);
         }
        terminal.flush();
    }
}