package capital.component;

import capital.rendering.RenderUtil;
import capital.rendering.Vertex;
import capital.util.Util;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Biw on 14/9/2557.
 */
public class Mesh
{
    public static int vbo;
    public static int ibo;
    public static int size;

    public Mesh()
    {
        vbo = RenderUtil.createID();
        ibo = RenderUtil.createID();
        size = 0;
    }

    public void addVertices(Vertex[] vertices, int[] indices)
    {
        size = indices.length;

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL15.GL_STATIC_DRAW);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBufeer(indices), GL15.GL_STATIC_DRAW);
    }


    public void draw()
    {
        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, Vertex.SIZE * 4, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL11.glDrawElements(GL11.GL_TRIANGLES, size, GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
    }
}
