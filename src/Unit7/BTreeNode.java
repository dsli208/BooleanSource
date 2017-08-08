/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit7;

/**
 *
 * @author dsli
 */
public class BTreeNode {
    boolean isHead;
    int min;
    int[] numbers;
    BTreeNode[] nodeReferences;
    public BTreeNode(int b, boolean isHead) {
        this.isHead = isHead;
        if (b == 1 && isHead) {
            this.numbers = new int[b];
            this.nodeReferences = new BTreeNode[b + 1];
        }
    }
}
