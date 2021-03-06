import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;

public class RedClown extends GameObject{
  private final ImageIcon imageIcon;
  private final Image image;
  private final ObjectsHandler handler;
  private int numOfPlatesStick1, numOfPlatesStick2;
  private int score;
  private final Stack<Plate> leftStickPaltes = new Stack<Plate>();
  private final Stack<Plate> RightStickPaltes = new Stack<Plate>();
  private final Random rand;
  public static boolean maxPlates;

  RedClown(final int x, final int y, final ID id, final ObjectsHandler handler, final Game game) {
    super(x, y, ID.RedClown);
    imageIcon = new ImageIcon(getClass().getResource("clown2.PNG"));
    image = imageIcon.getImage();
    this.handler = handler;
    numOfPlatesStick1 = 0;
    numOfPlatesStick2 = 0;
    score = 0;
    rand = new Random();
    maxPlates = false;
  }

  @Override
  protected void tick() {
    if (x <= 0) {
      x = 0;
    }
    if (x >= 1200 - 135) {
      x = 1200 - 135;
    }
    x += velX;
    collision();
    checkConsecutivePlates();
  }

  @Override
  protected void render(final Graphics g) {
    g.setColor(Color.WHITE);
    g.drawImage(image, x, y, null);
    updateScore(g);
  }

  private void updateScore(final Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("Red Clown Score = " + score, 700, 20);
  }

  private void checkConsecutivePlates() {
    boolean leftCheckMaxPlates = false;
    boolean rightCheckMaxPlates = false;

    if (leftStickPaltes.size() >= 3) {
      final Plate plate1 = leftStickPaltes.pop();
      final Plate plate2 = leftStickPaltes.pop();
      final Plate plate3 = leftStickPaltes.pop();
      if(plate1.y <= 70) {
        leftCheckMaxPlates = true;
      }
      if (plate1.color == plate2.color && plate2.color == plate3.color) {
        handler.removeObject(plate1);
        handler.removeObject(plate2);
        handler.removeObject(plate3);
        numOfPlatesStick1 -= 3;
        score++;
      } else {
        leftStickPaltes.push(plate3);
        leftStickPaltes.push(plate2);
        leftStickPaltes.push(plate1);
      }
    }
    if (RightStickPaltes.size() >= 3) {
      final Plate plate1 = RightStickPaltes.pop();
      final Plate plate2 = RightStickPaltes.pop();
      final Plate plate3 = RightStickPaltes.pop();
      if(plate1.y <= 70) {
        rightCheckMaxPlates = true;
      }
      if (plate1.color == plate2.color && plate2.color == plate3.color) {
        handler.removeObject(plate1);
        handler.removeObject(plate2);
        handler.removeObject(plate3);
        numOfPlatesStick2 -= 3;
        score++;
      } else {
        RightStickPaltes.push(plate3);
        RightStickPaltes.push(plate2);
        RightStickPaltes.push(plate1);
      }
    }
    if(leftCheckMaxPlates && rightCheckMaxPlates) {
      maxPlates = true;
    }
  }

  private void collision() {
    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.Plate) {
        final GameObject currentObject = handler.objects.get(i);
        if (!currentObject.collision) {
          if (getBound().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player2Stick1";
            numOfPlatesStick1++;
            leftStickPaltes.push((Plate) currentObject);
          } else if (getBound2().intersects(currentObject.getBound())) {
            currentObject.collision = true;
            currentObject.playerStick = "player2Stick2";
            numOfPlatesStick2++;
            RightStickPaltes.push((Plate) currentObject);
          }
        }
      }
    }
  }

  @Override
  public Rectangle getBound() {
    final int heightOfPlate = 17;
    return new Rectangle(x, y - numOfPlatesStick1 * heightOfPlate, 35, 1);
  }

  public Rectangle getBound2() {
    final int heightOfPlate = 17;
    return new Rectangle(x + 90, y - numOfPlatesStick2 * heightOfPlate, 35, 1);
  }


  /**
   * @return the score
   */
  public int getScore() {
    return score;
  }
}
