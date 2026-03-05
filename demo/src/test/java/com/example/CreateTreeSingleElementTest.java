package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CreateTreeSingleElementTest {

  @Test
  void shouldCreateRootNodeWhenElementIsNotNull() {
    IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();

    Node<Integer> rootNode = binaryTreeOps.createTree(6);

    assertEquals(6, rootNode.getValue());
    assertNull(rootNode.getLeft());
    assertNull(rootNode.getRight());
  }

  @Test
  void shouldReturnNullWhenElementIsNull() {
    IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();

    Node<Integer> rootNode = binaryTreeOps.createTree((Integer) null);

    assertNull(rootNode);
  }
}
