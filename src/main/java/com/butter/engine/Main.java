package com.butter.engine;

import com.butter.engine.core.EngineManager;
import com.butter.engine.core.WindowManager;
import com.butter.engine.core.utils.Consts;
import org.lwjgl.Version;

public class Main {
    private static WindowManager window;
    private static EngineManager engine;

     public static void main(String[] args) {
         System.out.println(Version.getVersion());
         window = new WindowManager(Consts.TITLE, 1600, 900, false);

         engine = new EngineManager();
         try {
            engine.start();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    public static WindowManager getWindow() {
        return window;
    }
}
