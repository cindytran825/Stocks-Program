package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * This interface represents the GUI View interface that displays portfolio and its functions
 * (buy/sell and composition/value query).
 */
public interface GUIView {

  /**
   * assigns Java Swing buttons/text/action event to an event listener.
   *
   * @param listener an ActionListener (ideally the controller)
   */
  void setListener(ActionListener listener);

}
