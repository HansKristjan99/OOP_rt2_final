package oop;

import java.io.File;
import java.io.IOException;

public class LogiFailiEiLeitudErind extends Exception{


    public LogiFailiEiLeitudErind(File file) throws IOException {
        file.createNewFile();
    }
}
