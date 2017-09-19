//Added a second comment
import java.util.Scanner;
public class LinkedList {

	Node head=null;
	
	class Node{
		int data;
		Node next;
		Node(int data){
			this.data=data;
			this.next=null;
		}
	}
	
	public void push(int data){
		Node n = new Node(data);
		n.next=head;
		head=n;
	}
	
	
	public void print(Node head){
		
		while(head!=null){
			System.out.print(head.data+"->");
			head=head.next;
		}
		System.out.print("NULL");
	}
	
	public int count(Node n){
		n=head;
		int count=0;
		while(n!=null){
			count++;
			n=n.next;
		}
		return count;
	}
	
	public void removedup(Node head){
		if(head==null)
			return;
		else{
			Node n=head;
			while(n.next!=null){
				if(n.next.data==n.data)
					n.next=n.next.next;
				else
					n=n.next;
			}
		}
	}
	
	public void swap(int x,int y){
		if(x==y)
			return;
		
		Node prevx=null,currx=head,prevy=null,curry=head;
		
		while(currx!=null&&currx.data!=x){
			prevx=currx;
			currx=currx.next;
		}
		
		while(curry!=null&&curry.data!=y){
			prevy=curry;
			curry=curry.next;
		}
		if(currx==head){
			prevy.next=currx;
			Node n=currx.next;
			currx.next=curry.next;
			curry.next=n;
			head=curry;
		}
		else if(curry==head){
			prevx.next=curry;
			Node n=curry.next;
			curry.next=currx.next;
			currx.next=n;
			head=currx;
		}
		else{
			prevx.next=curry;
			prevy.next=currx;
			Node n=currx.next;
			currx.next=curry.next;
			curry.next=n;
		}
	}
	
	public void reverse(Node curr){
		Node prev=null;
		Node next;
		while(curr!=null){
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}
		head=prev;
	}
	
	public void rotateanti(Node head,int k){
		if(head==null||k==0)
			return;
		else{
			Node n=head;
			int count=1;
			while(count!=k){
				n=n.next;
				count++;
			}
			Node last=head;
		    while(last.next!=null){
		    	last=last.next;
		    }
		    last.next=head;
		    this.head=n.next;
		    n.next=null;
		    
		}
	}
	
	public void swapPairWise(Node head){
		if(head==null)
			return;
		Node n=head;
		Node k=n.next;
		if(k==null)
			return;
		else{
			n.next=k.next;
			k.next=n;
			this.head=k;
			Node prev=n;
			n=n.next;
			
			while(n!=null&&n.next!=null){
				k=n.next;
				prev.next=n.next;
				n.next=k.next;
				k.next=n;
				prev=n;
				n=n.next;
			}
		}
		System.out.println(this.head.data);
		
	}
	
	public void LastToFront(Node head){
		if(head==null)
			return;
		Node prev=head;
		while(head.next!=null){
			prev=head;
			head=head.next;
		}
		head.next=this.head;
		prev.next=null;
		this.head=head;
	}
	
	public Node intersection(Node head1,Node head2){
		if(head1==null||head2==null)
			return null;
		else{
			LinkedList inter=new LinkedList();
			Node n=inter.head;
			while(head1!=null&&head2!=null){
				if(head1.data==head2.data){
					if(inter.head==null){
						n=new Node(head1.data);
						inter.head=n;
					}
					else{
						Node k=new Node(head1.data);
						n.next=k;
						n=n.next;
					}
					head1=head1.next;
					head2=head2.next;
				}
				else if(head1.data<head2.data)
					head1=head1.next;
				else
					head2=head2.next;
			}
			return inter.head;
		}
	}
	
	public Node merge(Node head1,Node head2){
	      LinkedList merge=new LinkedList();
	      Node m=head1;
	      Node n=head2;
	      Node p;
	      if(m.data<n.data){
	    	  p=new Node(m.data);
	    	  merge.head=p;
	    	  m=m.next;
	      }
	      else{
	    	  p=new Node(n.data);
	    	  merge.head=p;
	    	  n=n.next;
	      }
	      while(m!=null&&n!=null){
	    	  if(m.data<n.data){
	    		  Node k=new Node(m.data);
	    		  p.next=k;
	    		  p=p.next;
	    		  m=m.next;
	    	  }
	    	  else{
	    		  Node k=new Node(n.data);
	    		  p.next=k;
	    		  p=p.next;
	    		  n=n.next;
	    	  }
	      }
	      
	      while(n!=null){
	    	  Node k=new Node(n.data);
    		  p.next=k;
    		  p=p.next;
    		  n=n.next;
	      }
	      while(m!=null){
	    	  Node k=new Node(m.data);
    		  p.next=k;
    		  p=p.next;
    		  m=m.next;
	      }
	     return merge.head;
	}
	
	public void deletenext(Node head){
		if(head.data<head.next.data)
			head=head.next;
		Node prev=head;
		Node curr=head.next;
		while(curr.next!=null){
			if(curr.next!=null&&curr.data<curr.next.data)
				prev.next=curr.next;
			else{
				prev=curr;
				curr=curr.next;
			}
		}
	}
	
	public void delNafterM(Node head,int m,int n){
		if(head==null)
			return;
		int count1=1,count2=1;
		while(head.next!=null){
			if(count1!=m){
				head=head.next;
				count1++;
				count2=0;
			}
			else{
				head.next=head.next.next;
				if(count2!=n)
					count2++;
			}
			if(count2==n){
				count1=0;
			}
		}
	}
	
	void merge(LinkedList list2){
		if(this.head==null)
			return;
		Node n=this.head;
		Node p=list2.head;
		Node next;
		while(n!=null&&p!=null){
			next=n.next;
			list2.head=p.next;
			n.next=p;
			p.next=next;
			n=next;
			p=list2.head;
		}
	}
	public Node addopp(Node head1,Node head2){
		Node head=null;
		int sum=0,rem=0;
		Node p=head;
		while(head1!=null||head2!=null){
			if(head1==null){
			  sum=head2.data;
			  head2=head2.next;
			}
			else if(head2==null){
				sum=head1.data;
				head1=head1.next;
			}
				
			else{
				sum=head1.data+head2.data;
				head2=head2.next;
				head1=head1.next;
			}
			sum=sum+rem;
			Node n=new Node(sum%10);
			rem=sum/10;
			if(head==null){
				head=n;
				p=head;
			}
			else{
				p.next=n;
				p=p.next;
			}
		}
		if(rem!=0){
			Node n=new Node(rem);
			p.next=n;
			p=p.next;
		}
		return head;
	}
	
	Node swap_pair(Node head){
		Node n=head;
		Node prev=null;
		while(n!=null&&n.next!=null){
			Node temp=n.next;
			n.next=temp.next;
			temp.next=n;
			if(n==head)
				head=temp;
			else
				prev.next=temp;
			prev=n;
			n=n.next;
		}
		return head;
	}
	
	public void alternate(Node head){
		if(head==null||head.next==null)
			return;
		Node ni=head;
		Node nr=head.next;
		while(nr!=null){
			Node p=nr.next;
			Node l=ni.next;
			ni.next=p;
			nr.next=p.next;
			nr=nr.next;
			p.next=l;
			ni=ni.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedList list1=new LinkedList();
		Scanner input=new Scanner(System.in);
		int m=input.nextInt();
		while(m-->0){
			int x=input.nextInt();
			list1.push(x);
		}
        list1.print(list1.head);
        list1.head=list1.swap_pair(list1.head);
        System.out.println();
        list1.print(list1.head);
        //int a=input.nextInt();
        //int b=input.nextInt();
        //list.swap(a,b);
        //list.print();
        //int k=input.nextInt();
        //int n=input.nextInt();
//        list1.alternate(list1.head);
//        System.out.println();
//        list1.print(list1.head);
//        
        /*LinkedList list2=new LinkedList();
		int n=input.nextInt();
		while(n-->0){
			int x=input.nextInt();
			list2.push(x);
		}
        list2.print(list2.head);*/
        /*LinkedList sum=new LinkedList();
       sum.head=sum.addopp(list1.head,list2.head);
       System.out.println();
        sum.print(sum.head);*/
        /*LinkedList inter=new LinkedList();
        inter.head=inter.intersection(list1.head,list2.head);
        System.out.println();
        inter.print(inter.head);*/
		/*list1.merge(list2);
		 System.out.println();
		list1.print(list1.head);
		 System.out.println();
		list2.print(list2.head);*/
	}
}
