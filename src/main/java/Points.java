import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Points {

    private char cookie = '\uDF6A'; // Ändra sen!!!!
    private ArrayList<Position> positions;

    public Points() {
        this.positions = new ArrayList<>();
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void pointsMap1() {
        positions.add(new Position(11, 12));
        positions.add(new Position(16, 4));
        positions.add(new Position(25, 25));
        positions.add(new Position(13,11));
        positions.add(new Position(18, 28));
        positions.add(new Position(4, 29));
        positions.add(new Position(19, 5));
        positions.add(new Position(60, 25));
        positions.add(new Position(40, 21));
        positions.add(new Position(29, 12));
    }

    public void printPointsMap1(Terminal terminal) throws IOException {
        pointsMap1();
        for (Position position : positions) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.putCharacter(cookie);
        }
        terminal.flush();
    }

}


