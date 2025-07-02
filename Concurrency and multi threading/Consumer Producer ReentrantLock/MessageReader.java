import java.util.Random;

public class MessageReader implements Runnable {
    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage = "";
        do {
            try {
                Thread.sleep(500, 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equalsIgnoreCase("Finished"));
    }
}
