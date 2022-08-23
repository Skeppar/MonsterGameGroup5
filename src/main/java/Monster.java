public class Monster extends Characters {

    public Monster(boolean isAlive, Position position, char symbol) {
        super(isAlive, position, symbol);
    }

    public char getSymbol() {

        return symbol;
    }

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
            // Move horizontal! <--->
            if (diffX < 0) {
                int x = position.getX();
                position.setX(x + 1);
            } else {
                int x = position.getX();
                position.setX(x - 1);
            }
        } else if (absDiffX < absDiffY) {
            // Move vertical! v / ^
            if (diffY < 0) {
                int y = position.getY();
                position.setX(y + 1);
            } else {
                int y = position.getY();
                position.setX(y - 1);
            }
        } else {
            // Move diagonal! / or \
            if (diffX < 0) {
                int x = position.getX();
                position.setX(x + 1);
            } else {
                int x = position.getX();
                position.setX(x - 1);
            }
            if (diffY < 0) {
                int y = position.getY();
                position.setX(y + 1);
            } else {
                int y = position.getY();
                position.setX(y - 1);
            }
        }
    }

    /*
    @Override
    public String toString() {
        return "monstergame2.Monster{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }

     */
}