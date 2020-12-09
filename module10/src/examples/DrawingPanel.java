package examples;

import java.awt.*;

public class DrawingPanel extends Panel {
  private static int SHAPE_WIDTH = 75;
  private static int SHAPE_HEIGHT = 75;
  private static int COLUMN_SPACE_WIDTH = 20;
  private static int ROW_SPACE_HEIGHT = 40;
  private static Font TITLE_FONT = new Font("SanSerif", Font.BOLD, 24);
  private static Font METHOD_FONT = new Font("Monospaced", Font.PLAIN, 14);

  public static int PANEL_WIDTH = (6 * SHAPE_WIDTH) + (7 * COLUMN_SPACE_WIDTH);
  public static int PANEL_HEIGHT = 550; // guess

  public DrawingPanel() {
    super();
  }

  /**
   * This is the main paint method; overriding the Panel.paint method.
   */
  public void paint(Graphics g) {
    int y;

    g.setColor(Color.blue);

    // Draw Unfilled Shapes
    y = 20;
    y = drawUnfilledShapes(g, y);

    // Draw Filled Shapes
    y += ROW_SPACE_HEIGHT;
    y = drawFilledShapes(g, y);

    // Draw Other Shapes
    y += ROW_SPACE_HEIGHT;
    y = drawOtherShapes(g, y);
  }

  /**
   * This method draws a row of unfilled shapes.
   *
   * @return  the adjusted y coordinate (y + height of line)
   */
  private int drawUnfilledShapes(Graphics g, int y) {
    int pentagonXs[] = new int[5];
    int pentagonYs[] = new int[5];
    int x = 20;

    // Draw title of this row
    y = centerText(g, "Unfilled Shapes", PANEL_WIDTH/2, y, TITLE_FONT);

    // draw an arc
    g.drawArc(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, 0, 90);
    centerText(g, "drawArc", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw an oval
    g.drawOval(x, y, SHAPE_WIDTH, SHAPE_HEIGHT);
    centerText(g, "drawOval", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw a polygon
    calcPentagon(x + SHAPE_WIDTH/2, y + SHAPE_HEIGHT/2, SHAPE_WIDTH/2,
		 pentagonXs, pentagonYs);
    g.drawPolygon(pentagonXs, pentagonYs, 5);
    centerText(g, "drawPolygon", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw a rectangle
    g.drawRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT);
    centerText(g, "drawRect", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw a rounded rectangle
    g.drawRoundRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, 15, 15);
    centerText(g, "drawRoundRect", x + SHAPE_WIDTH/2, y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw a 3D rectangle
    g.draw3DRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, true);
    y = centerText(g, "draw3DRect", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);

    return y;
  }


  private int drawFilledShapes(Graphics g, int y) {
    int pentagonXs[] = new int[5];
    int pentagonYs[] = new int[5];
    int x = 20;

    // Draw title of this row
    y = centerText(g, "Filled Shapes", PANEL_WIDTH/2, y, TITLE_FONT);

    // fill an arc
    g.fillArc(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, 0, 90);
    centerText(g, "fillArc", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // fill an oval
    g.fillOval(x, y, SHAPE_WIDTH, SHAPE_HEIGHT);
    centerText(g, "fillOval", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // fill a polygon
    calcPentagon(x + SHAPE_WIDTH/2, y + SHAPE_HEIGHT/2, SHAPE_WIDTH/2,
		 pentagonXs, pentagonYs);
    g.fillPolygon(pentagonXs, pentagonYs, 5);
    centerText(g, "fillPolygon", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // fill a rectangle
    g.fillRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT);
    centerText(g, "fillRect", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // fill a rounded rectangle
    g.fillRoundRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, 15, 15);
    centerText(g, "fillRoundRect", x + SHAPE_WIDTH/2, y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // fill a 3D rectangle
    g.fill3DRect(x, y, SHAPE_WIDTH, SHAPE_HEIGHT, true);
    y = centerText(g, "fill3DRect", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);

    return y;
  }

  private int drawOtherShapes(Graphics g, int y) {
    int pentagonXs[] = new int[5];
    int pentagonYs[] = new int[5];
    int x = 20;

    // Draw title of this row
    y = centerText(g, "Other Shapes", PANEL_WIDTH/2, y, TITLE_FONT);

    // draw an line
    g.drawLine(x, y, x + SHAPE_WIDTH, y + SHAPE_HEIGHT);
    centerText(g, "drawLine", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw an polyline
    calcPentagon(x + SHAPE_WIDTH/2, y + SHAPE_HEIGHT/2, SHAPE_WIDTH/2,
		 pentagonXs, pentagonYs);
    g.drawPolyline(pentagonXs, pentagonYs, 5);
    centerText(g, "drawPolyline", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);
    x = x + SHAPE_WIDTH + COLUMN_SPACE_WIDTH;

    // draw a string
    Font font = new Font("Serif", Font.ITALIC, 10);
    String text = "This is a string.";
    FontMetrics fm = g.getFontMetrics(font);
    int text_width = fm.stringWidth(text);
    g.setFont(font);
    g.drawString(text, x + SHAPE_WIDTH/2 - text_width/2, y + SHAPE_HEIGHT/2);
    centerText(g, "drawString", (x + SHAPE_WIDTH/2), y + SHAPE_HEIGHT, METHOD_FONT);

    return y;
  }

  /**
   * This method draws the 'text' centered on the 'x' coordinate,
   * where 'y' is the top of the text and 'font' is the desired font.
   *
   * @return  the adjusted y coordinate (y + font.height)
   */
  private int centerText(Graphics g, String text, int x, int y, Font font) {
    FontMetrics fm = g.getFontMetrics(font);
    int text_width = fm.stringWidth(text);
    int text_height = fm.getHeight();
    Color color = g.getColor();

    g.setColor(Color.black);
    g.setFont(font);
    g.drawString(text, x - text_width/2, y + text_height/2 + 5);
    g.setColor(color);

    return (y + text_height);
  }

  private void calcPentagon(int center_x, int center_y, int radius,
			    int[] pointsX, int[] pointsY) {
    double delta_radians = 2.0 * Math.PI / 5.0;
    double angle = 0.0;

    for ( int i = 0; i < 5; i++ ) {
      float x = (float) (radius * Math.sin(angle));
      float y = (float) (radius * Math.cos(angle));
      pointsX[i] = Math.round(center_x + x);
      pointsY[i] = Math.round(center_y + y);
      angle += delta_radians;
    }
  }
}
