package driver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import bitmanipulation.NumberThatDoesNotAppearTwice;

import maps.implementations.MyHashMapList;
import matrices.PrintSpiral;
import numbers.DecimalBinary;
import numbers.IsPowerOf;
import numbers.Primes;
import numbers.StringToDouble;
import numbers.ThreeSum2;

import linkedlists.MergeTwoSortedLists;
import linkedlists.ReverseLinkedList;

import recursion.backtracking.ReplaceSimilarConnectedNumbers;
import recursion.combinationpermutation.StringPermutations;
import reusableobjects.ListNode;
import reusableobjects.TreeNode;
import searching.KRank;
import searching.SquareRoot;
import sorting.implementations.CountingSort;
import sorting.implementations.DescendingComparator;
import sorting.implementations.InsertionSort;
import sorting.implementations.MergeSort;
import sorting.implementations.SelectionSort;
import stacks.StackSort;
import stacks.ValidParentheses;
import stringandarrays.AToI;
import stringandarrays.FirstOccurrence;
import stringandarrays.MergeNSortedLists;
import stringandarrays.MinimumPathInTriangle;
import stringandarrays.PalindromeSpecial;
import stringandarrays.RansomNote;
import stringandarrays.RemoveDuplicateSpaces;
import stringandarrays.ReverseWords;
import stringandarrays.RunLengthEncoding;
import trees.ClosestNumberBST;
import trees.DistanceBetweenTwoNodes;
import trees.MaximumSumPath;
import trees.PerimeterOfTree;
import trees.Traversals;
import trees.TreePaths;
import trees.implementations.BinarySearchTree;
import trees.implementations.BinaryTree;


public class Driver {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Tree - longest path and longest sum path
		/*BinaryTree bt = new BinaryTree();
		BinaryTree bt2 = new BinaryTree();
		Integer [] deserializedTree = {1,2,3,4,5,null,null,6,null,null,7,8,null,9,10,11,null,null,12,null};
		Integer [] deserializedTree2 = {-1,2,-3,100,1,null,null};
		Integer [] ds3 = {1,-2,-3,1,3,-2,null,-1};
		
		
		TreeNode root = bt.createTree(deserializedTree);
		System.out.println("Tree: " + bt.toString());
		LongestPathBetweenTwoNodes lp = new LongestPathBetweenTwoNodes();
		int lpath = lp.longestPath(root);
		System.out.println("Longest Path: " + lpath);
		
		MaximumSumPath ms = new MaximumSumPath();
		int mspath = ms.maxSumPath(root);
		System.out.println("Longest Sum: " + mspath);
		
		TreeNode root2 = bt2.createTree(deserializedTree2);
		System.out.println("Tree: " + bt2.toString());
		LongestPathBetweenTwoNodes lp2 = new LongestPathBetweenTwoNodes();
		int lpath2 = lp2.longestPath(root2);
		System.out.println("Longest Path: " + lpath2);
		
		MaximumSumPath ms2 = new MaximumSumPath();
		int mspath2 = ms2.maxSumPath(root2);
		System.out.println("Longest Sum: " + mspath2);
		
		LargestSubarray ls = new LargestSubarray();
		int [] x = {1, -2, 4, 6, 8, -1, 3, -20, 1};
		System.out.println("Sum: " + ls.largestSeqSum(x));
		
		TreeNode root2 = bt2.createTree(ds3);
		System.out.println("Tree: " + bt2.toString());
		MaximumSumPath ms2 = new MaximumSumPath();
		int mspath2 = ms2.maxSumPath(root2);
		System.out.println("Longest Sum: " + mspath2);*/
		
		/*int [] x = {1,5,2,6,3,7,8,9,3,4,3,4};
		KRank k = new KRank();
		System.out.println(k.kSmallest(x, 12));*/
		
		/*SquareRoot s = new SquareRoot();
		System.out.println(s.squareRoot(64));*/
		
		/*int [] x = {1,2,-3,4};
		int [] y = {1,2,-3};
		
		NumberThatDoesNotAppearTwice n = new NumberThatDoesNotAppearTwice();
		System.out.println(n.numberAppearingOnce(x, y));*/
		
		/*StackSort s = new StackSort();
		Stack<Integer> st = new Stack<>();
		st.push(5); st.push(4); st.push(2); st.push(8); st.push(11); st.push(1); st.push(8);
		printStack(st);
		st.push(5); st.push(4); st.push(2); st.push(8); st.push(11); st.push(1); st.push(8);
		st = s.sort(st);
		printStack(st);*/
		
		/*MergeTwoSortedLists m = new MergeTwoSortedLists();
		ListNode list1 = new ListNode(1);
		ListNode a = new ListNode(2);
		list1.next = a;
		ListNode b = new ListNode(8);
		a.next = b;
		ListNode c = new ListNode(10);
		b.next = c;
		ListNode d = new ListNode(11);
		c.next = d;
		
		ListNode list2 = new ListNode(1);
		ListNode e = new ListNode(3);
		list2.next = e;
		ListNode f = new ListNode(5);
		e.next = f;
		ListNode g = new ListNode(18);
		f.next = g;
		
		ListNode merged = m.mergeIter(list1, list2);
		printListNode(merged);*/
		
	/*	RunLengthEncoding rle = new RunLengthEncoding();
		System.out.println(rle.encode("ssssseeeeeewkkkkkkkeeeeeeeehhhhhhhssssssssnnnnnnnnnbsssssaaaaaasksijssssssssssiiiiiiiiii"));*/
		
		/*int[][] matrix =
			  {
			  { 1, 3, 2, 2, 2, 4 },
			  { 3, 3, 3, 2, 4, 4 },
			  { 4, 3, 1, 2, 3, 3 },
			  { 4, 3, 1, 3, 3, 1 },
			  { 4, 3, 3, 3, 1, 1 } };

		PrintSpiral ps = new PrintSpiral();
		ps.printSpiral(matrix);
		
		ReplaceSimilarConnectedNumbers p = new ReplaceSimilarConnectedNumbers();
		printMatrix(matrix);
		p.replace(matrix, 3, 0, 0, 1);
		printMatrix(matrix);*/
		
		/*RemoveDuplicateSpaces rs = new RemoveDuplicateSpaces();
		System.out.println(rs.removeSpaces("     Uts  av      Pan  dey     "));*/
		int x = 10;
		int y = 6;
		int xy = x >> 4;
		
		System.out.println(xy);
		
	}
	
	public static void printStack(Stack<Integer> s) {
		System.out.println();
		while(!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}
	}
	
	public static void printListNode(ListNode head) {
		System.out.println();
		while (head != null) {
			System.out.print(head.num + " ");
			head = head.next;
		}
	}
	
	public static void printArray(int [] array) {
		System.out.println();
		for(int i : array)
			System.out.print(i + " ");
	}
	
	public static void printMatrix(int [][] array) {
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}	
	}
	
	
	
	

}
