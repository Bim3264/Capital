package capital.input;

import org.lwjgl.input.Keyboard;

/**
 * Created by Biw on 29/9/2557.
 */
public class Input
{
    public static int W = Keyboard.KEY_W;
    public static int A = Keyboard.KEY_A;
    public static int S = Keyboard.KEY_S;
    public static int D = Keyboard.KEY_D;

    public boolean isKeyDown(int key)
    {
        return Keyboard.isKeyDown(key);
    }
}
