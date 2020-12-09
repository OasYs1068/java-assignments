package examples;

import java.awt.*;
import java.awt.event.*;

public class MouseClickHandler extends MouseAdapter {

	// We just need the mouseClick handler, so we use
	// the Adapter to avoid having to write all the
	// event handler methods

	public void mouseClicked (MouseEvent e) {
		// Do stuff with the mouse click...
	}
}
