import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonsterGame {

    public static void main(String[] args) throws IOException {

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

    public static Points loadPoints(Terminal terminal) throws IOException {
        Points points = new Points();
        points.printPointsMap1(terminal);
        return points;
    }

    private static void startGame() throws IOException, InterruptedException {
        TerminalSize ts = new TerminalSize(100, 40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        Terminal terminal = terminalFactory.createTerminal();
        Map map = loadMap(terminal);
        Points points = loadPoints(terminal);
        terminal.setCursorVisible(false);
        terminal.flush();

        Player player = createPlayer();

        List<Monster> monsters = createMonsters();

        drawCharacters(terminal, player, monsters);



        do {
            KeyStroke keyStroke = getUserKeyStroke(terminal);

            movePlayer(player, keyStroke);

            crash(map, terminal, player);

            moveMonsters(player, monsters);
            crashMonster(map, terminal, monsters.get(0));

            drawCharacters(terminal, player, monsters);

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

    private static Terminal createTerminal() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static void drawCharacters(Terminal terminal, Player player, List<Monster> monsters) throws IOException {
        for (Monster monster : monsters) {
            //terminal.setCursorPosition(monster.getPosition().getPreviousX(), monster.getPosition().getPreviousY());
            //terminal.setCursorPosition(monster.setX(monster.getPreviousX(), monster.setY(mo);););


            terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
            terminal.putCharacter(' ');
            //monster.position.getX();
            //terminal.setCursorPosition(monster.getPosition().getX(), monster.getPosition().getY());
            terminal.setCursorPosition(monster.getX(), monster.getY());
            terminal.putCharacter(monster.getSymbol());
        }


        terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());

        terminal.flush();

    }

    private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                player.isAlive = true;
                return false;
            }
        }
        return true;
    }

    private static void collectPoints(Player player, List<Points> points) {
        for (Points point : points) {
            if (point.getPositions().)
        }
    }
}