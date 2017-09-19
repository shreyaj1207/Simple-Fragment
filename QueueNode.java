
public class QueueNode {

	/**
	 * @param args
	 */
	Node front=null,rear=null;
	class Node{
		Node node;
		Node next;
		Node(){
			next=null;
		}
	}
	
    boolean isEmpty(){
    	return front==null;
    }
    
    void enqueue(int data){
    	Node n=new Node();
    	n.data=data;
    	if(front==null)
    		front=rear=n;
    	else{
    		rear.next=n;
    		rear=n;
    	}
    }
    
    int dequeue(){
    	int x=front.data;
    	if(front==rear)
    		front=rear=null;
    	else
    		front=front.next;
    	return x;
    }
    
    void print(){
    	Node n=front;
    	while(n!=null){
    		System.out.print(n.data+" ");
    		n=n.next;
    	}
    }
}
