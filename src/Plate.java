import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Plate extends GameObject {
  private final Color colorArr[] = { Color.BLUE, Color.WHITE, Color.GREEN,
      Color.RED };
  private final Random rand;
  private final ID direction;
  private final ObjectsHandler handler;

  Plate(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty) {
    super(x, y, ID.Plate);
    if (difficulty == "easy") {
      velX = 3;
      velY = 3;
    } else if (difficulty == "medium") {
      velX = 5;
      velY = 5;
    } else if (difficulty == "hard") {
      velX = 7;
      velY = 10;
    }
    rand = new Random();
    color = colorArr[rand.nextInt(4)];
    this.direction = direction;
    this.handler = handler;
  }

  @Override
  protected void tick() {
    if (!collision) {
      if (direction == ID.LeftUp && x < 550) {
        x += velX;
      } else if (direction == ID.RightUp && x > 650) {
        x -= velX;
      } else if (direction == ID.LeftDown && x < 350) {
        x += velX;
      } else if (direction == ID.RightDown && x > 800) {
        x -= velX;
      } else {
        y += velY;
      }
      if (y > 750) {
        handler.removeObject(this);
      }
    } else {
      if (playerStick.equals("player1Stick1")) {
        x = Menu.clown1.x;
      } else if (playerStick.equals("player1Stick2")) {
        x = Menu.clown1.x + 93;
      } else if (playerStick.equals("player2Stick1")) {
        x = Menu.clown2.x;
      } else if (playerStick.equals("player2Stick2")) {
        x = Menu.clown2.x + 93;
      }
    }
  }

  @Override
  protected void render(final Graphics g) {
//    g.setColor(Color.black);
//    g.fillRect(x, y, 42, 17);
    g.setColor(color);
    g.fillRect(x, y, 42, 17);
//    g.fillRect(x + 1, y + 1, 42 - 2, 17 - 2);
  }

  @Override
  public Rectangle getBound() {
    return new Rectangle(x, y, 42, 17);
  }

}
