package capital.rendering;

import com.sun.deploy.util.BufferUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class RenderUtil
{

    public static void vertexBufferData(int vboID, FloatBuffer buffer)
    {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    public static void indicesBufferData(int id, IntBuffer buffer)
    {
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }


    public static void bindTexture(int id, Texture texture, IntBuffer textureData)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureData, GL15.GL_STATIC_DRAW);
    }


    public static void render(int vboID)
    {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
    }

    public static void renderVAO(int vaoID)
    {
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public static void renderLine(int vboID)
    {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

        GL11.glDrawArrays(GL11.GL_LINE, 0, 3);
    }

    public static void render(int vboID, int iboID)
    {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 3);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0);
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

    /**
     * This method must only be used with the block drawing
     *
     * NOTE: Under change and testing by Bim3264
     * //TODO: Make it a 3d cube
     *
     * @param vertexBufferedID      vboID
     * @param startPos              start drawing pos
     * @param north                 how much do the drawing span north
     * @param east                  how much do the drawing span east
     */
    public static void createCube(int vertexBufferedID, Vector3f startPos, float north, float east, float size)
    {
        //Top Part
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer2 = BufferUtils.createFloatBuffer(9);

        //Bottom Part
        FloatBuffer vertexBuffer3 = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer4 = BufferUtils.createFloatBuffer(9);

        //East Part
        FloatBuffer vertexBuffer5 = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer6 = BufferUtils.createFloatBuffer(9);

        //West Part
        FloatBuffer vertexBuffer7 = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer8 = BufferUtils.createFloatBuffer(9);

        //North Part
        FloatBuffer vertexBuffer9 = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer10 = BufferUtils.createFloatBuffer(9);

        //South Part
        FloatBuffer vertexBuffer11 = BufferUtils.createFloatBuffer(9);
        FloatBuffer vertexBuffer12 = BufferUtils.createFloatBuffer(9);

        //Top Part
        //Lower-right Triangle
        vertexBuffer.put(startPos.x).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x + east).put(startPos.y).put(startPos.z);
        vertexBuffer.put(startPos.x + east).put(startPos.y).put(startPos.z + north);
        vertexBuffer.flip();

        //Top Part
        //Upper-left Triangle
        vertexBuffer2.put(startPos.x).put(startPos.y).put(startPos.z);
        vertexBuffer2.put(startPos.x + east).put(startPos.y).put(startPos.z + north);
        vertexBuffer2.put(startPos.x).put(startPos.y).put(startPos.z + north);
        vertexBuffer2.flip();

        //Bottom Part
        //Lower-right Triangle
        vertexBuffer3.put(startPos.x).put(startPos.y - size).put(startPos.z);
        vertexBuffer3.put(startPos.x + east).put(startPos.y - size).put(startPos.z);
        vertexBuffer3.put(startPos.x + east).put(startPos.y - size).put(startPos.z + north);

        //Bottom Part
        //Upper-left Triangle
        vertexBuffer4.put(startPos.x).put(startPos.y - size).put(startPos.z);
        vertexBuffer4.put(startPos.x + east).put(startPos.y - size).put(startPos.z + north);
        vertexBuffer4.put(startPos.x).put(startPos.y - size).put(startPos.z + north);


        vertexBufferData(vertexBufferedID, vertexBuffer);
        vertexBufferData(vertexBufferedID + 1, vertexBuffer2);
        vertexBufferData(vertexBufferedID + 2, vertexBuffer3);
        vertexBufferData(vertexBufferedID + 3, vertexBuffer4);
        vertexBufferData(vertexBufferedID + 4, vertexBuffer5);

        render(vertexBufferedID);
        render(vertexBufferedID + 1);
        render(vertexBufferedID + 2);
        render(vertexBufferedID + 3);
        render(vertexBufferedID + 4);

        cleanUP();
    }

    public static void createQuad(int vertexBufferedID, int vertexArrayID, Vector3f startPos, float size)
    {
        GL30.glBindVertexArray(vertexArrayID);

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
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

//        vertexBufferData(vertexBufferedID + 1, vertexBuffer2);
//        GL20.glVertexAttribPointer(1, 3,GL11.GL_FLOAT, false, 0, 0);

        renderVAO(vertexArrayID);

        cleanUP();
    }

    public void drawWorld()
    {
        
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
