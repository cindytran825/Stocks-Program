package controller;

/**
 * This represents the controller. It connects the view.
 * and the model.
 */
public interface Controller {
  /**
   * method called in StocksController.
   * calls the methods in model to do the implementation.
   * calls methods in view to display instructions to user.
   */
  void goControl();

}
