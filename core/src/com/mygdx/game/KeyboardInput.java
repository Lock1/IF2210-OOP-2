package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class KeyboardInput extends Thread {
    long lastPoll;
    long delta;
    boolean isThreadRunning;

    public KeyboardInput() {
        lastPoll = System.currentTimeMillis();
        isThreadRunning = true;
        delta = 0;
    }

    @Override
    public void run() {
        while (isThreadRunning)
            delta = System.currentTimeMillis() - lastPoll;
    }

    public String getKeypress() {
        if (delta > 150) {
            lastPoll = System.currentTimeMillis();
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                return "Up";
            else if (Gdx.input.isKeyPressed(Input.Keys.A))
                return "Left";
            else if (Gdx.input.isKeyPressed(Input.Keys.S))
                return "Down";
            else if (Gdx.input.isKeyPressed(Input.Keys.D))
                return "Right";
            else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
                return "Back";
        }

        return null;
    }

    public void stopThread() {
        isThreadRunning = false;
    }

}
