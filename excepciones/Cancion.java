package excepciones;

import java.util.Calendar;

import exceptions.AutorNoValidoException;
import exceptions.FechaNoValidaException;
import exceptions.TituloNoValidoException;

/**
 * Clase para crear una canci�n haciendo uso de las expresiones regulares y de
 * las excepciones.
 * 
 * @author Nieves Borrero.
 * @version 1.0
 */
public class Cancion {
	private String titulo;
	private String artistaOgrupo;
	private int anio;
	private static final Calendar FECHA = Calendar.getInstance();
	private static final int ANIOACTUAL = FECHA.get(Calendar.YEAR);
	private static final String patron = "([a-zA-Z\\d������Ѵ,\\.]+\\s?){1,}";

	/**
	 * Constructor que crea una nueva canci�n si los argumentos dados cumplen
	 * las condiciones, de lo contrario, lanza una excepci�n.
	 * 
	 * @param titulo
	 * @param artistaOgrupo
	 * @param anio
	 * @throws CancionNoValidaException
	 * @throws AutorNoValidoException
	 * @throws FechaNoValidaException
	 * @throws TituloNoValidoException
	 */
	public Cancion(String titulo, String artistaOgrupo, int anio)
			throws AutorNoValidoException, FechaNoValidaException, TituloNoValidoException {
		setTitulo(titulo);
		setAutor(artistaOgrupo);
		setFecha(anio);
	}

	/**
	 * Asigna una fecha a la canci�n
	 * 
	 * @param anio
	 *            nuevo a�o
	 */
	private void setFecha(int anio) throws FechaNoValidaException {
		if (anio < 1900 || anio >= ANIOACTUAL)
			throw new FechaNoValidaException("Fecha no valida");
		this.anio = anio;
	}

	/**
	 * Asigna el t�tulo a la Canci�n
	 * 
	 * @param titulo
	 * 			nuevo t�tulo
	 * @throws CancionNoValidaException si no cumple el formato del t�tulo
	 */
	private void setTitulo(String titulo) throws TituloNoValidoException {
		if (!titulo.matches(patron))
			throw new TituloNoValidoException("T�tulo no v�lido. ");
		this.titulo = titulo;
	}

	/**
	 * Asigna el autor a la Canci�n
	 * 
	 * @param autor
	 *            nuevo autor
	 * @throws CancionNoValidaException si no cumple el formato del autor
	 */
	private void setAutor(String autor) throws AutorNoValidoException {
		if (!autor.matches("([a-zA-Z\\d������Ѵ,\\.]+\\s?){1,}"))
			throw new AutorNoValidoException("Autor no v�lido. ");

		this.artistaOgrupo = autor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		if (anio == 0) {
			if (other.anio != 0)
				return false;
		}
		if (artistaOgrupo == null) {
			if (other.artistaOgrupo != null)
				return false;
		} else if (!artistaOgrupo.equalsIgnoreCase(other.artistaOgrupo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equalsIgnoreCase(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "titulo:" + titulo + ", artista/grupo:" + artistaOgrupo + ", annio:" + anio;
	}

}
