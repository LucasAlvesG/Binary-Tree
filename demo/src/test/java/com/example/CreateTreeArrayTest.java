package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CreateTreeArrayTest {

  @Test
  void shouldCreateBinarySearchTreeFromArrayFollowingInsertionOrder() {
    IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
    Integer[] elements = new Integer[] { 6, 2, 8, 1, 4, 3 };

    Node<Integer> rootNode = binaryTreeOps.createTree(elements);

    assertEquals(6, rootNode.getValue());
    assertEquals(2, rootNode.getLeft().getValue());
    assertEquals(8, rootNode.getRight().getValue());
    assertEquals(1, rootNode.getLeft().getLeft().getValue());
    assertEquals(4, rootNode.getLeft().getRight().getValue());
    assertEquals(3, rootNode.getLeft().getRight().getLeft().getValue());
  }

  @Test
  void shouldReturnNullWhenArrayIsNullOrEmpty() {
    IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();

    assertNull(binaryTreeOps.createTree((Integer[]) null));
    assertNull(binaryTreeOps.createTree(new Integer[] {}));
  }

  @Test
  void shouldIgnoreNullAndDuplicatedValues() {
    IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
    Integer[] elements = new Integer[] { null, 6, 2, 2, 8, null };

    Node<Integer> rootNode = binaryTreeOps.createTree(elements);

    assertEquals(6, rootNode.getValue());
    assertEquals(2, rootNode.getLeft().getValue());
    assertEquals(8, rootNode.getRight().getValue());
    assertNull(rootNode.getLeft().getLeft());
    assertNull(rootNode.getLeft().getRight());
  }
}
