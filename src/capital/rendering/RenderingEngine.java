package capital.rendering;

import capital.component.block.Block;
import capital.main.Game;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import sun.font.TrueTypeFont;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;

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

        Block floor = new Block(0, 1, false);

        Block.drawBlockArray(floor, 128, 128);

//        worldRenderer.render();
    }

    public void update()
    {
        Game.mapKeys();
        Game.camera.update();
    }

}
