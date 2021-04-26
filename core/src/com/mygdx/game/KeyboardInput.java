package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class KeyboardInput extends Thread {
    long lastPoll;
    long delta;
    boolean isThreadRunning;
    boolean isBlocked;

    public KeyboardInput() {
        lastPoll = System.currentTimeMillis();
        isThreadRunning = true;
        delta = 0;
        isBlocked = false;
    }

    @Override
    public void run() {
        while (isThreadRunning)
            delta = System.currentTimeMillis() - lastPoll;
    }

    public String getKeypress() {
        if (delta > 150 && !isBlocked) {
            lastPoll = System.currentTimeMillis();
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                return "Up";
            else if (Gdx.input.isKeyPressed(Input.Keys.A))
                return "Left";
            else if (Gdx.input.isKeyPressed(Input.Keys.S))
                return "Down";
            else if (Gdx.input.isKeyPressed(Input.Keys.D))
                return "Right";
        }

        return null;
    }

    public void stopThread() {
        isThreadRunning = false;
    }

    public void blockInput() {
        isBlocked = true;
    }

    public void unblockInput() {
        isBlocked = false;
    }

}
