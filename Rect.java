import java.awt.*;

public class Rect implements GraphicsShape {

    public Rectangle rectangle;
    public Color color;

    public Rect(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public String toString() {
        return "Rect{" +
                "rectangle=" + rectangle +
                ", color=" + color +
                '}';
    }
}