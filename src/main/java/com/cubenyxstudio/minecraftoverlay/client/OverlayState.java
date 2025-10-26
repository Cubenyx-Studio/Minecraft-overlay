package com.cubenyxstudio.minecraftoverlay.client;

/**
 * Manages persistent state for overlay tools
 */
public class OverlayState {
    // Stopwatch state
    private static long stopwatchStartTime = 0;
    private static long stopwatchElapsedTime = 0;
    private static boolean stopwatchRunning = false;
    private static final java.util.List<Long> stopwatchLaps = new java.util.ArrayList<>();

    // Timer state
    private static int timerRemainingSeconds = 0;
    private static boolean timerRunning = false;
    private static long timerLastTickTime = 0;

    // Stopwatch methods
    public static void startStopwatch() {
        if (!stopwatchRunning) {
            stopwatchStartTime = System.currentTimeMillis() - stopwatchElapsedTime;
            stopwatchRunning = true;
        }
    }

    public static void stopStopwatch() {
        if (stopwatchRunning) {
            stopwatchElapsedTime = System.currentTimeMillis() - stopwatchStartTime;
            stopwatchRunning = false;
        }
    }

    public static void resetStopwatch() {
        stopwatchRunning = false;
        stopwatchElapsedTime = 0;
        stopwatchStartTime = 0;
        stopwatchLaps.clear();
    }

    public static void recordLap() {
        if (stopwatchRunning) {
            long currentTime = System.currentTimeMillis() - stopwatchStartTime;
            stopwatchLaps.add(currentTime);
        }
    }

    public static long getStopwatchTime() {
        return stopwatchRunning ? System.currentTimeMillis() - stopwatchStartTime : stopwatchElapsedTime;
    }

    public static boolean isStopwatchRunning() {
        return stopwatchRunning;
    }

    public static java.util.List<Long> getStopwatchLaps() {
        return stopwatchLaps;
    }

    // Timer methods
    public static void startTimer(int seconds) {
        if (!timerRunning && seconds > 0) {
            timerRemainingSeconds = seconds;
            timerRunning = true;
            timerLastTickTime = System.currentTimeMillis();
        }
    }

    public static void stopTimer() {
        timerRunning = false;
    }

    public static void resetTimer() {
        timerRunning = false;
        timerRemainingSeconds = 0;
    }

    public static void tickTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - timerLastTickTime;

            if (elapsed >= 1000) {
                timerRemainingSeconds--;
                timerLastTickTime = currentTime;

                if (timerRemainingSeconds <= 0) {
                    timerRemainingSeconds = 0;
                    timerRunning = false;
                }
            }
        }
    }

    public static int getTimerRemainingSeconds() {
        return timerRemainingSeconds;
    }

    public static boolean isTimerRunning() {
        return timerRunning;
    }
}

