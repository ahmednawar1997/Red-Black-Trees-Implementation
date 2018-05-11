package redblacktrees;

import java.util.ArrayList;

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
    public int treeSize = 0;
    private ArrayList<E> sortedTree = new ArrayList<>();

    public Node<E> root = null;

    public RedBlackTree() {
        nil = new Node<>(null);
        nil.color = BLACK;
    }

    public void insert(E key) {
        treeSize++;
        if (root == null) {
            root = new Node<>(key);
            root.color = BLACK;
            setNodeToLeaf(root);
        } else {

            insertNode(key, root);
        }

    }

    public int getTreeSize() {
        return treeSize;
    }

    public ArrayList<E> getSortedTreeArray() {
        return sortedTree;
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
        if (!isNil(node.right) && node.right != root) {
            node.right.parent = node;
        }

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
        rightChild.left.parent = node;
        node.parent = rightChild;

    }

    private void rotateRight(Node<E> node) {
        Node<E> leftChild = node.left;
        Node<E> originalNode = node;

        node.left = leftChild.right;
        if (!isNil(node.right) && node.right != root) {
            node.left.parent = node.left;
        }
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
        leftChild.right.parent = node;
        node.parent = leftChild;
    }

    public void printBreadthFirst() {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printGivenLevel(root, i);
        }
    }

    public int getTreeHeight() {
        return height(root);
    }

    private int height(Node<E> root) {
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
        sortedTree.clear();
        if (root == null) {
            return;
        }
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (!isNil(node)) {
            inOrder(node.left);
            if (node.color == RED) {
                sortedTree.add(node.key);
                System.out.print(ANSI_RED + node.key + " ");
            } else {
                sortedTree.add(node.key);
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
        if (isNil(node) || node == null) {
            return false;
        } else if (node.key.equals(key)) {
            return true;
        } else if (node.key.compareTo(key) > 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    private Node<E> find(E key) {
        return findWithRoot(root, key);
    }

    private Node<E> findWithRoot(Node<E> node, E key) {
        if (isNil(node)) {
            return node;
        } else if (node.key.equals(key)) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return findWithRoot(node.left, key);
        } else {
            return findWithRoot(node.right, key);
        }
    }

    private void transplant(Node<E> node1, Node<E> node2) {
        System.out.println("hey transplant");
        if (isNil(node1.parent) || node1 == root) {
            root = node2;
            System.out.println("ana root");
        } else if (node1 == node1.parent.left) {
            node1.parent.left = node2;
        } else {
            node1.parent.right = node2;
        }
        node2.parent = node1.parent;
    }

    private void deleteNode(Node<E> node) {
        Node<E> y = node;
        int yColour = y.color;
        Node<E> x;
        if (isNil(node.left)) {
            x = node.right;
            transplant(node, node.right);
        } else if (isNil(node.right)) {
            x = node.left;
            transplant(node, node.left);
        } else {
            y = treeMinimum(node.right);
            yColour = y.color;
            x = y.right;
            if (y.parent == node) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node, y);

            y.left = node.left;
            y.left.parent = y;
            y.color = node.color;
        }
        if (yColour == BLACK) {
            System.out.println("Haroo7 l fixup");
            deleteFixUp(x);
        }
    }

    private Node<E> treeMinimum(Node<E> node) {
        if (!isNil(node.left)) {
            node = treeMinimum(node.left);
            return node;
        }
        return node;
    }

    private void deleteFixUp(Node<E> node) {
        Node<E> tempNode;
        while (node != root && node.color == BLACK) {
            if (node == node.parent.left) {
                tempNode = node.parent.right;
                if (tempNode.color == RED) {
                    tempNode.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    tempNode = node.parent.right;
                }
                if (tempNode.left.color == BLACK && tempNode.right.color == BLACK) {
                    tempNode.color = RED;
                    node = node.parent;
                } else if (tempNode.right.color == BLACK) {
                    tempNode.left.color = BLACK;
                    tempNode.color = RED;
                    rotateRight(tempNode);
                    tempNode = node.parent.right;
                } else {
                    tempNode.color = node.parent.color;
                    node.parent.color = BLACK;
                    tempNode.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else if (node == node.parent.right) {
                tempNode = node.parent.left;
                if (tempNode.color == RED) {
                    tempNode.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    tempNode = node.parent.left;
                }
                if (tempNode.right.color == BLACK && tempNode.left.color == BLACK) {
                    tempNode.color = RED;
                    node = node.parent;
                } else if (tempNode.left.color == BLACK) {
                    tempNode.right.color = BLACK;
                    tempNode.color = RED;
                    rotateLeft(tempNode);
                    tempNode = node.parent.left;
                } else {
                    tempNode.color = node.parent.color;
                    node.parent.color = BLACK;
                    tempNode.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    public void delete(E key) {
        Node<E> node;
        node = find(key);

        if (node == root && isNil(node.left) && isNil(node.right)) {
            treeSize--;
            root = null;
            return;
        }
        if (!isNil(node)) {
            deleteNode(node);
            treeSize--;

        } else {
            System.out.println("Can't Delete Node That Doesn't Exist");
        }
    }

}
