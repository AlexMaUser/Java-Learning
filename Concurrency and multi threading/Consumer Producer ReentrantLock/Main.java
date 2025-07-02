public class Main {
    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository), "Reader");
        Thread writer = new Thread(new MessageWriter(messageRepository), "Writer");

        reader.start();
        writer.start();
    }
}