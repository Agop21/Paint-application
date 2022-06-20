import java.awt.*;

public class Oval implements GraphicsShape {

    public Rectangle rectangle;
    public Color color;

    public Oval(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public String toString() {
        return "Oval{" +
                "rectangle=" + rectangle +
                ", color=" + color +
                '}';
    }
}