public class Player extends Characters {

    private int points;



    public Player(boolean isAlive, Position position, char symbol) {
        super(isAlive, position, symbol);
    }


    public char getSymbol() {
        return symbol;
    }


    public void moveUp(){
        position.setPreviousX(position.getX());
        position.setPreviousY(position.getY());
        position.setY(position.getPreviousY() - 1);

    }

    public void moveDown(){
        position.setPreviousX(position.getX());
        position.setPreviousY(position.getY());
        position.setY(position.getPreviousY() + 1);

    }

    public void moveLeft(){
        position.setPreviousX(position.getX());
        position.setPreviousY(position.getY());
        position.setX(position.getPreviousX() -1);

    }

    public void moveRight(){
        position.setPreviousX(position.getX());
        position.setPreviousY(position.getY());
        position.setX(position.getPreviousX() + 1);


    }

    public void setPoints() {
        points++;
    }

    public int getPoints() {
        return points;
    }

}