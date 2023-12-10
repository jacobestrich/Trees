// Dean Wilcox & Jacob Estrich - CSC340 Project Code

public class BinaryNode //create a binary node class
{
    //Create our variables used for searching our AVL Tree
    public int value;
    public int height; //height or level of tree
    public BinaryNode left; //left children nodes
    public BinaryNode right; //right children nodes

    BinaryNode() //constructor to create starting height as zero. "Helper method"
    {
        this.height = 0; //whenever we initiallize a new node, its height will be zero
    }
}
