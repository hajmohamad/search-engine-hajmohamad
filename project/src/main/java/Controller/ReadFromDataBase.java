package Controller;

import Model.UnsortedTableMap;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ReadFromDataBase {
     public static SortedMap<String, ArrayList<String>> MapData;
    public ReadFromDataBase() throws IOException {
        MapData =new TreeMap<>();
      ///  reedEnglishDataFile();
    //    saveMapAsFile();
        readMapFile();

    }
    private void readMapFile(){
        //read from file
        try {
            File toRead=new File("MapFile");
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);
            MapData=(TreeMap<String,ArrayList<String>>)ois.readObject();
            ois.close();
            fis.close();

        } catch(Exception e) {}
    }
    private void saveMapAsFile()  {
        File fileOne=new File("MapFile" );
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(fileOne);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream oos= null;
        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(MapData);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void reedEnglishDataFile() throws IOException {
        File file = new File("I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Model\\EnglishData");
        for(File f:file.listFiles()){
            if(f.isFile()){
           String txtInput= Files.readAllLines(f.toPath()).toString();
                addEnglishDataFileToMap(f.getName(),txtInput);}
            else{
                break;
            }

        }

    }
    private void addEnglishDataFileToMap(String fileName,String txt){
        String[] wordList=txt.split(" ");
        for(String word:wordList){
            if(MapData.get(word)==null){
            MapData.put(word,new ArrayList<>());}
            if(!MapData.get(word).contains(fileName)){
            MapData.get(word).add(fileName);}
        }

    }




}
