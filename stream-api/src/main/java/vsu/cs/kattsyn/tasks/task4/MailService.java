package vsu.cs.kattsyn.tasks.task4;

import java.util.*;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<Sendable<T>> {

    private Map<String, List<T>> mailBox = new HashMap<>() {
        @Override
        public List<T> get(Object key) {
            if (this.containsKey(key)) {
                return super.get(key);
            } else {
                return Collections.emptyList();
            }
        }
    };

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }


    @Override
    public void accept(Sendable<T> tSendable) {
        if (!mailBox.containsKey(tSendable.getTo())) {
            mailBox.put(tSendable.getTo(), new ArrayList<>());
        }
        mailBox.get(tSendable.getTo()).add(tSendable.getContent());
    }
}
