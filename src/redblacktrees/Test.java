package redblacktrees;

/**
 *
 * @author ahmed
 */
public class Test {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        for (int i = 1; i < 19; i++) {
            tree.insert(i);
        }
        tree.printBreadthFirst();
        tree.delete(16);
        tree.printBreadthFirst();
        //System.out.println("Height: "+tree.height(tree.root));
    }

}
