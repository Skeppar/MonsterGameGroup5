import java.util.ArrayList;

public class Map {

    private char block = '\u2588';
    private ArrayList<Position> positions;

    public Map() {
        this.positions = new ArrayList<>();
    }

    public void map1(Terminal terminal) {
        for (int a = 6; a < 16; a++) { // a = distance from right a < length. Next (a, star from top)
            positions.add(new Position(a, 10));


            terminal.setCursorPosition(a, 10);
            terminal.putCharacter(block);
        }

    }

}