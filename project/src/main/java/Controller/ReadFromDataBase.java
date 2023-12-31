package Controller;

import Model.UnsortedTableMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadFromDataBase {
     public static UnsortedTableMap<String, ArrayList<String>> MapData;
    public ReadFromDataBase() {
        MapData = new UnsortedTableMap<>();
    }
    private void reedFile() throws IOException {
        File file = new File("I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Model\\EnglishData");
        for(File f:file.listFiles()){
           String txtInput= Files.readAllLines(f.toPath()).toString();
           addToMap(f.getName(),txtInput);





        }

    }

    private void addToMap(String fileName,String txt){
        String[] wordList=txt.split(" ");
        for(String word:wordList){
            MapData.put(word,new ArrayList<>());
            MapData.get(word).add(fileName);
        }

    }



    public static void main(String[] args) throws Exception{


    }

}
