import java.awt.*;

public class BSTree {
  BSTNode root;

  public void insert(int key, Graphics g, MyFrame m) throws InterruptedException {
    if (root == null) {
      root = new BSTNode(key);
      root.nodeX = 800;
      root.nodeY = 100;
      root.nodeDeph = 0;
      m.drawKnob(g, key, root.nodeX, root.nodeY, root.nodeX, root.nodeY);
    } else {
      BSTNode actual = root;
      BSTNode parent = null;
      while (actual != null) {
        m.lightUp(g, actual.key, actual.nodeX, actual.nodeY);
        Thread.sleep(1000);
        m.lightDown(g, actual.key, actual.nodeX, actual.nodeY);
        parent = actual;
        actual = (actual.key > key) ? actual.left : actual.right;
      }
      if (parent.key > key) {
        parent.left = new BSTNode(key);
        parent.left.parent = parent;
        parent.left.nodeDeph = parent.nodeDeph + 1;
        parent.left.nodeX = parent.nodeX - 800 / (int) Math.pow(2, parent.left.nodeDeph);
        parent.left.nodeY = parent.nodeY + 100;
        m.drawKnob(g, key, parent.left.nodeX, parent.left.nodeY, parent.nodeX, parent.nodeY);
      } else {
        parent.right = new BSTNode(key);
        parent.right.parent = parent;
        parent.right.nodeDeph = parent.nodeDeph + 1;
        parent.right.nodeX = parent.nodeX + 800 / (int) Math.pow(2, parent.right.nodeDeph);
        parent.right.nodeY = parent.nodeY + 100;
        m.drawKnob(g, key, parent.right.nodeX, parent.right.nodeY, parent.nodeX, parent.nodeY);
      }
    }
  }

  public void delete(int key, Graphics g, MyFrame m) throws InterruptedException {
    int right = 0;
    if (root == null) {
      m.noKnob(g, key);
    } else {
      BSTNode actual = root;
      while (actual != null) {
        m.lightUp(g, actual.key, actual.nodeX, actual.nodeY);
        Thread.sleep(1000);
        m.lightDown(g, actual.key, actual.nodeX, actual.nodeY);

        if (actual.key == key) {

          BSTNode newActual;
          if (actual.right != null) {
            newActual = actual.right;

            while (newActual.left != null) {
              newActual = newActual.left;
            }
            actual.key = newActual.key;
            if (actual.right.left != null) {
              newActual.parent.left = newActual.right;
              if (newActual.right != null) newActual.right.parent = newActual.parent;
            } else {
              newActual.parent.right = null;
            }

          } else if (actual.left != null) {
            newActual = actual.left;
            while (newActual.right != null) {
              newActual = newActual.right;
            }
            actual.key = newActual.key;

            if (actual.left.right != null) {
              newActual.parent.right = newActual.left;
              if (newActual.left != null) newActual.left.parent = newActual.parent;
            } else {
              newActual.parent.left = null;
            }

          } else {
            if (right == 1) {
              actual.parent.left = null;
            } else if (right == 2) {
              actual.parent.right = null;
            } else {
              root = null;
            }
          }
          m.clear(g);
          redrawTree(root, g, m);
          return;
        }
        if (actual.key > key) {
          actual = actual.left;
          right = 1;
        } else {
          actual = actual.right;
          right = 2;
        }
      }
      m.noKnob(g, key);
    }
  }

  public void redrawTree(BSTNode actual, Graphics g, MyFrame m) {
    if (actual != null) {

      if (actual == root) {
        m.drawKnob(g, actual.key, actual.nodeX, actual.nodeY, actual.nodeX, actual.nodeY);
      } else {
        m.drawKnob(
            g, actual.key, actual.nodeX, actual.nodeY, actual.parent.nodeX, actual.parent.nodeY);
      }

      if (actual.left != null) {
        redrawTree(actual.left, g, m);
      }
      if (actual.right != null) {
        redrawTree(actual.right, g, m);
      }
    }
  }

  public void search(int key, Graphics g, MyFrame m) throws InterruptedException {
    if (root == null) {
      m.noKnob(g, key);
    } else {
      BSTNode actual = root;
      while (actual != null) {
        m.lightUp(g, actual.key, actual.nodeX, actual.nodeY);
        Thread.sleep(1000);
        m.lightDown(g, actual.key, actual.nodeX, actual.nodeY);
        if (actual.key == key) {
          m.found(g, actual.key, actual.nodeX, actual.nodeY);
          return;
        }
        actual = (actual.key > key) ? actual.left : actual.right;
      }
      m.noKnob(g, key);
    }
  }
}
