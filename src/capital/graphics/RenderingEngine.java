package capital.graphics;

import capital.main.Game;
import capital.util.ResourceLoader;

public class RenderingEngine
{
    public static RenderingEngine instance = new RenderingEngine();

    public void init()
    {
        RenderUtil.init3D();
    }

    public void render()
    {
        RenderUtil.clearScreen();
        Game.camera.translatePosition();

        Model model = ResourceLoader.loadModel("cube.obj");
        model.loadTexture("green.png");
        model.draw();
    }

    public void update()
    {
        Game.mapKeys();
        Game.camera.update();
    }

}
