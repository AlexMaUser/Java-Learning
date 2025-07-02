public class OddThread extends Thread {
    @Override
    public void run() {
        for(int i = 1; i <= 10; i+=2) {
            System.out.println("OddThread " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                break;
            }
        }
    }
}
