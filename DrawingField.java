import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingField extends Canvas {

    private static final int MAX_SHAPE_COUNT = 3000;
    private final GraphicsShape[] shapes = new GraphicsShape[MAX_SHAPE_COUNT];
    private int shapeNumber = 0;
    private int shapeCounter = 0;
    private Color backGroundColor = Color.BLACK;
    private Color borderColor = Color.BLACK;

    public DrawingField() {
        final Rectangle rect = new Rectangle();
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                rect.x = e.getX();
                rect.y = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                rect.width = e.getX();
                rect.height = e.getY();
                adjustCoordinates(rect);

                GraphicsShape toAdd = null;
                if (shapeNumber == 0) {
                    toAdd = new Rect(rect, borderColor);
                } else if (shapeNumber == 1) {
                    toAdd = new FilledOval(rect, backGroundColor, borderColor);
                } else if (shapeNumber == 2) {
                    toAdd = new Oval(rect, borderColor);
                }

                if (toAdd != null) {
                    shapes[shapeCounter++] = toAdd;
                    repaint();
                }
            }
        });
    }

    public void paint(Graphics g) {
        for (int i = 0; i < shapeCounter; i++) {
            shapes[i].draw(g);
        }
    }

    public void print() {
        for (int i = 0; i < shapeCounter; i++) {
            System.out.println(shapes[i].toString());
        }
    }

    public int getShape() {
        return this.shapeNumber;
    }

    public void setShape(int shape) {
        this.shapeNumber = shape;
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public void setBackgroundColor(Color color) {
        this.backGroundColor = color;
    }

    private void adjustCoordinates(Rectangle rect) {
        if (rect.x <= rect.width && rect.y <= rect.height) {
            rect.width -= rect.x;
            rect.height -= rect.y;
        } else if (rect.x < rect.width && rect.y > rect.height) {
            rect.width -= rect.x;
            int oldHeight = rect.height;
            rect.height = rect.y - rect.height;
            rect.y = oldHeight;
        } else if (rect.x > rect.width && rect.y < rect.height) {
            rect.height -= rect.y;
            int oldWidth = rect.width;
            rect.width = rect.x - rect.width;
            rect.x = oldWidth;
        } else if (rect.x > rect.width && rect.y > rect.height) {
            int oldWidth = rect.width;
            rect.width = rect.x - rect.width;
            rect.x = oldWidth;

            int oldHeight = rect.height;
            rect.height = rect.y - rect.height;
            rect.y = oldHeight;
        }
    }
}