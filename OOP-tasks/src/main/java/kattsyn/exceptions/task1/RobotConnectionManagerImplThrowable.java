package kattsyn.exceptions.task1;

public class RobotConnectionManagerImplThrowable implements RobotConnectionManager{
    @Override
    public RobotConnection getConnection() {
        throw new RobotConnectionException("Connection ERROR");
    }
}
