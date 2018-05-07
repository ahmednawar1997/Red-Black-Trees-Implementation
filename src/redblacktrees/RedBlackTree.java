package redblacktrees;

/**
 *
 * @author ahmed
 * @param <E>
 */
public class RedBlackTree<E extends Comparable<E>> {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final int RED = 0;
    public static final int BLACK = 1;

    public final Node<E> nil;

    public Node<E> root = null;

    public RedBlackTree() {
        nil = new Node<>(null);
        nil.color = BLACK;
    }

    public void insert(E key) {
        if (root == null) {
            root = new Node<>(key);
            root.color = BLACK;
            setNodeToLeaf(root);
        } else {

            insertNode(key, root);
        }

    }

    private void insertNode(E key, Node<E> node) {
        Node<E> newNode = new Node<>(key);
        if (key.compareTo(node.key) > 0) {
            if (isNil(node.right) || node.right == null) {
                node.right = newNode;
                newNode.parent = node;
                setNodeToLeaf(newNode);

                balanceTree(newNode);
                return;
            }
            insertNode(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            if (isNil(node.left) || node.left == null) {
                node.left = newNode;
                newNode.parent = node;
                setNodeToLeaf(newNode);
                balanceTree(newNode);
                return;
            }
            insertNode(key, node.left);
        }

    }

    private void setNodeToLeaf(Node<E> node) {
        node.left = nil;
        node.right = nil;
    }

    private void balanceTree(Node<E> node) {
        while (node.parent != null && node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                Node<E> y = node.parent.parent.right;
                if (y.color == RED) {
                    node.parent.color = BLACK;
                    y.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node.isRightChild()) {
                    node = node.parent;
                    rotateLeft(node);
                } else {
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node<E> y = node.parent.parent.left;
                if (y.color == RED) {
                    node.parent.color = BLACK;
                    y.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node.isLeftChild()) {
                    node = node.parent;
                    rotateRight(node);
                } else {
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }

            }
        }
        root.color = BLACK;

    }

    private void rotateLeft(Node<E> node) {
        Node<E> rightChild = node.right;
        Node<E> originalNode = node;

        node.right = rightChild.left;

        if (node.parent == null) {
            root = rightChild;
            rightChild.parent = null;
        } else if (originalNode.isLeftChild()) {
            node.parent.left = rightChild;
            rightChild.parent = originalNode.parent;
        } else {
            node.parent.right = rightChild;
            rightChild.parent = originalNode.parent;
        }
        rightChild.left = node;
        node.parent = rightChild;

    }

    private void rotateRight(Node<E> node) {
        Node<E> leftChild = node.left;
        Node<E> originalNode = node;

        node.left = leftChild.right;

        if (node.parent == null) {
            root = leftChild;
            leftChild.parent = null;
        } else if (originalNode.isLeftChild()) {
            node.parent.left = leftChild;
            leftChild.parent = originalNode.parent;
        } else {
            node.parent.right = leftChild;
            leftChild.parent = originalNode.parent;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    public void printBreadthFirst() {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printGivenLevel(root, i);
        }
    }

    public int height(Node<E> root) {
        if (isNil(root) || root == null) {
            return 0;
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            } else {
                return (rightHeight + 1);
            }
        }
    }

    private void printGivenLevel(Node<E> root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {

            if (isNil(root)) {
                System.out.print(ANSI_RESET + "Nil ");
            } else if (root.color == RED) {
                System.out.print(ANSI_RED + root.key + " ");
            } else {
                System.out.print(ANSI_RESET + root.key + " ");
            }

        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }

    public void printSorted() {
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (!isNil(node)) {
            inOrder(node.left);
            if (node.color == RED) {
                System.out.print(ANSI_RED + node.key + " ");
            } else {
                System.out.print(ANSI_RESET + node.key + " ");
            }
            inOrder(node.right);
        }
    }

    private boolean isNil(Node<E> node) {
        return node == nil;
    }

    public boolean contains(E key) {
        return search(root, key);
    }

    private boolean search(Node<E> node, E key) {
        if (isNil(node)) {
            return false;
        } else if (node.key == key) {
            return true;
        } else if (node.key.compareTo(key) > 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
}
