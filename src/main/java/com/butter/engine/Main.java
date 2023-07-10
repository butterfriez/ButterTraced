package com.butter.engine;

import com.butter.engine.core.WindowManager;
import org.lwjgl.Version;

public class Main {
     public static void main(String[] args) {
         System.out.println(Version.getVersion());
         WindowManager window = new WindowManager("Butter Traced", 1600, 900, true);
         window.init();

         while(!window.windowShouldClose()) {
             window.update();
         }

         window.cleanup();
     }
}
