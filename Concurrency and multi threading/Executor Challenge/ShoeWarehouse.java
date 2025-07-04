import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {
    private List<Order> shippingItems;
    private final ExecutorService fulfillmentService;
    public final static String[] PRODUCT_LISt =  {"Running Shoes", "Sandals", "Boots", "High Tops"};

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
        fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public void shutDown() {
        fulfillmentService.shutdown();
    }

    public synchronized void receiveOrder(Order item) {
        while (shippingItems.size() > 20) {
            // wait until there is room for new orders
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        shippingItems.add(item);
        System.out.println(Thread.currentThread().getName() + " Incoming: " + item);
        fulfillmentService.submit(() -> fulfillOrder());
        notifyAll();
    }

    public synchronized Order fulfillOrder() {
        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Order item = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() +  " Fulfilled: " + item);
        notifyAll();
        return item;
    }
}
