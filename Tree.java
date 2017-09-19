
public class Tree {

	Node root=null;
	static int preor=0;
	Node head=null;
	Node prev=null;
	Node result;
	int maxlevel=0;
	
	class Level{
		int maxlevel=0;
	}
  
	
	void inorder(Node root){
		if(root==null)
			return;
		inorder(root.left);
		System.out.print(root.key+" ");
		inorder(root.right);
	}
	
	void preorder(Node root){
		if(root==null)
			return;
		System.out.print(root.key+" ");
		preorder(root.left);
		preorder(root.right);
	}
	
	void postorder(Node root){
		if(root==null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.key+" ");
	}
	
    void boundaryleft(Node root){
    	if(root!=null){
    		if(root.left!=null){
    			System.out.print(root.key+" ");
    			boundaryleft(root.left);
    		}
    		else if(root.right!=null){
    			System.out.print(root.key+" ");
    			boundaryleft(root.right);
    		}
    	}
    }
    
    void boundaryright(Node root){
    	if(root!=null){
    		if(root.right!=null){
    			boundaryright(root.right);
    			System.out.print(root.key+" ");
    			boundaryleft(root.left);
    		}
    		else if(root.left!=null){
    			boundaryright(root.right);
    			System.out.print(root.key+" ");
    		}
    	}
    }
    
    void leaves(Node root){
    	if(root==null)
    		return;
    	leaves(root.left);
    	if(root.left==null&&root.right==null)
    		System.out.print(root.key+" ");
    		leaves(root.right);
    }
	
    void boundary(Node root){
    	if(root==null)
    		return;
    	System.out.print(root.key+" ");
    	boundaryleft(root.left);
    	leaves(root.left);
    	leaves(root.right);
    	boundaryright(root.right);
    }
    
    void perfecttrav(Node root1,Node root2){
    	if(root1.left==null||root2.right==null)
    		return;
    	perfecttrav(root1.left,root2.right);
    	perfecttrav(root1.right,root2.left);
    	System.out.print(root1.left.key+" "+root2.right.key+" ");
    	System.out.print(root1.right.key+" "+root2.left.key+" ");
    	
    }
    
    int search(int[] in,int data){
    	int i=0;
    	for(i=0;i<in.length;i++)
    		if(in[i]==data)
    			return i;
    	return i;
    }
    
    Node inpre(int[] in,int[] pre,int start,int end){
    	if(start>end)
    		return null;
    	int x=pre[preor++];
    	Node n=new Node(x);
    	if(start==end)
    		return n;
    	int index=search(in,x);
    	n.left=inpre(in,pre,start,index-1);
    	n.right=inpre(in,pre,index+1,end);
    	return n;
    }
    
    Node inlevel(int[] in,int[] level,int start,int end){
    	if(start>end)
    		return null;
    	if(start==end)
    		return new Node(in[start]);
    	int index=-1;
    	boolean b=false;
    	Node startNode=null;
    	for(int i=0;i<level.length;i++){
    		int data=level[i];
    		for(int j=start;j<=end;j++){
    			if(data==in[j]){
    				b=true;
    				index=j;
    				startNode=new Node(data);
    				break;
    			}
    		}
    		if(b==true)
				break;
    	}
    	
    	startNode.left=inlevel(in,level,start,index-1);
    	startNode.right=inlevel(in,level,index+1,end);
    	return startNode;
    }
    
	Node prebuild(int[] pre,char[] ch){
		if(ch[preor]=='L')
			return new Node(pre[preor++]);
		Node n=new Node(pre[preor++]);
		n.left=prebuild(pre,ch);
		n.right=prebuild(pre,ch);
		return n;
	}
	
	Node inbuild(int[] in,int start,int end){
		if(start>end)
			return null;
		if(start==end)
			return new Node(in[start]);
		int index=-1;
		int max=Integer.MIN_VALUE;
		for(int i=end;i>=start;i--){
			if(in[i]>max){
				max=in[i];
				index=i;
			}
		}
		Node n=new Node(max);
		n.left=inbuild(in,start,index-1);
		n.right=inbuild(in,index+1,end);
		return n;
	}
	
	Node parent(int[] par,int index){
		if(par[index]!=-1){
			int in=-2;
			for(int i=0;i<par.length;i++){
				if(par[i]==index){
					in=i;
					break;
				}
			}
			if(in==-2)
				return null;
			index=in;
		}
			Node n=new Node(index);
			par[index]=-2;
			n.left=parent(par,index);
			n.right=parent(par,index);
		    return n;		
	}
	
	Node postin(int[] in,int[] post,int start,int end){
		if(start>end)
			return null;
		if(start==end)
			return new Node(post[preor--]);
		int x=post[preor--];
		Node n=new Node(x);
		int index=-1;
		for(int i=0;i<in.length;i++)
			if(in[i]==x){
				index=i;
				break;
			}
		if(index==-1)
			return null;
		System.out.println(x+" "+index);
		n.right=postin(in,post,index+1,end);
		n.left=postin(in,post,start,index-1);
		return n;
	}
	
	void DLL(Node root){
		if(root==null)
			return;
		DLL(root.left);
		if(prev==null)
			head=root;
		else{
			prev.right=root;
			root.left=prev;
		}
		prev=root;
		DLL(root.right);
	}
	
	void printList(Node head){
		while(head!=null){
			System.out.print(head.key+" ");
			head=head.right;
		}
	}
	
	void childsum(Node root){
		if(root==null)
			return;
		int sum1=0,sum2=0;
		childsum(root.left);
		childsum(root.right);
		if(root.left!=null)
			sum1+=root.left.key;
		if(root.right!=null)
			sum1+=root.right.key;
		sum2+=root.key;
		int diff=(sum1-sum2);
		if(diff>=0)
			root.key+=diff;
		else
			increment(root,-diff);
	}
	
	void increment(Node root,int diff){
		if(root.left!=null){
			root.left.key+=diff;
			increment(root.left,diff);
		}
		else if(root.right!=null){
			root.right.key+=diff;
			increment(root.left,diff);
		}
	}
	
	void convert(Node root){
	     if(root==null)
	    	 return;
	     convert(root.left);
	     convert(root.right);
	     if(root.left!=null){
	    	 root.left.right=root.right;
	    	 root.right=null;
	     }
	}
	
	Node mirror(Node root){
		if(root.left==null&&root.right==null)
			return root;
		Node x=null;
		Node y=null;
		if(root.left!=null)
			x=mirror(root.left);
		if(root.right!=null)
			y=mirror(root.right);
		root.right=x;
		root.left=y;
		
		return root;
	}
	
	void downrighttrav(Node root){
		if(root==null)
			return;
		System.out.print(root.key+" ");
		downrighttrav(root.right);
		downrighttrav(root.left);
	}
	
	int sumTree(Node root){
		if(root==null)
			return 0;
		int x=root.key;
		int sum=0;
		sum+=sumTree(root.left);
		sum+=sumTree(root.right);
		root.key=sum;
		return sum+x;
	}
	
	int sumLeft(Node root){
		if(root==null)
			return 0;
		root.key+=sumLeft(root.left);
		sumLeft(root.right);
		return root.key;
	}
	
	boolean heightbal(Node root){
		if(root==null)
			return true;
		int lh=this.height(root.left);
		int rh=this.height(root.right);
		return Math.abs(lh-rh)<=1&&heightbal(root.left)&&heightbal(root.right);
	}
	int height(Node root){
		if(root==null)
			return 0;
		int lheight=0;
		int rheight=0;
		lheight=height(root.left)+1;
		rheight=height(root.right)+1;
		if(lheight>rheight)
			return lheight;
		else
			return rheight;
		
	}
	
	static boolean compare(Node root1,Node root2){
		if(root1==null&&root2==null)
			return true;
		if(root1==null||root2==null)
			return false;
		if(compare(root1.left,root2.left)&&compare(root1.right,root2.right)&&root1.key==root2.key)
				return true;
		else
			return false;
	}
	
	int count(Node root){
		if(root==null)
			return 0;
		int ct1=0,ct2=0;
		ct1=count(root.left);
		ct2=count(root.right);
		return ct1+1+ct2;
		}
	
	boolean sumpath(Node root,int sum){
		if(root==null)
			return sum==0;
		else{
			boolean ans=false;
			sum-=root.key;
			if(root.left==null&&root.right==null&&sum==0)
				return true;
			if(root.left!=null)
				ans=ans||sumpath(root.left,sum);
			if(root.right!=null)
				ans=ans||sumpath(root.right,sum);
			return ans;
		}
	}


	int diameter(Node root){
		if(root==null)
			return 0;
		int lheight=height(root.left);
		int rheight=height(root.right);
		int ldia=diameter(root.left);
		int rdia=diameter(root.right);
		return Math.max(lheight+rheight+1,Math.max(ldia,rdia));
	}
	
	boolean checkchildsum(Node root){
		if(root==null)
			return true;
		if(root.left==null&&root.right==null)
			return true;
		boolean b=checkchildsum(root.left)&&checkchildsum(root.right);
		int sum=0;
		if(root.left!=null)
			sum+=root.left.key;
		if(root.right!=null)
			sum+=root.right.key;
		return b&&(root.key==sum);
	}
	
	int countleaf(Node root){
		if(root==null)
			return 0;
		if(root.left==null&&root.right==null)
			return 1;
		int count=0;
		return count+countleaf(root.left)+countleaf(root.right);
	}
	
	Node LCA(Node root,int n1,int n2){
		if(root==null)
			return null;
		if(n1<root.key&&n2<root.key)
			return LCA(root.left,n1,n2);
		else if(n1>root.key&&n2>root.key)
			return LCA(root.right,n1,n2);
		return root;
	}
	
	
	void RTL(Node root,String s){
		if(root==null)
			return;
		s+=root.key+" ";
		RTL(root.left,s);
		RTL(root.right,s);
        if(root.left==null&&root.right==null)
        	System.out.println(s);
	}
	
	int sum(Node root){
		if(root==null)
			return 0;
		return root.key+sum(root.left)+sum(root.right);
	}
	
	boolean sumtree(Node root){
		if(root==null)
			return true;
		if(root.left==null&&root.right==null)
			return true;
		int sumnode=sum(root.left)+sum(root.right);
		System.out.print(sumnode+" ");
		return sumtree(root.left)&&sumtree(root.right)&&(root.key==sumnode);
	}
	
	boolean ancestor(Node root,int key){
		if(root==null)
			return false;
		if(root.key==key)
			return true;
		if(ancestor(root.left,key)||ancestor(root.right,key)){
			System.out.print(root.key+" ");
			return true;
		}
		return false;
	}
	
	void doubletree(Node root){
		if(root==null)
			return;
		doubletree(root.left);
		doubletree(root.right);
		Node temp=new Node(root.key);
		temp.left=root.left;
		root.left=temp;
	}
	
	Node searchBST(Node root,int key){
		if(root==null||root.key==key)
			return root;
		if(key<root.key)
			return searchBST(root.left,key);
		return searchBST(root.right,key);
	}
	
	int sumf(Node root){
		return sumfind(root,0);
	}
	
	int sumfind(Node root,int val){
		if(root==null)
			return 0;
		val=val*10+root.key;
		if(root.left==null&&root.right==null)
			return val;
		return sumfind(root.left,val)+sumfind(root.right,val);
	}
	
	void deepleft(Node root,int lvl,Level level,boolean isleft){
		if(root==null)
			return;
		if(isleft==true&&root.left==null&&root.right==null&&lvl>level.maxlevel){
			result=root;
			level.maxlevel=lvl;
		}
		
		deepleft(root.left,lvl+1,level,true);
		deepleft(root.right,lvl+1,level,false);
	}
	
	void deepl(Node root){
		Level level=new Level();
		deepleft(root,1,level,false);
		if(result!=null)
			System.out.println(result.key);
	}
	
	void leftview(Node root,int level){
		if(root==null)
			return;
		if(level>maxlevel){
			System.out.print(root.key+" ");
			maxlevel=level;
		}
		
		leftview(root.left,level+1);
		leftview(root.right,level+1);
	}
	
	class leaflevel{
		int leafl=0;
	}
	boolean samelevel(Node root){
		leaflevel level=new leaflevel();
		return leaflev(root,level,0);
	}
	
	boolean leaflev(Node root,leaflevel level,int lev){
		if(root==null)
			return true;
		if(root.left==null&&root.right==null){
			if(level.leafl==0){
				level.leafl=lev;
				return true;
			}
			return lev==level.leafl;
		}
		return leaflev(root.left,level,lev+1)&&leaflev(root.right,level,lev+1);
	}
	
	class oddlev{
		int lev=1;
	}
	
	int oddlevel(Node root){
		oddlev lev=new oddlev();
		return deeplevel(root,lev,1);
	}
	
	int deeplevel(Node root,oddlev lev,int level){
		if(root==null)
			return 0;
		if(root.left==null&&root.right==null){
			if(level%2!=0&&level>lev.lev){
				lev.lev=level;
			}
			return lev.lev;
		}
		int l=deeplevel(root.left,lev,level+1);
		int r=deeplevel(root.right,lev,level+1);
		if(l>r)
			return l;
		else
			return r;
	}
	
	int sumevenodd(Node root,int level,int sum){
		if(root==null)
			return sum;
		if(level%2==0)
			sum-=root.key;
		else
			sum+=root.key;
		sum=sumevenodd(root.left,level+1,sum);
		sum=sumevenodd(root.right,level+1,sum);
		return sum;
	}
	
	boolean isomorph(Node root1,Node root2){
		if(root1==null&&root2==null)
			return true;
		if(root1==null||root2==null)
			return false;
		if(root1.key!=root2.key)
			return false;
		return isomorph(root1.left,root2.left)&&isomorph(root1.right,root2.right)||isomorph(root1.left,root2.right)&&isomorph(root1.right,root2.left);
		
	}
	
	public static void main(String[] args){
		Tree tree = new Tree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
 
        tree.root.left.left  = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left  = new Node(6);
        tree.root.right.right = new Node(7);
  
        tree.root.left.left.left  = new Node(8);
        tree.root.left.left.right  = new Node(9);
        tree.root.left.right.left  = new Node(10);
        tree.root.left.right.right  = new Node(11);
        tree.root.right.left.left  = new Node(12);
        tree.root.right.left.right  = new Node(13);
        tree.root.right.right.left  = new Node(14);
        tree.root.right.right.right  = new Node(15);
  
        tree.root.left.left.left.left  = new Node(16);
        tree.root.left.left.left.right  = new Node(17);
        tree.root.left.left.right.left  = new Node(18);
        tree.root.left.left.right.right  = new Node(19);
        tree.root.left.right.left.left  = new Node(20);
        tree.root.left.right.left.right  = new Node(21);
        tree.root.left.right.right.left  = new Node(22);
        tree.root.left.right.right.right  = new Node(23);
        tree.root.right.left.left.left  = new Node(24);
        tree.root.right.left.left.right  = new Node(25);
        tree.root.right.left.right.left  = new Node(26);
        tree.root.right.left.right.right  = new Node(27);
        tree.root.right.right.left.left  = new Node(28);
        tree.root.right.right.left.right  = new Node(29);
        tree.root.right.right.right.left  = new Node(30);
        tree.root.right.right.right.right  = new Node(31);
		
		tree.inorder(tree.root);
		System.out.println();
		tree.preorder(tree.root);
		System.out.println();
		tree.perfecttrav(tree.root.left,tree.root.right);
		System.out.print(tree.root.left.key+" "+tree.root.right.key+" "+tree.root.key);
		
		int[] in={4,2,5,1,6,3};
		int[] pre={1,2,4,5,3,6};
		Tree t=new Tree();
		t.root=t.inpre(in,pre,0,in.length-1);
		System.out.println();
		t.inorder(t.root);
		
	    int[] inn={4,8,10,12,14,20,22};
		int[] level={20,8,22,4,12,10,14};
		Tree tr=new Tree();
		tr.root=tr.inlevel(inn, level,0,inn.length-1);
		System.out.println();
	    tr.inorder(tr.root);
	    
	    int pree[]={1,2,3,4,5,6,7};
	    char[] ch={'N','N','L','L','N','L','L'};
	    Tree trr=new Tree();
	    trr.root=trr.prebuild(pree, ch);
	    System.out.println();
	    trr.preorder(trr.root);
	    
	    int innn[]={-1,0,0,1,1,3,5};
	    Tree trre=new Tree();
        int i=0;
	    for(;i<innn.length;i++){
	    	if(innn[i]==-1){
	    		break;
	    	}
	    }
	    trre.root=trre.parent(innn,i);
	    System.out.println();
	    trre.inorder(trre.root);
	    
	    int[] ino={4,8,2,5,1,6,3,7};
	    int[] post={8,4,5,2,6,7,3,1};
	    preor=ino.length-1;
	    Tree tre=new Tree();
	    System.out.println();
	    tre.root=tre.postin(ino, post,0,ino.length-1);
	    System.out.println();
	    tre.inorder(tre.root);
	    
	    Tree treee = new Tree();
        treee.root = new Node(10);
        treee.root.left = new Node(12);
        treee.root.right = new Node(15);
        treee.root.left.left = new Node(25);
        treee.root.left.right = new Node(30);
        treee.root.right.left = new Node(36);
  
        treee.DLL(treee.root);
        System.out.println();  
        treee.printList(treee.head);
        
        tree.root = new Node(50);
        tree.root.left = new Node(7);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(1);
        tree.root.right.right = new Node(30);
        System.out.println();
        tree.inorder(tree.root);
        tree.childsum(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        
        tree.root=new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(5);
        tree.root.right.left.left = new Node(6);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(8);
        
        tree.convert(tree.root);
        System.out.println();
        tree.downrighttrav(tree.root);
        
        tree.root = new Node(10);
        tree.root.left = new Node(-2);
        tree.root.right = new Node(6);
        tree.root.left.right = new Node(-4);
        tree.root.right.left = new Node(7);
        tree.sumTree(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        
        tree.root  = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.right = new Node(6);
        tree.sumLeft(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root=tree.mirror(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        
        tree.root=new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(5);
        tree.root.right.left.left = new Node(6);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(8);
        System.out.println();
        System.out.println(tree.height(tree.root));
        
        Tree tree1=new Tree();
        Tree tree2=new Tree();
        tree1.root = new Node(1);
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(3);
        tree1.root.left.left = new Node(4);
        tree1.root.left.right = new Node(5);
        
        tree2.root = new Node(1);
        tree2.root.left = new Node(2);
        tree2.root.right = new Node(3);
        tree2.root.left.left = new Node(4);
        tree2.root.right.right = new Node(5);
        
        if(compare(tree1.root,tree2.root))
        	System.out.println("Identical");
        else
           System.out.println("Not Identical");
        
        System.out.println();
        System.out.println(tree1.count(tree1.root));
        
        tree.root=new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(5);
        tree.root.right.left.left = new Node(6);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(8);
        int sum=15;
        System.out.println(tree.sumpath(tree.root,sum));
        
        tree.root=new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.right.left = new Node(4);
        tree.root.right.right = new Node(5);
        tree.root.right.left.left = new Node(6);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(8);
        System.out.println(tree.heightbal(tree.root));
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        System.out.println(tree.diameter(tree.root));
  
        tree.root = new Node(9);
        tree.root.left = new Node(8);
        tree.root.right = new Node(1);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(10);
        tree.root.right.right = new Node(1);
       
        System.out.println(tree.checkchildsum(tree.root));
        System.out.println(tree.countleaf(tree.root));
        
        tree.root = new Node(20);
        tree.root.left = new Node(8);
        tree.root.right = new Node(22);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(12);
        tree.root.left.right.left = new Node(10);
        tree.root.left.right.right = new Node(14);
        int n1=12,n2=22;
        Node n=tree.LCA(tree.root, n1, n2);
        System.out.println(n.key);

        tree.RTL(tree.root,"");
        
        tree.root = new Node(24);
        tree.root.left = new Node(9);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.right = new Node(3);
        System.out.println(tree.sumtree(tree.root));
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(7);
        tree.ancestor(tree.root,9);
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.doubletree(tree.root);
        tree.inorder(tree.root);
        
        System.out.println();
        
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);
        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(7);
        Node np=tree.searchBST(tree.root,3);
        System.out.println(np.key);
        
        tree.root = new Node(6);
        tree.root.left = new Node(3);
        tree.root.right = new Node(5);
        tree.root.right.right = new Node(4);
        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(5);
        tree.root.left.right.right = new Node(4);
        tree.root.left.right.left = new Node(7);
        System.out.println();
        System.out.println(tree.sumf(tree.root));
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(6);
        tree.root.right.left.right = new Node(7);
        tree.root.right.right.right = new Node(8);
        tree.root.right.left.right.left = new Node(9);
        tree.root.right.right.right.right = new Node(10);
        tree.deepl(tree.root);
        
        tree.root = new Node(12);
        tree.root.left = new Node(10);
        tree.root.right = new Node(30);
        tree.root.right.left = new Node(25);
        tree.root.right.right = new Node(40);
        tree.leftview(tree.root,1);
        
        tree.root = new Node(12);
        tree.root.left = new Node(5);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(9);
        tree.root.left.left.left = new Node(1);
        tree.root.left.right.left = new Node(1);
        if (tree.samelevel(tree.root))
            System.out.println("Leaves are at same level");
        else
            System.out.println("Leaves are not at same level");
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(6);
        tree.root.right.left.right = new Node(7);
        tree.root.right.right.right = new Node(8);
        tree.root.right.left.right.left = new Node(9);
        tree.root.right.right.right.right = new Node(10);
        tree.root.right.right.right.right.left = new Node(11);
        System.out.println(tree.oddlevel(tree.root));
        
        tree.root = new Node(5);
        tree.root.left = new Node(2);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(4);
        tree.root.left.right.left = new Node(3);
        tree.root.right.right = new Node(8);
        tree.root.right.right.right = new Node(9);
        tree.root.right.right.left = new Node(7);
        System.out.println(tree.sumevenodd(tree.root,1,0));
        
        tree1.root = new Node(1);
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(3);
        tree1.root.left.left = new Node(4);
        tree1.root.left.right = new Node(5);
        tree1.root.right.left = new Node(6);
        tree1.root.left.right.left = new Node(7);
        tree1.root.left.right.right = new Node(8);
        
        tree2.root = new Node(1);
        tree2.root.left = new Node(3);
        tree2.root.right = new Node(2);
        tree2.root.right.left = new Node(4);
        tree2.root.right.right = new Node(5);
        tree2.root.left.right = new Node(6);
        tree2.root.right.right.left = new Node(8);
        tree2.root.right.right.right = new Node(7);
        
        System.out.println(tree1.isomorph(tree1.root,tree2.root));
  
		}
	}
	


