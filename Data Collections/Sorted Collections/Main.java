import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 17852, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 60, 40);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.2, 500);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.3, 7);
        stockList.addStock(temp) ;

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.23, 52);
        stockList.addStock(temp);

        temp = new StockItem("towel", 82.6, 35);
        stockList.addStock(temp);

        temp = new StockItem("vase", 83.2, 51);
        stockList.addStock(temp);

        System.out.println(stockList);

        for(String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket timsBasket = new Basket("Tim");
        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        sellItem(timsBasket, "car", 1);
        sellItem(timsBasket, "spanner", 5);
        System.out.println(timsBasket);

        sellItem(timsBasket, "juice", 4);
        sellItem(timsBasket, "cup", 12);
        sellItem(timsBasket, "bread", 1);
        System.out.println(timsBasket);
        System.out.println(stockList);

        temp = new StockItem("pen", 1.12);
//        stockList.Items().put(temp.getName(), temp);
        stockList.Items().get("car").adjustStock(2000);
        stockList.Items().get("car").adjustStock(-1000);
        System.out.println(stockList);

        for(Map.Entry<String, Double> price : stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        //retrieve item
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + stockItem);
            return 0;
        }

        if(stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}