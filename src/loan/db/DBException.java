package loan.db;

/**
 * データベース関係の処理中に問題が生じたことを表す例外です。
 * @author Ryota FUJISAWA
 */
public final class DBException extends Exception {
	private static final long serialVersionUID = -4489290477837492068L;
	public DBException(String msg, Throwable cause) {
		super(msg, cause);
	}
}