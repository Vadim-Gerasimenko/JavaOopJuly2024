package ru.academits.gerasimenko.list;

public class SinglyLinkedList<E> {
    private ListNode<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public E getFirst() {
        return head.getData();
    }

    public E get(int index) {
        return findNode(index).getData();
    }

    public E set(int index, E data) {
        ListNode<E> changedNode = findNode(index);
        E changedNodeData = changedNode.getData();

        changedNode.setData(data);

        return changedNodeData;
    }

    private ListNode<E> findNode(int index) {
        validateIndex(index);

        int i = 0;
        ListNode<E> pointer = head;

        while (i != index) {
            pointer = pointer.getNext();
            ++i;
        }

        return pointer;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of range. "
                    + "Valid index: from 0 to " + (count - 1) + ". "
                    + "Current index: " + index);
        }
    }

    public void addFirst(E data) {
        head = new ListNode<>(data, head);
        ++count;
    }

    public void add(int index, E data) {
        ListNode<E> previousNode = findNode(index - 1);
        ListNode<E> nextNode = previousNode.getNext();

        previousNode.setNext(new ListNode<>(data, nextNode));
        ++count;
    }
}