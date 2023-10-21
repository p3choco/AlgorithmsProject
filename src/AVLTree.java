import java.awt.*;

class AVLTree {
  public SVGNode rootNode;
  public AVLTree() {
    rootNode = null;
  }

  public void removeAll() {
    rootNode = null;
  }

  public boolean checkEmpty() {
    if (rootNode == null) return true;
    else return false;
  }

  public void insertElement(int element, Graphics g, MyFrame m) throws InterruptedException {
    rootNode = insertElement(element, rootNode, g, m, 0, null);
    Thread.sleep(1000);
    m.clear(g);
    redrawTree(rootNode, g, m, 0);
  }

  private int getHeight(SVGNode node) {
    return node == null ? -1 : node.h;
  }

  private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
    return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
  }

  private SVGNode insertElement(
      int element, SVGNode node, Graphics g, MyFrame m, int pos, SVGNode parent) {
    if (node == null) {
      node = new SVGNode(element);
      if (pos == 0) {
        node.nodeX = 800;
        node.nodeY = 100;
        node.nodeDeph = 0;
        m.drawKnob(g, element, node.nodeX, node.nodeY, node.nodeX, node.nodeY);
      } else {
        node.parent = parent;
        node.nodeDeph = parent.nodeDeph + 1;
        node.nodeY = parent.nodeY + 100;

        if (pos == -1) {
          node.nodeX = parent.nodeX - 800 / (int) Math.pow(2, node.nodeDeph);
        } else {
          node.nodeX = parent.nodeX + 800 / (int) Math.pow(2, node.nodeDeph);
        }
        m.drawKnob(g, element, node.nodeX, node.nodeY, parent.nodeX, parent.nodeY);
      }

    } else if (element < node.element) {
      node.leftChild = insertElement(element, node.leftChild, g, m, -1, node);
      if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2)
        if (element < node.leftChild.element) {
          node = rotateWithLeftChild(node);
        } else {
          node = doubleWithLeftChild(node);
          node.leftChild.parent = node;
        }
    } else if (element > node.element) {
      node.rightChild = insertElement(element, node.rightChild, g, m, 1, node);
      if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2)
        if (element > node.rightChild.element) {
          node = rotateWithRightChild(node);
        } else {
          node = doubleWithRightChild(node);
          node.rightChild.parent = node;
        }
    } else
      ;
    node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
    return node;
  }

  private SVGNode rotateWithLeftChild(SVGNode node2) {
    if (node2 == null || node2.leftChild == null) {
      return null;
    }
    SVGNode node1 = node2.leftChild;
    node2.leftChild = node1.rightChild;
    if (node1.rightChild != null) node1.rightChild.parent = node2;
    node1.parent = node2.parent;
    node1.rightChild = node2;
    node2.parent = node1;
    node2.h = getMaxHeight(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
    node1.h = getMaxHeight(getHeight(node1.leftChild), node2.h) + 1;
    return node1;
  }

  private SVGNode rotateWithRightChild(SVGNode node1) {
    if (node1 == null || node1.rightChild == null) {
      return null;
    }

    SVGNode node2 = node1.rightChild;
    node1.rightChild = node2.leftChild;
    if (node2.leftChild != null) node2.leftChild.parent = node1;
    node2.parent = node1.parent;
    node2.leftChild = node1;
    node1.parent = node2;
    node1.h = getMaxHeight(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
    node2.h = getMaxHeight(getHeight(node2.rightChild), node1.h) + 1;
    return node2;
  }
  private SVGNode doubleWithLeftChild(SVGNode node3) {
    node3.leftChild = rotateWithRightChild(node3.leftChild);
    if (node3.leftChild != null) node3.leftChild.parent = node3;
    return rotateWithLeftChild(node3);
  }

  private SVGNode doubleWithRightChild(SVGNode node1) {
    node1.rightChild = rotateWithLeftChild(node1.rightChild);
    if (node1.rightChild != null) node1.rightChild.parent = node1;
    return rotateWithRightChild(node1);
  }
  public void redrawTree(SVGNode actual, Graphics g, MyFrame m, int pos) {
    if (actual != null) {
      if (actual == rootNode) {
        actual.nodeX = 800;
        actual.nodeY = 100;
        actual.nodeDeph = 0;
        m.drawKnob(g, actual.element, actual.nodeX, actual.nodeY, actual.nodeX, actual.nodeY);
      } else if (pos == -1) {
        actual.nodeDeph = actual.parent.nodeDeph + 1;
        actual.nodeY = actual.parent.nodeY + 100;
        actual.nodeX = actual.parent.nodeX - 800 / (int) Math.pow(2, actual.nodeDeph);
        m.drawKnob(
            g,
            actual.element,
            actual.nodeX,
            actual.nodeY,
            actual.parent.nodeX,
            actual.parent.nodeY);
      } else if (pos == 1) {
        actual.nodeDeph = actual.parent.nodeDeph + 1;
        actual.nodeY = actual.parent.nodeY + 100;
        actual.nodeX = actual.parent.nodeX + 800 / (int) Math.pow(2, actual.nodeDeph);
        m.drawKnob(
            g,
            actual.element,
            actual.nodeX,
            actual.nodeY,
            actual.parent.nodeX,
            actual.parent.nodeY);
      }

      if (actual.leftChild != null) {
        redrawTree(actual.leftChild, g, m, -1);
      }
      if (actual.rightChild != null) {
        redrawTree(actual.rightChild, g, m, 1);
      }
    }
  }

  int getBalance(SVGNode N) {
    if (N == null) return 0;
    return getHeight(N.leftChild) - getHeight(N.rightChild);
  }

  SVGNode minValueNode(SVGNode node) {
    SVGNode current = node;

    while (current.leftChild != null) current = current.leftChild;

    return current;
  }

  public void deleteNode(int key, Graphics g, MyFrame m) {
    this.rootNode = deleteNode(this.rootNode, key, g, m);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    m.clear(g);
    redrawTree(rootNode, g, m, 0);

  }

  public void searchElement(int key, Graphics g, MyFrame m) throws InterruptedException {
    if (rootNode == null) {
      m.noKnob(g, key);
    } else {
      SVGNode actual = rootNode;
      while (actual != null) {
        m.lightUp(g, actual.element, actual.nodeX, actual.nodeY);
        Thread.sleep(1000);
        m.lightDown(g, actual.element, actual.nodeX, actual.nodeY);
        if (actual.element == key) {
          m.found(g, actual.element, actual.nodeX, actual.nodeY);
          return;
        }
        actual = (actual.element > key) ? actual.leftChild : actual.rightChild;
      }
      m.noKnob(g, key);
    }
  }

  SVGNode deleteNode(SVGNode root, int key, Graphics g, MyFrame m) {

    if (root == null) return root;

    if (key < root.element) {
      root.leftChild = deleteNode(root.leftChild, key, g, m);
      if (root.leftChild != null) root.leftChild.parent = root;
    }

    else if (key > root.element) {
      root.rightChild = deleteNode(root.rightChild, key, g, m);
      if (root.rightChild != null) root.rightChild.parent = root;
    }

    else {

      if ((root.leftChild == null) || (root.rightChild == null)) {
        SVGNode temp = null;
        if (temp == root.leftChild) temp = root.rightChild;
        else temp = root.leftChild;

        if (temp == null) {
          temp = root;
          root = null;

        } else
        root = temp;
      } else {

        SVGNode temp = minValueNode(root.rightChild);

        root.element = temp.element;

        root.rightChild = deleteNode(root.rightChild, temp.element, g, m);
        if (root.rightChild != null) root.rightChild.parent = root;
      }
    }

    if (root == null) return root;

    root.h = getMaxHeight(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;

    int balance = getBalance(root);

    if (balance > 1 && getBalance(root.leftChild) >= 0) return rotateWithLeftChild(root);

    if (balance > 1 && getBalance(root.leftChild) < 0) {
      return doubleWithLeftChild(root);
    }

    if (balance < -1 && getBalance(root.rightChild) <= 0) return rotateWithRightChild(root);

    if (balance < -1 && getBalance(root.rightChild) > 0) {
      return doubleWithRightChild(root);
    }
    return root;
  }
}
