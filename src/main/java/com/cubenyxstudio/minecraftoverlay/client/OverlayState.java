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

    // Play time tracking
    private static long instanceStartTime = 0; // When Minecraft was launched
    private static long worldJoinTime = 0; // When player joined current world/server
    private static long dimensionJoinTime = 0; // When player entered current dimension
    private static String currentWorldName = "";
    private static String currentDimensionName = "";

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

    // Play time tracking methods
    public static void initInstanceStartTime() {
        if (instanceStartTime == 0) {
            instanceStartTime = System.currentTimeMillis();
        }
    }

    public static void onWorldJoin(String worldName) {
        worldJoinTime = System.currentTimeMillis();
        currentWorldName = worldName;
    }

    public static void onDimensionChange(String dimensionName) {
        dimensionJoinTime = System.currentTimeMillis();
        currentDimensionName = dimensionName;
    }

    public static void onWorldLeave() {
        worldJoinTime = 0;
        dimensionJoinTime = 0;
        currentWorldName = "";
        currentDimensionName = "";
    }

    public static long getInstancePlayTime() {
        if (instanceStartTime == 0) return 0;
        return System.currentTimeMillis() - instanceStartTime;
    }

    public static long getWorldPlayTime() {
        if (worldJoinTime == 0) return 0;
        return System.currentTimeMillis() - worldJoinTime;
    }

    public static long getDimensionPlayTime() {
        if (dimensionJoinTime == 0) return 0;
        return System.currentTimeMillis() - dimensionJoinTime;
    }

    public static String getCurrentWorldName() {
        return currentWorldName;
    }

    public static String getCurrentDimensionName() {
        return currentDimensionName;
    }

    public static String formatPlayTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        long displaySeconds = seconds % 60;
        long displayMinutes = minutes % 60;

        if (hours > 0) {
            return String.format("%dh %02dm %02ds", hours, displayMinutes, displaySeconds);
        } else if (minutes > 0) {
            return String.format("%dm %02ds", displayMinutes, displaySeconds);
        } else {
            return String.format("%ds", displaySeconds);
        }
    }
}


