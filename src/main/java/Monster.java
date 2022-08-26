public class Monster extends Characters implements InterfaceMonster {

    public Monster(boolean isAlive, Position position, char symbol) {
        super(isAlive, position, symbol);
    }

    public char getSymbol() {

        return symbol;
    }

    @Override
    public void moveTowards(Player player) {
        // a monster wants to minimize the distance between itself and the player

        // Along which axis should the monster move in?
        // The monster will move in the direction in which the distance between monster and player is the largest.
        // Let's use the absolute value of the difference between the x-ccordinates vs the y-coordinates!
        // Example of Math.abs -> https://www.tutorialspoint.com/java/lang/math_abs_int.htm

        int diffX = this.position.getX() - player.getX();
        int absDiffX = Math.abs(diffX);
        int diffY = this.position.getY() - player.getY();
        int absDiffY = Math.abs(diffY);

        if (absDiffX > absDiffY) {
            // Move horizontally
            if (diffX < 0) {
                int x = position.getX();
                setPreviousX();
                setPreviousY();
                position.setX(x + 1);
            } else {
                int x = position.getX();
                setPreviousX();
                setPreviousY();
                position.setX(x - 1);
            }
        } else if (absDiffX < absDiffY) {
            // Move vertically
            if (diffY < 0) {
                int y = position.getY();
                setPreviousX();
                setPreviousY();
                position.setY(y + 1);
            } else {
                int y = position.getY();
                setPreviousX();
                setPreviousY();
                position.setY(y - 1);
            }
        } else {
            // Move diagonally
            if (diffX < 0) {
                int x = position.getX();
                setPreviousX();
                position.setX(x + 1);
            } else {
                int x = position.getX();
                setPreviousX();
                position.setX(x - 1);
            }
            if (diffY < 0) {
                int y = position.getY();
                setPreviousY();
                position.setY(y + 1);
            } else {
                int y = position.getY();
                setPreviousY();
                position.setY(y - 1);
            }
        }
    }

    public void setPreviousX() {
        position.setPreviousX(position.getX());
    }
    public void setPreviousY(){
        position.setPreviousY(position.getY());
    }
}