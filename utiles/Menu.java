package utiles;

public class Menu {
	private String titulo;
	private String opciones[];
	private int opcion;
	private int salir;
	

	public Menu(String titulo, String[]opciones){
		setTitulo(titulo);
		setOpciones(opciones);
		setSalir(opciones.length);
	}
	
	public int getSalir() {
		return salir;
	}

	private void setSalir(int salir) {
		this.salir = salir;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	/**
	 * Permite mostrar el menï¿½
	 */

	public void mostrar(){
		System.out.println(getTitulo());
		for(int i=0; i<opciones.length; i++){
			System.out.println((i+1)+"."+getOpciones()[i]);
		}
	}
	/**
	 * Permite recoger una opción del menú
	 * @return entero introducido por teclado, que se corresponderï¿½ con una opciï¿½n del menï¿½
	 */
	int recogerOpcion(){
		int opcion;
		do{
			opcion=Teclado.leerEntero("Introduce una opcion entre 1 y "+opciones.length);
		}while(opcion>opciones.length+1||opcion<0);
		return opcion;
	}
	
	public int getOpcion() {
		return opcion;
	}


	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
/**
 * Gestiona el menú
 * @return número de opción recogida por teclado
 */

	public int gestionar(){
		mostrar();
		return recogerOpcion();
	}
	
}
