package by.epam.training.task4.library.implementations;

public class Entity<K, V> implements by.epam.training.task4.library.interfaces.Entity<K, V> {
    private K key;
    private V value;

    public Entity(){

    }
    public Entity(K key, V value){
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }


    public V getValue() {
        return value;
    }

}
