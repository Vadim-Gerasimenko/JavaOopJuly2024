package ru.academits.gerasimenko.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public ArrayList(int capacity) {
        validateCapacity(capacity);
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList(Collection<E> itemsCollection) {
        size = itemsCollection.size();
        //noinspection unchecked
        items = (E[]) new Object[size];
        int i = 0;

        for (E item : itemsCollection) {
            items[i] = item;
            ++i;
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, (items.length + 1) * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range. "
                    + "Valid index: from 0 to " + (size - 1) + ". "
                    + "Current index: " + index);
        }
    }

    private void validateInsertionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range. "
                    + "Valid insertion index: from 0 to " + size + ". "
                    + "Current insertion index: " + index);
        }
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Arraylist capacity must be not a negative number. "
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
        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            validateNextItemExist();
            validateListModified();

            ++currentIndex;
            return items[currentIndex];
        }

        private void validateNextItemExist() {
            if (!hasNext()) {
                throw new NoSuchElementException("The arraylist is over.");
            }
        }

        private void validateListModified() {
            if (initialModCount != ArrayList.this.modCount) {
                throw new ConcurrentModificationException("The arraylist has been modified.");
            }
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int itemIndex = indexOf(o);

        if (itemIndex == -1) {
            return false;
        }

        remove(itemIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object collectionItem : c) {
            if (!contains(collectionItem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        validateInsertionIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        int collectionSize = c.size();
        final int newSize = size + collectionSize;

        ensureCapacity(newSize);
        System.arraycopy(items, index, items, index + collectionSize, size - index);

        int i = index;

        for (E collectionItem : c) {
            items[i] = collectionItem;
            ++i;
        }

        size = newSize;
        ++modCount;
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }


    @Override
    public boolean removeAll(@SuppressWarnings("NullableProblems") Collection<?> c) {
        int initialModCount = modCount;

        for (int i = size - 1; i >= 0; --i) {
            if (c.contains(items[i])) {
                remove(i);
            }
        }

        return initialModCount != modCount;
    }

    @Override
    public boolean retainAll(@SuppressWarnings("NullableProblems") Collection<?> c) {
        int initialModCount = modCount;

        for (int i = size - 1; i >= 0; --i) {
            if (!c.contains(items[i])) {
                remove(i);
            }
        }

        return initialModCount != modCount;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; ++i) {
            items[i] = null;
        }

        size = 0;
        ++modCount;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return items[index];
    }

    @Override
    public E set(int index, E item) {
        validateIndex(index);

        E CurrentItemByIndex = items[index];
        items[index] = item;

        return CurrentItemByIndex;
    }

    @Override
    public void add(int index, E item) {
        validateInsertionIndex(index);

        final int newSize = size + 1;

        if (newSize > items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        ++size;
        ++modCount;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        final int newSize = size - 1;
        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[newSize] = null;
        --size;
        ++modCount;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i] == null) {
                if (o == null) {
                    return i;
                }
            } else {
                if (items[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i] == null) {
                if (o == null) {
                    return i;
                }
            } else {
                if (items[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        final String separator = ", ";

        for (E item : this) {
            stringBuilder.append(item).append(separator);
        }

        stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
        return stringBuilder.append(']').toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        ArrayList<E> arrayList = (ArrayList<E>) o;

        if (arrayList.size != size()) {
            return false;
        }

        for (int i = 0; i < size; ++i) {
            if (arrayList.items[i] == null) {
                if (items[i] != null) {
                    return false;
                }
            } else {
                if (!arrayList.items[i].equals(items[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    public int hashCode() {
        final int prime = 17;
        int hash = 1;

        for (E item : this) {
            hash = hash * prime + (item != null ? item.hashCode() : 0);
        }

        return hash;
    }
}