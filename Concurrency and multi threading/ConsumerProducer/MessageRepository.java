public class MessageRepository {
    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {
        while (!hasMessage) {
            // wait until there is message to read
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (hasMessage) {
            // wait until there is empty slot for message
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = true;
        this.message = message;
        notifyAll();
    }
}
