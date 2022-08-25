import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonsterGame {

    public static void main(String[] args) {

        try {
            startGame();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println("Game over!");
        }
    }

    public static Map loadMap(Terminal terminal) throws IOException {
        Map map = new Map();
        map.printMap(terminal);
        return map;
    }

    public static Cookie loadPoints(Terminal terminal) throws IOException {
        Cookie cookie = new Cookie();
        cookie.printPointsMap1(terminal);
        return cookie;
    }

    private static void startGame() throws IOException, InterruptedException {
        TerminalSize ts = new TerminalSize(100, 41);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        Terminal terminal = terminalFactory.createTerminal();
        Map map = loadMap(terminal);
        Cookie cookie = loadPoints(terminal);
        terminal.setCursorVisible(false);
        terminal.flush();

        Player player = createPlayer();

        List<Monster> monsters = createMonsters();

        drawCharacters(terminal, player, monsters);


        do {
            KeyStroke keyStroke = getUserKeyStroke(terminal);

            terminal.setCursorPosition(3, 0);
            terminal.putString("Points: " + player.getPoints());

            movePlayer(player, keyStroke);

            collectPoints(player, cookie);

            crash(map, terminal, player);

            moveMonsters(player, monsters);
            crashMonster(map, terminal, monsters.get(0));

            crashMonsterCookie(cookie, terminal, monsters.get(0));

            drawCharacters(terminal, player, monsters);

            if (crashMonsterCookie(cookie, terminal, monsters.get(0))) {
                // terminal.setCursorPosition(monsters.get(0).getPreviousX(), monsters.get(0).getPreviousY());
                for (Position p : cookie.getCookies()) {
                    if (p.getisAlive()) {
                        terminal.setCursorPosition(p.getX(), p.getY());
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

    private static void crashMonster(Map map, Terminal terminal, Monster monster) throws IOException {
        boolean crashIntoObsticle = false;
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

    private static boolean crashMonsterCookie(Cookie cookie, Terminal terminal, Monster monster) throws IOException {
        boolean crashIntoObsticle = false;
        for (Position p : cookie.getCookies()) {
            if (monster.getPreviousX() == p.getX() && monster.getPreviousY() == p.getY() && p.getisAlive()) {
                crashIntoObsticle = true;
                return crashIntoObsticle;
            }
        }
        terminal.flush();
        return crashIntoObsticle;
    }


    private static void moveMonsters(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            monster.moveTowards(player);
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
        return new Player(true, new Position(11, 11), 'P');
    }

    private static List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(true, new Position(3, 3), 'X'));
        return monsters;
    }

    private static void drawCharacters(Terminal terminal, Player player, List<Monster> monsters) throws IOException {
        for (Monster monster : monsters) {

            terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
            terminal.putCharacter(' ');
            terminal.setCursorPosition(monster.getX(), monster.getY());
            terminal.putCharacter(monster.getSymbol());
        }

        terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());

    }

    private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
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