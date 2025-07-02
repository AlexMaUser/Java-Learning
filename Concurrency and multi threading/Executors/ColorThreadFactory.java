import java.util.concurrent.ThreadFactory;

public class ColorThreadFactory implements ThreadFactory {
    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    public ColorThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if(name == null) {
            name = ThreadColor.values()[colorValue].name();
        }
        if(colorValue++ > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}
