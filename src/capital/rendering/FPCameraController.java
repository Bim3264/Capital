package capital.rendering;

import capital.main.Game;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class FPCameraController
{
    Vector3f vector = new Vector3f();
    Vector3f rotation = new Vector3f();
    Vector3f vectorPrevious = new Vector3f();
    boolean moveForward = false, moveBackward = false;
    boolean strafeLeft = false, strafeRight = false;
    boolean jump = false;
    static final float speed = 0.1f;
    Game game;

    public FPCameraController(Game game)
    {
        this.game = game;
        Mouse.setGrabbed(true);
    }

    public void update()
    {
        updatePrevious();
        input();
        updateVector();
    }

    public void updateVector()
    {
        if (moveForward)
        {
            vector.x -= (float) (Math.sin(-rotation.y * Math.PI / 180) * speed);
            vector.z -= (float) (Math.cos(-rotation.y * Math.PI / 180) * speed);
        }
        if (moveBackward)
        {
            vector.x += (float) (Math.sin(-rotation.y * Math.PI / 180) * speed);
            vector.z += (float) (Math.cos(-rotation.y * Math.PI / 180) * speed);
        }
        if (strafeLeft)
        {
            vector.x += (float) (Math.sin((-rotation.y - 90) * Math.PI / 180) * speed);
            vector.z += (float) (Math.cos((-rotation.y - 90) * Math.PI / 180) * speed);
        }
        if (strafeRight)
        {
            vector.x += (float) (Math.sin((-rotation.y + 90) * Math.PI / 180) * speed);
            vector.z += (float) (Math.cos((-rotation.y + 90) * Math.PI / 180) * speed);
        }
        if (jump)
        {
            //TODO: Make this really functional
            vector.x -= (float) (Math.sin(-rotation.y * Math.PI / 180) * speed);
            vector.y += (float) (Math.tan(-rotation.x * Math.PI / 180) * speed);
            vector.z -= (float) (Math.cos(-rotation.y * Math.PI / 180) * speed);
        }
    }

    public void translatePosition()
    {
        //This is the code that changes 3D perspective to the camera's perspective.
        GL11.glRotatef(rotation.x, 1, 0, 0);
        GL11.glRotatef(rotation.y, 0, 1, 0);
        GL11.glRotatef(rotation.z, 0, 0, 1);
        //-vector.y-2.4f means that your y is your feet, and y+2.4 is your head.
        GL11.glTranslatef(-vector.x, -vector.y-2.4f, -vector.z);
    }

    public void updatePrevious()
    {
        //Update last position (for collisions (later))
        vectorPrevious.x = vector.x;
        vectorPrevious.y = vector.y;
        vectorPrevious.z = vector.z;
    }

    public void input()
    {
        //Keyboard Input for Movement
        moveForward = game.keys[Keyboard.KEY_W];
        moveBackward = game.keys[Keyboard.KEY_S];
        strafeLeft = game.keys[Keyboard.KEY_A];
        strafeRight = game.keys[Keyboard.KEY_D];
//        jump = game.keys[Keyboard.KEY_SPACE];

        //Mouse Input for looking around...
        if(Mouse.isGrabbed())
        {
            float mouseDX = Mouse.getDX() * 0.8f * 0.16f;
            float mouseDY = Mouse.getDY() * 0.8f * 0.16f;
            if (rotation.y + mouseDX >= 360)
            {
                rotation.y = rotation.y + mouseDX - 360;
            }
            else if (rotation.y + mouseDX < 0)
            {
                rotation.y = 360 - rotation.y + mouseDX;
            }
            else
            {
                rotation.y += mouseDX;
            }
            if (rotation.x - mouseDY >= -89 && rotation.x - mouseDY <= 89)
            {
                rotation.x += -mouseDY;
            }
            else if (rotation.x - mouseDY < -89)
            {
                rotation.x = -89;
            }
            else if (rotation.x - mouseDY > 89)
            {
                rotation.x = 89;
            }
        }
    }
}
