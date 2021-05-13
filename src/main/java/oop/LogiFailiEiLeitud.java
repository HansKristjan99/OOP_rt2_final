package oop;

import java.io.File;
import java.io.IOException;

public class LogiFailiEiLeitud extends Exception{


    public LogiFailiEiLeitud(File file) throws IOException {
        file.createNewFile();
    }
}
