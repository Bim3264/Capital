package capital.graphics;

import capital.main.Game;
import capital.util.ResourceLoader;
import org.lwjgl.opengl.GL11;

public class RenderingEngine
{
    public static RenderingEngine instance = new RenderingEngine();
    public static WorldRenderer worldRenderer = new WorldRenderer();;

    public void init()
    {
        RenderUtil.init3D();
    }

    public void render()
    {
        RenderUtil.clearScreen();
        Game.camera.translatePosition();

        Model model = ResourceLoader.loadModel("cube.obj");
        model.draw();
    }

    public void update()
    {
        Game.mapKeys();
        Game.camera.update();
    }

}
