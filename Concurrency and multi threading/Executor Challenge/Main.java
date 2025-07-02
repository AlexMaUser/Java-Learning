import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

record Order(long orderId, String item, int quantity) {}

public class Main {
    public static final Random random = new Random();

    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        ExecutorService orderingService = Executors.newCachedThreadPool();

//        Callable<Order> orderingTask = () -> {
//
//            Order newOrder = generateOrder();
//            try {
//                Thread.sleep(random.nextInt(500, 2000));
//                warehouse.receiveOrder(newOrder);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return newOrder;
//        };

//        List<Callable<Order>> tasks = Collections.nCopies(15, orderingTask);
//        try {
//            orderingService.invokeAll(tasks);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try {
            for(int j = 0; j < 15; j++) {
                Thread.sleep(random.nextInt(500, 2000));
                orderingService.submit(() -> warehouse.receiveOrder(generateOrder()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        orderingService.shutdown();
        try {
            orderingService.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        warehouse.shutDown();
    }

    private static Order generateOrder() {
        return new Order(
                random.nextLong(1000000, 9999999),
                ShoeWarehouse.PRODUCT_LISt[random.nextInt(0, 4)],
                random.nextInt(1, 4));
    }
}