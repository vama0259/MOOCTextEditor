package bstImplementation;

public class MyTree<E> {
	TreeNode<E> root;
	
	public MyTree() {
		root = new TreeNode<E>(null);
	}
	
	public MyTree(E value) {
		root = new TreeNode<E>(value);
	}
	
	public boolean addToTree(E value) {
		
		
		return false;
	}
}

class TreeNode<E>{
	public E value;
	public TreeNode<E> rightChild;
	public TreeNode<E> leftChild;
	
	public TreeNode() {
		this.value = null;
		this.rightChild = null;
		this.leftChild = null;
	}
	
	public TreeNode(E value) {
		this.value = value;
		this.rightChild = null;
		this.leftChild = null;
	}
}