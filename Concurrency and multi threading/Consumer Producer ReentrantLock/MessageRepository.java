import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageRepository {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();

    public String read() {
        if(lock.tryLock()) {
            try {
                while (!hasMessage) {
                    // wait until there is message to read
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(" ** read was blocked " + lock);
            hasMessage = false;
        }
        return message;
    }

    public void write(String message) {
        if(lock.tryLock()) {
            try {
                while (hasMessage) {
                    // wait until there is empty slot for message
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(" ** writer blocked " + lock);
            hasMessage = true;
        }
        this.message = message;
    }
}
