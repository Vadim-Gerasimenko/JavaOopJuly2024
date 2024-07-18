package ru.academits.gerasimenko.list;

public class ListNode<E> {
    private E data;
    private ListNode<E> next;

    public ListNode(E data) {
        this.data = data;
    }

    public ListNode(E data, ListNode<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        return data.equals(o);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 17;

        return data.hashCode() * prime;
    }
}