package excepciones;

import java.util.*;

import exceptions.AutorNoValidoException;
import exceptions.CancionYaExisteException;
import exceptions.FechaNoValidaException;
import exceptions.PosicionNoValidaException;
import exceptions.TituloNoValidoException;

/**
 * Top Music. Mejoremos la versi�n anterior, pero como conocemos QU� ES una
 * excepci�n, vamos a utilizarla. Los errores los controlaremos mediante las
 * siguientes excepciones: FechaNoValidaException CancionNoValidaException.
 * AutorNoValidoException PosicionNoValidaException Y recuerda: En caso de
 * error, el usuario ha de saber CON EXACTITUD cu�l ha sido el problema. Error
 * al insertar la canci�n: t�tulo no es v�lido. Error al insertar la canci�n:
 * autor no es v�lido Error al borrar la canci�n: posici�n no v�lida Error al
 * bajar la canci�n: posici�n no v�lida. Error al subir la canci�n: la canci�n
 * est� la primera. El m�todo Cancion.getInstance() ya no es necesario. Sigue
 * usando expresiones regulares Utiliza pruebas unitarias para el control de
 * errores.
 * 
 * El t�tulo de la canci�n: Me too, Don�t let me down, 19 d�as y 500 noches,
 * Come... El autor/grupo: The B-52's, Jain... El a�o: Nunca posterior al a�o
 * actual
 * 
 * @author Nieves Borrero
 * @version 1.0
 */
public class TopMusic {
	private ArrayList<Cancion> top;

	/**
	 * Constructor que crea una lista vac�a
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
	 * A�ade una nueva canci�n al TopMusic en el index se�alado.
	 * 
	 * @param index
	 * @param cancion
	 * @throws TituloNoValidoException
	 * @throws FechaNoValidaException
	 * @throws AutorNoValidoException
	 * @throws CancionYaExisteException
	 *             si la canci�n ya ha sido a�adida a la lista.
	 * @throws PosicionNoValidaException
	 *             si la posici�n se sale de rango.
	 */
	void add(int index, String titulo, String artista, int fecha) throws AutorNoValidoException, FechaNoValidaException,
			TituloNoValidoException, CancionYaExisteException, PosicionNoValidaException {
		try {
			Cancion cancion = new Cancion(titulo, artista, fecha);
			if (getTop().contains(cancion))
				throw new CancionYaExisteException("La canci�n ya existe");
			getTop().add(index - 1, cancion);

		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException();
		}

	}

	/**
	 * A�ade una cancion al final
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
	 * @throws PosicionNoValidaException si la posici�n dada no existe
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
	 * Sube la posici�n en el top (baja posici�n de �ndice)
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
	 * Baja la posici�n en el top (sube la posici�n de �ndice)
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
	 * Saca fuera del top 10 una canci�n
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
	 * devuelve la canci�n con la primera posici�n.
	 * 
	 * @return una canci�n
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
