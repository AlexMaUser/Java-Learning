import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG);

//        Callable<ZonedDateTime> waitThenDoIt = () -> {
//            ZonedDateTime zdt = null;
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                zdt = ZonedDateTime.now();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return zdt;
//        };
//
//        var threadPool = Executors.newFixedThreadPool(4);
//        List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);

//        try {
//            System.out.println("---> " + ZonedDateTime.now().format(dtf));
//            List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);
//            for(Future<ZonedDateTime> result : futureList) {
//                try {
//                    System.out.println(result.get(500, TimeUnit.MILLISECONDS).format(dtf));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

//        for(int i = 0; i < 4; i++) {
//            executor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2 * (i + 1), TimeUnit.SECONDS);
//        }

        Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                System.out.println("A " + ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                System.out.println("Finished");
            }
        };

        var scheduledTask = executor.scheduleAtFixedRate(
                dateTask,
                2,
                2,
                TimeUnit.SECONDS);

        var scheduledTask2 = executor.scheduleWithFixedDelay(
                () -> System.out.println("B " + ZonedDateTime.now().format(dtf)),
                2,
                4,
                TimeUnit.SECONDS);

        long time = System.currentTimeMillis();
        while (!scheduledTask.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if((System.currentTimeMillis() - time) / 1000 > 10) {
                    scheduledTask.cancel(true);
                }
            } catch (InterruptedException e) {
                System.out.println("Finished");
            }
        }
        executor.shutdown();
    }
}