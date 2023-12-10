// Dean Wilcox & Jacob Estrich - CSC340 Project Code

public class Main 
{
    public static void main(String[] args) 
    {
        AVL newAVL = new AVL(); //create instance of the AVL tree
        newAVL.insert(5);
        newAVL.insert(10);
        newAVL.insert(15);
        newAVL.insert(20);
        newAVL.insert(48);
        //newAVL.levelOrder();

        newAVL.delete(5);
        System.out.println();
        newAVL.levelOrder();
        System.out.println();
        //newAVL.clearAVL();
    }
    
}