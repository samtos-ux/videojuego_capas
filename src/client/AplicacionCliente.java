package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class AplicacionCliente {
	// IP y Puerto a la que nos vamos a conectar
	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {
		System.out.println("*****************************************");
		System.out.println("************ APP CALCULADORA ************");
		System.out.println("*****************************************");

		Socket socketCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

		Scanner sc = new Scanner(System.in);
		try {
			socketCliente = new Socket();
			socketCliente.connect(direccionServidor);
			System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);

			entrada = new InputStreamReader(socketCliente.getInputStream());// entrada de datos del servidor (from)
			salida = new PrintStream(socketCliente.getOutputStream());// salida de datos al servidor(to)

			System.out.println("CLIENTE: Introduzca la operación a realizar:");
			System.out.println("1 - Sumar\n2 - Restar-\n3 - Multiplicar\n4 - Dividir\n5 - Salir");
			String numero1;
			String numero2;
			String operandos;
			String strOperacion = sc.next();
			switch (strOperacion) {
			case "1":
				System.out.println("CLIENTE: Introduzca los numeros a sumar star");
				numero1 = sc.nextLine();// aqui se queda parada la app hasta que intro datos
				numero2 = sc.nextLine();
				operandos = numero1 + "+" + numero2;
				salida.println(operandos); // 3+4
				break;
			case "2":
				System.out.println("CLIENTE: Introduzca los numeros a restar");
				numero1 = sc.nextLine();// aqui se queda parada la app hasta que intro datos
				numero2 = sc.nextLine();
				operandos = numero1 + "-" + numero2;
				salida.println(operandos);// 3-4
				break;
			case "3":
				System.out.println("CLIENTE: Introduzca los numeros a multiplicar");
				numero1 = sc.nextLine();// aqui se queda parada la app hasta que intro datos
				numero2 = sc.nextLine();
				operandos = numero1 + "*" + numero2;
				salida.println(operandos);// 3*4
				break;
			case "4":
				System.out.println("CLIENTE: Introduzca los numeros a dividir");
				numero1 = sc.nextLine();// aqui se queda parada la app hasta que intro datos
				numero2 = sc.nextLine();
				operandos = numero1 + "/" + numero2;
				salida.println(operandos);// 3/4
				break;
			case "5":
				System.out.println("SALIENDO ...");
				break;
			default:
				System.out.println("No has elegido ninguna opcion válida");
				break;
			}

			// esta clase nos ayuda a leer datos del servidor linea a linea
			BufferedReader bf = new BufferedReader(entrada);
			// En la siguiente linea se va a quedar parado el hilo principal
			// de ejecución hasta que el servidor responda
			String resultado = bf.readLine();// Hola soy el servidor, te reenvio la suma:7

			System.out.println("CLIENTE: " + resultado);// resultado:7
			String[] arrayResultado = resultado.split(":");

			System.out.println("CLIENTE: El resultado de la suma es: " + arrayResultado[1]);// 7
		} catch (Exception e) {
			System.err.println("Error: " + e);
			e.printStackTrace();
		} finally {// ES MUYYY IMPORTANTE QUE EN LOS SOCKETS SIEMPRE SE CIERREN LAS CONEXIONES
			try {
				salida.close();
				entrada.close();
				socketCliente.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
