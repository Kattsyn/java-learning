package kattsyn.exceptions.task1;

public class RobotConnectionImpl implements RobotConnection{
    @Override
    public void moveRobotTo(int x, int y) {
        System.out.println("Robot moved to (" + String.valueOf(x) + ", " + String.valueOf(y) + ")");
    }

    @Override
    public void close() {
        System.out.println("Connection Closed");
    }
}
