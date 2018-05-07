package redblacktrees;

/**
 *
 * @author ahmed
 */
public class Test {
    
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
//        tree.insert(20);
//        tree.insert(10);
//        tree.insert(30);
//        tree.insert(5);
//        tree.insert(1);
//        tree.insert(3);
//        tree.insert(2);
//        tree.insert(33);
//        tree.insert(34);
//        tree.insert(24);
//        tree.insert(100);
//        tree.insert(80);
//        tree.insert(40);

        for (int i = 1; i < 15; i++) {
            tree.insert(i);
        }
        tree.printSorted();
        //System.out.println("Height: "+tree.height(tree.root));

        //System.out.println(tree.contains(14));
        //System.out.println(tree.contains(9));
        //tree.printBreadthFirst();
        
    }
    
}
