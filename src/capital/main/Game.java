package capital.main;

import capital.component.GameComponent;
import capital.graphics.FPCameraController;
import org.lwjgl.input.Keyboard;

public class Game
{
    public static boolean[] keys = new boolean[256];
    public static FPCameraController camera = new FPCameraController(new Game());

    public static void main(String[] args)
    {
        GameComponent.init();
        Game game = new Game();
    }

    public static void mapKeys()
    {
        //Update keys
        for(int i=0;i<keys.length;i++){
            keys[i] = Keyboard.isKeyDown(i);
        }
    }
}
