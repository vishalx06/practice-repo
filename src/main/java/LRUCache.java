import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;

    //Dummy head and tail
    private Node head;
    private Node tail;

    public LRUCache (int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
//        tail.next = head;
    }

    // get the value by key
    public int get(int key){
        if(!map.containsKey(key)){
            System.out.println("Key not found"+ key);
            return -1;
        }

        Node node = map.get(key);
//        Moving to front as used
        remove(node);
        insertAtFront(node);

        System.out.println("GET "+ key);
         printCache();
        return node.value;
    }

    // put key-value
    public void put(int key, int value){
        // if already exist
        if(map.containsKey(key)){
            Node existing = map.get(key);
            existing.value = value;

            remove(existing);
            insertAtFront(existing);
        } else {
            if (map.size() == capacity){
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                System.out.println("Removed LRU key "+ lru.key);
            }
            Node newNode =  new Node(key, value);
            insertAtFront(newNode);
            map.put(key,newNode);
        }
        System.out.println("PUT "+ key);
        printCache();
    }
// Printing the cache
    private void printCache() {
        Node current = head.next;
        System.out.println("Cache right now: ");
        while (current != tail){
            System.out.println("[ "+ current.key+" = "+current.value+"]");
            current = current.next;
        }
    }

    // Remove node from list
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert node right after head
    private void insertAtFront(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,10);
        cache.put(2,20);
//        cache.put(3,30);
        cache.get(1);
        cache.put(5,50);
        cache.printCache();
    }

















}
