package assignment;

/**
 * 
 * Implements a node for a BinaryTree
 * 
 * @author Adam Divelbiss
 *
 * @param <T>
 */
class BinaryNode<T> {

	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	
	/**
	 * Constructor taking a data object and left and right child nodes.
	 * @param data
	 * @param left
	 * @param right
	 */
	public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Constructor specifying the data object only
	 * @param data
	 */
	public BinaryNode(T data) {
		this(data,null,null);
	}
	
	/**
	 * No-arg constructor
	 */
	public BinaryNode() {
		this(null);
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public BinaryNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BinaryNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}
	
	/**
	 * Returns true if the node has a left child node
	 * @return
	 */
	public boolean hasLeft() {
		return this.left!=null;
	}
	
	/**
	 * returns true if the node has a right child node
	 * @return
	 */
	public boolean hasRight() {
		return this.right!=null;
	}
	
	/**
	 * Returns true if this node is a leaf node
	 * @return
	 */
	public boolean isLeaf() {
		return (this.left==null) && (this.right==null);
	}
	
	/**
	 * Returns the total number of nodes in the sub-tree with this node as the root
	 * @return
	 */
	public int getNumberOfNodes() {
		int countL = (this.left!=null) ? this.left.getNumberOfNodes() : 0;
		int countR = (this.right!=null) ? this.right.getNumberOfNodes() : 0;
		return countL + countR + 1;
	}
	
	/**
	 * Private recursive method to determine the height of the tree 
	 * with the given node as root.
	 * @param node
	 * @return
	 */
	private int getHeight(BinaryNode<T> node) {
		int height = 0;
		if (node!=null) {
			height = Math.max(getHeight(node.left), getHeight(node.right));
			height++;
		}
		return height;
	}
	
	/**
	 * Public method to return the height of the subtree with this node as the root
	 * @return
	 */
	public int getHeight() {
		return getHeight(this);
	}
	
	/**
	 * Makes a complete copy of the subtree with this node as the root
	 * @return
	 */
	public BinaryNode<T> copy() {
		BinaryNode<T> root = new BinaryNode<>(this.data);
		if (this.left!=null) {
			root.setLeft(this.left.copy());
		}
		if (this.right!=null) {
			root.setRight(this.right.copy());
		}
		return root;
	}
}
