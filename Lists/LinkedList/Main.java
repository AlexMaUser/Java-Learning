import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> placesToVisit = new LinkedList<>();
        placesToVisit.add("Sydney");
        placesToVisit.add(0, "Canberra");
        System.out.println(placesToVisit);

        addMoreElements(placesToVisit);
        System.out.println(placesToVisit);

//        removeElements(placesToVisit);
//        System.out.println(placesToVisit);

//        gettingElements(placesToVisit);

        System.out.println("-".repeat(30));
//        printItinerary3(placesToVisit);
        testIterator(placesToVisit);
    }

    private static void addMoreElements(LinkedList<String> places) {
        places.addFirst("Darwin");
        places.addLast("Hobart");

        // Queue
        places.offer("Melbourne");
        places.offerFirst("Brisbane");
        places.offerLast("Toowoomba");

        // Stack
        places.push("Alice Springs");
    }

    private static void removeElements(LinkedList<String> places) {
        places.remove(4);
        places.remove("Brisbane");

        System.out.println(places);
        String s1 = places.remove(); // remove first element
        System.out.println(s1 + " was removed");

        String s2 = places.removeFirst(); // remove first element
        System.out.println(s2+ " was removed first");

        String s3 = places.removeLast(); // remove first element
        System.out.println(s3 + " was removed last");

        // Queue
        String p1 = places.poll();
        System.out.println(p1 + " was removed");

        String p2 = places.pollFirst();
        System.out.println(p2 + " was removed");

        String p3 = places.pollLast();
        System.out.println(p3 + " was removed");

        // stack pushing items
        places.push("Sydney");
        places.push("Brisbane");
        places.push("Canberra");

        String p4 = places.pop(); // removed the last element from the list
        System.out.println(p4 + " was removed");
    }

    private static void gettingElements(LinkedList<String> places) {
        System.out.println("Retrieved Element = " + places.get(4));
        System.out.println("First Element = " + places.getFirst());
        System.out.println("Last Element = " + places.getLast());

        System.out.println("Darwin is at index " + places.indexOf("Darwin"));
        System.out.println("Melbourne is at position " + places.lastIndexOf("Melbourne"));

        // queue retrieval
        System.out.println("Element from element() -> " + places.element());

        // stack retrieval methods
        System.out.println("Element from peek() " + places.peek());
        System.out.println("Element from peekFirst() " + places.peekFirst());
        System.out.println("Element from peekLast() " + places.peekLast());
    }

    private static void printItinerary(LinkedList<String> places) {
        System.out.println("Trip starts " + places.getFirst());

        for(int i = 0; i < places.size(); i++) {
            System.out.println(places.get(i));
        }

        System.out.println("Trip ends " + places.getLast());
    }

    private static void printItinerary2(LinkedList<String> places) {
        System.out.println("Trip starts " + places.getFirst());

        String previousTown = places.getFirst();
        for(String town : places) {
            System.out.println("From ->>> " + previousTown + " to " + town);
            previousTown = town;
        }

        System.out.println("Trip ends " + places.getLast());
    }

    private static void printItinerary3(LinkedList<String> places) {
        System.out.println("Trip starts " + places.getFirst());
        String previousTown = places.getFirst();

        ListIterator<String> iterator = places.listIterator(1);

        while (iterator.hasNext()) {
            var town = iterator.next();
            System.out.println("From ->>> " + previousTown + " to " + town);
            previousTown = town;
        }

        System.out.println("Trip ends " + places.getLast());
    }

    private static void testIterator(LinkedList<String> list) {
        var iterator = list.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            if(iterator.next().equalsIgnoreCase("Brisbane")) {
                iterator.add("Lake Wivenhoe");
            }
        }

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println(list);

        var iterator2 = list.listIterator(3);
//        System.out.println(iterator2.next());
        System.out.println(iterator2.previous());

    }
}