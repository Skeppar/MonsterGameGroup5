public class Player extends Characters {

    //private int x;
    //private int y;
    private int previousX;
    private int previousY;

    public Player(boolean isAlive, Position position, char symbol) {
        super(isAlive, position, symbol);
    }



    public char getSymbol() {
        return symbol;
    }


    public void moveUp(){
        previousX = position.getX();
        previousY = position.getY();
        position.setY(previousY - 1);
    }

    public void moveDown(){
        previousX = position.getX();
        previousY = position.getY();
        position.setY(previousY + 1);
    }

    public void moveLeft(){
        previousX = position.getX();
        previousY = position.getY();
        position.setX(previousX - 1);
    }

    public void moveRight(){
        previousX = position.getX();
        previousY = position.getY();
        position.setX(previousX + 1);
    }

    /**
    @Override
    public String toString() {
        return "monstergame2.Player{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }*/
}