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
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList(List<E> elementsList) {
        size = elementsList.size();
        //noinspection unchecked
        items = (E[]) Arrays.copyOf(elementsList.toArray(), size);
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            increaseCapacity();
            ensureCapacity(capacity);
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
        for (E element : this) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCount = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            validateNextElementExists();
            validateListModify();

            ++currentIndex;
            return items[currentIndex];
        }

        private void validateNextElementExists() {
            if (!hasNext()) {
                throw new NoSuchElementException("The arraylist is over.");
            }
        }

        private void validateListModify() {
            if (modCount != ArrayList.this.modCount) {
                throw new ConcurrentModificationException("The arraylist has been modified.");
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);

        items[size] = e;
        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int elementIndex = indexOf(o);

        if (elementIndex == -1) {
            return false;
        }

        remove(elementIndex);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int collectionSize = c.size();

        if (collectionSize > size) {
            return false;
        }

        boolean[] isItemsChecked = new boolean[size];

        E[] collectionElementsArray = (E[]) c.toArray();

        for (int i = 0; i < collectionSize; ++i) {
            E collectionElement = collectionElementsArray[i];
            boolean isElementPresent = false;

            for (int j = 0; j < size; ++j) {
                if (items[j].equals(collectionElement) && !isItemsChecked[j]) {
                    isItemsChecked[j] = true;
                    isElementPresent = true;
                    break;
                }
            }

            if (isElementPresent) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        validateIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        int collectionSize = c.size();
        final int newSize = size + collectionSize;

        ensureCapacity(newSize);

        System.arraycopy(items, index, items, index + collectionSize, size - index);
        System.arraycopy(c.toArray(), 0, items, index, collectionSize);

        size = newSize;
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        int collectionSize = c.size();
        final int newSize = size + collectionSize;

        ensureCapacity(collectionSize);
        System.arraycopy(c.toArray(), 0, items, size, collectionSize);

        size = newSize;
        ++modCount;

        return true;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;

        while (i < size) {
            if (c.contains(items[i])) {
                remove(i);
            } else {
                ++i;
            }
        }

        trimToSize();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;

        while (i < size) {
            if (!c.contains(items[i])) {
                remove(i);
            } else {
                ++i;
            }
        }

        trimToSize();
        return true;
    }

    @Override
    public void clear() {
        for (E element : this) {
            element = null;
        }

        size = 0;
        ++modCount;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return items[index];
    }

    public E getFirst() {
        return get(0);
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E previousValue = items[index];
        items[index] = element;
        ++modCount;

        return previousValue;
    }

    @Override
    public void add(int index, E element) {
        validateIndex(index);

        final int newSize = size + 1;
        ensureCapacity(newSize);

        System.arraycopy(items, index, items, index + 1, size - index);


        items[index] = element;
        size = newSize;
        ++modCount;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        final int newSize = size - 1;
        E deletedElement = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[newSize] = null;
        size = newSize;
        ++modCount;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        if (!isEmpty()) {
            final String separator = ", ";

            for (E element : this) {
                stringBuilder.append(element).append(separator);
            }

            stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
        }

        return stringBuilder.append(']').toString();
    }
}