package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class BinarySearchTreeTest {

	public static void main(String[] args) {
		
		testBinarySearchTree(10);
		
		testBinarySearchTree(100);
		
		testBinarySearchTree(1000);
		
		testBinarySearchTree(10000);
		
		testBinarySearchTree(100000);

	}

	
	/**
	 * Displays the list
	 * @param <T>
	 * @param aList
	 */
	public static <T extends Comparable<? super T>> void display(SearchTreeInterface<T> tree)
	{
		Iterator<T> it = tree.getInorderIterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}		
		System.out.println();
	}  // end displayList
	
	
	/**
	 * Method to run a sort test on the given list.
	 * 
	 * @param tree the list to use for sorting
	 * @param size the number of items in the list
	 */
	public static void testBinarySearchTree(int size) {
		
		// Create a list of size random integers
		System.out.println("---------------------------------------------------");
		System.out.printf("Creating a binary search tree of %,d nodes.\n", size);
		
		SearchTreeInterface<NameInterface> tree = load(size);
		
		
		// print some statistics
		System.out.printf("%-36s%,9d\n", "count:", tree.getNumberOfNodes());
		System.out.printf("%-36s%,9d\n", "height:", tree.getHeight());
		System.out.println();
		
		// Test the sort ability for size random items.
		System.out.printf("%-38s", "Testing sort order: ");
		
		// Validate the sort
		if (validate(tree)) {
			System.out.println(" passed");
		} else {
			System.out.println(" failed");
		}
		
		// Search test
		NameInterface searchTest = new NameInfo("jones");
		System.out.printf("%-38s", String.format("Testing search for %s: ", searchTest.getName()));
		
		NameInterface searchResult = tree.getEntry(searchTest);
		if (searchResult!=null && searchResult.getName().compareTo(searchTest.getName())==0) {
			System.out.println(" passed");
		} else {
			System.out.println(" failed");
		}

		// Remove test
		System.out.printf("%-38s", String.format("Testing remove %s: ", searchTest.getName()));
		
		NameInterface removeResult = tree.remove(searchTest);
		if (removeResult!=null && removeResult.getName().compareTo(searchTest.getName())==0) {
			System.out.println(" passed");
		} else {
			System.out.println(" failed");
		}
		
		// Tree validation test
		System.out.printf("%-38s", String.format("Testing order after removing %s: ", searchTest.getName()));
		// Validate the sort
		if (validate(tree)) {
			System.out.println(" passed");
		} else {
			System.out.println(" failed");
		}
		
		
		System.out.println("");
	}
	
	
	/**
	 * Method to validate that the given SearchTree is sorted in increasing order.
	 * 
	 * Checks a previous and next element in the list. 
	 * 
	 * If the value of the previous element is ever greater than the next element the method
	 * returns false without checking any more elements.
	 * 
	 * Returns true if the array contains less than two elements.
	 * @param <T>
	 * 
	 * @param aList - the list to validate
	 * @return true if the list is sorted properly otherwise false
	 */
	public static <T extends Comparable<? super T>> boolean validate(SearchTreeInterface<T> tree) {
		boolean isValid = true;
		Iterator<T> it = tree.getInorderIterator();
		T prev = it.next();
		while (it.hasNext() && isValid) {
			isValid = (prev.compareTo(it.next()) <= 0);
		}		
		return isValid;
	}
	
	
	public static SearchTreeInterface<NameInterface> load(int maxCount) {
		
		// Load the given number of entries from the file
		// Add all items from the census file to the dictionary
		int indexOfJones = -1;
		ArrayList<NameInterface> names = new ArrayList<>();
		File file = new File("LastNames2000Census.txt");
		Scanner data;
		try {
			data = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load the file.");
			return null;
		}
		
		try {
			// read information from the file
			System.out.println("loading names ...");
			int count = 0;
			while (data.hasNextLine()) {
				NameInterface name = createFromString(data.nextLine());
				names.add(name);
				if (name.getName().equalsIgnoreCase("jones")) {
					indexOfJones = count;
				}
				count++;
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		
		// close the file after reading
		data.close();
		
		System.out.println("loading names finished");
		System.out.println("");

		// create and load randomized names into the binary search tree
		System.out.println("Adding names to BinarySearchTree");
		Random rand = new Random();
		SearchTreeInterface<NameInterface> bst = new BinarySearchTree<>();
		bst.add(names.get(indexOfJones));
		int count = 1;
		while (names.size()>0 && count<maxCount) {
			int index = rand.nextInt(names.size());
			if (index!=indexOfJones) {
				bst.add(names.remove(index));
				count++;
			}
			if (maxCount>10000) {
				if (count % 200 == 0) {
					System.out.print(".");					
				}
				if (count % (10000) == 0) {
					System.out.printf(" : %,d\n",count);
				}
			}
		}		
		System.out.println("Adding names to BinarySearchTree ... finished.");
		System.out.println();
		return bst;		
	}
	
	
	/**
	 * Static factory method to create a new NameInfo object from the given 
	 * comma separated value string.
	 * 
	 * @param csv
	 * @return
	 */
	public static NameInterface createFromString(String csv) {
		NameInterface info = new NameInfo();
		String[] tokens = csv.trim().split(",");
		if (tokens.length>0) {
			info.setName(tokens[0].toLowerCase().trim());
		}
		if (tokens.length>1) {
			info.setRank(Integer.parseInt(tokens[1]));
		}
		if (tokens.length>2) {
			info.setFrequency(Integer.parseInt(tokens[2]));
		}
		if (tokens.length>3) {
			info.setProportion(Double.parseDouble(tokens[3]));
		}
		if (tokens.length>4) {
			info.setCumulativeProportion(Double.parseDouble(tokens[4]));
		}
		return info;
	}


}
