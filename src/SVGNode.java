class SVGNode {
  int element;
  int h;

  int nodeDeph;
  SVGNode parent;
  SVGNode leftChild;
  SVGNode rightChild;
  int nodeX;
  int nodeY;

  public SVGNode() {
    leftChild = null;
    rightChild = null;
    element = 0;
    h = 0;
  }

  public SVGNode(int element) {
    this.parent = null;
    leftChild = null;
    rightChild = null;
    this.element = element;
    h = 0;
  }
}
