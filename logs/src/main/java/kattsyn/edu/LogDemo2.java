package kattsyn.edu;

import java.util.logging.Formatter;
import java.util.logging.*;

public class LogDemo2 {
    public static void main(String[] args) {
        //Пример настройки Logger'а
        Logger logger = Logger.getLogger(LogDemo2.class.getName());
        Handler handler;
        try {
            handler = new FileHandler("log.xml");
        } catch (Exception e) {
            handler = new ConsoleHandler();
        }
        Formatter formatter = new XMLFormatter();

        handler.setFormatter(formatter);
        handler.setLevel(Level.WARNING);
        logger.addHandler(handler);

        logger.log(Level.INFO, "test info");
        logger.log(Level.WARNING, "test warning"); // Выводится в файл
    }

    /*
    мини-конспект:
    java.until.logging.Handler //абстрактный класс

    обработчик (Handler) определяет куда будет записано сообщение
    наследники:
    java.until.logging.ConsoleHandler // выводит в консоль
    java.until.logging.FileHandler // выводит в файл
    java.until.logging.SocketHandler // отправляет по сети

    Handler задаётся в конфигурации или прикрепляется к логгеру вызовом метода логгера

    someLogger.addHandler(...)

    Формат записи сообщений:

    Handler имеет ссылку на форматтер и делегирует ему всю работу по превращению залогированного сообщения
    в окончательный вид для записи в файл или для передачи для сети
    java.until.logging.Formatter
    //реализации в стандартной библиотеке:
    java.until.logging.SampleFormatter - в более читаемый вид для человека
    java.until.logging.XMLFormatter - в XML формат

     */


}
