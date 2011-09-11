package loan.db;

/**
 * DBの処理を含む処理関数です。
 * @param <T> 関数の引数の型
 * @param <R> 関数の戻り値の型
 * @author Ryota FUJISAWA
 */
public interface Fun<T, R> {
	/**
	 * 関数オブジェクトを実行します。
	 * @param x 関数の引数
	 * @return 関数の戻り値
	 * @throws DBException DB処理に失敗した場合
	 */
	R apply(T x) throws DBException;
}