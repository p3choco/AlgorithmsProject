import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Scanner keyboard = new Scanner(System.in);
    MyFrame m = new MyFrame();

  int dec = 0;
    do {
    System.out.println("Hello, ");
    System.out.println("if you want to make BST choose 1");
    System.out.println("if you want to make AVL tree choose 2");
    System.out.println("if you want to end program 0");
    dec = keyboard.nextInt();
    if(dec == 1)
    {
      int checker;
      int elem;
      BSTree tree = new BSTree();
      System.out.println("Ok, basic BST");
      do {
        System.out.println("What do you want to do?");
        System.out.println("1 - Insert elemetnt");
        System.out.println("2 - Search Element");
        System.out.println("3 - Delete Element");
        System.out.println("0 - Go back to tree type choosing");
        checker = keyboard.nextInt();
        if(checker == 1) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.insert(elem, m.getGraphics(),  m);
        }
        if(checker == 2) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.search(elem, m.getGraphics(),  m);
        }
        if(checker == 3) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.delete(elem, m.getGraphics(),  m);
        }
        if(checker == 0 )
        {
          m.clear(m.getGraphics());
        }
        if(checker != 1 && checker!= 2 && checker != 3 && checker != 0) {
          System.out.println("Entered value is on equal to 1 or 2 or 3 or 0, please try again");
        }
      }while(checker != 0);
    }
    if(dec == 2)
    {
      AVLTree tree = new AVLTree();
      int checker;
      int elem;
      System.out.println("Uhh, AVL Tree");
      do {
        System.out.println("What do you want to do?");
        System.out.println("1 - Insert elemetnt");
        System.out.println("2 - Search Element");
        System.out.println("3 - Delete Element");
        System.out.println("0 - Go back to tree type choosing");
        checker = keyboard.nextInt();
        if(checker == 1) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.insertElement(elem, m.getGraphics(),  m);
        }
        if(checker == 2) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.searchElement(elem , m.getGraphics(), m);
        }
        if(checker == 3) {
          System.out.println("Now choose the element to perform the action");
          elem = keyboard.nextInt();
          tree.deleteNode(elem, m.getGraphics(),  m);
        }
        if(checker == 0 )
        {
          m.clear(m.getGraphics());
        }
        if(checker != 1 && checker!= 2 && checker != 3 && checker != 0) {
          System.out.println("Entered value is on equal to 1 or 2 or 3 or 0, please try again");
        }
      }while(checker != 0);

    }
    if(dec != 1 && dec != 2 && dec != 0)
    {
      System.out.println("Entered value is on equal to 1 or 2 or 0, please try again");
    }
  } while(dec != 0);
    System.out.println("Program has Ended");
}

}

