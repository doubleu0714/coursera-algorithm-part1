import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private class Node {
        Item item;
        int idx;
        Node next;
        Node prev;

        public Node(Item item, int idx) {
            this.item = item;
            this.idx = idx;
            this.next = null;
            this.prev = null;
        }
    }

    private Node first;
    private int size;

    public RandomizedQueue() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node addedNode = new Node(item, size++);
        if(first == null) {
            first = addedNode;
        } else {
            Node oldFirst = first;
            first = addedNode;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
    }

    public Item dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        int dequeueIdx = StdRandom.uniform(size);
        Node targetNode = findNode(dequeueIdx);
        Node prevNode = targetNode.prev;
        if(prevNode != null) {
            prevNode.next = targetNode.next;
        }
        size--;
        Node node = first;
        for(int i = size - 1 ; i >= 0 ; i--) {
            node.idx = i;
            node = node.next;
        }
        return targetNode.item;
    }

    public Item sample() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        int sampleIdx = StdRandom.uniform(size);
        Node targetNode = findNode(sampleIdx);
        return targetNode.item;
    } 

    private Node findNode(int idx) {
        Node node = first;
        if(node.idx < idx) {
            throw new IllegalArgumentException();
        }

        while(true) {
            if(node.idx == idx) {
                return node;
            }

            node = node.next;
        }
    }

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
            Node current = first;

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