package capital.component;

import capital.rendering.RenderUtil;

/**
 * Created by Biw on 11/9/2557.
 */
public class DrawableComponent
{
    //Draw size
    protected int d_size;
    //For rendering method
    protected boolean opaque = true;
    protected boolean loadAsModel = false;

    public DrawableComponent(int d_size)
    {
        this.d_size = d_size;
    }

    public DrawableComponent isOpaque(boolean opaque)
    {
        this.opaque = opaque;
        return this;
    }

    public DrawableComponent loadAsModel(boolean state)
    {
        this.loadAsModel = state;
        return this;
    }

    public DrawableComponent drawData()
    {
        if (opaque)
        {
            RenderUtil.setRenderingMode(RenderUtil.NORMAL_MODE);
        }
        else if (!opaque && loadAsModel)
        {
            RenderUtil.setRenderingMode(RenderUtil.MODEL_MODE);
        }
        else
        {
            System.err.println("Error: Can't specify rendering mode.");
            System.exit(1);
        }
        return this;
    }
}
