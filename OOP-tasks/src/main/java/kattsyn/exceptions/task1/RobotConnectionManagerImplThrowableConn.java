package kattsyn.exceptions.task1;

public class RobotConnectionManagerImplThrowableConn implements RobotConnectionManager{
    @Override
    public RobotConnection getConnection() {
        return new RobotConnectionImplThrowable();
    }
}
