import processing.core.PApplet;
import processing.core.PImage;

public class Logica extends Thread {

	private PApplet app;
	private PImage CA, CARecurso;
	private boolean empezar = false;
	private int contador = 5;

	public Logica(PApplet app) {
		super();
		this.app = app;
		CA = app.loadImage("../data/CA.jpg");
		CARecurso = app.loadImage("../data/CA.jpg"); // esta imagen es solo para
														// tomar los pixeles y
														// poder reiniciar al
														// estado inicial
		start();

	}

	public void imagen() {

		app.image(CA, 0, 0);

		// se aplica el filtro despues de 5 segundos
		if (contador == 0) {
			filtro();
		}

		app.fill(255);
		app.textSize(40);
		app.text(contador, 10, 50);
	}

	public void filtro() {

		CARecurso.loadPixels();

		for (int x = 0; x < CARecurso.width; x++) {
			for (int y = 0; y < CARecurso.height; y++) {

				int pos = x + y * CARecurso.width;

				float r = app.red(CARecurso.pixels[pos]);
				float g = app.green(CARecurso.pixels[pos]);
				float b = app.blue(CARecurso.pixels[pos]);

				if (app.dist(x, y, 305, 100) > 80) {
					// agrega mas rojo
					if ((x > 0 && x < 40) || (x > 120 && x < 160) || (x > 240 && x < 280) || (x > 360 && x < 400)
							|| (x > 480 && x < 520) || (x > 600 && x < 640)) {
						CA.pixels[pos] = app.color(r + 30, g, b);
					}

					// agrega mas azul
					if ((x > 40 && x < 80) || (x > 160 && x < 200) || (x > 280 && x < 320) || (x > 400 && x < 440)
							|| (x > 520 && x < 560)) {
						CA.pixels[pos] = app.color(r, g, b + 30);
					}

					// agrega mas blanco
					if ((x > 80 && x < 120) || (x > 200 && x < 240) || (x > 320 && x < 360) || (x > 440 && x < 480)
							|| (x > 560 && x < 600)) {
						CA.pixels[pos] = app.color(r + 50, g + 50, b + 50);
					}
				}

				// coloca el borde negro
				if (app.dist(x, y, 305, 100) > 80 && app.dist(x, y, 305, 100) < 85) {
					CA.pixels[pos] = app.color(0);
				}
			}
		}
		CA.updatePixels();
	}

	public void run() {

		while (true) {
			System.out.println("corriendo");

			try {
				sleep(1000);

				if (empezar == true && contador >= 1) {
					contador--;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void click() {

		// condicional que permite reiniciar el conteo antes de que termine cada
		// vez que se da click
		if (contador <= 5 && contador > 0) {
			empezar = true;
			contador = 5;
		}

		// vuelve a la normalidad
		if (contador == 0) {
			empezar = false;
			contador = 5;

			CARecurso.loadPixels();

			for (int x = 0; x < CARecurso.width; x++) {
				for (int y = 0; y < CARecurso.height; y++) {

					int pos = x + y * CARecurso.width;

					float r = app.red(CARecurso.pixels[pos]);
					float g = app.green(CARecurso.pixels[pos]);
					float b = app.blue(CARecurso.pixels[pos]);

					CA.pixels[pos] = app.color(r, g, b);
				}
			}
			CA.updatePixels();
		}
	}

}
