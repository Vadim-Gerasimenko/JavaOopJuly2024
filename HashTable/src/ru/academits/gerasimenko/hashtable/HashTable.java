package ru.academits.gerasimenko.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<LinkedList<E>> arrayList;
    private int size;
    private int modCount;

    public HashTable() {
        this(50);
    }

    public HashTable(int capacity) {
        validateCapacity(capacity);
        arrayList = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; ++i) {
            arrayList.add(null);
        }
    }

    private int getIndex(Object o) {
        return Math.abs(Objects.hashCode(o) % arrayList.size());
    }

    private static void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Hash table capacity must be a positive number. "
                    + "Current capacity value: " + capacity);
        }
    }

    private static void validateCollectionForNullReference(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("The collection must not be a null reference.");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);
        List<E> list = arrayList.get(index);

        return list != null && list.contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int listIndex = -1;
        private int arrayIndex;
        private int passedElementsCount;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return passedElementsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Collection is over.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("The collection was modified.");
            }

            List<E> list;

            while (true) {
                while ((list = arrayList.get(arrayIndex)) == null) {
                    ++arrayIndex;
                }

                ++listIndex;

                if (listIndex < list.size()) {
                    break;
                }

                listIndex = -1;
                ++arrayIndex;
            }

            ++passedElementsCount;
            return list.get(listIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        copyToArray(array);

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array == null) {
            throw new NullPointerException("The passed array must not refer to null.");
        }

        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        copyToArray(array);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    private void copyToArray(Object[] array) {
        int i = 0;

        for (E e : this) {
            array[i] = e;
            ++i;
        }
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (arrayList.get(index) == null) {
            arrayList.set(index, new LinkedList<>());
        }

        arrayList.get(index).add(e);
        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        List<E> list = arrayList.get(index);

        if (list == null || !list.remove(o)) {
            return false;
        }

        --size;
        ++modCount;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        validateCollectionForNullReference(c);

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        validateCollectionForNullReference(c);

        if (c.isEmpty()) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        validateCollectionForNullReference(c);

        if (isEmpty()) {
            return false;
        }

        int oldSize = size;

        for (List<E> list : arrayList) {
            if (list != null) {
                int listSize = list.size();

                if (list.removeAll(c)) {
                    size -= listSize - list.size();
                }
            }
        }

        ++modCount;
        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        validateCollectionForNullReference(c);

        if (isEmpty()) {
            return false;
        }

        int oldSize = size;

        for (List<E> list : arrayList) {
            if (list != null) {
                int listSize = list.size();

                if (list.retainAll(c)) {
                    size -= listSize - list.size();
                }
            }
        }

        ++modCount;
        return oldSize != size;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (List<E> list : arrayList) {
            if (list != null) {
                list.clear();
            }
        }

        ++modCount;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        final String separator = ", ";

        for (E e : this) {
            stringBuilder.append(e).append(separator);
        }

        int stringBuilderLength = stringBuilder.length();
        stringBuilder.delete(stringBuilderLength - separator.length(), stringBuilderLength);
        return stringBuilder.append(']').toString();
    }
}