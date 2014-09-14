package capital.component;

/**
 * Created by Biw on 11/9/2557.
 */
public class DrawableComponent
{
    //Draw size
    protected int d_size;
    //For rendering method
    protected boolean opaque = true;

    public DrawableComponent(int d_size)
    {
        this.d_size = d_size;
    }

    public DrawableComponent isOpaque(boolean opaque)
    {
        this.opaque = opaque;
        return this;
    }
}
