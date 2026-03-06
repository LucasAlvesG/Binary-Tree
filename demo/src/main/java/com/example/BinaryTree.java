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
    if (rootNode == null || nodeElement == null) {
      return null;
    }

    Node<T> currentNode = rootNode;

    // Busca o nó que contém nodeElement
    while (currentNode != null) {
      int compareResult = nodeElement.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        // Encontrou o nó, calcula o grau (número de filhos) tentando ir para direita e esquerda.
        int degree = 0;
        if (currentNode.getLeft() != null) {
          degree++;
        }
        if (currentNode.getRight() != null) {
          degree++;
        }
        return degree;
      }

      if (compareResult < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }
    }

    // Elemento não encontrado
    return null;
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
    // Valida entradas inválidas.
    if (rootNode == null || nodeElement == null || rootNode.getValue() == null) {
      return false;
    }

    // parentNode guarda o pai do currentNode durante a busca.
    Node<T> parentNode = null;
    Node<T> currentNode = rootNode;

    // Busca o nó que contém nodeElement.
    while (currentNode != null) {
      int compareResult = nodeElement.compareTo(currentNode.getValue());

      // Encontrou o nó que será removido ele sai do loop infinito.
      if (compareResult == 0) {
        break;
      }

      parentNode = currentNode;
      if (compareResult < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }
    }

    // Não encontrou o elemento na árvore.
    if (currentNode == null) {
      return false;
    }

    // Caso com 2 filhos: promove o filho da direita e
    // encaixa a subárvore da esquerda no nó mais à esquerda dele.
    if (currentNode.getLeft() != null && currentNode.getRight() != null) {
      Node<T> rightSubtree = currentNode.getRight();
      Node<T> leftSubtree = currentNode.getLeft();

      // Começa no topo da subárvore direita para caminhar até o nó mais à esquerda.
      Node<T> leftMostInRightSubtree = rightSubtree;
      while (leftMostInRightSubtree.getLeft() != null) {
        leftMostInRightSubtree = leftMostInRightSubtree.getLeft();
      }
      
      leftMostInRightSubtree.setLeft(leftSubtree);
      // basicamente ele pegou o nó mais esquerda da subarvore direita 
      // e colocou a subarovre esquerda como filho dele.

      // Se o alvo é a raiz, copia os dados da nova raiz para o objeto raiz atual.
      if (parentNode == null) {
        rootNode.setValue(rightSubtree.getValue());
        rootNode.setLeft(rightSubtree.getLeft());
        rootNode.setRight(rightSubtree.getRight());
        return true;
      }

      // Reconecta o pai para apontar para a nova raiz da subárvore.
      if (parentNode.getLeft() == currentNode) {
        parentNode.setLeft(rightSubtree);
      } else {
        parentNode.setRight(rightSubtree);
      }

      return true;
    }

    // Nó com 0 ou 1 filho: pega o único filho (ou null).
    Node<T> childNode = currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight();

    // Remoção da raiz.
    if (parentNode == null) {
      // Raiz sem filhos: limpa o conteúdo do nó raiz.
      if (childNode == null) {
        rootNode.setValue(null);
        rootNode.setLeft(null);
        rootNode.setRight(null);
        return true;
      }

      // Raiz com 1 filho: copia os dados do filho para a raiz.
      rootNode.setValue(childNode.getValue());
      rootNode.setLeft(childNode.getLeft());
      rootNode.setRight(childNode.getRight());
      return true;
    }

    // Religa o pai ao filho do nó removido.
    if (parentNode.getLeft() == currentNode) {
      parentNode.setLeft(childNode);
    } else {
      parentNode.setRight(childNode);
    }

    return true;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null || rootNode.getValue() == null) {
      return null;
    }
    Node<T> parentNode = null;
    Node<T> currentNode = rootNode;
    
    while (currentNode.getLeft() != null && currentNode.getRight() != null ) {
      int compareResult = nodeElement.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        break;
      } 
      if (compareResult < 0) {
        parentNode = currentNode;
        currentNode = currentNode.getLeft();
      } else {
        parentNode = currentNode;
        currentNode = currentNode.getRight();
      }
      
    }
    return parentNode;
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null || rootNode.getValue() == null) {
      return null;
    }
    boolean leftOrRight = true;
    Node<T> parentNode = null;
    Node<T> brotherNode = null;
    Node<T> currentNode = rootNode;
    
    while (true) {
      int compareResult = nodeElement.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        if (parentNode != null) {
          if (leftOrRight) {
            brotherNode = parentNode.getRight();
          } else {
            brotherNode = parentNode.getLeft();
          }
        }
        break;
      } 
      if (compareResult < 0) {
        parentNode = currentNode;
        currentNode = currentNode.getLeft();
        leftOrRight = true;
      } else {
        parentNode = currentNode;
        currentNode = currentNode.getRight();
        leftOrRight = false;
      }
      
    }
    return brotherNode;
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    if (rootNode == null || element == null) {
      return null;
    }

    Node<T> currentNode = rootNode;
    while (currentNode != null) {
      int compareResult = element.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        return currentNode;
      }

      if (compareResult < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }
    }

    return null;
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    // Se a raiz é nula, a profundidade é 0 (árvore vazia)
    if (rootNode == null) {
      return 0;
    }

    // Se o nó é uma folha (sem filhos), a profundidade é 0
    if (rootNode.getLeft() == null && rootNode.getRight() == null) {
      return 0;
    }

    // Calcula a profundidade da subárvore esquerda
    int leftDepth = calculateTreeDepth(rootNode.getLeft());
    // Calcula a profundidade da subárvore direita
    int rightDepth = calculateTreeDepth(rootNode.getRight());

    // basicamente ele entra em recursividade ate chegar no nó mais profundo da árvore, 
    // e depois vai retornando o valor da profundidade para os nós anteriores que vão 
    // se somando 1 a cada nível, até chegar na raiz que vai retornar a profundidade total da árvore.
    //     6    
    //    / \
    //   2   8
    //  / \
    // 1   4  o Math.max funcionaria aqui onde o nó da esquerda retorna 0 e o da direita retorna 1
    //    /   e ele vai pegar o maior para fazer a soma.
    //   3
    return 1 + Math.max(leftDepth, rightDepth);
  }

  @Override
  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null) {
      return null;
    }

    Node<T> currentNode = rootNode;
    int level = 0;

    while (currentNode != null) {
      int compareResult = nodeElement.compareTo(currentNode.getValue());

      if (compareResult == 0) {
        return level;
      }

      if (compareResult < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }

      level++;
    }

    return null;
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

    // a ideia aqui é descobrir o Depth da arvore e verificar qual seria o numero de 
    // elementos para que uma arvore dessa profundidade seja completa se o numero de nodes ou elementos
    // for menor doq o correto quer dizer 
    if (rootNode == null) return true;
    int depth = calculateTreeDepth(rootNode);
    int maxNodes = (int) (Math.pow(2, depth) - 1);
    int actualNodes = countNodes(rootNode);
    if (actualNodes < maxNodes) {
      return false;
    } else {
      return true;
    }
  }

  private int countNodes(Node<T> node) {
    if (node == null) return 0;
    return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
  }

}