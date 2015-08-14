Basic space shooter implemented in Java with OpenGL (via JOGL).

Before running:
put the .dll files (available under "Downloads") in your java installation's /bin folder.

To run:
download executable jar and required library jar, double click the executable.

NOTE: you need JDK 6 to run this project.


to compile (from main project folder):
javac -classpath .\lib\jogl.jar;.\lib\gluegen-rt.jar -sourcepath src src/ao/cps511/a1/run/GalagaStarter.java

to run (from /src):
java -classpath "..\lib\jogl.jar;..\lib\gluegen-rt.jar;." ao.cps511.a1.run.GalagaStarter