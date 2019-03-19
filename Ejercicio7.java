package eXist;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import net.xqj.exist.ExistXQDataSource;

public class Ejercicio7 {

	public static void main(String[] args) throws XQException {
		XQDataSource server = new ExistXQDataSource();
		server.setProperty ("serverName", "192.168.56.102");
		server.setProperty ("port","8080");
		server.setProperty ("user","admin");
		server.setProperty ("password","austria");
		try {
			XQConnection conn = server.getConnection();
			XQExpression consulta = conn.createExpression();
			String insertar = "update insert " +" <DEP_ROW><DEPT_NO>50</DEPT_NO><DNOMBRE>INFORMÁTICA</DNOMBRE><LOC>Valencia</LOC></DEP_ROW>" +" into /departamentos";
			
			consulta.executeCommand(insertar);
		} catch (XQException e) {
			e.printStackTrace();
		} 
	}
}