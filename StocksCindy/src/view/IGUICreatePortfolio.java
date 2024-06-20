package view;

/**
 * This interface represents a GUI for creating a new portfolio.
 */
public interface IGUICreatePortfolio extends GUIView {
  /**
   * gets the name of the newly created portfolio.
   *
   * @return name of new portfolio
   */
  String getName();

  /**
   * displays the success message upon a successful portfolio creation.
   */
  void displaySuccess();
}
