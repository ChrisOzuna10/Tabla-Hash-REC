package models;

import java.util.LinkedList;

public class HashTable<K, v> {
    private int size = 10000;
    private LinkedList<Entry<K, v>>[] table;

    public HashTable() {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public LinkedList<K> keySet() {
        LinkedList<K> keys = new LinkedList<>();
        for (LinkedList<Entry<K, v>> bucket : table) {
            for (Entry<K, v> entry : bucket) {
                keys.add(entry.key);
            }
        }
        return keys;
    }
    public void agregar(K key, v value) {
        int index = hash(key);
        LinkedList<Entry<K, v>> bucket = table[index];
        bucket.add(new Entry<>(key, value));
    }

    public v obtener(K key) {
        int index = hash(key);
        for (Entry<K, v> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
    private int hash(K key) {
        //return Math.abs(murmur3Hash(key.hashCode())) % size;
        return Math.abs(djb2Hash(String.valueOf(key))) % size;
    }

    public int djb2Hash(String key) {
        int hash = 5381;
        for (int i = 0; i < key.length(); i++) {
            hash = ((hash << 5) + hash) + key.charAt(i);
        }
        return hash;
    }

    private int murmur3Hash(int key) {
        key ^= (key >>> 16);
        key *= 0x85ebca6b;
        key ^= (key >>> 13);
        key *= 0xc2b2ae35;
        key ^= (key >>> 16);
        return key;
    }
}
