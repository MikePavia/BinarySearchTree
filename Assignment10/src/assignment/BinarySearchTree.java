package assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> 
implements SearchTreeInterface<T>
{

	// NO new fields are needed

	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T entry) {
		super(entry);
		
	}

	@Override
	public boolean contains(T anEntry) {

		return getEntry(anEntry) != null;
	}

	@Override
	public T getEntry(T anEntry) {		
		return findEntry(getRoot(), anEntry);
	}
	
	/**
	 * finds the given emtry 
	 * @param rootNode
	 * @param anEntry
	 * @return
	 */
	private T findEntry(BinaryNode<T> rootNode, T anEntry) {
		T result = null;

		if ( rootNode != null) {
			T rootEntry = rootNode.getData();
			if (anEntry.equals(rootEntry)) {
				result = rootEntry;
			}else if (anEntry.compareTo(rootEntry) < 0) {
				result = findEntry(rootNode.getLeft(), anEntry);				
			}else 
				result = findEntry(rootNode.getRight(), anEntry);
		}
		return result;
	}// end findEntry



	@Override
	public T add(T anEntry) {
		T result = null;
		
		if (isEmpty()) {
			setRoot(new BinaryNode<>(anEntry));
		}else
			result = addEntry(getRoot(), anEntry);
		return result;
	}// end add

	/**
	 * adds anEntry into correct apot in the tree
	 * @param rootNode
	 * @param anEntry
	 * @return
	 */
	public T addEntry(BinaryNode<T> rootNode, T anEntry) {
		assert rootNode != null;
		T result = null;
		int comparison = anEntry.compareTo(rootNode.getData());
		// if data is equal sets root nodes data to anEntry
		if (comparison == 0) {
			result = rootNode.getData();
			rootNode.setData(anEntry);
			// checks if comparison is less. if root has left child recursivly calls addEntry if not make a new left child with anEntry's data
		}else if (comparison < 0 ) {
			if ( rootNode.hasLeft()) {
				result = addEntry(rootNode.getLeft(), anEntry);
			}else 
				rootNode.setLeft(new BinaryNode<>(anEntry));
			// checks if comparison is greater. if root has right child recursive calls method if not creates new right child 
		}else {
			assert comparison > 0;
			if ( rootNode.hasRight()) {
				result = addEntry(rootNode.getRight(), anEntry);
			}else
				rootNode.setRight(new BinaryNode<>(anEntry));
		}
		return result;
	}// end addEntry
	
	
	
	@Override
	public T remove(T anEntry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRoot(), anEntry, oldEntry);
		setRoot(newRoot);
		return oldEntry.getObject();
	}


	
	
	// ------------------------------------------------------------
	//                 REMOVE HELPER METHODS
	// ------------------------------------------------------------
	
	/**
	 * removes a specified entry 
	 * @param rootNode
	 * @param anEntry
	 * @param oldEntry
	 * @return
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T anEntry, ReturnObject oldEntry) {
		
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = anEntry.compareTo(rootData);
			if (comparison == 0) {
				oldEntry.setObject(rootData);
				rootNode = removeFromRoot(rootNode);
			}else if (comparison < 0 ) {
				BinaryNode<T> leftChild = rootNode.getLeft();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, anEntry, oldEntry);
				rootNode.setLeft(subtreeRoot);
			}else {
				BinaryNode<T> rightChild = rootNode.getRight();
				rootNode.setRight(removeEntry(rightChild, anEntry, oldEntry));
			}						
		}
		return rootNode;
	}
	
	/**
	 * Returns the root of a subtree after the node is removed 
	 * @param rootNode
	 * @return
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		
		if (rootNode.hasLeft() && rootNode.hasRight()) {
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeft();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			
			rootNode.setData(largestNode.getData());
			
			rootNode.setLeft(removeLargest(leftSubtreeRoot));
		}
		
		else if (rootNode.hasRight()) {
			rootNode = rootNode.getRight();
		}else {
			rootNode = rootNode.getLeft();
		}
		return rootNode;
	}// end remove from root 
	
	/**
	 * finds the largest value of a subtree
	 * @param rootNode
	 * @return
	 */
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRight()) {
			rootNode = findLargest(rootNode.getRight());
		}
		return rootNode;
	}
	
	/**
	 * removes the found largest value 
	 * @param rootNode
	 * @return
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		
		if (rootNode.hasRight()) {
			BinaryNode<T> rightChild = rootNode.getRight();
			rightChild = removeLargest(rightChild);
			rootNode.setRight(rightChild);
		}else {
			rootNode = rootNode.getLeft();
		}
		return rootNode;
	}
	
	
	
	
	
	@Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	
	
	
	
	private class InorderIterator implements Iterator<T> {

		private Stack<BinaryNode<T>> stack;
		private BinaryNode<T> current;
		
		// default constructor
		public InorderIterator() {
			stack = new Stack<BinaryNode<T>>();
			current = getRoot();
		}
			
		@Override
		public boolean hasNext() {
			return (!stack.isEmpty()) || (current != null);
		}

		@Override
		public T next() {
			BinaryNode<T> nextNode = null;
			// find leftmost node with no child
			while (current != null) {
				stack.push(current);
				current = current.getLeft();
			}
			// get leftmost node then move to right subtree
			if (!stack.isEmpty()) {
				nextNode = stack.pop();				
				current = nextNode.getRight();
			}else {
				throw new NoSuchElementException();			
			}
						
			return nextNode.getData();
		}
		
	}
	
	private class ReturnObject {
		private T object;
		
		public ReturnObject(T object) {
		this.object = object;
		}
		
		public T getObject() {
			return object;
		}
		
		public void setObject(T rootData) {
			this.object = rootData;
		}
		
	}
	
}
