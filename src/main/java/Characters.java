public abstract class Characters {

    protected boolean isAlive;

    protected Position position;

    protected char symbol;

    public Characters(boolean isAlive, Position position, char symbol) {

        this.isAlive = isAlive;
        this.position = position;
        this.symbol = symbol;

    }

    public boolean getisAlive() {
        return isAlive;
    }

    public void setisAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    /**public Position getPosition() {
        return position;
       // position.getX() / position.setX() / position.getY() / position.setY()
    }*/

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getPreviousX() {
        return position.getPreviousX();
    }

    public int getPreviousY() {
        return position.getPreviousY();
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
