package assignment;

public class BinaryTree<T> implements BinaryTreeInterface<T> {

	private BinaryNode<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public BinaryTree(T data) {
		this.root = new BinaryNode<>(data);
	}
	
	public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
		this.initializeTree(data, left, right);
	}
	
	private void initializeTree(T data, BinaryTree<T> left, BinaryTree<T> right ) {
		// set the root node
		this.root = new BinaryNode<>(data);

		// METHOD 1:
		// Use the trees as-is.  
		//Possible that same node appears in many trees
		if ((left!=null) && !left.isEmpty()) {
			this.root.setLeft(left.root);
		}		
		if ((right!=null) && !right.isEmpty()) {
			this.root.setRight(right.root);
		}		
		
		// METHOD 2:
		// always make a copy.
		// Safe but could be expensive
		if ((left!=null) && !left.isEmpty()) {
			this.root.setLeft(left.root.copy());
		}		
		if ((right!=null) && !right.isEmpty()) {
			this.root.setRight(right.root.copy());
		}		
		
		// METHOD 3:
		// Make a copy when needed
		// Check that the left tree exists and is not empty
		if ((left!=null) && !left.isEmpty()) {
			this.root.setLeft(left.root);
			// prevent nodes in left tree appearing in two separate trees
			if (left != this) {
				left.clear();
			}
		}
		// Check that the right tree exits and is not empty
		if ((right!=null) && !right.isEmpty()) {
			// check that left and right tree are not identical
			if (right!=left) {
				this.root.setRight(right.root);
				// prevent nodes in right tree appearing in two separate trees
				if (right != this) {
					right.clear();
				}
			} else {
				// create separate copy of the identical tree
				this.root.setRight(right.root.copy());
			}
		}
		
		
	}
	
	// protected interface methods
	protected BinaryNode<T> getRoot() {
		return this.root;
	}
	
	protected void setRoot(BinaryNode<T> root) {
		this.root = root;
	}
	
	// public interface methods
	@Override
	public T getRootData() {
		if (this.root==null) {
			return null;
		}
		return this.root.getData();
	}

	@Override
	public int getHeight() {
		if (this.root==null) {
			return 0;
		}
		return this.root.getHeight();
	}

	@Override
	public int getNumberOfNodes() {
		if (this.root==null) {
			return 0;
		}
		return this.root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {
		if (this.root==null) {
			return true;
		}
		return this.root.getNumberOfNodes()==0;
	}

	@Override
	public void clear() {
		this.root = null;
	}

	@Override
	public void setRootData(T data) {
		if (this.root!=null) {
			this.root.setData(data);
		} else {
			this.root = new BinaryNode<>(data);
		}
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		initializeTree(rootData,(BinaryTree<T>)leftTree,(BinaryTree<T>)rightTree);		
	}

}
