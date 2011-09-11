package loan.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class Config {
	public final String dbDriverName;
	public final String dbUrl;
	public final String dbUser;
	public final String dbPass;
	public final int year;
	public final int month;
	Config(String dbDriverName, String dbUrl, String dbUser, String dbPass, int year, int month) {
		this.dbDriverName = dbDriverName;
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
		this.year = year;
		this.month = month;
	}
	public static Config parse(String[] args) {
		if (args.length != 4) {
			throw invalid("起動引数が不正です。");
		}
		// 集計年・月
		if (!isNumeric(args[0])) {
			throw invalid(String.format("第1引数 \"%s\" は集計年として不正です。", args[0]));
		}
		final int year = Integer.parseInt(args[0]);
		if (year < 1970 || 2038 <= year) {
			// 集計年の範囲は 1970 < year ≦ 2038 を対象
			throw invalid(String.format("第1引数 \"%s\" は集計年として不正です。集計年は1970以上2038未満を指定してください。", args[0]));
		}
		if (!isNumeric(args[1])) {
			throw invalid(String.format("第2引数 \"%s\" は集計月として不正です。", args[1]));
		}
		final int month = Integer.parseInt(args[1]);
		if (month < 1 || 12 < month) {
			// 集計年の範囲は 1 ≦ year ≦ 12
			throw invalid(String.format("第2引数 \"%s\" は集計月として不正です。集計月は1以上12以下を指定してください。", args[1]));
		}
		// データベースユーザ, パスワード
		String[] user = args[2].split("/");
		if (user.length != 2) {
			throw invalid(String.format("第3引数 \"%s\" はデータベースユーザ/パスワードとして不正です。", args[2]));
		}
		// DBDriver, DBURL
		final String dbUser = user[0];
		final String dbPass = user[1];
		Properties properties = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(args[3]);
			properties.load(file);
		} catch (FileNotFoundException e) {
			throw invalid(String.format("第4引数で指定された設定ファイル \"%s\" が存在しません。", args[3]), e);
		} catch (IOException e) {
			throw invalid(String.format("第4引数で指定された設定ファイル \"%s\" の読み取り中にエラーが発生しました。", args[3]), e);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					throw invalid(String.format("第4引数で指定された設定ファイル \"%s\" の読み取り中にエラーが発生しました。", args[3]), e);
				}
			}
		}
		final String dbDriver = properties.getProperty("DBDriver");
		if (dbDriver == null) {
			throw invalid("設定ファイルに \"DBDriver\" の設定がありません。");
		}
		final String dbUrl = properties.getProperty("DBURL");
		if (dbUrl == null) {
			throw invalid("設定ファイルに \"DBURL\" の設定がありません。");
		}
		// 解析成功
		return new Config(dbDriver, dbUrl, dbUser, dbPass, year, month);
	}
	private static IllegalArgumentException invalid(String msg) {
		return new IllegalArgumentException(msg);
	}
	private static IllegalArgumentException invalid(String msg, Throwable e) {
		return new IllegalArgumentException(msg, e);
	}
	private static boolean isNumeric(String s) {
		// 0～9までの数字文字の1回以上の繰り返しを、数字を表す文字列とする
		return s.matches("[\\d]+");
	}
}
