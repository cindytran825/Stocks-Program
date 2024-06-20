package view;

/**
 * This interface represents the GUI window for stock portfolio analysis. It can get the
 * composition and the value distribution of any available portfolio on file.
 */
public interface IGUIComposition extends GUIView {
  /**
   * display the composition or value distribution of the stocks in the evaluated portfolio.
   *
   * @param result the composition or value distribution string of the portfolio
   */
  void setResultText(String result);

  /**
   * gets the day, month, and year from the window and compile it into a single date string.
   *
   * @return date (YYYY-MM-DD)
   */
  String getDate();
}
