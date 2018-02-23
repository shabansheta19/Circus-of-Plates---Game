import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter {

  private final ObjectsHandler handler;
  private boolean left = false;
  private boolean right = false;

  keyInput(final ObjectsHandler handler) {
    this.handler = handler;
  }

  @Override
  public void keyPressed(final KeyEvent e) {
    final int key = e.getKeyCode();
//     System.out.println(key);
    if (key == 32) {
      Game.pause = !Game.pause;
    }
    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.YellowClown) {
        if (key == 39) {
          handler.objects.get(i).setVelX(5);
          right = true;
        }
        if (key == 37) {
          handler.objects.get(i).setVelX(-5);
          left = true;
        }
      }
//      if (handler.objects.get(i).id == ID.Plate) {
//        if (handler.objects.get(i).collision) {
//          if (key == 39) {
//            handler.objects.get(i).setVelX(5);
//            right = true;
//          }
//          if (key == 37) {
//            handler.objects.get(i).setVelX(-5);
//            left = true;
//          }
//        }
//      }
    }
  }

  @Override
  public void keyReleased(final KeyEvent e) {
    final int key = e.getKeyCode();

    for (int i = 0; i < handler.objects.size(); i++) {
      if (handler.objects.get(i).id == ID.YellowClown) {
        if (key == 39) {
          right = false;
        }
        if (key == 37) {
          left = false;
        }
        if (!right && !left) {
          handler.objects.get(i).setVelX(0);
        }
      }
//      if (handler.objects.get(i).id == ID.Plate) {
//        if (handler.objects.get(i).collision) {
//          if (key == 39) {
//            right = false;
//          }
//          if (key == 37) {
//            left = false;
//          }
//          if (!right && !left) {
//            handler.objects.get(i).setVelX(0);
//          }
//        }
//      }
    }
  }
}
