package redblacktrees;

/**
 *
 * @author ahmed
 */
public class Test {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

//        for (int i = 1; i < 19; i++) {
//            tree.insert(i);
//        }
tree.insert(5);
        tree.printBreadthFirst();
        tree.delete(5);
        tree.insert(4);
//        tree.delete(2);
//        tree.delete(8);
//        tree.delete(14);
        tree.printBreadthFirst();
        //System.out.println("Height: "+tree.height(tree.root));
    }

}
