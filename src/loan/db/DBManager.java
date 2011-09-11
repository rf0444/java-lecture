package loan.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static interface ConnectionFactory {
		Connection get() throws DBException;
	}
	private final ConnectionFactory connectionFactory;
	
	public DBManager(String dbDriverName, final String dbUrl, final String dbUser, final String dbPass) {
		loadDriver(dbDriverName);
		connectionFactory = new ConnectionFactory() {
			public Connection get() throws DBException {
				return getConnection(dbUrl, dbUser, dbPass);
			}
		};
	}
	public <T> T loan(Fun<Connection, T> f) {
		Connection connection = null;
		try {
			connection = connectionFactory.get();
			return f.apply(connection);
		} catch (DBException de) {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException se) {
				throw new IllegalStateException("データベースのロールバックに失敗しました。", de);
			}
			throw new IllegalStateException(de.getMessage(), de);
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new IllegalStateException("データベース接続のクローズに失敗しました。", e);
			}
		}
	}

	private static void loadDriver(String driverName) {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(String.format("指定されたドライバ \"%s\" を読み込めませんでした。", driverName), e);
		}
	}
	private static Connection getConnection(String dburl, String user, String pass) throws DBException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, user, pass);
			con.setAutoCommit(false);
			return con;
		} catch (SQLException e1) {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// 接続失敗として伝える
			}
			throw new DBException(String.format("指定されたデータベース \"%s\" への接続に失敗しました。", dburl), e1);
		}
	}
}
