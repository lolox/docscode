/**
 *  @author Manuel Velardo y Rafael González. Sencillo Cliente que develve una página web
 *  denominada "index.html"
 * 
 */

import java.net.*;
import java.io.*;

class MiniWebClient
{
	public static void main(String[] args) throws IOException
	{
	   
	    Socket s = new Socket("127.0.0.1", 8080);
	    BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    PrintStream salida = new PrintStream(s.getOutputStream());
	    salida.println("GET /index.html HTTP/1.0");
	    salida.println();
	    String str = null;
	    do
	    {
	        str = entrada.readLine();
	        if (str!= null)
	        {
	        System.out.println(str);
	        }
	    } while (str != null);
	    
	    entrada.close();
	    salida.close();
	    s.close();
	} 
	}