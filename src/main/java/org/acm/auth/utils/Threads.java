package org.acm.auth.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Threads {
    public static final int NUM_OF_COLORS = 5;

    public static int lastColor = -1;

    public static HashSet<Thread> threads = new HashSet<>();
    public static Color[] colors = new Color[] {
            Color.BLUE,
            Color.ORANGE,
            Color.GREEN,
            Color.GRAY,
            Color.RED
    };

    public static boolean addThread(String threadName) {
        lastColor = (lastColor+1) % NUM_OF_COLORS;

        if (!threadExists(threadName))
            return threads.add(new Thread(threadName, colors[lastColor]));
        else
            return false;
    }

    public static boolean deleteThread(String oldThread) {
        return threads.removeIf(thread -> thread.name.equals(oldThread));
    }

    public static boolean threadExists(String threadName) {
        return getThread(threadName) != null;
    }

    public static Color getThreadColor(String threadName) {
        Thread thread = getThread(threadName);
        if (thread != null)
            return thread.color;
        return Color.BLACK;
    }

    public static Thread getThread(String threadName) {
        for (Thread thread : threads)
            if (thread.name.equals(threadName))
                return thread;
        return null;
    }

    public static class Thread {
        String name;
        Color color;

        public Thread(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Thread thread = (Thread) o;
            return Objects.equals(name, thread.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, color);
        }
    }
}
