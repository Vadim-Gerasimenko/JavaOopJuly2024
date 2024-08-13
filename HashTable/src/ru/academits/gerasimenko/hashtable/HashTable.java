package ru.academits.gerasimenko.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<LinkedList<E>> hashTable;
    private final int capacity;
    private int size;
    private int modCount;

    public HashTable() {
        this(50);
    }

    public HashTable(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;

        hashTable = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; ++i) {
            hashTable.add(null);
        }
    }

    private int getIndex(Object o) {
        return Math.abs(o.hashCode() % capacity);
    }

    private static void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Array's capacity must be positive number. "
                    + "Current capacity value: " + capacity);
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
        if (o == null) {
            return false;
        }

        int index = getIndex(o);
        List<E> elements = hashTable.get(index);

        if (elements == null) {
            return false;
        }

        return elements.contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int elementsCurrentIndex = -1;
        private int arrayListCurrentIndex;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Collection is over.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("The collection was modified.");
            }

            List<E> elements;

            while ((elements = hashTable.get(arrayListCurrentIndex)) == null) {
                ++arrayListCurrentIndex;
            }

            ++elementsCurrentIndex;

            if (elementsCurrentIndex < elements.size()) {
                ++currentIndex;
                return elements.get(elementsCurrentIndex);
            }

            elementsCurrentIndex = -1;
            ++arrayListCurrentIndex;
            return next();
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        copyToArray(array);

        return array;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a != null && a.length >= size) {
            copyToArray(a);
            return a;
        }

        @SuppressWarnings("unchecked") T[] array = (T[]) toArray();
        return array;
    }

    private void copyToArray(Object[] array) {
        int arrayPosition = 0;

        for (List<E> elements : hashTable) {
            if (elements != null) {
                System.arraycopy(elements.toArray(), 0, array, arrayPosition, elements.size());
                arrayPosition += elements.size();
            }
        }
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }

        int index = getIndex(e);

        if (hashTable.get(index) == null) {
            hashTable.set(index, new LinkedList<>());
        }

        hashTable.get(index).add(e);
        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int index = getIndex(o);
        List<E> elements = hashTable.get(index);

        if (elements == null) {
            return false;
        }

        if (elements.remove(o)) {
            if (elements.isEmpty()) {
                hashTable.set(index, null);
            }

            --size;
            ++modCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(@SuppressWarnings("NullableProblems") Collection<?> c) {
        if (c == null) {
            return true;
        }

        for (Object collectionElement : c) {
            int index = getIndex(collectionElement);
            List<E> elements = hashTable.get(index);

            //noinspection SuspiciousMethodCalls
            if (elements == null || !elements.contains(collectionElement)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E collectionElement : c) {
            add(collectionElement);
        }

        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        int previousSize = size;
        int newSize = size;

        for (Object collectionElement : c) {
            while (remove(collectionElement)) {
                --newSize;
                ++modCount;
            }
        }

        size = newSize;
        return previousSize != newSize;
    }

    @Override
    public boolean retainAll(@SuppressWarnings("NullableProblems") Collection<?> c) {
        if (c == null) {
            return false;
        }

        int previousSize = size;

        for (int i = 0; i < capacity; ++i) {
            List<E> elements = hashTable.get(i);

            if (elements != null) {
                int elementsCount = elements.size();

                if (elements.retainAll(c)) {
                    size -= elementsCount - elements.size();
                    ++modCount;
                }

                if (elements.isEmpty()) {
                    hashTable.set(i, null);
                }
            }
        }

        return previousSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            hashTable.set(i, null);
            ++modCount;
        }

        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        final char leftBracket = '[';
        stringBuilder.append(leftBracket);

        final String separator = ", ";
        final int separatorSize = 2;

        for (E element : this) {
            stringBuilder.append(element).append(separator);
        }

        stringBuilder.delete(stringBuilder.length() - separatorSize, stringBuilder.length());

        final char rightBracket = ']';
        stringBuilder.append(rightBracket);

        return stringBuilder.toString();
    }
}