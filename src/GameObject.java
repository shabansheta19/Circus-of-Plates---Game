import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected float velX, velY;
	protected ID id;
	protected Color color;
	protected boolean collision;
	protected String playerStick, difficulty;

	GameObject(final int x, final int y, final ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		collision = false;
	}


	protected  abstract void tick();
	protected abstract void render(Graphics g);
	public abstract Rectangle getBound();

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(final int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(final int y) {
		this.y = y;
	}
	/**
	 * @return the velX
	 */
	public float getVelX() {
		return velX;
	}
	/**
	 * @param velX the velX to set
	 */
	public void setVelX(final float velX) {
		this.velX = velX;
	}
	/**
	 * @return the velY
	 */
	public float getVelY() {
		return velY;
	}
	/**
	 * @param velY the velY to set
	 */
	public void setVelY(final float velY) {
		this.velY = velY;
	}

}
