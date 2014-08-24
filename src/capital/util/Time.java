package capital.util;

import org.lwjgl.Sys;

/**
 * Created by Biw on 23/8/2557.
 */
public class Time
{
    public static long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
