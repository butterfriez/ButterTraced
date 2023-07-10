package com.butter.engine.core;

import com.butter.engine.Main;
import com.butter.engine.core.utils.Consts;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class EngineManager {

    public static final long nanosecond = 1000000000L;
    public static final float framerate = 1000;

    private static int fps;
    private static float frametime = 1.0f / framerate;

    private boolean isRunning;

    private WindowManager window;
    private GLFWErrorCallback errorCallback;

    public void start() throws Exception {
        init();
        if(isRunning) return;
        run();
    }

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Main.getWindow();
        window.init();
    }

    public void run() {
        this.isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) nanosecond;
            frameCounter += passedTime;

            input();

            while (unprocessedTime > frames) {
                render = true;
                unprocessedTime -= frametime;

                if(window.windowShouldClose()) stop();

                if(frameCounter >= nanosecond) {
                    setFps(frames);
                    window.setTitle(Consts.TITLE + " FPS: " + getFps());
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if(render) {
                update();
                render();
                frames++;
            }
        }
        cleanup();
    }

    private void stop() {
        if(!isRunning) return;
        isRunning = false;
    }

    private void input() {

    }

    public void render() {
        window.update();
    }

    private void update() {

    }

    private void cleanup() {
        window.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
