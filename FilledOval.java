import java.awt.*;

public class FilledOval implements GraphicsShape {

    public Rectangle rectangle;
    public Color borderColor;
    public Color color;

    public FilledOval(Rectangle rectangle, Color background, Color border) {
        this.rectangle = new Rectangle(rectangle);
        this.color = background;
        this.borderColor = border;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.borderColor);
        g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(this.color);
        g.fillOval(rectangle.x + 1, rectangle.y + 1, rectangle.width - 1, rectangle.height - 1);
    }

    @Override
    public String toString() {
        return "FilledOval{" +
                "rectangle=" + rectangle +
                ", borderColor=" + borderColor +
                ", color=" + color +
                '}';
    }
}
