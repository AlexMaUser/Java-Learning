public class Product {
    private String model;
    private String manufacturer;
    private int width;
    private int height;
    private int depth;

    public Product(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
}

class Monitor extends Product {
    private int size;
    private String resolution;

    public Monitor(String manufacturer, String model) {
        super(manufacturer, model);
    }

    public Monitor(String manufacturer, String model, int size, String resolution) {
        super(manufacturer, model);
        this.size = size;
        this.resolution = resolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println(String.format("Drawing pixel at %d,%d in color %s ", x, y, color));
    }
}

class Motherboard extends Product {
    private int ramSlots;
    private int cardSlots;
    private String bios;

    public Motherboard(String manufacturer, String model) {
        super(manufacturer, model);
    }

    public Motherboard(String manufacturer, String model, int ramSlots, int cardSlots, String bios) {
        super(manufacturer, model);
        this.ramSlots = ramSlots;
        this.cardSlots = cardSlots;
        this.bios = bios;
    }

    public void loadProgram(String programName) {
        System.out.println("Program " + programName + " is now loading...");
    }
}

class ComputerCase extends Product {
    private String powerSupply;

    public ComputerCase(String manufacturer, String model) {
        super(manufacturer, model);
    }

    public ComputerCase(String manufacturer, String model, String powerSupply) {
        super(manufacturer, model);
        this.powerSupply = powerSupply;
    }

    public void pressPowerButton() {
        System.out.println("Power button pressed");
    }
}