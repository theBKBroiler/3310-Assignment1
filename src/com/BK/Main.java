//program written by Ken Rivard for CS3310
//September 27th, 2016 for professor Ajay Gupta

package com.BK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //prompt user for input
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        //ask user to enter max value to generate numbers to (max value)
        System.out.println("Enter a maximum value to generate numbers to: ");
        int max = in.nextInt();
        //ask user to enter max number of numbers to generate (range)
        System.out.println("Enter a maximum number of values to generate: ");
        int range = in.nextInt();
        System.out.println("How many times do you want the program to loop? ");
        int loop = in.nextInt();
        int low = 1;



        for(int i = 0; i < loop; i++) {
            long startList = System.currentTimeMillis();
            //generate random numbers and put those numbers in a doubly linked list
            randLinkedList(low, range, max);
            long endList = System.currentTimeMillis() - startList;
            System.out.println(endList + "ms for [" + i + "] Doubly Linked List");
            long startArray = System.currentTimeMillis();
            //generate random numbers and put those numbers in an array
            randomArray(low, range, max);
            long endArray = System.currentTimeMillis() - startArray;
            System.out.println(endArray + "ms for ["+ i + "] Array");
        }


    }//end main

    //generate random integers and add to doubly linked list
    public static void randLinkedList(int low, int range, int max) {
        linkedList list = new linkedList();
        Random random = new Random();
        int randNum;

        //generate random numbers
        for(low = 1; low < range; low++) {
            randNum = random.nextInt((max - low) + 1 + low);
            list.insertAtStart(randNum);
        }
        list.display();
        list.numGreaterList(50,range);
        list.display();
    }

    //add randomly generated numbers to an array
    public static void randomArray(int low, int range, int max) {
        Random random = new Random();
        int[] randArray = new int[range];

        for (low = 1; (low < randArray.length); low++) {
            randArray[low] = random.nextInt(max - low + 1) + low;
        }
        System.out.println("Numbers: " + Arrays.toString(randArray));
        bubbleSort(randArray);
        numGreaterArr(randArray, 50, range);
    }//end randomArray

    //bubble sort
    public static void bubbleSort(int randArray[]) {
        int n = randArray.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (randArray[i] > randArray[k]) {
                    swapNumbers(i, k, randArray);
                }
            }
            System.out.println("Sorting Array Values ... ");
            printNumbers(randArray);
        }
    }//end bubbleSort

    //swap the values to get an increasing order in bubble sort method
    private static void swapNumbers(int i, int j, int[] randArray) {
        int temp;
        temp = randArray[i];
        randArray[i] = randArray[j];
        randArray[j] = temp;
    }//end swapNumbers

    //print the values from the bubble sort method
    private static void printNumbers(int[] randArray) {
        for (int i = 0; i < randArray.length; i++) {
            System.out.print(randArray[i] + ", ");
        }
        System.out.println("\n");
    }//end printNumbers

    //check the original array for values over 50
    public static int[] numGreaterArr(int[] randArray, int val,int range) {
        int i;
        int j;
        int temp;
        if (randArray == null || randArray.length == 0) {
            throw new IllegalArgumentException();
        }
        int[] newArr = new int[randArray.length];
        int ip = 0;
        int check = 0;
        //check if values in array over int 50 are greater than 5
        for (int r : randArray) {
            if (r > val || check >= 5) {
                check++;
                randArray[ip++] = r;
            }
        }
        System.out.println("Numbers in array > 50: " + check);
        if(check < 5) {
            for (i = 0; i < (range - 1); i++) {
                for (j = 0; j < range - i - 1; j++) {
                    if (randArray[j] < randArray[j+1]) {
                        temp = randArray[j];
                        randArray[j] = randArray[j+1];
                        randArray[j+1] = temp;
                    }
                }
            }
            randArray[5] = 0;
            System.out.println("Non-Increasing Order Array: \n" + Arrays.toString(randArray));
        } else {
            randArray[0] = 10;
            System.out.println("Non-Decreasing Order Array: \n" + Arrays.toString(randArray));
        }
        return Arrays.copyOf(randArray, ip);
    }//end numGreater


}//end class Main


//begin class Node
class Node {
    protected int data;
    protected Node next;
    protected Node prev;

    public Node() {
        next = null;
        prev = null;
        data = 0;
    }//end Node

    public Node(int d, Node n, Node p) {
        data = d;
        next = n;
        prev = p;
    }//end Node

    public Node getLinkNext() {
        return next;
    }//end getLinkNext

    public void setLinkNext(Node n) {
        next = n;
    }//end setLinkNext

    public Node getLinkPrev() {
        return prev;
    }//end getLinkPrev

    public void setLinkPrev(Node p) {
        prev = p;
    }//endLinkPrev

    public int getData() {
        return data;
    }//endgetData

    public void setData(int d) {
        data = d;
    }//end setData


}//end class Node

class linkedList {
    public int size;
    protected Node start;
    protected Node end;

    public linkedList() {
        start = null;
        end = null;
        size = 0;
    }//end linkedList

    public boolean isEmpty() {
        return start == null;
    }

    public int getSize() {
        return size;
    }//end isEmpty

    //insert val at head of linked list
    public void insertAtStart(int val) {
        Node nptr = new Node(val, null, null);
        if (start == null) {
            start = nptr;
            end = start;
        } else {
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++;
    }//end insertAtStart

    //insert val at tail of linked list
    public void insertAtEnd(int val) {
        Node nptr = new Node(val, null, null);
        if (start == null) {
            start = nptr;
            end = start;
        } else {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            end = nptr;
        }
        size++;
    }//end insertAtEnd

    //insert val at specified pos in linked list
    public void insertAtPosition(int val, int pos) {
        Node nptr = new Node(val, null, null);
        if (pos == 1) {
            insertAtStart(val);
            return;
        }
        Node ptr = start;
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++;
    }//end insertAtPosition

    //delete value from specified pos from linked list
    public void deleteAtPosition(int pos) {
        if (pos == 1) {
            if (size == 1) {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getLinkNext();
            start.setLinkPrev(null);
            size--;
            return;
        }
        if (pos == size) {
            end = end.getLinkPrev();
            end.setLinkNext(null);
            size--;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size--;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }//end deleteAtPosition

    //print/display the linked list
    public void display() {
        System.out.print("\nDoubly Linked List = ");
        if (size == 0) {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == null) {
            System.out.println(start.getData());
            return;
        }
        Node ptr = start;
        System.out.print(start.getData() + " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != null) {
            System.out.print(ptr.getData() + " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData() + "\n");
    }//end display

    //sort the linked list in a non-decreasing order
    public void sortLinkedListIn() {
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                Node currentNode = start;
                Node next = start.getLinkNext();
                for (int j = 0; j < size - 1; j++) {
                    if (currentNode.data > next.data) {
                        int temp = currentNode.data;
                        currentNode.data = next.data;
                        next.data = temp;
                    }
                    currentNode = next;
                    next = next.getLinkNext();
                }
            }
        }
    }//end sortLinkedListIn

    //sort the linked list in a non-decreasing order
    public void sortLinkedListDe() {
        if (size > 1) {
            for (int i = 0; i < (size -1); i++) {
                Node currentNode = start;
                Node next = start.getLinkNext();
                for (int j = 0; j < size - i - 1; j++) {
                    if (currentNode.data < next.data) {
                        int temp = currentNode.data;
                        currentNode.data = next.data;
                        next.data = temp;
                    }
                    currentNode = next;
                    next = next.getLinkNext();
                }
            }
        }
    }//end sortLinkedListDe

    //check the doubly linked list for values over 50
    public int numGreaterList(int val, int range) {
        Node current = start;
        int count = 0;
        while (current != null) {
            if (current.data > 50)
                count++;
                current = current.next;
            if (count <= 5) {
                System.out.println("Integers > 50: " + count);
                sortLinkedListIn();
                deleteAtPosition(5);
                insertAtStart(10);
                sortLinkedListIn();
            } else if(count == 5){
                System.out.println("Integers > 50: " + count);
                sortLinkedListDe();
                deleteAtPosition(2);
                insertAtPosition(10,1);
                sortLinkedListDe();
            }
        }
        return count;
    }//end numGreaterList

}//end class linkedList