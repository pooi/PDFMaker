
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tw
 */
public class DbConnectionPools extends Object {

	DbConnectionPools() throws NamingException {
		// InitialContext ctx = new InitialContext();
		// ds = (DataSource)ctx.lookup("jdbc/login");
	}

	public static Connection getPoolConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pageconnected", "pageconnected", "123456");

			return connection;
		} catch (Exception e) {
			System.out.println("ERROR connection " + e);
			e.printStackTrace();
			return null;
		}
	}

	public static void closeResources(Connection conn, PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		}
	}

}
