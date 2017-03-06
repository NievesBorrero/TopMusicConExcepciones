package exceptions;

public class PosicionNoValidaException extends Exception{
	public PosicionNoValidaException(){
		super("La posicion indicada no es valida");
	}
}
