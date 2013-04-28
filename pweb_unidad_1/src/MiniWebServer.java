/**
 * Segundo intento de práctica de Manuel Velardo y Rafael calvo. Subsanados los erres de concepto
 * apuntados por el profesor. thx
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;



public class MiniWebServer extends Thread{

    Socket socket;
	
	public MiniWebServer( Socket s ) {
		socket = s;	
	}
    public static final int puerto =8080;

    public void run(){
	
	 byte[] buffer = new byte[1024];
	 int bytes;

	try{
		
	  BufferedReader lee = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	  PrintWriter escribe = new PrintWriter(socket.getOutputStream());
	  StringTokenizer tokens = new StringTokenizer(lee.readLine());      
      //recorremos los tokens recibidos
      tokens.nextToken(); //método HTTP
      
      String archivo = "." + tokens.nextToken(); // parámetro correspondiente al fichero
      System.out.println(archivo);
      
      FileInputStream fich = null; // comprobamos si existe
      boolean existe = true;	
      
      try 
      
      {
      	fich = new FileInputStream(archivo);
             	
      }
      catch (FileNotFoundException e) {
      
      	existe = false;
      	
      }
      escribe.println("Content-Type: text/html");	
      if (existe && archivo.length()>2)
      	
  		  while((bytes = fich.read(buffer)) != -1 ) // enviar archivo
  	
  			socket.getOutputStream().write(buffer, 0, bytes);
  	
  	    else escribe.println("<h1>404 No encontrado</h1>");
  	
  	  escribe.close();
      socket.close();
         
          
		
	}catch (IOException e) {
	    e.printStackTrace();
	}
	
}
	
public static void main(String[] args) throws IOException{
	ServerSocket webserver = new ServerSocket (puerto);
	while (true){
		Socket aux = webserver.accept();
		MiniWebServer hiloServidor = new MiniWebServer(aux);
		hiloServidor.start();
	}
}
}