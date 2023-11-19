public class Main 
{
    public static void main(String[] args) 
    {
        AVL newAVL = new AVL(); //create instance of the AVL tree
        newAVL.insert(5);
        newAVL.insert(10);
        newAVL.insert(15);
        newAVL.insert(20);
        newAVL.levelOrder();
    }
    
}