import java.util.HashMap;
import java.util.Map;

class Node{
	int data;
	Node next;
	Node prev;
	public Node() {}
	public Node(int data) {
		this.data=data;
		this.prev=this.next=null;
	}
}
public class CustomLRUCache {
	
	private final static int  MAX_CAPACITY = 3;
	private int size = 0;
	
	Node head;
	Node tail;
	
	Map<Integer,Node> hashtable=new HashMap<Integer,Node>();
	
	public CustomLRUCache() {
		
		this.head=new Node();
		this.tail=new Node();
		
		this.head.prev=null;
		this.head.next=tail;
		this.tail.prev=head;
		this.tail.next=null;
		
		size=0;
		
		hashtable.clear();
		
	}
	
	public void put(int key) {
		Node node=hashtable.get(key);
		if(node == null) {
			addNode(key);
		}else {
			updateNodePos(key,node);
		}
	}

	private void updateNodePos(int key, Node node) {
		Node temp=node;
		Node prev=node.prev;
		if(head == node)
			return;
		if(node.next == null) {
			prev.next=null;
			node.prev=null;
		}else {
			prev.next=node.next;
			node.next.prev=prev;
		}
		Node nxt=head.next;
		node.prev=head;
		head=prev;
		nxt.prev=node;
		node.next=nxt;
		
	}

	public int get(int key) {
		Node node=hashtable.get(key);
		if(node == null) {
			return -1;
		}else {
			updateNodePos(key,node);
			return node.data;
		}
	}
	private void addNode(int key) {
		if(MAX_CAPACITY == size) {
			removeNode();
		}
		add(key);
		size++;
	}

	private void add(int key) {
		Node node=new Node(key);
		Node temp=head.next;
		head.next=node;
		node.prev=head;
		node.next=temp;
		temp.prev=node;
		hashtable.put(key, node);
	}

	private void removeNode() {
		Node temp=tail;
		Node prev=tail.prev;
		prev.next=null;
		tail=prev;
		size--;
		hashtable.remove(temp);
	}
	
	
	
}
