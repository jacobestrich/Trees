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
        System.out.println(node.value + " "); //print out value of node
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
        System.out.println(node.value + " "); //print out value of node
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
        System.out.println(node.value + " "); //print out value of node
    }

    //Level-Order Travesal - visits all nodes level by level. Unlike the other methods
    void levelOrder()
    {
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>(); //creates a queue data structure
        queue.add(root); //creates root node and adds to queue. This is the start of the traversal
        while (!queue.isEmpty()) //while the queue is not empty continue the traversal 
        {
            BinaryNode presentNode = queue.remove(); //Remove and process the front node from the queue
            System.out.println(presentNode.value + " "); //Print value of the current node
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
    BinaryNode search(BinaryNode node, int value)
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







}
