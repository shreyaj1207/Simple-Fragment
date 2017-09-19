//Added a comment
public class BST {
 
	Node root=null;
	
	void inorder(Node root){
		if(root==null)
			return;
		inorder(root.left);
		System.out.print(root.key+" ");
		inorder(root.right);
	}

	Node search(Node root,int key){
		if(root==null||root.key==key)
			return root;
		if(root.key>key)
			return search(root.left,key);
		else
			return search(root.right,key);
		
	}
	
	Node insert(Node root,int key){
		if(root==null){
		    root=new Node(key);
			return root;
		}
		if(root.key>key)
		 root.left=insert(root.left,key);
		else
			root.right=insert(root.right,key);
		return root;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST tree=new BST();
		tree.root=new Node(4);
		tree.root.left=new Node(3);
		tree.root.right=new Node(7);
		tree.root.left.left=new Node(1);
		tree.root.left.right=new Node(2);
		tree.root.right.left=new Node(5);
		tree.root.right.right=new Node(6);
		
        Node n=tree.search(tree.root,2);
        if(n!=null)
        	System.out.println(n.key);
        
        tree.root=null;
        
        tree.root=tree.insert(tree.root,50);
        tree.root=tree.insert(tree.root,30);
        tree.root=tree.insert(tree.root,20);
        tree.root=tree.insert(tree.root,40);
        tree.root=tree.insert(tree.root,70);
        tree.root=tree.insert(tree.root,60);
        tree.root=tree.insert(tree.root,80);
        
        tree.inorder(tree.root);
        

	}

}
