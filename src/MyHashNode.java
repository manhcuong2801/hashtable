
import java.util.ArrayList; 
  

class MyHashNode<K, V> 
{ 
    K key; 
    V value; 
  
 
    MyHashNode<K, V> next; 
  
     
    public MyHashNode(K key, V value) 
    { 
        this.key = key; 
        this.value = value; 
    } 
} 
  
 class Map<K, V> 
{ 
     private ArrayList<MyHashNode<K, V>> bucketArray; 
  
     private int numBuckets; 
  
     private int size; 
  
     public Map() 
    { 
        bucketArray = new ArrayList<>(); 
        numBuckets = 10; 
        size = 0; 
  
        // Create empty chains 
        for (int i = 0; i < numBuckets; i++) 
            bucketArray.add(null); 
    } 
  
    public int size() { return size; } 
    public boolean isEmpty() { return size() == 0; } 
  
      private int getBucketIndex(K key) 
    { 
        int hashCode = key.hashCode(); 
        int index = hashCode % numBuckets; 
        return index; 
    } 
  
     public void remove(K key) 
    { 
         int bucketIndex = getBucketIndex(key); 
  
         MyHashNode<K, V> head = bucketArray.get(bucketIndex); 
  
         MyHashNode<K, V> prev = null; 
        while (head != null) 
        { 
             if (head.key.equals(key)) 
                break; 
  
              prev = head; 
            head = head.next; 
        } 
  
         if (head == null) 
            return ; 
  
       size--;
  
        // Remove key 
        if (prev != null) 
            prev.next = head.next; 
        else
            bucketArray.set(bucketIndex, head.next); 
  
//        return head.value; 
    } 
  
 
    public V get(K key) 
    { 
         int bucketIndex = getBucketIndex(key); 
        MyHashNode<K, V> head = bucketArray.get(bucketIndex); 
  
         while (head != null) 
        { 
            if (head.key.equals(key)) 
                return head.value; 
            head = head.next; 
        } 
  
        // If key not found 
        return null; 
    } 
    public MyHashNode<K,V> search(K key) 
    { 
         int bucketIndex = getBucketIndex(key); 
        MyHashNode<K, V> head = bucketArray.get(bucketIndex); 
  
         while (head != null) 
        { 
            if (head.key.equals(key)) 
                return head; 
            head = head.next; 
            
        } 
  
        // If key not found 
        return null; 
    } 
    public void set(K key, V newValue) {
        
        MyHashNode<K,V> e = search(key); V v = get(key);
        if ((e.key.equals(key)) && (v != null)) {
            e.value = newValue;   
        }
    }
     public void add(K key, V value) 
    { 
          int bucketIndex = getBucketIndex(key); 
        MyHashNode<K, V> head = bucketArray.get(bucketIndex); 
  
         while (head != null) 
        { 
            if (head.key.equals(key)) 
            { 
                head.value = value; 
                return; 
            } 
            head = head.next; 
        } 
  
         size++; 
        head = bucketArray.get(bucketIndex); 
        MyHashNode<K, V> newNode = new MyHashNode<K, V>(key, value); 
        newNode.next = head; 
        bucketArray.set(bucketIndex, newNode); 
  
         if ((1.0*size)/numBuckets >= 0.7) 
        { 
            ArrayList<MyHashNode<K, V>> temp = bucketArray; 
            bucketArray = new ArrayList<>(); 
            numBuckets = 2 * numBuckets; 
            size = 0; 
            for (int i = 0; i < numBuckets; i++) 
                bucketArray.add(null); 
  
            for (MyHashNode<K, V> headNode : temp) 
            { 
                while (headNode != null) 
                { 
                    add(headNode.key, headNode.value); 
                    headNode = headNode.next; 
                } 
            } 
        } 
    } 
  
     public static void main(String[] args) 
    { 
        Map<String, Integer>map = new Map<>(); 
        map.add("this",1 ); 
        map.add("coder",2 ); 
        map.add("this",4 ); 
        map.add("x",10 ); 
        map.add("hi",7 ); 
        map.add("hi",5 ); 
        map.set("x", 8);
        System.out.println(map.get("coder"));
        System.out.println(map.size()); 
        System.out.println(map.isEmpty()); 
        System.out.println(map.get("x"));
        System.out.println(map.search("hi").value);
        map.remove("this");
        map.toString();
    } 
} 