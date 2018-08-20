import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {

    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    private void addDeque(Item item, boolean isFirst) {
        Node addedNode = new Node(item);
        size++;
        if(isEmpty()) {
            first = addedNode;
            last = addedNode;
            return;
        }

        Node old = null;
        if(isFirst) {
            old = first;
            first = addedNode;
            first.next = old;
            old.prev = first;
        } else {
            old = last;
            last = addedNode;
            old.next = last;
            last.prev = old;
        }
    }

    private Item removeDeque(boolean isFirst) {
        Item returnValue = null;
        size--;

        if(isFirst) {
            returnValue = first.item;
            first = first.next;
            if(first != null)
                first.prev = null;
        } else {
            returnValue = last.item;
            last = last.prev;
            if(last != null)
                last.next = null;
        }

        if(size < 1) {
            first = null;
            last = null;
        }
        return returnValue;

    }

    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        addDeque(item, true);
    }

    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        addDeque(item, false);
    }

    public Item removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeDeque(true);
    }

    public Item removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeDeque(false);
    }

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if(current == null) {
                    throw new NoSuchElementException();
                }
                Item returnValue = current.item;
                current = current.next;
                return returnValue;
            }
        };
	}
}