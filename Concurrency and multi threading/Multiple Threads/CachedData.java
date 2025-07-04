import java.util.concurrent.TimeUnit;

public class CachedData {
    private volatile boolean flag = false;

    public void toggleFlag() {
        this.flag = !flag;
    }

    public boolean isReady() {
        return flag;
    }

    public static void main(String[] args) {

        CachedData example = new CachedData();

        Thread writerThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            example.toggleFlag();
            System.out.println("A. Flag is set to " + example.isReady());
        });

        Thread readerThread = new Thread(() -> {
            while (!example.isReady()) {
                // wait until flag becomes true
            }
            System.out.println("B. Flag is set to " + example.isReady());
        });

        writerThread.start();
        readerThread.start();
    }
}
