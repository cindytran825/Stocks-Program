package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUICreatePortfolio extends JPanel implements GUIView {
  private JPanel mainPanel;
  private JButton pButton;
  private JLabel display;

  /**
   * called when the user wants to create a new portfolio.
   */
  public GUICreatePortfolio() {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    JPanel pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder("Name new portfolio"));
    mainPanel.add(pPanel);

    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));
    JTextArea textArea = new JTextArea(0, 10);
    pPanel.add(textArea);
    pButton = new JButton("Create");
    display = new JLabel("");
    pPanel.add(display);
    pButton.setActionCommand("Create");
    pPanel.add(pButton);

  }


  @Override
  public void setListener(ActionListener listener) {
    pButton.addActionListener(listener);
  }
}
