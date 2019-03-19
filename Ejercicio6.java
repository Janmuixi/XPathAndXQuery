package eXist;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import net.xqj.exist.ExistXQDataSource;

public class Ejercicio6 {

	public static void main(String[] args) throws XQException {
		XQDataSource server = new ExistXQDataSource();
		server.setProperty ("serverName", "192.168.56.102");
		server.setProperty ("port","8080");
		server.setProperty ("user","admin");
		server.setProperty ("password","austria");
		try {
			XQConnection conn = server.getConnection();
			XQExpression consulta = conn.createExpression();
			String actualitzacio = "update value " +"/EMPLEADOS/EMP_ROW[EMP_NO=7369]/APELLIDO " +"with 'NuevoApellido'";
			
			consulta.executeCommand(actualitzacio);
		} catch (XQException e) {
			e.printStackTrace();
		} 
	}
}