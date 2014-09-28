package capital.graphics;

import capital.component.DrawableComponent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class RenderUtil
{
    public static int NORMAL_MODE = 0x01;
    public static int MODEL_MODE = 0x02;
    public static ArrayList<String> modelFilename = new ArrayList<String>();

    private static int size = 0;

    public static void vertexBufferData(int vboID, FloatBuffer buffer)
    {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    public static void indicesBufferData(int iboID, IntBuffer buffer)
    {
        size = buffer.capacity();

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }


    public static void bindTexture(int id, Texture texture, IntBuffer textureData)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureData, GL15.GL_STATIC_DRAW);
    }


    //TODO: Swap rendering mode
    public static void setRenderingMode(DrawableComponent component, int mode)
    {
        if (mode == NORMAL_MODE)
        {
            //TODO:Normal mode render
        }
        if (mode == MODEL_MODE)
        {
            modelMode(component);
        }
    }

    public static void modelMode(DrawableComponent component)
    {

    }

    public static void render(int vboID)
    {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
    }

    public static void render(int vbo, int ibo)
    {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLE_STRIP, size, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
    }

    public static int createID()
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        GL15.glGenBuffers(buffer);
        return buffer.get(0);
    }

    public static int createVAOID()
    {
        return GL30.glGenVertexArrays();
    }

    //Utilities function

    /**
     * Must be binned first before using
     *
     * @param pos1
     * @param pos2
     * @param pos3
     * @param pos4
     */
    public static void createQuad(int vboID , Vector3f pos1, Vector3f pos2, Vector3f pos3, Vector3f pos4)
    {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer2 = BufferUtils.createFloatBuffer(9);

        vertexBuffer.put(pos1.x).put(pos1.y).put(pos1.z);
        vertexBuffer.put(pos2.x).put(pos2.y).put(pos2.z);
        vertexBuffer.put(pos3.x).put(pos3.y).put(pos3.z);
        vertexBuffer.flip();

        vertexBuffer2.put(pos1.x).put(pos1.y).put(pos1.z);
        vertexBuffer2.put(pos3.x).put(pos3.y).put(pos3.z);
        vertexBuffer2.put(pos4.x).put(pos4.y).put(pos4.z);
        vertexBuffer2.flip();

        vertexBufferData(vboID, vertexBuffer);
        vertexBufferData(vboID + 1, vertexBuffer2);

        render(vboID);
        render(vboID + 1);

        cleanUP();
    }

    public static void createQuad(int vertexBufferedID, Vector3f startPos, float size)
    {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer2 = BufferUtils.createFloatBuffer(9);

        //Lower-right Triangle
        vertexBuffer.put(startPos.x).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x + size).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x + size).put(startPos.y).put(startPos.z + size);
        vertexBuffer.flip();

        //Upper-left Triangle
        vertexBuffer2.put(startPos.x).put(startPos.y).put(startPos.z);
        vertexBuffer2.put(startPos.x + size).put(startPos.y).put(startPos.z + size);
        vertexBuffer2.put(startPos.x).put(startPos.y).put(startPos.z + size);
        vertexBuffer2.flip();

        vertexBufferData(vertexBufferedID, vertexBuffer);
        vertexBufferData(vertexBufferedID + 1, vertexBuffer2);

        render(vertexBufferedID);
        render(vertexBufferedID + 1);

        cleanUP();
    }

    @Deprecated
    public static void createCube(int vboID, Vector3f startPos, float offsetX, float offsetY, float offsetZ)
    {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(24);
//        IntBuffer indexBuffer = BufferUtils.createIntBuffer();

        vertexBuffer.put(startPos.x).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x).put(startPos.y + offsetY).put(startPos.z);
        vertexBuffer.put(startPos.x + offsetX).put(startPos.y + offsetY).put(startPos.z);
        vertexBuffer.put(startPos.x + offsetX).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x).put(startPos.y).put(startPos.z + offsetZ);
        vertexBuffer.put(startPos.x).put(startPos.y + offsetY).put(startPos.z + offsetZ);
        vertexBuffer.put(startPos.x + offsetX).put(startPos.y + offsetY).put(startPos.z + offsetZ);
        vertexBuffer.put(startPos.x + offsetX).put(startPos.y).put(startPos.z + offsetZ);
        vertexBuffer.flip();

        cleanUP();
    }

    /**
     * Clean UP VBO & VAO
     */
    private static void cleanUP()
    {
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
    }

    public static void init3D(){
        //Start 3D Stuff
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GLU.gluPerspective(70, Display.getWidth() / Display.getHeight(), 0.3f, 1000);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glFrontFace(GL11.GL_CW);
    }

    public static void clearScreen(){
        //Clear the screen
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();
    }
}
