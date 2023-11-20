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
        if(balance > 1 && nodeValue < node.left.value)  //Left, Left condition
        {
            return rotateRight(node); //rotate right
        }

        if(balance > 1 && nodeValue > node.left.value) //Left, Rigth condition
        {
            node.left = rotateLeft(node.left); //rotate left
            return rotateRight(node); //rotate right
        }

        if(balance < -1 && nodeValue > node.right.value) //Right, Right condition
        {
            return rotateLeft(node); //rotate left
        }

        if(balance < -1 && nodeValue < node.right.value) //Right, Left condition
        {
            node.right = rotateRight(node.right); //rotate right
            return rotateLeft(node); //rotate left
        }
        return node; //return the updated node
    }



    //Method used to insert a value into the AVL tree.
    public void insert(int value) 
    {
        root = insertNode(root, value); //call the insertNode method to insert value
    }


    
    //Minimum node finder 
    public BinaryNode minimumNode(BinaryNode node)
    {
        if(node.left == null) //if there is no left child
        {
            return node; //return current node
        }
        return minimumNode(node.left); //if there is a left child, keep searching the left subtree for smallest value
    }



    // Delete Node O(logN)
    public BinaryNode deleteNode(BinaryNode node, int value)
    {
        if(node == null) //if node is null
        {
            System.out.println("Value not found in tree"); //value is not found in the tree
            return node;
        }
        if(value < node.value) //search for and delete the value in the left subtree is value is less than the node value
        {
            node.left = deleteNode(node.left, value);
        }
        else if(value > node.value) //search for and delete the value in the right subtree if value is greater than the node value
        {
            node.right = deleteNode(node.right, value);
        }
        else 
        {
            if(node.left != null && node.right != null) //if the left child and right child are not null
            {
                BinaryNode tmp = node; //create tmp node
                BinaryNode minNodeForRight = minimumNode(tmp.right); 
                node.value = minNodeForRight.value; //replace current node's value with the value of the minimum node in the right sub-tree
                node.right = deleteNode(node.right, minNodeForRight.value); //delete the minimum node in the right subtree
            }
            else if(node.left != null) //if node's left child is not null
            {
                node = node.left; //replace the current node with its left child
            }
            else if(node.right != null) //if node's right child is not null
            {
                node = node.right; //replace the current node with it's right child
            }
            else //if the node has no children
            {
                node = null; //remove the node
            }
        }

        int balance = getBalance(node); //calculate the balance of node
        if(balance > 1 && getBalance(node.left) >= 0) //LL Condition, Right rotation
        {
            return rotateRight(node);
        }
        if(balance > 1 && getBalance(node.left) < 0)  //LR Condition, Left rotation on left child and then right rotation on disbalanced node
        {
            node.left = rotateLeft(node.left); 
            return rotateRight(node);
        }
        if(balance < -1 && getBalance(node.right) <= 0) //RR Condition, Left Rotation
        {
            return rotateLeft(node);
        }
        if(balance < -1 && getBalance(node.right) > 0) //RL Condition, Right rotation on the right child, then left rotation on disbalanced node
        {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node; //return the updated node after balancing
    }



    //Calls deleteNode method to delete a value from tree
    public void delete(int value)
    {
        root = deleteNode(root, value);
    }



    //Clear Entire Tree - O(1)
    public void clearAVL()
    {
        root = null;
        System.out.println("AVL Tree has been successfully cleared and deleted!");
    }    
}


/*Notes:
 * Run Times:
 * 
 * AVL Tree:
 *  -Create AVL:            O(1)
 *  -Insert a Node:         O(logN)
 *  -Traverse AVL:          O(N)
 *  -Search for a Node:     O(logN)
 *  -Delete a Node:         O(logN)
 *  -Delete Entire AVL:     O(1)
 * 
 * 
 * Binary Search Tree(BST):
 *  -Create AVL:            O(1)
 *  -Insert a Node:         O(N)
 *  -Traverse Tree:         O(N)
 *  -Search for a Node:     O(N)
 *  -Delete a Node:         O(N)
 *  -Delete Entire Tree:    O(1) 
 * 
 */

