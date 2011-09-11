package loan;

import java.sql.Connection;

import loan.conf.Config;
import loan.db.DBException;
import loan.db.DBManager;
import loan.db.Fun;

public class LoanMain {
	public static void main(String[] args) {
		Config conf = Config.parse(args);
		DBManager db = new DBManager(conf.dbDriverName, conf.dbUrl, conf.dbUser, conf.dbPass);
		int a = db.loan(new Fun<Connection, Integer>() {
			public Integer apply(Connection x) throws DBException {
				return 12;
			}
		});
		System.out.println(a);
	}
}
