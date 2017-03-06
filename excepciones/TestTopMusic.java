package excepciones;

import exceptions.AutorNoValidoException;
import exceptions.CancionYaExisteException;
import exceptions.FechaNoValidaException;
import exceptions.PosicionNoValidaException;
import exceptions.TituloNoValidoException;
/**
 * @author Nieves Borrero
 * @version 1.0
 */
import utiles.*;

public class TestTopMusic {
	static TopMusic topMusic = new TopMusic();

	public static void main(String[] args)
			throws TituloNoValidoException, AutorNoValidoException, FechaNoValidaException, PosicionNoValidaException {
		Menu menu = new Menu("OPCIONES:",
				new String[] { "añadir cancion", "Mostrar lista", "subir puesto de cancion", "Bajar puesto de cancion",
						"Sacar del Top 10", "Mostrar cancion en el puesto numero 1", "Eliminar una cancion", "salir" });
		deseaPregenerar();
		int opcion;
		do {
			switch (opcion = menu.gestionar()) {
			case 1:
				annadirCancion();
				break;
			case 2:
				mostrarTop();
				break;
			case 3:
				subirPosicion();
				break;
			case 4:
				bajarPosicion();
				break;
			case 5:
				sacarDelTop();
				break;
			case 6:
				topCancion();
				break;
			case 7:
				EliminarCancion();
				break;
			case 8:
				salir();
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
			}

		} while (opcion != menu.getSalir());
	}// main

	private static void salir() {
		System.out.println("Hasta otra");
	}

	private static void EliminarCancion() throws PosicionNoValidaException {
		if (!isEmpty()) {
			try {
				topMusic.remove(Teclado.leerEntero("Introduce el indice de la cancion"));
				System.out.println("Eliminada");
			} catch (PosicionNoValidaException e) {
				System.out.println(e.getMessage() + "\nImposible eliminar");
			}
		}
	}

	private static void topCancion() {
		if (!isEmpty())
			System.out.println(topMusic.top());
	}

	private static void sacarDelTop() throws PosicionNoValidaException {
		if (!isEmpty()) {
			try {
				if (topMusic.pullOut(Teclado.leerEntero("Introduce el indice de la cancion"))) {
					System.out.println("La cancion ha salido del top10");
				} else
					System.out.println("Imposible sacar del top10, no hay 10 canciones");
			} catch (PosicionNoValidaException e) {
				System.out.println(e.getMessage() + "\nImposible sacar del top10");
			}

		}
	}

	private static void bajarPosicion() throws PosicionNoValidaException {
		if (!isEmpty()) {
			try {
				topMusic.down(Teclado.leerEntero("Introduce el indice de la cancion"));
				System.out.println("La cancion ha bajado de posicion");
			} catch (PosicionNoValidaException e) {
				System.out.println(e.getMessage() + "\nImposible bajar la posicion");
				
			}
		}
	}

	private static void subirPosicion() throws PosicionNoValidaException {
		if (!isEmpty()) {
			try {
				topMusic.up(Teclado.leerEntero("Introduce el indice de la cancion"));
				System.out.println("La cancion ha subido de posicion");
			} catch (PosicionNoValidaException e) {
				System.out.println(e.getMessage() + "\nImposible subir la posicion");
			}
		}
	}

	private static void mostrarTop() {
		if (!isEmpty())
			System.out.println(topMusic);
	}

	private static boolean isEmpty() {
		if (topMusic.isEmpty())
			System.out.println("El top Music Esta vacio");
		return topMusic.isEmpty();
	}

	private static void annadirCancion() {
		try {
			int posicion;
			if (!isEmpty())
				posicion = Teclado.leerEntero("Introduce la posicion de la cancion a añadir:");
			else
				posicion = 1;
			topMusic.add((posicion), Teclado.leerCadena("Titulo:").trim(),
					Teclado.leerCadena("Artista o grupo:").trim(), Teclado.leerEntero("año:"));

			System.out.println("Cancion añadida a la lista");

		} catch (PosicionNoValidaException | AutorNoValidoException | FechaNoValidaException | TituloNoValidoException
				| CancionYaExisteException e) {
			System.out.println(e.getMessage() + "\nError al añadir la canción.");
		}

	}

	/**
	 * Pregunta al usuario si desea obtener un top music pregenerado
	 * 
	 * @throws FechaNoValidaException
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 */
	private static void deseaPregenerar()
			throws TituloNoValidoException, AutorNoValidoException, FechaNoValidaException {
		if (Teclado.deseaContinuar("Desea pregenerar la lista?")) {
			pregenerar();
		}
	}

	/**
	 * Añade canciones ya creadas al top music
	 * 
	 * @throws FechaNoValidaException
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidaException
	 */
	static void pregenerar() throws TituloNoValidoException, AutorNoValidoException, FechaNoValidaException {
		try {
			topMusic.add("Californication", "Red Hot Chili Peppers", 1999);
			topMusic.add("Thunderstruck", "ACDC", 1990);
			topMusic.add("American Woman", "Lenny Kravitz", 1999);
			topMusic.add("The Trooper", "Iron Maiden", 1983);
			topMusic.add("I Wish I Had an Angel", "Nightwish", 2004);
			topMusic.add("So Payaso", "Extremoduro", 1996);
			topMusic.add("Corazon de Mimbre", "Marea", 2000);
			topMusic.add("Estampida", "Ska p", 2002);
			topMusic.add("Hit the Road Jack", "Ray Charles", 1961);
			topMusic.add("House of the Rising Sun", "White Buffalo", 2011);
			topMusic.add("La Danza del Fuego", "Mago de Oz", 2000);
			topMusic.add("Alas de Cristal", "Avalanch", 2005);
			topMusic.add("Feo, Fuerte y Formal", "Loquillo", 2006);
		} catch (Exception e) {
			System.err.println("Aquí no debería entrarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		}
	}
}
