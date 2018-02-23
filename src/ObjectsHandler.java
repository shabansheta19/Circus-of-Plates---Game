import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectsHandler{

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	protected  synchronized void tick() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
	}


	protected synchronized void render(final Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}

	public void addObject(final GameObject newObject) {
		objects.add(newObject);
	}

	public void removeObject(final GameObject Object) {
		objects.remove(Object);
	}


}





