package project;

/**
 * Esta es la clase principal que utilizo para poner en marcha el sistema.
 * Mi objetivo con esta clase es actuar como el punto de entrada para 
 * iniciar la ejecución del servidor.
 * 
 * @author Pablo Rubio Prado
 * @version 1.0
 */
public class Servidor {

	/**
	 * Este es el método principal que se ejecuta al arrancar el programa.
	 * Aquí es donde decido instanciar mi objeto Server y ponerlo en funcionamiento.
	 * 
	 * @param args Argumentos que podrían pasarse por consola (no los uso actualmente).
	 */
	public static void main(String[] args) {
		// Aquí creo una nueva instancia de la clase Server para gestionar la lógica.
		Server servidor = new Server();
		
		// Una vez configurado, le ordeno al servidor que se quede esperando una conexión.
		servidor.esperaConexion();
	}

}