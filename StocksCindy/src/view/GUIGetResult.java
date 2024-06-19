package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIGetResult extends JPanel implements GUIView, ActionListener {
  private JButton createPortButton;
  private JPanel mainPanel;
  private JPanel submitPanel;
  private JLabel portLabel;

  public GUIGetResult() {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.AFTER_LAST_LINE);

    submitPanel = new JPanel();
    portLabel = new JLabel("");
    submitPanel.add(portLabel);
    createPortButton = new JButton("submit");
    createPortButton.setActionCommand("submit");
    createPortButton.addActionListener(this);
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(createPortButton);
    mainPanel.add(submitPanel);
//    add(mainPanel);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "submit":
        portLabel.setText("submitted!");
        break;
    }
  }
}
