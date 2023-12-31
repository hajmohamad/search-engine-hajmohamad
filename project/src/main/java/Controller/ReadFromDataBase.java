package Controller;

import Model.UnsortedTableMap;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadFromDataBase {
     public static HashMap<String, ArrayList<String>> MapData;
    public ReadFromDataBase() throws IOException {
        MapData = new HashMap<>();

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
            MapData.get(word).add(fileName);
        }

    }




    public static void main(String[] args) throws Exception{
        long time1=System.currentTimeMillis();
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();
        System.out.println((System.currentTimeMillis()-time1)/1000);

        Scanner sc=new Scanner(System.in);








    }

}
