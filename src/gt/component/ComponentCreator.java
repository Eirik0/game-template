package gt.component;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ComponentCreator {
	public static void setCrossPlatformLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
	}
}
