package eXist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Ejercicio5 {

	public static void main(String[] args) throws IOException {
		try{
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");
			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;
			XQPreparedExpression consulta2;
			XQResultSequence resultado2;
			String zona;
			
			System.out.println("Numero de zona: ");
			Scanner scanner = new Scanner(System.in);
			zona = scanner.nextLine();

			consulta = conn.prepareExpression ("xquery version \"3.1\";\r\n" + 
					"for $zona in doc('nueva/zonas.xml')/zonas/zona[cod_zona = "+ zona + "]\r\n" + 
					"for $product in doc('nueva/productos.xml')/productos/produc[cod_zona = " + zona + "]\r\n" + 
					"return ($product/cod_prod, $product/denominacion, $product/precio, $zona/nombre, $zona/director)");
			resultado = consulta.executeQuery();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("zona" + zona + ".xml"));
			
			writer.write("<?xml version='1.0' encoding='UTF-8'?>");
			writer.newLine();
			
			while (resultado.next()) {
				String cad = resultado.getItemAsString(null);
				System.out.println(cad);
				writer.write(cad);
				writer.newLine();
			}
			writer.close();
		} catch (XQException ex) {System.out.println("Error al operar " + ex.getMessage());}

	}

}
