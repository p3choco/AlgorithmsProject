import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
  MyFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1600, 1600);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
  }

  public void drawKnob(Graphics g, int value, int x, int y, int pX, int pY) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(Color.black);
    g.drawLine(pX + 15, pY + 32, x + 15, y + 15);
    g2D.setColor(Color.pink);
    g2D.fillOval(x, y, 34, 34);
    g2D.setColor(Color.black);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
  }

  public void lightUp(Graphics g, int value, int x, int y) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(Color.yellow);
    g2D.fillOval(x, y, 34, 34);
    g2D.setColor(Color.black);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
  }

  public void lightDown(Graphics g, int value, int x, int y) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(Color.pink);
    g2D.fillOval(x, y, 34, 34);
    g2D.setColor(Color.black);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
  }

  public void found(Graphics g, int value, int x, int y) throws InterruptedException {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(Color.GREEN);
    g2D.fillOval(x, y, 34, 34);
    g2D.setColor(Color.black);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
    Thread.sleep(5000);
    g2D.setColor(Color.pink);
    g2D.fillOval(x, y, 34, 34);
    g2D.setColor(Color.black);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
  }

  public void deleteKnob(Graphics g, int value, int x, int y, int pX, int pY) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(getBackground());
    g.drawLine(pX + 15, pY + 32, x + 15, y + 15);
    g2D.fillOval(x, y, 34, 34);
    g2D.drawString(Integer.toString(value), x + 5, y + 22);
  }

  public void noKnob(Graphics g, int value) throws InterruptedException {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setColor(Color.red);
      g2D.setFont(new Font("Serif", Font.PLAIN, 40));
      g2D.drawString("BRAK WĘZŁA    " + Integer.toString(value) +"   W DRZEWIE "  , 500, 70);
      Thread.sleep(5000);
      g2D.setColor(getBackground());
      g2D.fillRect( 500, 40, 600, 40);
  }
  public void clear(Graphics g) {
       g.clearRect(0,0,2000,2000);
  }
}
