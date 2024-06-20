package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUICreatePortfolio extends JPanel implements GUIView {
  private JPanel createPanel;
  private JButton pButton;
  private JLabel display;
  private JTextArea textArea;
  private JPanel pPanel;

  /**
   * called when the user wants to create a new portfolio.
   */
  public GUICreatePortfolio() {
    super();
    createPanel = new JPanel();
    this.add(createPanel, BorderLayout.CENTER);
    pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder("Name new portfolio"));

    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));
    textArea = new JTextArea(0, 10);
    pPanel.add(textArea);
    pButton = new JButton("Create");
    display = new JLabel("");
    pButton.setActionCommand("Create");
    pPanel.add(display);
    pPanel.add(pButton);
    createPanel.add(pPanel);
  }

  public String getName() {
    String newName = this.textArea.getText();
    textArea.setText("");
    createPanel.revalidate();
    createPanel.repaint();
    return newName;
  }

  public void displaySuccess() {
    display.setText("Successfully created a new portfolio!");
    createPanel.revalidate();
    createPanel.repaint();
  }


  @Override
  public void setListener(ActionListener listener) {
    pButton.addActionListener(listener);
  }
}
