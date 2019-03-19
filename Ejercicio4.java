package eXist;

import java.util.Scanner;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Ejercicio4 {

	public static void main(String[] args) {
		try{
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");
			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;
			String line;
			
			System.out.println("Tipo de departamento: ");
			Scanner scanner = new Scanner(System.in);
			line = scanner.nextLine();
			
			consulta = conn.prepareExpression ("for $dep in doc('nueva/universidad.xml')/universidad/departamento[@tipo = '" + line + "'] return $dep/empleado/nombre/text()");
			resultado = consulta.executeQuery();
			
			if (!resultado.next()) {
				System.out.println("No hay empleados que pertenezcan a ese departamento");
			} else {
				while (resultado.next()) {
					System.out.println(resultado.getItemAsString(null));
				}
			}
			
			conn.close();
			
		} catch (XQException ex) {System.out.println("Error al operar " + ex.getMessage());}

	}

}
