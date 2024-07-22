package ru.academits.gerasimenko.list;

public class SinglyLinkedList<E> {
    private ListNode<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public E getFirst() {
        return head.getData();
    }

    public E get(int index) {
        validateIndex(index);

        return findNode(index).getData();
    }

    public E set(int index, E data) {
        validateIndex(index);

        ListNode<E> changedNode = findNode(index);
        E changedNodeData = changedNode.getData();

        changedNode.setData(data);

        return changedNodeData;
    }

    private ListNode<E> findNode(int index) {
        int i = 0;
        ListNode<E> pointer = head;

        while (i != index) {
            pointer = pointer.getNext();
            ++i;
        }

        return pointer;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty.");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range. "
                    + "Valid index: from 0 to " + (size - 1) + ". "
                    + "Current index: " + index);
        }
    }

    public void addFirst(E data) {
        head = new ListNode<>(data, head);
        ++size;
    }

    private void addNext(ListNode<E> previousNode, E data) {
        ListNode<E> nextNode = previousNode.getNext();

        previousNode.setNext(new ListNode<>(data, nextNode));
        ++size;
    }

    public void add(int index, E data) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        validateIndex(index - 1);

        ListNode<E> previousNode = findNode(index - 1);
        addNext(previousNode, data);
    }

    public E removeFirst() {
        validateIndex(0);

        E deletedNodeData = head.getData();
        head = head.getNext();
        --size;

        return deletedNodeData;
    }

    private E removeNext(ListNode<E> previousNode) {
        if (previousNode == null) {
            return removeFirst();
        }

        ListNode<E> deletedNode = previousNode.getNext();

        previousNode.setNext(deletedNode.getNext());
        --size;

        return deletedNode.getData();
    }

    public E remove(int index) {
        if (index == 0) {
            return removeFirst();
        }

        validateIndex(index);
        return removeNext(findNode(index - 1));
    }

    public boolean remove(E data) {
        for (ListNode<E> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                removeNext(prev);

                return true;
            }
        }

        return false;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        reverse();

        for (ListNode<E> p = head; p != null; p = p.getNext()) {
            listCopy.addFirst(p.getData());
        }

        reverse();

        return listCopy;
    }

    public void reverse() {
        head = reverse(head);
    }

    private ListNode<E> reverse(ListNode<E> pointer) {
        if (pointer == null) {
            return null;
        }

        ListNode<E> nextNode = pointer.getNext();

        if (nextNode == null) {
            return pointer;
        }

        ListNode<E> head = reverse(nextNode);
        nextNode.setNext(pointer);
        pointer.setNext(null);

        return head;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ListNode<E> p = head; p != null; p = p.getNext()) {
            stringBuilder.append(p).append(", ");
        }

        final int separatorSize = 2;
        stringBuilder.delete(stringBuilder.length() - separatorSize, stringBuilder.length());

        return stringBuilder.append(']').toString();
    }
}