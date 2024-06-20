import javax.swing.*;

import controller.GUIController;
import model.Model;
import model.StocksModel;
import view.GUIViewImpl;

public class GUI {

  public static void execute() {
    Model model = new StocksModel();
    GUIViewImpl.setDefaultLookAndFeelDecorated(false);
    GUIViewImpl guiView = new GUIViewImpl(model.getPortfolioNames().split("\\n"));

    guiView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiView.setVisible(true);

    GUIController controller = new GUIController(model, guiView);
    controller.goControl();

//
//    try {
//      // Set cross-platform Java L&F (also called "Metal")
//      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//
//      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
//
//      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
//      //    {
//      //       if ("Nimbus".equals(info.getName())) {
//      //          UIManager.setLookAndFeel(info.getClassName());
//      //         break;
//      //    }
//      // }
//    } catch (UnsupportedLookAndFeelException e) {
//      // handle exception
//    } catch (ClassNotFoundException e) {
//      // handle exception
//    } catch (InstantiationException e) {
//      // handle exception
//    } catch (IllegalAccessException e) {
//      // handle exception
//    } catch (Exception e) {
//    }
//
//  }
  }

}
