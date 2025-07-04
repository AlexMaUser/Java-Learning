import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile rao;

    public static void main(String[] args) {
        try(RandomAccessFile rao = new RandomAccessFile("locations_rand.dat", "rwd")) {
           rao.writeInt(locations.size());
           int indexSize = locations.size() * 3 * Integer.BYTES;
           int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
           rao.writeInt(locationStart);

           long indexStart = rao.getFilePointer();

           int startPointer = locationStart;
           rao.seek(startPointer);

           for(Location location : locations.values()) {
               rao.writeInt(location.getLocationID());
               rao.writeUTF(location.getDescription());
               StringBuilder builder = new StringBuilder();
               for(String direction : location.getExits().keySet()) {
                   if(!direction.equalsIgnoreCase("Q")) {
                       builder.append(direction);
                       builder.append(",");
                       builder.append(location.getExits().get(direction));
                       builder.append(",");
                   }
               }
               rao.writeUTF(builder.toString());
               IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
               index.put(location.getLocationID(), record);

               startPointer = (int) rao.getFilePointer();
           }

            rao.seek(indexStart);
            for(Integer locationID : index.keySet()) {
                rao.writeInt(locationID);
                rao.writeInt(index.get(locationID).getStartByte());
                rao.writeInt(index.get(locationID).getLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            rao = new RandomAccessFile("locations_rand.dat", "rwd");
            int numLocations = rao.readInt();
            long locationStartPoint = rao.readInt();

            while (rao.getFilePointer() < locationStartPoint) {
                int locationID = rao.readInt();
                int locationStart = rao.readInt();
                int locationLength = rao.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationID, record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
        rao.seek(record.getStartByte());
        int id = rao.readInt();
        String description = rao.readUTF();
        String exits = rao.readUTF();
        String[] data = exits.split(",");
        System.out.println(Arrays.toString(data));

        Location location = new Location(locationId, description, null);

        if(locationId != 0) {
            for (int i = 0; i < data.length; i += 2) {
                String direction = data[i];
                int destination = Integer.parseInt(data[i + 1]);
                System.out.println("Direction = " + direction);
                System.out.println("Destination = " + destination);
                location.addExit(direction, destination);
                }
        }

        return location;
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        rao.close();
    }
}
