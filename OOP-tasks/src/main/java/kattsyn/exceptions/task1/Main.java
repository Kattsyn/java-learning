package kattsyn.exceptions.task1;

public class Main {
    public static void main(String[] args) {
        //tests
        //test1: все норм
        RobotConnectionManager robotConnectionManager1 = new RobotConnectionManagerImpl();
        moveRobot(robotConnectionManager1, 4, 1);

        /*
        //test2: RobotConnectionError выбрасывает RobotConnectionImplThrowable при вызове moveRobotTo()
        RobotConnectionManager robotConnectionManager2 = new RobotConnectionManagerImplThrowableConn();
        moveRobot(robotConnectionManager2, 4, 1);
        //после трех попыток вызывает throw самого нижнего Exception
         */

        //test3: RobotConnectionManager выбрасывает ошибку при попытке подключения
        RobotConnectionManager robotConnectionManager3 = new RobotConnectionManagerImplThrowable();
        moveRobot(robotConnectionManager3, 4, 1);

    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        RobotConnection robotConnection = null;
        boolean success = false;
        boolean connected = false;
        int maxAttempts = 3;
        for (int attempts = 0; attempts < maxAttempts && !success; attempts++) {
            try {
                robotConnection = robotConnectionManager.getConnection();
                connected = true;
                robotConnection.moveRobotTo(toX, toY);
                success = true;
            } catch (RobotConnectionException ignored) {
            } finally {
                if (connected) {
                    try {
                        robotConnection.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        if (!success) {
            throw new RobotConnectionException("robotException");
        }
    }
}
