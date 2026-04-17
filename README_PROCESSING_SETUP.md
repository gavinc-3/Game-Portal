# Processing Setup for GamePortal

1. Install Processing: https://processing.org/download  

2. Copy core.jar: cp /Applications/Processing.app/Contents/Java/core/core.jar lib/  

3. Compile: javac -cp "lib/core.jar" -d bin src/*.java 'src/Election Analysis'/*.java  

4. Run: java -cp "bin:lib/core.jar" GamePortal  

Select 4 – game launches, portal waits (latch/exitActual fixed).  

Standalone ExplodingKittensStandalone.pde in Processing IDE.  

App.java syntax fixed.
