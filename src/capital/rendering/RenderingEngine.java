package capital.rendering;

import capital.component.Mesh;
import capital.main.Game;
import capital.util.ResouceLoader;

public class RenderingEngine
{
    public static RenderingEngine instance = new RenderingEngine();
    public static WorldRenderer worldRenderer = new WorldRenderer();;
    public Mesh mesh;

    public void init()
    {
        RenderUtil.init3D();
    }

    public void render()
    {
        RenderUtil.clearScreen();
        Game.camera.translatePosition();

        //Debug
//        Block floor = new Block(0, 1, false);

//        Block.drawBlock(floor);

        mesh = ResouceLoader.loadMesh("cube.obj");
        mesh.draw();
    }

    public void update()
    {
        Game.mapKeys();
        Game.camera.update();
    }

}
