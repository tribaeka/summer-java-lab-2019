package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;
import by.epam.training.task4.library.utils.ArrayReflection;
import by.epam.training.task4.library.interfaces.Map;

import java.util.NoSuchElementException;

public class ArrayMap<K, V> implements Map<K, V> {
    public K[] keys;
    public V[] values;
    public int size;

    public ArrayMap (int capacity) {
        keys = (K[])new Object[capacity];
        values = (V[])new Object[capacity];
    }

    public int indexOfKey (K key) {
        Object[] keys = this.keys;
        if (key == null) {
            for (int i = 0, n = size; i < n; i++)
                if (keys[i] == key) return i;
        } else {
            for (int i = 0, n = size; i < n; i++)
                if (key.equals(keys[i])) return i;
        }
        return -1;
    }
    public void removeIndex (int index) {
        if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
        Object[] keys = this.keys;
        size--;

        keys[index] = keys[size];
        values[index] = values[size];

        keys[size] = null;
        values[size] = null;
    }

    private void resize (int newSize) {
        K[] newKeys = (K[]) ArrayReflection.newInstance(keys.getClass().getComponentType(), newSize);
        System.arraycopy(keys, 0, newKeys, 0, Math.min(size, newKeys.length));
        this.keys = newKeys;

        V[] newValues = (V[])ArrayReflection.newInstance(values.getClass().getComponentType(), newSize);
        System.arraycopy(values, 0, newValues, 0, Math.min(size, newValues.length));
        this.values = newValues;
    }

    public void set (K key, V value) {
        int index = indexOfKey(key);
        if (index == -1) {
            if (size == keys.length) resize(Math.max(8, (int)(size * 1.75f)));
            index = size++;
        }
        keys[index] = key;
        values[index] = value;
    }

    @Override
    public void set(by.epam.training.task4.library.interfaces.Entity entity) {
        int index = indexOfKey((K) entity.getKey());
        if (index == -1) {
            if (size == keys.length) resize(Math.max(8, (int)(size * 1.75f)));
            index = size++;
        }
        keys[index] = (K) entity.getKey();
        values[index] = (V) entity.getValue();
    }

    @Override
    public by.epam.training.task4.library.interfaces.Entity getEntity(K key) {
        return new Entity(key, get(key));
    }


    @Override
    public int size() {
        return size;
    }

    public V get (K key) {
        return get(key, null);
    }

    public V get (K key, V defaultValue) {
        Object[] keys = this.keys;
        int i = size - 1;
        if (key == null) {
            for (; i >= 0; i--)
                if (keys[i] == key) return values[i];
        } else {
            for (; i >= 0; i--)
                if (key.equals(keys[i])) return values[i];
        }
        return defaultValue;
    }

    public K getKey (V value, boolean identity) {
        Object[] values = this.values;
        int i = size - 1;
        if (identity || value == null) {
            for (; i >= 0; i--)
                if (values[i] == value) return keys[i];
        } else {
            for (; i >= 0; i--)
                if (value.equals(values[i])) return keys[i];
        }
        return null;
    }

    public void setKey (int index, K key) {
        if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
        keys[index] = key;
    }

    public void setValue (int index, V value) {
        if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
        values[index] = value;
    }

    public boolean contains (V value) {
        V[] values = this.values;
        int i = size - 1;
        while (i >= 0){
            if (value.equals(values[i--])) return true;
        }
        return false;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int clear () {
        K[] keys = this.keys;
        V[] values = this.values;
        int oldSize = size;
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
        return oldSize;
    }

    public int hashCode () {
        K[] keys = this.keys;
        V[] values = this.values;
        int h = 0;
        for (int i = 0, n = size; i < n; i++) {
            K key = keys[i];
            V value = values[i];
            if (key != null) h += key.hashCode() * 31;
            if (value != null) h += value.hashCode();
        }
        return h;
    }

    public class Entries<K, V> implements Iterator<Entity<K, V>> {
        private ArrayMap<K, V> map;
        Entity<K, V> entry = new Entity();
        int index;

        public Entries (ArrayMap<K, V> map) {
            this.map = map;
        }

        public boolean hasNext () {
            return index < map.size;
        }

        public Entity<K, V> getNext () {
            if (index >= map.size) throw new NoSuchElementException(String.valueOf(index));
            entry = new Entity<>(map.keys[index], map.values[index++]);
            return entry;
        }

        public void remove () {
            index--;
            map.removeIndex(index);
        }

        @Override
        public void addBefore(Entity<K, V> kvEntity) {
            map.insert(index - 1, entry.getKey(), entry.getValue());
        }

        @Override
        public void addAfter(Entity<K, V> kvEntity) {
            map.insert(index + 1, entry.getKey(), entry.getValue());
        }

        public void reset () {
            index = 0;
        }

    }


    public void insert (int index, K key, V value) {
        if (index > size) throw new IndexOutOfBoundsException(String.valueOf(index));
        if (size == keys.length) resize(Math.max(8, (int)(size * 1.75f)));

        keys[size] = keys[index];
        values[size] = values[index];

        size++;
        keys[index] = key;
        values[index] = value;
    }

    public Entity remove (K key) {
        Object[] keys = this.keys;
        if (key == null) {
            for (int i = 0, n = size; i < n; i++) {
                if (keys[i] == key) {
                    V value = values[i];
                    removeIndex(i);
                    return new Entity(key, value);
                }
            }
        } else {
            for (int i = 0, n = size; i < n; i++) {
                if (key.equals(keys[i])) {
                    V value = values[i];
                    removeIndex(i);
                    return new Entity(key, value);
                }
            }
        }
        return null;
    }

    @Override
    public by.epam.training.task4.library.interfaces.Entity remove(by.epam.training.task4.library.interfaces.Entity entity) {
        return remove((K) entity.getKey());
    }

    @Override
    public List getKeys() {
        return new ArrayList(keys);
    }

    @Override
    public List getValues() {
        return new ArrayList(values);
    }
}
