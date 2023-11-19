import java.util.LinkedList; //used in level order traversal
import java.util.Queue; //used in level order traversal

public class AVL 
{
    BinaryNode root;
    
    //Constructor
    AVL()
    {
        root = null; //when instance of AVL is created. We set root node to null
    }

    //Pre-Order Traversal
    public void preOrder(BinaryNode node)
    {
        if(node == null)
        {
            return;
        }
        System.out.print(node.value + " "); //print out value of node
        preOrder(node.left); //check left sub-tree
        preOrder(node.right); //check right sub-tree
    }

    //In-Order Traversal
    public void inOrder(BinaryNode node)
    {
        if(node == null)
        {
            return;
        }
        inOrder(node.left); //check left sub-tree
        System.out.print(node.value + " "); //print out value of node
        inOrder(node.right); //check right sub-tree
    }

    //Post-Order Traversal
    public void postOder(BinaryNode node)
    {
        if(node == null)
        {
            return;
        }
        postOder(node.left); //check left sub-tree
        postOder(node.right); //check right sub-tree
        System.out.print(node.value + " "); //print out value of node
    }

    //Level-Order Travesal - visits all nodes level by level. Unlike the other methods
    public void levelOrder()
    {
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>(); //creates a queue data structure
        queue.add(root); //creates root node and adds to queue. This is the start of the traversal
        while (!queue.isEmpty()) //while the queue is not empty continue the traversal 
        {
            BinaryNode presentNode = queue.remove(); //Remove and process the front node from the queue
            System.out.print(presentNode.value + " "); //Print value of the current node
            if(presentNode.left != null) //If the left child of the current node exists, add it to the queue for later processing
            {
                queue.add(presentNode.left); 
            }
            if(presentNode.right != null) //If the right child of the current node exists, add it to the queue for later processing
            {
                queue.add(presentNode.right);
            }
        }
    }

    //Search Method - looks for a specific value in the AVL tree, starting at a given node
    public BinaryNode search(BinaryNode node, int value)
    {
        if(node == null) //if current node is null
        {
            System.out.println("Value: " + value + " not found in AVL"); //value not foudn in AVL tree
            return null;
        }
        else if(node.value == value) //if value of current node is equal to the target value, we found it. 
        {
            System.out.println("Value: " + value + " found in AVL");
            return node; //break out of loop after finding target value/node
        }
        else if(value < node.value) //If target value is less than the current node's value, continue searching for it in the left subtree
        {
            return search(node.left, value);
        }
        else //If target value is greater than or equal to the current node, continue searching in the right subtree
        {
            return search(node.right, value);
        }
    }

    //Get Height - returns height of node
    public int getHeight(BinaryNode node)
    {
        if(node == null) //If the node is null, the height is zero
        {
        return 0;
        }
        return node.height; //If the node is not null, return the height of the node
    }

    //Rotate Right O(1) - Do a right rotation on a disbalanced node to balance the tree
    private BinaryNode rotateRight(BinaryNode disbalancedNode)
    {
        BinaryNode newRoot = disbalancedNode.left; //new root is stored as the left child of the disbalanced node
        disbalancedNode.left = disbalancedNode.left.right; //update the left child of disbalanced node to its right
        newRoot.right = disbalancedNode; //new root's right child is now the disbalanced node
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right)); //update the height for disbalanced node
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right)); //update the height for new root
        return newRoot; //return the root after roation
    }

    //Rotate Left O(1) - Do a left rotation on a disbalanced node
    private BinaryNode rotateLeft(BinaryNode disbalancedNode)
    {
        BinaryNode newRoot = disbalancedNode.right; //new root is stored as the right child of the disbalanced node
        disbalancedNode.right = disbalancedNode.right.left; //update the right child of disbalanced node to its left
        newRoot.left = disbalancedNode; //new root's left child is now the disbalanced node
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right)); //update the height for disbalanced node
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right)); //update the height for new root
        return newRoot; //return the root after rotation
    }

    //Get Balance O(1) - Figure out the tree balance
    public int getBalance(BinaryNode node)
    {
        if(node == null) //if the node is null, its balance is zero
        {
            return 0; 
        }
        return getHeight(node.left) - getHeight(node.right); //If node is not null, calculate the balance or difference between left side and right
    }

    //Insert Node Method O(logN) - insert new node with a given value
    private BinaryNode insertNode(BinaryNode node, int nodeValue)
    {
        if(node == null) //if no nodes (empty)
        {
            BinaryNode newNode = new BinaryNode(); //create a new node
            newNode.value = nodeValue; //put node value in the new node
            newNode.height = 1; //set the new node's height as one
            return newNode; //return new node
        }
        else if(nodeValue < node.value) //if the node value is less than the node its comparing itself too, move to the left side of tree
        {
            node.left = insertNode(node.left, nodeValue);
        }
        else  //if the node value is greater that the node, move to the right part of tree
        {
            node.right = insertNode(node.right, nodeValue);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right)); //update height
        int balance = getBalance(node); //get balance of the tree

        //Do the rotations below
        if(balance > 1 && nodeValue < node.left.value)  //Left, Left method
        {
            return rotateRight(node); //rotate right
        }

        if(balance > 1 && nodeValue > node.left.value) //Left, Rigth method
        {
            node.left = rotateLeft(node.left); //rotate left
            return rotateRight(node); //rotate right
        }

        if(balance < -1 && nodeValue > node.right.value) //Right, Right method
        {
            return rotateLeft(node); //rotate left
        }

        if(balance < -1 && nodeValue < node.right.value) //Right, Left method
        {
            node.right = rotateRight(node.right); //rotate right
            return rotateLeft(node); //rotate left
        }
        return node; //return the updated node
    }

    //Method used to insert a value into the AVL tree.
    public void insert(int value) 
    {
        root = insertNode(root, value);
    }




}
