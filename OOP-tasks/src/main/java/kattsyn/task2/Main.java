package kattsyn.task2;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.x = 1;
        robot.y = 1;
        robot.direction = Direction.UP;
        moveRobot(robot, -2, -2);
        System.out.println(robot.x);
        System.out.println(robot.y);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {

        Direction xDir = toX - robot.getX() > 0 ? Direction.RIGHT : Direction.LEFT;
        Direction yDir = toX - robot.getX() > 0 ? Direction.UP : Direction.DOWN;

        while (robot.getDirection() != xDir) {
            robot.turnRight();
        }
        while (robot.getX() != toX) {
            robot.stepForward();
        }
        while (robot.getDirection() != yDir) {
            robot.turnRight();
        }
        while (robot.getY() != toY){
            robot.stepForward();
        }
    }
}






