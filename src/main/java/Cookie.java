import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Cookie {

    private char cookie = '\uDF6A'; // Ändra sen!!!!
    private ArrayList<Position> cookies;

    public Cookie() {
        this.cookies = new ArrayList<>();

    }

    public ArrayList<Position> getCookies() {
        return cookies;
    }

    public void pointsMap1() {
        cookies.add(new Position(31, 12));
        cookies.add(new Position(16, 4));
        cookies.add(new Position(25, 25));
        cookies.add(new Position(13,11));
        cookies.add(new Position(18, 28));
        cookies.add(new Position(4, 29));
        cookies.add(new Position(19, 6));
        cookies.add(new Position(60, 25));
        cookies.add(new Position(40, 21));
        cookies.add(new Position(29, 12));
    }

    public void printPointsMap1(Terminal terminal) throws IOException {
        pointsMap1();
        for (Position position : cookies) {
            terminal.setCursorPosition(position.getX(), position.getY());
            terminal.putCharacter(cookie);
        }
        terminal.flush();
    }




}


