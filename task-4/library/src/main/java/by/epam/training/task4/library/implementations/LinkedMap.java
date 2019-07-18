package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;
import by.epam.training.task4.library.interfaces.Map;

public class LinkedMap<K, V> implements Map<K, V> {

    private LinkedList<Entity<K, V>> entrySet;

    public LinkedMap() {
        this.entrySet = new LinkedList<>();
    }

    public int size() {
        return this.entrySet.size();
    }

    public boolean isEmpty() {
        return this.entrySet.isEmpty();
    }

    public V get(Object key) {
        Iterator iterator = entrySet.getIterator();
        while (iterator.hasNext()){
            Entity entity = (Entity) iterator.getNext();
            if (entity.getKey().equals(key)) {
                return (V) entity.getValue();
            }
        }
        return null;
    }

    public void set(K key, V value) {
        if (get(key) == null) {
            this.entrySet.add(new Entity<>(key, value));
        }
    }
    public Entity remove(Object key) {
        Iterator<Entity<K, V>> iterator = entrySet.getIterator();
        while (iterator.hasNext()) {
            Entity<K, V> entity = iterator.getNext();
            if (entity.equals(key)) {
                iterator.remove();
                return entity;
            }
        }
        return null;
    }

    public Entity remove(by.epam.training.task4.library.interfaces.Entity inEntity){
        Iterator<Entity<K, V>> iterator = entrySet.getIterator();
        while (iterator.hasNext()) {
            Entity<K, V> entity = iterator.getNext();
            if (entity.equals(inEntity.getKey())) {
                iterator.remove();
                return entity;
            }
        }
        return null;
    }

    public int clear() {
        int oldSize = entrySet.size();
        entrySet.clear();
        return oldSize;
    }

    @Override
    public void set(by.epam.training.task4.library.interfaces.Entity entity) {
        if (get(entity.getKey()) == null) {
            this.entrySet.add((Entity<K, V>) entity);
        }
    }

    @Override
    public List getKeys() {
        List<K> keys = new ArrayList<K>(entrySet.size());
        Iterator<Entity<K, V>> iterator = entrySet.getIterator();
        while (iterator.hasNext()) {
            keys.add(iterator.getNext().getKey());
        }
        return keys;
    }

    @Override
    public List getValues() {
        List<V> values = new ArrayList<V>(entrySet.size());
        Iterator<Entity<K, V>> iterator = entrySet.getIterator();
        while (iterator.hasNext()) {
            values.add(iterator.getNext().getValue());
        }
        return values;
    }

    @Override
    public by.epam.training.task4.library.interfaces.Entity getEntity(K key) {
        Iterator iterator = entrySet.getIterator();
        while (iterator.hasNext()){
            Entity entity = (Entity) iterator.getNext();
            if (entity.getKey().equals(key)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public boolean contains(V value) {
        Iterator<Entity<K, V>> iterator = entrySet.getIterator();
        while (iterator.hasNext()) {
            if (iterator.getNext().getValue().equals(value)){
                return true;
            }
        }
        return false;
    }
}
