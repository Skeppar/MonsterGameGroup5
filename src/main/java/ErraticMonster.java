import java.util.Random;

public class ErraticMonster extends Characters implements InterfaceMonster {


    public ErraticMonster(boolean isAlive, Position position, char symbol) {
        super(isAlive, position, symbol);

    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void moveTowards(Player player) {

        Random rand = new Random();


        int diffX = this.position.getX() - player.getX();
        int absDiffX = Math.abs(diffX);
        int diffY = this.position.getY() - player.getY();
        int absDiffY = Math.abs(diffY);
        int rn = rand.nextInt(8);



        if (absDiffX > absDiffY) {
            // Move horizontal! <--->
            if (diffX < 0) {
                int x = position.getX();
                setPreviousX();
                setPreviousY();
                position.setX(x + rn);
            } else {
                int x = position.getX();
                setPreviousX();
                setPreviousY();
                position.setX(x - rn);
            }
        } else if (absDiffX < absDiffY) {
            // Move vertical! v / ^
            if (diffY < 0) {
                int y = position.getY();
                setPreviousX();
                setPreviousY();
                position.setY(y + rn);
            } else {
                int y = position.getY();
                setPreviousX();
                setPreviousY();
                position.setY(y - rn);
            }
        } else {
            // Move diagonal! / or \
            if (diffX < 0) {
                int x = position.getX();
                setPreviousX();
                position.setX(x + rn);
            } else {
                int x = position.getX();
                setPreviousX();
                position.setX(x - rn);
            }
            if (diffY < 0) {
                int y = position.getY();
                setPreviousY();
                position.setY(y + rn);
            } else {
                int y = position.getY();
                setPreviousY();
                position.setY(y - rn);
            }
        }



    }

    public void setPreviousX() {
        position.setPreviousX(position.getX());
    }

    public void setPreviousY() {
        position.setPreviousY(position.getY());
    }





}
