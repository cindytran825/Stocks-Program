package view;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * This interface is the representation of the main GUI view component of the MVC, and it
 * is personally responsible for all GUI outputs and puts all the other GUI view classes
 * together to create an interactive, changing GUI.
 */
public interface IGUIViewMain extends GUIView {
  /**
   * Displays a warning for the user to select a portfolio before selecting any actions.
   */
  void msgBox();

  /**
   * Checks if the current amount of sub-panels in the view are only showing two panels at a
   * time (the main and the sub-panel that is created from clicking on a radio button).
   */
  void checkComponent();

  /**
   * When an invalid input is given to the GUI, whether that's an invalid date, stock, name, etc.
   */
  void invalidInput();

  /**
   * Creates the buying/selling box in the main window.
   *
   * @param listener Action listeners who will read all button inputs from this class
   * @param tickers  stock tickers
   */
  void buildBuyBox(ActionListener listener, String[] tickers);

  /**
   * Creates the component/value box in the main window.
   *
   * @param listener Action listeners that will read all button inputs from this class
   */
  void buildComponentBox(ActionListener listener);

  /**
   * Creates the create-portfolio box in the main window.
   *
   * @param listener Action listeners that will read all button inputs from this class
   */
  void buildCreatePortfolio(ActionListener listener);

  /**
   * sets the text display of the composition box to the portfolio's composition reading.
   *
   * @param result the portfolio composition breakdown from the model
   */
  void setCompositionText(String result);

  /**
   * sets the portfolio selection combobox to display the chosen portfolio.
   *
   * @param portfolioName name of the chosen portfolio
   */
  void setComboboxDisplay(String portfolioName);

  /**
   * gets the combobox button that is a drop-down box with all existing portfolio names.
   *
   * @return the combobox
   */
  JComboBox<String> getCombobox();

  /**
   * updates the existing portfolio list upon the creation of a new one.
   *
   * @param newPortfolioName the new portfolio's name
   */
  void updatePortfolio(String newPortfolioName);

  /**
   * gets the newly created portfolio's name.
   *
   * @return the new portfolio's name
   */
  String getCreateName();

  /**
   * display a success message upon a successful portfolio creation.
   */
  void createSuccess();

  /**
   * gets the current selected portfolio name in the combobox.
   *
   * @return selected portfolio name
   */
  String getSelectedPortfolioName();

  /**
   * gets the chosen ticker from the buy/sell box.
   *
   * @return stock ticker
   */
  String getPurchaseTicker();

  /**
   * gets the date of transaction from the buy/sell box.
   *
   * @return date (YYYY-MM-DD)
   */
  String getPurchaseDate();

  /**
   * gets the shares for transaction from the buy/sell box.
   *
   * @return stock shares
   */
  String getPurchaseShares();

  /**
   * gets the date for analysis from the composition/value box.
   *
   * @return analysis date
   */
  String getAnalysisDate();
}
