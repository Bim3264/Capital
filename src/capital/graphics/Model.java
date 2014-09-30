package capital.graphics;

import capital.graphics.Vertex;
import capital.util.Util;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Model
{
    private int vao;
    private int vbo;
    private int ibo;
    private int size;
    private int tex;

    Texture texture = null;

    public Model()
    {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        tex = glGenBuffers();
        size = 0;

    }

    public void addVertices(Vertex[] vertices, int[] indices)
    {
        size = indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBufeer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void addTextureCoordinate(Float[] uv)
    {
        glBindBuffer(GL_ARRAY_BUFFER, tex);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(uv), GL_STATIC_DRAW);
    }

    @Deprecated
    public void loadTexture(String filename)
    {
        try
        {
            texture = TextureLoader.getTexture("PNG", new FileInputStream("./res/" + filename));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw()
    {
        if (texture != null)
            texture.bind();

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 1);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }
}

