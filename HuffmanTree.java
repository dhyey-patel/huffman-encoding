/*
 *@author Dhyey Patel
 *
 *The purpose of this class is to create a binary tree
 *This binary tree will be created to encode a string with a binary repersentation
*/

public class HuffmanTree extends LinkedBinaryTree<HuffmanPair> implements Comparable<HuffmanTree> {
	// First constructor
	// Just creates an empty tree
	public HuffmanTree() {
		super();
	}

	// Second Constructor
	// Creates the HuffmanTree given the element as a parameter
	// Creates the tree with just the root
	public HuffmanTree(HuffmanPair element) {
		super(element);
	}

	// Third Constructor
	// Creates the root of the huffman tree with the given element
	// Adds the subtrees to the root
	public HuffmanTree(HuffmanPair element, HuffmanTree leftSubtree, HuffmanTree rightSubtree) {
		super(element);
		getRoot().setLeft(leftSubtree.getRoot());
		getRoot().setRight(rightSubtree.getRoot());

	}

	// Forth Constructor
	// Creates the HuffmanTree given a list of HuffmanPairs
	// Uses the given algorithm to create the tree accurately so that it can be used
	// to create the codes
	public HuffmanTree(ArrayOrderedList<HuffmanPair> pairsList) {
		ArrayOrderedList<HuffmanTree> buildList = new ArrayOrderedList<HuffmanTree>();
		int listSize = pairsList.size();
		// Creates trees with only the root and adds them to buildList
		for (int i = 0; i < listSize; i++) {
			HuffmanTree tempTree = new HuffmanTree(pairsList.removeFirst());
			buildList.add(tempTree);
		}
		HuffmanTree tempTree1, tempTree2;
		int totalFrequency;
		// In the case where there is only one type of character, make a HuffmanTree
		// that can be used to create the code
		if (pairsList.size() == 1) {
			tempTree1 = buildList.removeFirst();
			totalFrequency = tempTree1.getRoot().getElement().getFrequency();
			HuffmanPair tempElement = new HuffmanPair((char) 0, totalFrequency);
			HuffmanTree newTree = new HuffmanTree(tempElement, tempTree1, null);
		}
		// while there is more than one tree in the buildList
		while (buildList.size() > 1) {
			tempTree1 = buildList.removeFirst();
			tempTree2 = buildList.removeFirst();
			// Remove the first two trees (the ones with the smallest frequencies)
			// Create a new tree with the root have the total frequencies, and the smaller
			// trees being sub trees
			totalFrequency = tempTree1.getRoot().getElement().getFrequency()
					+ tempTree2.getRoot().getElement().getFrequency();
			HuffmanPair tempElement = new HuffmanPair((char) 0, totalFrequency);
			HuffmanTree newTree = new HuffmanTree(tempElement, tempTree1, tempTree2);
			buildList.add(newTree);
		}
		// set the root of the tree to the root of the HuffmanTree
		setRoot(buildList.removeFirst().getRoot());
	}

	// Method to compare two HuffmanTrees
	// Compares the trees by comparing the frequencies, and returns an int value
	// accordingly
	public int compareTo(HuffmanTree otherTree) {
		int thisFrequency, otherFrequency;
		thisFrequency = getRoot().getElement().getFrequency();
		otherFrequency = otherTree.getRoot().getElement().getFrequency();
		if (thisFrequency == otherFrequency) {
			return 0;
		} else if (thisFrequency > otherFrequency) {
			return 1;
		} else {
			return -1;
		}
	}

	// Method to create a string representation of the Huffman Tree by running the
	// toString method of every node in the tree
	public String toString() {
		String returnString = "";
		ArrayIterator<HuffmanPair> iterator = (ArrayIterator<HuffmanPair>) iteratorPreOrder();
		while (iterator.hasNext()) {
			returnString = returnString + iterator.next().toString();
		}
		return returnString;
	}

}
