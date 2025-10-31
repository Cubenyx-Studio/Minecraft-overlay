@echo off
echo ========================================
echo  Minecraft Overlay - Fix IDE Errors
echo ========================================
echo.

echo [1/3] Cleaning project...
call gradlew.bat clean

echo.
echo [2/3] Refreshing Gradle dependencies...
call gradlew.bat build --refresh-dependencies

echo.
echo [3/3] Done!
echo.
echo ========================================
echo  Next steps:
echo ========================================
echo 1. In IntelliJ IDEA:
echo    - Open Gradle panel (View ^> Tool Windows ^> Gradle)
echo    - Click the Reload button (circular arrow)
echo.
echo 2. Or: File ^> Invalidate Caches ^> Invalidate and Restart
echo.
echo The red errors should disappear after reload!
echo ========================================
pause

