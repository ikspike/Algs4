package chap1_searching;

public class SequentialSearchST<Key, Value>
{
    private Node first;

    private class Node
    {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key)
    {
        for (Node x = first; x != null; x = x.next)
            if (x.key == key)
                return x.val;
        return null;
    }

    public void put(Key key, Value val)
    {
        for (Node x = first; x != null; x = x.next)
            if (x.key == key)
            {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);  // Insert a new node at the beginning.
    }
}
