import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonsterGame {

    public static void main(String[] args) {

        try {
            introGameScreen();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println('\u8944' +" Game over! " + '\u8945');
        }
    }

    public static Map loadMap(Terminal terminal) throws IOException {
        Map map = new Map();
        map.printMap(terminal);
        return map;
    }

    public static Cookie loadPoints(Terminal terminal) throws IOException {
        Cookie cookie = new Cookie();
        terminal.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
        cookie.printPointsMap1(terminal);
        return cookie;
    }

    public static void introGameScreen() throws IOException, InterruptedException {
        TerminalSize ts = new TerminalSize(100, 40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        TextGraphics textGraphics = terminal.newTextGraphics();
        terminal.setBackgroundColor(TextColor.ANSI.BLACK);
        terminal.setForegroundColor(TextColor.ANSI.BLUE_BRIGHT);
        terminal.enableSGR(SGR.BOLD);
        textGraphics.putString(28, 15, "-- ** The Cookie Catcher ** --", SGR.BOLD, SGR.BORDERED, SGR.REVERSE);
        textGraphics.putString(27, 20, "--Press Enter to start the fun--", SGR.BLINK);
        terminal.flush();
        int x = 0;
        while (x == 0) {
            switch (getUserKeyStroke(terminal).getKeyType()) {
                case Enter -> {
                    x++;
                    startGame(terminal);
                }
                case Escape -> {
                x++;
                closeGame(terminal);
                }
            }
        }
    }

    private static void closeGame(Terminal terminal) throws IOException, InterruptedException {
        terminal.clearScreen();
        terminal.setBackgroundColor(TextColor.ANSI.BLACK);
        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.putString(25, 15, "Goodbye Friend, come back another time!", SGR.BOLD);
        Thread.sleep(3000);
        terminal.close();
    }

    private static void startGame(Terminal terminal) throws IOException, InterruptedException {
        terminal.clearScreen();
        Map map = loadMap(terminal);
        Cookie cookie = loadPoints(terminal);

        Player player = createPlayer();

        List<InterfaceMonster> monsters = createMonsters();

        drawCharacters(terminal, player, monsters);


        do {
            terminal.setCursorPosition(3, 0);
            terminal.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
            terminal.putString("Points: " + player.getPoints());
            KeyStroke keyStroke = getUserKeyStroke(terminal);

            movePlayer(player, keyStroke);

            collectPoints(player, cookie);

            crash(map, terminal, player);

            moveMonsters(player, monsters);
            crashMonster(map, terminal, monsters);

            crashMonsterCookie(cookie, terminal, monsters);

            drawCharacters(terminal, player, monsters);

            if (crashMonsterCookie(cookie, terminal, monsters)) {
                for (Position p : cookie.getCookies()) {
                    if (p.getisAlive()) {
                        terminal.setCursorPosition(p.getX(), p.getY());
                        terminal.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
                        terminal.putCharacter(cookie.getSymbol());
                    }
                }
            }
            terminal.flush();

        } while (isPlayerAlive(player, monsters));


        terminal.setForegroundColor(TextColor.ANSI.RED);
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        terminal.bell();
        terminal.flush();

        KeyStroke keyStroke = getUserKeyStroke(terminal);
        terminal.flush();

        if (keyStroke != null) {
            terminal.clearScreen();
            TextGraphics textGraphics = terminal.newTextGraphics();
            textGraphics.putString(40, 15, "Game over", SGR.BOLD, SGR.BLINK);
            textGraphics.putString(33, 20, "Play again? Press 'Enter'", SGR.BOLD);
            textGraphics.putString(37, 22, "Press ESC to quit", SGR.BOLD);
            terminal.flush();
            int x = 0;
            while (x == 0) {
                switch (getUserKeyStroke(terminal).getKeyType()) {
                    case Enter -> {
                        x++;
                        terminal.close();
                        introGameScreen();
                    }
                    case Escape -> {
                        x++;
                        closeGame(terminal);
                    }
                }
            }
        }
    }

    private static void crash(Map map, Terminal terminal, Player player) throws IOException {
        boolean crashIntoObsticle = false;
        for (Position p : map.getPositions()) {
            if (player.getX() == p.getX() && player.getY() == p.getY()) {
                crashIntoObsticle = true;
            }
        }

        if (crashIntoObsticle) {
            player.setX(player.getPreviousX());
            player.setY(player.getPreviousY());
        } else {

            terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
            terminal.putCharacter(' ');
            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());
        }
        terminal.flush();

    }

    private static void crashMonster(Map map, Terminal terminal, List<InterfaceMonster> monsters) throws IOException {
        boolean crashIntoObsticle = false;
        for (InterfaceMonster monster: monsters) {
            for (Position p : map.getPositions()) {
                if (monster.getX() == p.getX() && monster.getY() == p.getY()) {
                    crashIntoObsticle = true;
                }
            }
            if (crashIntoObsticle) {
                monster.setX(monster.getPreviousX());
                monster.setY(monster.getPreviousY());
            } else {

                terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
                terminal.putCharacter(' ');
                terminal.setCursorPosition(monster.getX(), monster.getY());
                terminal.putCharacter(monster.getSymbol());
            }
            terminal.flush();
        }

    }


    private static boolean crashMonsterCookie(Cookie cookie, Terminal terminal, List<InterfaceMonster> monsters) throws IOException {
        boolean crashIntoObsticle = false;
        for (InterfaceMonster monster: monsters) {
            for (Position p : cookie.getCookies()) {
                if (monster.getPreviousX() == p.getX() && monster.getPreviousY() == p.getY() && p.getisAlive()) {
                    crashIntoObsticle = true;
                    return crashIntoObsticle;
                }
            }
        }
        terminal.flush();
        return crashIntoObsticle;
        }



    private static void moveMonsters(Player player, List<InterfaceMonster> monsters) {
        for (InterfaceMonster m : monsters) {
            m.moveTowards(player);
        }
    }

    private static void movePlayer(Player player, KeyStroke keyStroke) {
        switch (keyStroke.getKeyType()) {
            case ArrowUp -> player.moveUp();
            case ArrowDown -> player.moveDown();
            case ArrowLeft -> player.moveLeft();
            case ArrowRight -> player.moveRight();
        }
    }

    private static KeyStroke getUserKeyStroke(Terminal terminal) throws InterruptedException, IOException {
        KeyStroke keyStroke;
        do {
            Thread.sleep(5);
            keyStroke = terminal.pollInput();
        } while (keyStroke == null);
        return keyStroke;
    }

    public static Player createPlayer() {
        return new Player(true, new Position(11, 11), '\u046a');
    }

    private static List<InterfaceMonster> createMonsters(){
        List<InterfaceMonster> monsters = new ArrayList<>();
        monsters.add(new Monster(true, new Position(3, 3), '\u046c'));
        monsters.add(new ErraticMonster(true, new Position(92, 35), '\u0468'));
        return monsters;
    }

    private static void drawCharacters(Terminal terminal, Player player, List<InterfaceMonster> monsters) throws IOException {
        for (InterfaceMonster monster : monsters) {
            terminal.setForegroundColor(TextColor.ANSI.RED);
            terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
            terminal.putCharacter(' ');
            terminal.setCursorPosition(monster.getX(), monster.getY());
            terminal.putCharacter(monster.getSymbol());
        }

        terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
        terminal.putCharacter(' ');
        terminal.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());

        terminal.flush();
    }

    private static boolean isPlayerAlive(Player player, List<InterfaceMonster> monsters) {
        for (InterfaceMonster monster : monsters) {
            if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                player.setisAlive(false);
                return player.getisAlive();
            }
        }
        return true;
    }

    private static void collectPoints(Player player, Cookie cookies) {
        for (Position cookie : cookies.getCookies()) {
            if (cookie.getX() == player.getX() && cookie.getY() == player.getY() && cookie.getisAlive()) {
                player.setPoints();
                cookie.setisAlive(false);
            }
        }
    }
}