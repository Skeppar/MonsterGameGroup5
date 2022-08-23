import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private char block = '\u2588';
    private ArrayList<Position> positions;

    public Map() {
        this.positions = new ArrayList<>();
    }



    public void map1() throws IOException {
        for (int a = 6; a < 16; a++) { // a = distance from right a < length. Next (a, star from top)
            positions.add(new Position(a, 10));
        }
        for (int a = 1; a < 100; a++) {
            positions.add(new Position(a, 100));
        }
        for (int a = 1; a < 100; a++) {
            positions.add(new Position(a, 0));
        }
        for (int a = 0; a < 50; a++) {
            positions.add(new Position(0, a));
        }
        for (int a = 0; a < 50; a++) {
            positions.add(new Position(100, a));
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
        for (int a = 30; a > 8; a--) {  // a = distance from top a < height. Next (start from right, a)
            positions.add(new Position(52, a));
        }
        for (int a = 52; a < 80; a++) { // a = distance from right a < length. Next (a, star from top)
            positions.add(new Position(a, 30));
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