package capital.component;

import capital.main.Game;
import capital.rendering.RenderUtil;
import capital.rendering.RenderingEngine;
import capital.util.Time;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameComponent
{
    public static RenderingEngine renderingEngine = new RenderingEngine();

    public static long lastFPS;
    public static int fps;
    public static long lastFrame;

    public static void init()
    {
        initWindowGL(1280, 720, "Capital Engine");
    }

    public static void initWindowGL(int width, int height, String title)
    {
        try
        {
            Display.setTitle(title);
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }

        RenderingEngine.instance.init();

        lastFPS = Time.getTime();


        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            renderingEngine.render();
            int delta = getDelta();

            update(delta);

            Display.update();
            Display.sync(60);
        }

//        RenderUtil.cleanUP();
        Display.destroy();
    }

    private static int getDelta()
    {
        long time = Time.getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private static void updateFPS()
    {
        if (Time.getTime() - lastFPS > 1000)
        {
            System.out.println("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public static void update(int delta)
    {
        renderingEngine.update();

        updateFPS();
    }
}
