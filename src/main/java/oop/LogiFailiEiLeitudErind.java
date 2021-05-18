package oop;

import java.io.File;
import java.io.IOException;

public class LogiFailiEiLeitudErind extends Exception{
    //Erindiklass, mis loob logifaili, kui seda juba pole
    public LogiFailiEiLeitudErind(File file) throws IOException {
        file.createNewFile();
    }
}
