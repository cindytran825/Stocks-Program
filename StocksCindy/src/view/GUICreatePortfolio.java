package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class is a GUI window for creating portfolios. It queries the user for a name
 * of the new portfolio, and it'll create a new, formatted, CSV file in the
 * portfolio directory automatically.
 */
public class GUICreatePortfolio extends JPanel implements IGUICreatePortfolio {
  private final JPanel createPanel;
  private final JButton pButton;
  private final JLabel display;
  private final JTextArea textArea;
  private final JPanel pPanel;
  private final JPanel mainPanel;

  /**
   * Creates the create-portfolio GUI. It creates a text box to enter the name of the new
   * portfolio.
   *
   * @param mainPanel the main GUI from the main view
   */
  public GUICreatePortfolio(JPanel mainPanel) {
    super();
    this.mainPanel = mainPanel; // TODO, are we keeping the mainPanel import (this was experimental)
    createPanel = new JPanel();
    this.add(createPanel, BorderLayout.CENTER);
    pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder("Name new portfolio"));

    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));
    textArea = new JTextArea(0, 10);
    pPanel.add(textArea);
    pButton = new JButton("Create");
    display = new JLabel("[SUCCESS MESSAGE GOES HERE]");
    pButton.setActionCommand("Create");
    pPanel.add(display);
    pPanel.add(pButton);
    createPanel.add(pPanel);
  }

  @Override
  public String getName() {
    String newName = this.textArea.getText();
    textArea.setText("");
    createPanel.revalidate();
    createPanel.repaint();
    return newName;
  }

  @Override
  public void displaySuccess() {
    display.setText("Successfully created a new portfolio!");
    mainPanel.repaint();
    //createPanel.revalidate();
    // createPanel.repaint();
  }

  @Override
  public void setListener(ActionListener listener) {
    pButton.addActionListener(listener);
  }
}
