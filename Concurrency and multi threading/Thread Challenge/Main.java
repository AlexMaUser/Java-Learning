public class Main {
    public static void main(String[] args) {
        OddThread oddThread = new OddThread();
//        Thread evenThread = new Thread(new EvenRunnable());

        Runnable runnable = () -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("EvenThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("EvenThread interrupted");
                    break;
                }
            }
        };
        Thread evenThread = new Thread(runnable);

        oddThread.start();
        evenThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oddThread.interrupt();
    }
}