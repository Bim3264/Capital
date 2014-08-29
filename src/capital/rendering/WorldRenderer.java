package capital.rendering;

import capital.rendering.quad.Quad;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;

public class WorldRenderer
{
    int size = 32;
    boolean[][] coordinate;

    FloatBuffer pos;

    /**
     * Under work
     *
     * @param resourceImage
     */
    public void readFromBitmap(FileInputStream resourceImage)
    {
        try
        {
            BufferedImage bitmap = ImageIO.read(resourceImage);

            pos = new BufferUtils().createFloatBuffer(bitmap.getWidth() * bitmap.getHeight());
            coordinate = new boolean[bitmap.getWidth()][bitmap.getHeight()];

            for (int i = 0; i < bitmap.getWidth(); i++)
            {
                for (int j = 0; j < bitmap.getHeight(); j++)
                {
                    if (bitmap.getRGB(i,j) == Color.BLACK.getRGB())
                    {
                        coordinate[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < bitmap.getWidth(); i++)
            {
                for (int j = 0; j < bitmap.getHeight(); j++)
                {
                    if (coordinate[i][j] == true)
                    {
                        pos.put(i).put(0).put(j);
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
        RenderUtil.vertexBufferData(pos);
    }
}
