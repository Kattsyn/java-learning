package vsu.cs.kattsyn.tasks.task4;

import java.util.*;
import java.util.function.Consumer;

public class EverythingInOne {
    public static void main(String[] args) {
        // Random variables
        String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

// Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );
        try {
            if (!firstMessage.getFrom().equals("Robert Howard"))
                throw new AssertionError("Wrong firstMessage from address");
            if (!firstMessage.getTo().equals("H.P. Lovecraft"))
                throw new AssertionError("Wrong firstMessage to address");
            if (!firstMessage.getContent().endsWith("Howard!"))
                throw new AssertionError("Wrong firstMessage content ending");
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял "
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        try {
            if (!mailBox.get("H.P. Lovecraft").equals(
                    Arrays.asList(
                            "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                    )
            )) throw new AssertionError("wrong mailService mailbox content (1)");

            if (!mailBox.get("Christopher Nolan").equals(
                    Arrays.asList(
                            "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                            "Я так и не понял "
                    )
            )) throw new AssertionError("wrong mailService mailbox content (2)");

            if (!mailBox.get(randomTo).equals(Collections.<String>emptyList()))
                throw new AssertionError("wrong mailService mailbox content (3)");
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }

        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

// Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        try {
            if (!salaries.get(salary1.getTo()).equals(Arrays.asList(1)))
                throw new AssertionError("wrong salaries mailbox content (1)");
            if (!salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)))
                throw new AssertionError("wrong salaries mailbox content (2)");
            if (!salaries.get(randomTo).equals(Arrays.asList(randomSalary)))
                throw new AssertionError("wrong salaries mailbox content (3)");
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }


    public static class MailBox<K, V> extends HashMap<K, V> {

        @Override
        @SuppressWarnings("unchecked")
        public V get(Object key) {
            if (this.containsKey(key)) {
                return super.get(key);
            } else {
                return (V) Collections.<String>emptyList();
            }
        }
    }

    public static interface Sendable<T> {

        String getFrom();

        String getTo();

        T getContent();
    }

    public static class Salary implements Sendable<Integer> {
        private String from;
        private String to;
        private Integer content;

        public Salary(String from, String to, Integer content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public Integer getContent() {
            return content;
        }
    }

    public static class MailService<T> implements Consumer<Sendable<T>> {

        private Map<String, List<T>> mailBox = new MailBox<>();

        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }


        @Override
        public void accept(Sendable<T> tSendable) {
            if (mailBox.containsKey(tSendable.getTo())) {
                mailBox.get(tSendable.getTo()).add(tSendable.getContent());
            } else {
                mailBox.put(tSendable.getTo(), new ArrayList<>());
                mailBox.get(tSendable.getTo()).add(tSendable.getContent());
            }
        }
    }

    public static class MailMessage implements Sendable<String> {

        private String from;
        private String to;
        private String content;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getContent() {
            return content;
        }
    }

}
