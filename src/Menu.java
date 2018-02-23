import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.MenuEvent;

public class Menu extends MouseAdapter {

  private final Game game;
  private final ObjectsHandler handler;
  public static YellowColwn clown1;
  public static RedClown clown2;

  Menu(final Game game, final ObjectsHandler handler) {
    this.game = game;
    this.handler = handler;
  }

  @Override
  public void mousePressed(final MouseEvent e) {
    final int mouseX = e.getX();
    final int mouseY = e.getY();
    // play again
    if (game.gameState == game.gameState.GameOver) {
      if (isOver(mouseX, mouseY, 150, 64, 525, 350)) {
        game.gameState = game.gameState.Menu;
        return;
      }
    }
    if (isOver(mouseX, mouseY, 150, 64, 525, 350)
        && game.gameState == game.gameState.Menu) {
      game.gameState = game.gameState.SelectDiffeculty;
      return;
    }

    if (game.gameState == game.gameState.SelectDiffeculty) {
      // easy
      if (isOver(mouseX, mouseY, 150, 64, 525, 350)) {
        clown1 = new YellowColwn(200, 450, ID.YellowClown, handler, game);
        clown2 = new RedClown(600, 450, ID.RedClown, handler, game);
        handler.addObject(clown1);
        handler.addObject(clown2);
        game.gameState = game.gameState.Game;
        clown1.difficulty = "easy";
      }
      // medium
      if (isOver(mouseX, mouseY, 150, 64, 525, 434)) {
        clown1 = new YellowColwn(200, 450, ID.YellowClown, handler, game);
        clown2 = new RedClown(600, 450, ID.RedClown, handler, game);
        handler.addObject(clown1);
        handler.addObject(clown2);
        game.gameState = game.gameState.Game;
        clown1.difficulty = "medium";
      }
      // hard
      if (isOver(mouseX, mouseY, 150, 64, 525, 518)) {
        clown1 = new YellowColwn(200, 450, ID.YellowClown, handler, game);
        clown2 = new RedClown(600, 450, ID.RedClown, handler, game);
        handler.addObject(clown1);
        handler.addObject(clown2);
        game.gameState = game.gameState.Game;
        clown1.difficulty = "hard";
      }
    }
  }

  private boolean isOver(final int mouseX, final int mouseY, final int width,
      final int height, final int x, final int y) {
    if (mouseX > x && mouseX < x + width) {
      if (mouseY > y && mouseY < y + height) {
        return true;
      } else
        return false;
    } else
      return false;
  }

  public void mouseReleased(final MenuEvent e) {

  }

  public void render(final Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("arial", 50, 30));
    if (game.gameState == game.gameState.Menu) {
      g.drawRect(525, 350, 150, 64);
      g.drawString("Play", 570, 395);
    } else if (game.gameState == game.gameState.SelectDiffeculty) {
      g.drawRect(525, 350, 150, 64);
      g.drawString("Easy", 570, 395);
      g.drawRect(525, 434, 150, 64);
      g.drawString("Medium", 550, 475);
      g.drawRect(525, 518, 150, 64);
      g.drawString("Hard", 570, 560);
    }

  }

}
