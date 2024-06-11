package kattsyn.exceptions.task1;

public class RobotConnectionManagerImpl implements RobotConnectionManager{
    @Override
    public RobotConnection getConnection() {
        return new RobotConnectionImpl();
    }
}
