import processing.core.PApplet;

public class Ejecutable extends PApplet {

	Logica app;

	public static void main(String[] args) {
		PApplet.main("Ejecutable");
	}

	public void settings() {
		size(600, 700);

	}

	public void setup() {
		app = new Logica(this);
	}

	public void draw() {
		app.imagen();
	}

	public void mousePressed() {
		app.click();
	}

}
