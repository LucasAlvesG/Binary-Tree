package com.example;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

  @Override
  public Node<T> createTree(T element) {
    if (element == null) {
      return null;
    }

    Node<T> rootNode = new Node<>();
    rootNode.setValue(element);
    return rootNode;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    if (elements == null || elements.length == 0) {
      return null;
    }

    Node<T> rootNode = null;

    for (T element : elements) {
      if (element == null) {
        continue;
      }

      if (rootNode == null) {
        rootNode = createTree(element);
        continue;
      }

      Node<T> currentNode = rootNode;

      while (true) {
        int compareResult = element.compareTo(currentNode.getValue());
        // compareTo() retorna:
        // < 0: element e menor que o valor atual
        // == 0: sao iguais
        // > 0: element e maior que o valor atual

        if (compareResult == 0) {
          break;
        }

        if (compareResult < 0) {
          if (currentNode.getLeft() == null) {
            Node<T> leftNode = new Node<>();
            leftNode.setValue(element);
            currentNode.setLeft(leftNode);
            break;
          }

          currentNode = currentNode.getLeft();
          continue;
        }

        if (currentNode.getRight() == null) {
          Node<T> rightNode = new Node<>();
          rightNode.setValue(element);
          currentNode.setRight(rightNode);
          break;
        }

        currentNode = currentNode.getRight();
      }
    }

    return rootNode;
  }

  @Override
  public Integer degree(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'degree'");
  }

  @Override
  public void insert(Node<T> rootNode, T element) {
    if (rootNode == null || element == null) {
      return;
    }

    Node<T> currentNode = rootNode;

    while (true) {
      int compareResult = element.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        return;
      }

      if (compareResult < 0) {
        if (currentNode.getLeft() == null) {
          Node<T> leftNode = new Node<>();
          leftNode.setValue(element);
          currentNode.setLeft(leftNode);
          return;
        }

        currentNode = currentNode.getLeft();
        // aqui o continue vai fazer volte para o inicio do while 
        // com a nova raiz sendo a esquerda da original e vai fazer toda a comparação ate que
        // a a proxima posição seja nula(podendo assim ser inserida).
        continue;
      }

      if (currentNode.getRight() == null) {
        Node<T> rightNode = new Node<>();
        rightNode.setValue(element);
        currentNode.setRight(rightNode);
        return;
      }

      currentNode = currentNode.getRight();
      // mesma coisa do continue anterior(não precisa de continue pois já esta no fim do codigo)
      // , só que agora para a direita da raiz original.
    }
  }

  @Override
  public boolean remove(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFather'");
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBrother'");
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByElement'");
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateTreeDepth'");
  }

  @Override
  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateNodeLevel'");
  }

  @Override
  public String toString(Node<T> rootNode) {
    if (rootNode == null) {
      return "";
    }

    return "root:" + buildNodeString(rootNode);
  }

  private String buildNodeString(Node<T> node) {
    StringBuilder output = new StringBuilder();
    output.append(node.getValue()).append(" ");

    if (node.getLeft() != null || node.getRight() != null) {
      output.append("(");

      if (node.getLeft() != null) {
        output.append("left:").append(buildNodeString(node.getLeft()));
      }

      if (node.getRight() != null) {
        output.append("right:").append(buildNodeString(node.getRight()));
      }

      output.append(")");
    }

    return output.toString();
  }

  @Override
  public Boolean isComplete(Node<T> rootNode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isComplete'");
  }

}