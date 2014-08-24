package capital.rendering;

import capital.rendering.quad.Quad;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.IntBuffer;

public class WorldRenderer
{
    @Deprecated
    public void renderFromBitmap(File resourceImage)
    {

        try
        {
            BufferedImage bitmap = ImageIO.read(resourceImage);
            for (int x = 0; x < bitmap.getWidth(); x++)
            {
                for (int z = 0; z < bitmap.getHeight(); z++)
                {
                    int nextX = 0;
                    int nextZ = 0;

                    int color = bitmap.getRGB(x,z);

//                    if (color == Color.BLACK.getRGB())
                    {
//                        int vboID = RenderUtil.createVBOID();

                        //TODO: Finish up the rendering

//                        RenderUtil.createQuad(vboID, new Vector3f(nextX,0,nextZ),
//                                                     new Vector3f(nextX + 0.5f,0,nextZ),
//                                                     new Vector3f(nextX + 0.5f,0,z),
//                                                     new Vector3f(nextX,0,z));


                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void render()
    {
        int vboID = RenderUtil.createID();
        int vaoID = RenderUtil.createVAOID();
        int size = 1;

        Quad quad = new Quad(vboID, new Vector3f(0,0,0), 50);
    }
}
