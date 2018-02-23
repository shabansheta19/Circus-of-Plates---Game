import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable {
  public static final int WIDTH = 1200;
  public static boolean pause = false;
  private final int HEIGHT = 700;
  private Thread thread;
  private boolean running = false;
  private final ObjectsHandler handler;
  private final Menu menu;
  private final ImageIcon imageIcon, imageIcon2;
  private final Image image, image2;

  public Game() {
    handler = new ObjectsHandler();
    menu = new Menu(this, handler);
    this.addKeyListener(new keyInput(handler));
    this.addMouseListener(menu);
    this.addMouseMotionListener(new MouseMove(handler));
    new Window(WIDTH, HEIGHT, "Let's Play a Game 2", this);
    imageIcon = new ImageIcon(getClass().getResource("clown.PNG"));
    image = imageIcon.getImage();
    imageIcon2 = new ImageIcon(getClass().getResource("clown2.PNG"));
    image2 = imageIcon2.getImage();
  }

  public enum STATE {
    Menu, Game, SelectDiffeculty, GameOver
  };

  STATE gameState = STATE.Menu;

  public void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public void stop() {
    try {
      thread.join();
      running = false;
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Game loop
   */
  @Override
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    final double amountOfTicks = 60.0;
    final double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    while (running) {

      final long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      while (delta >= 1) {
        if (!pause && gameState != STATE.GameOver) {
          handler.tick();
        }
        delta--;
      }
      if (running && !pause) {
        render();
      }
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
      }
    }
    stop();
  }

  private void render() {
    final BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    final Graphics g = bs.getDrawGraphics();
    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);
//    System.out.println(gameState);
    if (gameState == STATE.Game) {
      handler.render(g);
    } else if (gameState == STATE.Menu || gameState == STATE.SelectDiffeculty) {
//      System.out.println("MENU");
      menu.render(g);
    } else if (gameState == STATE.GameOver) {
      g.setColor(Color.WHITE);
      g.drawRect(525, 350, 150, 64);
      g.drawString("Back to Menu", 570, 395);
      g.setFont(new Font("Arial", 1, 30));
      showResult(g);
    }
    g.dispose();
    bs.show();

  }
  public void showResult(final Graphics g){
    if (Menu.clown1.getScore() > Menu.clown2.getScore()) {
      g.drawString("Yellow Clown Won With Score " + Menu.clown1.getScore(),
          400, 100);
      g.drawImage(image, 600 - 67, 450, null);
    } else if (Menu.clown1.getScore() < Menu.clown2.getScore()) {
      g.drawString("Red Clown Won With Score : " + Menu.clown2.getScore(),
          400, 100);
      g.drawImage(image2, 600 - 67, 450, null);
    } else {
      g.drawString("             Tie!", 400, 100);
    }
  }
  public static void main(final String args[]) {
    new Game();
  }
}
