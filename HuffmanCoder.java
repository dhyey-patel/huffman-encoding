
/*
 *@author Dhyey Patel
 *
 *The purpose of this class is to create the codes for each symbol using the other classes
*/

import java.util.Iterator;

public class HuffmanCoder {
	private HuffmanTree huffTree;
	ArrayUnorderedList<EncodingData> encodingList = new ArrayUnorderedList<EncodingData>();

	// Constructor of the class
	// Given the list of HuffmanPairs create a HuffmanTree and create a list of all
	// the codes using that tree
	public HuffmanCoder(ArrayOrderedList<HuffmanPair> pairsList) {
		huffTree = new HuffmanTree(pairsList);
		buildEncodingList(huffTree.getRoot(), "");
	}

	// This method is to decode a given code
	// It will follow the stems of the HuffmanTree to a leaf and return the symbol
	// if found
	public char decode(String code) {
		BinaryTreeNode<HuffmanPair> tempNode = null;
		// Go through each character in the code
		for (int i = 0; i < code.length(); i++) {
			// If it is the first character start from the root of the HuffmanTree
			if (i == 0) {
				if (code.substring(i, i + 1).equals("0")) {
					tempNode = huffTree.getRoot().getLeft();
				} else {
					tempNode = huffTree.getRoot().getRight();
				}
			}
			// If it is not the first character continue from the last node
			// Get the left node if the character is 0, and get the right node if the
			// character is 1
			else {
				if (code.substring(i, i + 1).equals("0")) {
					tempNode = tempNode.getLeft();
				} else {
					tempNode = tempNode.getRight();
				}
			}
			// If the tree ends before you get to the end of the code return (char)0
			if (tempNode == null) {
				return (char) 0;
			}
		}
		// If the last node is a leaf, return the symbol
		if (tempNode.isLeaf()) {
			return tempNode.getElement().getCharacter();
		}
		// If it is not a leaf return (char)0 to show that the code is invalid
		else {
			return (char) 0;
		}
	}

	// Method to encode the given symbol
	// Go through the list created in the constructor and return the code associated
	// with the given symbol
	public String encode(char c) throws ElementNotFoundException {
		Iterator<EncodingData> dataIterator = encodingList.iterator();
		char charCheck;
		EncodingData tempCheck;
		// use the iterator to go trough the array list until is is finished or the
		// symbol is found
		while (dataIterator.hasNext()) {
			tempCheck = dataIterator.next();
			charCheck = tempCheck.getSymbol();
			// if the symbol is found, return it
			if (charCheck == c) {
				return tempCheck.getEncoding();
			}
		}
		// If the symbol is not found after going through the list throw the exception
		throw new ElementNotFoundException("Element is not in the List");
	}

	// This method creates a string representation of the encoding list
	// Uses a iterator to traverse the list and create a string representation of
	// each object
	public String toString() {
		String returnString = "";
		Iterator<EncodingData> dataIterator = encodingList.iterator();
		while (dataIterator.hasNext()) {
			returnString = returnString + dataIterator.next().toString() + "\n";
		}
		return returnString;
	}

	// Private method to create the list of codes
	// Uses recursion to traverse the tree and create each code and add it to the
	// list
	private void buildEncodingList(BinaryTreeNode<HuffmanPair> node, String encoding) {
		if (node.isLeaf()) {
			EncodingData tempData = new EncodingData(node.getElement().getCharacter(), encoding);
			encodingList.addToRear(tempData);
		} else {
			buildEncodingList(node.getLeft(), encoding + "0");
			buildEncodingList(node.getRight(), encoding + "1");
		}
	}

}
