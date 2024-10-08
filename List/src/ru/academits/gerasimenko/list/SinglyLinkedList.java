package ru.academits.gerasimenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListNode<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public E getFirst() {
        validateForEmptiness();
        return head.getData();
    }

    public E get(int index) {
        validateIndex(index);

        return getNode(index).getData();
    }

    public E set(int index, E data) {
        validateIndex(index);

        ListNode<E> changedNode = getNode(index);
        E changedNodeData = changedNode.getData();

        changedNode.setData(data);

        return changedNodeData;
    }

    private ListNode<E> getNode(int index) {
        ListNode<E> node = head;

        for (int i = 0; i < index; ++i) {
            node = node.getNext();
        }

        return node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
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

    public void addFirst(E data) {
        head = new ListNode<>(data, head);
        ++size;
    }

    public void add(int index, E data) {
        validateInsertionIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListNode<E> previousNode = getNode(index - 1);
        previousNode.setNext(new ListNode<>(data, previousNode.getNext()));
        ++size;
    }

    public E removeFirst() {
        validateForEmptiness();

        E removedNodeData = head.getData();
        head = head.getNext();
        --size;

        return removedNodeData;
    }

    private E removeNextNode(ListNode<E> previousNode) {
        if (previousNode == null) {
            return removeFirst();
        }

        ListNode<E> removedNode = previousNode.getNext();

        previousNode.setNext(removedNode.getNext());
        --size;

        return removedNode.getData();
    }

    public E remove(int index) {
        validateIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        return removeNextNode(getNode(index - 1));
    }

    public boolean remove(E data) {
        for (ListNode<E> currentNode = head, previousNode = null;
             currentNode != null;
             previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(currentNode.getData(), data)) {
                removeNextNode(previousNode);
                return true;
            }
        }

        return false;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        if (isEmpty()) {
            return listCopy;
        }

        listCopy.head = new ListNode<>(head.getData());

        for (ListNode<E> listNode = head.getNext(), listCopyNode = listCopy.head;
             listNode != null;
             listNode = listNode.getNext(), listCopyNode = listCopyNode.getNext()) {
            listCopyNode.setNext(new ListNode<>(listNode.getData()));
        }

        listCopy.size = size;
        return listCopy;
    }

    public void reverse() {
        ListNode<E> previousNode = null;

        for (ListNode<E> currentNode = head, nextNode; currentNode != null; currentNode = nextNode) {
            nextNode = currentNode.getNext();

            currentNode.setNext(previousNode);
            previousNode = currentNode;
        }

        head = previousNode;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        final String separator = ", ";

        for (ListNode<E> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            stringBuilder.append(currentNode.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
        return stringBuilder.append(']').toString();
    }
}