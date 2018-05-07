/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktrees;

/**
 *
 * @author ahmed
 * @param <E>
 */
public class Node<E extends Comparable<E>> {

    public static final int RED = 0;
    public static final int BLACK = 1;

    public int color;
    public E key = null;
    public Node<E> left = null;
    public Node<E> right = null;
    public Node<E> parent = null;
    public E value;

    public Node(E key) {
        color = RED;
        this.key = key;
    }

    public boolean isRightChild() {
        if (this == null) {
            return false;
        }
        return this == this.parent.right;
    }

    public boolean isLeftChild() {
        if (this.parent == null) {
            return false;
        }
        return this == this.parent.left;
    }

}
