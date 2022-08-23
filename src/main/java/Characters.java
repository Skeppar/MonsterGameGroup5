public abstract class Characters {

    protected boolean isAlive;

    protected Position position;

    protected char symbol;

    public Characters(boolean isAlive, Position position, char symbol) {

        this.isAlive = isAlive;
        this.position = position;
        this.symbol = symbol;

    }

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
