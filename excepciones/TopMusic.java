package excepciones;

import java.util.*;

import exceptions.AutorNoValidoException;
import exceptions.CancionYaExisteException;
import exceptions.FechaNoValidaException;
import exceptions.PosicionNoValidaException;
import exceptions.TituloNoValidoException;

/**
 * Top Music. Mejoremos la versión anterior, pero como conocemos QUÉ ES una
 * excepción, vamos a utilizarla. Los errores los controlaremos mediante las
 * siguientes excepciones: FechaNoValidaException CancionNoValidaException.
 * AutorNoValidoException PosicionNoValidaException Y recuerda: En caso de
 * error, el usuario ha de saber CON EXACTITUD cuál ha sido el problema. Error
 * al insertar la canción: título no es válido. Error al insertar la canción:
 * autor no es válido Error al borrar la canción: posición no válida Error al
 * bajar la canción: posición no válida. Error al subir la canción: la canción
 * está la primera. El método Cancion.getInstance() ya no es necesario. Sigue
 * usando expresiones regulares Utiliza pruebas unitarias para el control de
 * errores.
 * 
 * El título de la canción: Me too, Don´t let me down, 19 días y 500 noches,
 * Come... El autor/grupo: The B-52's, Jain... El año: Nunca posterior al año
 * actual
 * 
 * @author Nieves Borrero
 * @version 1.0
 */
public class TopMusic {
	private ArrayList<Cancion> top;

	/**
	 * Constructor que crea una lista vacía
	 */
	TopMusic() {
		setTop(new ArrayList<Cancion>());
	}

	/**
	 * Obtiene Todas las canciones del top music
	 * 
	 * @return Todas las canciones del top music
	 */
	private ArrayList<Cancion> getTop() {
		return top;
	}

	private void setTop(ArrayList<Cancion> top) {
		this.top = top;
	}

	/**
	 * Añade una nueva canción al TopMusic en el index señalado.
	 * 
	 * @param index
	 * @param cancion
	 * @throws TituloNoValidoException
	 * @throws FechaNoValidaException
	 * @throws AutorNoValidoException
	 * @throws CancionYaExisteException
	 *             si la canción ya ha sido añadida a la lista.
	 * @throws PosicionNoValidaException
	 *             si la posición se sale de rango.
	 */
	void add(int index, String titulo, String artista, int fecha) throws AutorNoValidoException, FechaNoValidaException,
			TituloNoValidoException, CancionYaExisteException, PosicionNoValidaException {
		try {
			Cancion cancion = new Cancion(titulo, artista, fecha);
			if (getTop().contains(cancion))
				throw new CancionYaExisteException("La canción ya existe");
			getTop().add(index - 1, cancion);

		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}

	}

	/**
	 * Añade una cancion al final
	 * 
	 * @param cancion
	 * @throws TituloNoValidoException
	 * @throws FechaNoValidaException
	 * @throws AutorNoValidoException
	 */
	public boolean add(String titulo, String artista, int fecha)
			throws AutorNoValidoException, FechaNoValidaException, TituloNoValidoException {
		Cancion cancion = new Cancion(titulo, artista, fecha);
		return getTop().add(cancion);
	}

	/**
	 * Elimina un elemento del TopMusic
	 * 
	 * @param index
	 * @return true.
	 * @throws PosicionNoValidaException si la posición dada no existe
	 */
	boolean remove(int index) throws PosicionNoValidaException {
		try {
			getTop().remove(index - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}
		return true;
	}

	/**
	 * Sube la posición en el top (baja posición de índice)
	 * 
	 * @param index
	 * @return true si modifica la posicion.
	 * @throws PosicionNoValidaException si la posicion dada
	 * no existe o se sale de rango al subir de posicion.
	 */
	boolean up(int index) throws PosicionNoValidaException {
		try {
			getTop().add(index - 2, getTop().remove(index - 1));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}
		return true;
	}

	/**
	 * Baja la posición en el top (sube la posición de Índice)
	 * 
	 * @param index
	 * @return true si modifica la posicion
	 * @throws PosicionNoValidaException si la posicion dada
	 * no existe o se sale de rango al bajar de posicion.
	 */
	boolean down(int index) throws PosicionNoValidaException {
		try {
			getTop().add(index, getTop().remove(index - 1));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}
		return true;
	}

	/**
	 * Saca fuera del top 10 una canción
	 * 
	 * @param index
	 * @throws PosicionNoValidaException si la posicion dada no 
	 * existe.
	 */
	boolean pullOut(int index) throws PosicionNoValidaException {
		if (top.size() < 10)
			return false;
		try {
			top.add(10, getTop().remove(index - 1));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}
		return true;
	}

	/**
	 * devuelve la canción con la primera posición.
	 * 
	 * @return una canción
	 */
	Cancion top() {
		if (!isEmpty())
			return getTop().get(0);
		return null;
	}

	/**
	 * Verifica si la lista esta vacia o no
	 * 
	 * @return boolean
	 */
	boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public String toString() {
		if (!isEmpty()) {
			StringBuilder cadena = new StringBuilder();
			int tamanno = 10;
			if (getTop().size() < 10)
				tamanno = getTop().size();
			for (int i = 0; i < tamanno; i++) {
				cadena.append("(" + (i + 1) + ")" + getTop().get(i) + "\n");
			}
			return "TOPMUSIC:\n" + cadena;
		}
		return "";

	}

}
