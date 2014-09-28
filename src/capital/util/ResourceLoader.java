package capital.util;

import capital.graphics.Model;
import capital.graphics.Vertex;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Biw on 27/9/2557.
 */
public class ResourceLoader
{
    public static Model loadModel(String filename)
    {

        String[] splitArray = filename.split("\\.");
        String ext = splitArray[splitArray.length - 1];

        if (!ext.equals("obj"))
        {
            System.err.println("Error: File format not supported fro mesh data :" + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        BufferedReader meshReader = null;

        try
        {
            meshReader = new BufferedReader(new FileReader("./res/models/" + filename));
            String line;

            while ((line = meshReader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if (tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if (tokens[0].equals("v"))
                {
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3]))));
                }
                else if (tokens[0].equals("f"))
                {
                    indices.add(Integer.parseInt(tokens[1]) - 1);
                    indices.add(Integer.parseInt(tokens[2]) - 1);
                    indices.add(Integer.parseInt(tokens[3]) - 1);
                }
            }

            meshReader.close();

            Model res = new Model();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);

            res.addVertices(vertexData, Util.toIntArray(indexData));

            return res;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
