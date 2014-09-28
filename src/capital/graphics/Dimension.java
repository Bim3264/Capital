package capital.graphics;

/**
 * Created by Biw on 23/9/2557.
 */
public class Dimension
{
    private float width;
    private float height;
    private float depth;

    public Dimension(float width, float height, float depth)
    {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getDepth()
    {
        return depth;
    }

    public void setDepth(float depth)
    {
        this.depth = depth;
    }
}
