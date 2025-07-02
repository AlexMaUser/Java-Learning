public class Main {
    public static void main(String[] args) {
        var nationalUSParks = new Park[] {
                new Park("Yellowstone", "44.1239, -110.5913"),
                new Park("Grand Canyon", "36.185, -112.0964"),
                new Park("Yosmite", "37.8855, -119.5360")
        };

        Layer<Park> parkLayer = new Layer<>(nationalUSParks);
        parkLayer.renderLayer();

        var majorUSRivers = new River[] {
                new River("Mississippi", "47.1234, -95.2348", "29.1343, -89.2495", "35.9123, -90.0212"),
                new River("Missouri", "45.9239, -111.2342", "38.8164, -90.1282")
        };

        Layer<River> riverLayer = new Layer<>(majorUSRivers);
        riverLayer.addElements(
                new River("Colorado", "40.1231, -105.23891", "31.4234, -114.7723"),
                new River("Delaware", "42.2026, -75.00932", "39.4955, -75.5592"));
        riverLayer.renderLayer();
    }
}