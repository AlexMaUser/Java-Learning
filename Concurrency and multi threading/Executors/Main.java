import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        List<Callable<Integer>> taskList = List.of(
                () -> Main.sum(1, 10, 1, "red"),
                () -> Main.sum(10, 100, 10, "blue"),
                () -> sum(2, 20, 2, "green"));
        try {
            var results = multiExecutor.invokeAll(taskList);
            for(var result : results) {
                System.out.println(result.get(500, TimeUnit.MILLISECONDS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            multiExecutor.shutdown();
        }
    }
    public static void cachedMain(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        try {
            var redValue = multiExecutor.submit(() -> sum(1, 10, 1, "red"));
            var blueValue = multiExecutor.submit(() -> sum(10, 100, 10, "blue"));
            var greenValue = multiExecutor.submit(() -> sum(2, 20, 2, "green"));

//            multiExecutor.execute(() -> sum(1, 10, 1, "yellow"));
//            multiExecutor.execute(() -> sum(10, 100, 10, "cyan"));
//            multiExecutor.execute(() -> sum(2, 20, 2, "purple"));
//
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("\nNext task will be executed\n");
//            for(var color : new String[] {"red", "blue", "green", "yellow", "purple", "cyan", "black"}) {
//                multiExecutor.execute(() -> sum(1, 10, 1, color));
//            }
            try {
                System.out.println(redValue.get(500, TimeUnit.MILLISECONDS));
                System.out.println(blueValue.get(500, TimeUnit.MILLISECONDS));
                System.out.println(greenValue.get(500, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            multiExecutor.shutdown();
        }
    }

    public static void fixedMain(String[] args) {
        int count = 3;
        var multiExecutor = Executors.newFixedThreadPool(count, new ColorThreadFactory());
        for (int i = 0; i < 6; i++) {
            multiExecutor.execute(() -> countDown());
        }
        multiExecutor.shutdown();
    }


    public static void singleMain(String[] args) {
        var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(() -> countDown());
        blueExecutor.shutdown();
        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (isDone) {
            System.out.println("Blue executor finished, Yellow is starting");
            var yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
            yellowExecutor.execute(() -> countDown());
            yellowExecutor.shutdown();

            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isDone) {
                System.out.println("Yellow executor finished, Red is startin");
            }

            var redExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
            redExecutor.execute(() -> countDown());
            redExecutor.shutdown();

            try {
                isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isDone) {
                System.out.println("Red Executor finished. All processes finished");
            }
        }
    }

    public static void notMain(String[] args) {
        Thread blue = new Thread(() -> countDown(), ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(() -> countDown(), ThreadColor.ANSI_YELLOW.name());
        Thread red = new Thread(() -> countDown(), ThreadColor.ANSI_RED.name());

        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.start();

        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {
        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException e) {
            // user may pass bad color, we just ignore it
        }

        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " " + threadName.replace("ANSI_", "") + " " + i);
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException e) {
            // user may pass wrong color
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i <= end; i += delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " + colorString + " " + sum);
        return sum;
    }
}