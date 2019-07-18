import by.epam.training.task4.library.IntegerComparator;
import by.epam.training.task4.library.implementations.*;
import by.epam.training.task4.library.implementations.Entity;
import by.epam.training.task4.library.interfaces.*;

public class Runner {
    private static Integer[] integerArray = new Integer[]{6, 7};
    public static void main(String[] args) {
        arrayListTest();
        linkedListTest();
        arrayStackTest();
        linkedStackTest();
        arrayQueueTest();
        linkedQueueTest();
        entityTest();
    }

    private static void arrayListTest() {
        System.out.println("\tArrayList:");
        List<Integer> arrayList = new ArrayList<>(new IntegerComparator());
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(null);
        arrayList.trim();
        arrayList.addAll(new ArrayList<>(new IntegerComparator(), new Integer[]{4, 5}));
        arrayList.addAll(integerArray);
        arrayList.set(6, 6);
        arrayList.remove(5);
        System.out.println(arrayList.find(6));
        System.out.println(arrayList.get(5));
        arrayList.filterDifference(new Integer[]{5, 6});
        arrayList.filterMatches(new Integer[]{1, 2});
        Iterator arrayListIterator = arrayList.getIterator();
        while (arrayListIterator.hasNext()) {
            System.out.println(arrayListIterator.getNext());
        }
        Object[] someArray = arrayList.toArray();
        System.out.println(arrayList.size());
        System.out.println(someArray.length);
    }

    private static void linkedListTest() {
        System.out.println("\tLinkedList:");
        List<Integer> linkedList = new LinkedList<>(new IntegerComparator());
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(null);
        linkedList.trim();
        linkedList.addAll(new LinkedList<>(new IntegerComparator(), new Integer[]{4, 5}));
        linkedList.addAll(integerArray);
        Iterator linkedListIterator = linkedList.getIterator();
        while (linkedListIterator.hasNext()) {
            System.out.println(linkedListIterator.getNext());
        }
        Object[] someArray1 = linkedList.toArray();
        System.out.println(linkedList.size());
        System.out.println(someArray1.length);
    }

    private static void arrayStackTest(){
        System.out.println("\tArratStack:");
        Stack<Integer> arrayStack = new ArrayStack<>(7);
        arrayStack.push(2);
        arrayStack.push(1);
        arrayStack.push(3);
        arrayStack.clear();
        arrayStack.pushAll(new ArrayStack<Integer>(2){{push(4);push(5);}});
        arrayStack.pushAll(integerArray);
        arrayStack.sort(new IntegerComparator());
        Iterator arrayStackIterator = arrayStack.getIterator();
        while (arrayStackIterator.hasNext()){
            System.out.println(arrayStackIterator.getNext());
        }
        System.out.println(arrayStack.search(7));
    }

    private static void linkedStackTest(){
        System.out.println("\tLinkedStack:");
        Stack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(2);
        linkedStack.push(1);
        linkedStack.push(3);
        linkedStack.clear();
        linkedStack.pushAll(new ArrayStack<Integer>(2){{push(4);push(5);}});
        linkedStack.pushAll(integerArray);
        linkedStack.sort(new IntegerComparator());
        Iterator linkedStackIterator = linkedStack.getIterator();
        while (linkedStackIterator.hasNext()){
            System.out.println(linkedStackIterator.getNext());
        }
    }

    private static void arrayQueueTest(){
        System.out.println("\tArrayQeue:");
        Queue<Integer> arrayQueue = new ArrayQueue<>(5);
        arrayQueue.push(1);
        arrayQueue.push(2);
        arrayQueue.push(3);
        arrayQueue.pushAll(integerArray);
        Iterator arrayQueueIterator = arrayQueue.getIterator();
        while (arrayQueueIterator.hasNext()){
            System.out.println(arrayQueueIterator.getNext());
        }
        System.out.println(arrayQueue.isEmpty());
    }

    private static void linkedQueueTest(){
        System.out.println("\tLinkedQeue:");
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.push(1);
        linkedQueue.push(2);
        linkedQueue.push(3);
        linkedQueue.pushAll(integerArray);
        Iterator arrayQueueIterator = linkedQueue.getIterator();
        while (arrayQueueIterator.hasNext()){
            System.out.println(arrayQueueIterator.getNext());
        }
        System.out.println(linkedQueue.size());
    }

    private static void entityTest(){
        System.out.println("\tEntity:");
        Entity<String, Integer> entity = new Entity<>("string", 1);
        System.out.println(entity.getKey());
        System.out.println(entity.getValue());
    }

    private static void arrayMapTest(){
        System.out.println("\tArrayMap:");
        Map<String, Integer> arrayMap = new ArrayMap<>(5);
        arrayMap.set("q", 1);
        arrayMap.set("w", 2);
        arrayMap.set("e", 3);
        arrayMap.set(new Entity("r", 4));
        arrayMap.set(new Entity("t", 5));
        Iterator keysIterator = arrayMap.getKeys().getIterator();
        System.out.println("Keys:");
        while (keysIterator.hasNext()){
            System.out.println(keysIterator.getNext());
        }
        System.out.println(((ArrayMap<String, Integer>) arrayMap).indexOfKey("w"));
        System.out.println(arrayMap.contains(1));
        System.out.println(arrayMap.getEntity("q").getValue());
    }

    private static void linkedMapTest(){
        System.out.println("\tLinkedMap:");
        Map<String, Integer> linkedMap = new LinkedMap<>();
        linkedMap.set("q", 1);
        linkedMap.set("w", 2);
        linkedMap.set("e", 3);
        linkedMap.set(new Entity("r", 4));
        linkedMap.set(new Entity("t", 5));
        System.out.println(linkedMap.get("q"));
        System.out.println(linkedMap.contains(1));
        System.out.println(linkedMap.getEntity("q").getValue());
    }
}