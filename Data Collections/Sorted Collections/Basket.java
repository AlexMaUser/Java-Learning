import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            // check if we have the item
            int qty = list.getOrDefault(item, 0);
            list.put(item, qty + quantity);
            return qty;
        }
        return 0;
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping Basket " + name + " contains " + list.size() + (list.size() == 1 ? " item" : " items" ) + "\n";
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost +=  item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
