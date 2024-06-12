package kattsyn.edu;

import java.util.Arrays;
import java.util.logging.*;

public class LogDemo3 {
    /*
        ОБЯЗАТЕЛЬНО ОТКРЫВАЕМ КОНФИГУРАЦИИ ЗАПУСКА КЛАССА
        ОТКРЫВАЕМ ПАРАМЕТРЫ VM (ПАРАМЕТРЫ ЗАПУСКА), ОНИ ОБЫЧНО СКРЫТЫ
        ПИШЕМ ТУДА -Djava.util.logging.config.file={абсолютный путь файла с конфигурацией}

        Уровни детальности логирования  (java.util.logging.Level) от самого детального к самому краткому
        ALL уровень, при котором будут записаны все логи из системы.
        FINEST
        FINER
        FINE сообщение об успешной операции
        CONFIG
        INFO информационное сообщение
        WARNING предупреждение
        SEVERE ошибка
        OFF никакие логи не записываются, все будут проигнорированы;
     */

    //задаем логгер по имени нашего класса
    private static final Logger LOGGER = Logger.getLogger(LogDemo3.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.FINE, "Stared with arguments: {0}", Arrays.toString(args));

        try {
            //вызываем метод, который с 50% шансом выдает IllegalStateException, либо число
            randomlyFailingAlgorithm();
        } catch (IllegalStateException e) {
            //Отлавливание в логгер исключения с записью stacktrace
            LOGGER.log(Level.SEVERE, "Exception caught", e);
            System.exit(2);
        }
        //если все хорошо прошло, то в логгер также укажем это
        LOGGER.log(Level.FINE, "Finished successfully");
    }

    private static void randomlyFailingAlgorithm() {
        double randomNumber = Math.random();
        LOGGER.log(Level.FINE, "Generated random number: {0}", randomNumber);
        if (randomNumber < 0.5) {
            throw new IllegalStateException("Invalid phase of Moon");
        }
    }
}