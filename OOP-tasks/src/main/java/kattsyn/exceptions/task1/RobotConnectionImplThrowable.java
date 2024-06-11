package kattsyn.exceptions.task1;

public class RobotConnectionImplThrowable implements RobotConnection {
    @Override
    public void moveRobotTo(int x, int y) {
        throw new RobotConnectionException("RobotERROR");
    }

    @Override
    public void close() {
        System.out.println("Connection Closed");
    }
}
