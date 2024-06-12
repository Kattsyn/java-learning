package kattsyn.tasks.task2;

import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        MailService spy = new Spy(Logger.getLogger(Class.class.getName()));
        Thief thief = new Thief(10);
        MailService inspector = new Inspector();
        MailService[] msArray = {spy, thief, inspector};
        MailMessage mail1 = new MailMessage("Romeo", "Juliet", "I love you!");
        MailMessage mail2 = new MailMessage("Austin Powers", "James Bond", "Big secret!");
        MailPackage mail3 = new MailPackage("Romeo", "Juliet", new Package("Flowers", 15));
        MailPackage mail4 = new MailPackage("Romeo", "Juliet", new Package("Flowers", 25));
        MailPackage mail5 = new MailPackage("Austin Powers", "James Bond", new Package("weapons", 5));

        UntrustworthyMailWorker umw = new UntrustworthyMailWorker(msArray);
        try {
            umw.processMail(mail1);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail2);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail3);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail4);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        try {
            umw.processMail(mail5);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }

        System.out.println("Thief have stolen $" + thief.getStolenValue() + "!");
    }


    /*
Интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.
*/
    public static interface Sendable {
        String getFrom();

        String getTo();
    }

    public static class UntrustworthyMailWorker implements MailService {

        RealMailService realMailService = new RealMailService();
        MailService[] strangePeople;

        public UntrustworthyMailWorker(MailService[] strangePeople) {
            this.strangePeople = strangePeople;
        }

        /*
        UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
        чтобы передать почтовый объект непосредственно в сервис почты,
         последовательно передает этот объект набору третьих лиц, а затем,
         в конце концов, передает получившийся объект непосредственно экземпляру RealMailService.
         У UntrustworthyMailWorker должен быть конструктор от массива MailService
         (результат вызова processMail первого элемента массива передается на вход processMail второго элемента, и т. д.)
         и метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService.
         */

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService service : strangePeople) {
                mail = service.processMail(mail);
            }
            getRealMailService().processMail(mail);
            return mail;
        }
    }

    public static class Spy implements MailService {

        private Logger logger = null;

        public Spy(Logger logger) {
            this.logger = logger;
        }


        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom().equals(AUSTIN_POWERS) || mail.getTo().equals(AUSTIN_POWERS)) {
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{((MailMessage) mail).getFrom(), ((MailMessage) mail).getTo(), ((MailMessage) mail).getMessage()});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{((MailMessage) mail).getFrom(), ((MailMessage) mail).getTo()});
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {

        int minPackageCost;
        int totalStolenValue;

        public Thief(int minPackageCost){
            this.minPackageCost = minPackageCost;
            this.totalStolenValue = 0;
        }
        public int getStolenValue(){
            return totalStolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                if (((MailPackage) mail).getContent().price >= minPackageCost) {
                    totalStolenValue += ((MailPackage) mail).getContent().price;
                    mail = new MailPackage(
                            ((MailPackage) mail).from,
                            ((MailPackage) mail).to,
                            new Package("stones instead of " + ((MailPackage) mail).content.content, 0));
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                if (((MailPackage) mail).getContent().content.equals(WEAPONS) || ((MailPackage) mail).getContent().content.equals(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException("Found " + ((MailPackage) mail).getContent().content);
                } else if (((MailPackage) mail).getContent().content.contains("stones")) {
                    throw new StolenPackageException("Got stones in package");
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {

        public IllegalPackageException() {}

        public IllegalPackageException(String message) {
            super(message);
        }

        public IllegalPackageException(String message, Throwable cause) {
            super(message, cause);
        }

        public IllegalPackageException(Throwable cause) {
            super(cause);
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {}

        public StolenPackageException(String message) {
            super(message);
        }

        public StolenPackageException(String message, Throwable cause) {
            super(message, cause);
        }

        public StolenPackageException(Throwable cause) {
            super(cause);
        }
    }


    /*
Абстрактный класс, который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.
*/
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    /*
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
*/
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            return Objects.equals(message, that.message);
        }

    }

    /*
Посылка, содержимое которой можно получить с помощью метода `getContent`
*/
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            return content.equals(that.content);
        }

    }

    /*
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
*/
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            return content.equals(aPackage.content);
        }
    }

    /*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }
}
