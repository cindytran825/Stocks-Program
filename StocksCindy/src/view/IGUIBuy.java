package view;

/**
 * This interface is a GUI window for transactions within the stock portfolios.
 */
public interface IGUIBuy extends GUIView {

  /**
   * gets the date from the buy/sell window.
   *
   * @return the date in YYYY-MM-DD format
   */
  String getDate();

  /**
   * gets the shares that will be used for the transaction.
   *
   * @return quantity of shares
   */
  String getShares();

  /**
   * gets the selected stock ticker from the window.
   *
   * @return stock ticker
   */
  String getTicker();
}
