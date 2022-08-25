import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
public abstract introGameClass {

    public static void introGameScreen() {
        //introskärm. Funktion ska visa text "Monster Game 5", ska även ha enter för att köra, esc för att avsluta.
        TerminalSize ts = new TerminalSize(100, 40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        terminal.flush();
        /* DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        // textstring
        final TextGraphics textGraphics = terminal.newTextGraphics();
        terminal.setBackgroundColor(TextColor.ANSI.BLACK);
        terminal.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        terminal.enableSGR(SGR.BOLD);
        textGraphics.putString(2, 5,"-- ** Packman The Cookie Cather ** --", SGR.BOLD);
        textGraphics.putString(4, 5,"--Press Enter to start the fun--", SGR.BLINK);
        terminal.flush();
        boolean stop = false;
        while(!stop) {
            KeyStroke key =terminal.readInput();
            //wait for key press
            while (key == null) key = terminal.readInput();
            // enter to run the program
            switch (key.getKeyType()) {
                case Enter -> {
                    stop = true;
                    break;
                }
                // esc to be able to not run the program (change to q?)
                case Escape -> {
                    startGame();
                    textGraphics.putString(2, 5, "Goodbye Friend, come back another time!");
                    stop =true;
                }
            }
        }
    }



}
*/
