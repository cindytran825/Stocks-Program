package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUINoPortfolio extends JPanel implements GUIView, ActionListener {

  private JPanel mainPanel;

  public GUINoPortfolio() {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    JPanel pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder("Please select a portfolio."));
    mainPanel.add(pPanel);

    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));
    JTextArea textArea = new JTextArea(0, 10);
    pPanel.add(textArea);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
