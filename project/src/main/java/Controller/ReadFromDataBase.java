package Controller;

import Model.UnsortedTableMap;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ReadFromDataBase {
     public static SortedMap<String, ArrayList<String>> MapData;
    public ReadFromDataBase() throws IOException {
        MapData =new TreeMap<>();
//        reedEnglishDataFile();
//        saveMapAsFile();
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
                addEnglishDataFileToMap(f.getName().toLowerCase(),txtInput);}
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
    public  static ArrayList<String> findContentForMainSearch(String searchText){
        ArrayList<String> content = new ArrayList<>();
        if(MapData.get(searchText)!=null){
            content.add(searchText);
        }
        for(int i=searchText.length()-1;i>0;i--){
            if (MapData.get(searchText.substring(0,i))!=null){
                content.add(searchText.substring(0,i));
            }
        }

        String temp="";

        for(char c:searchText.toCharArray()){
            temp=searchText;
            for(int i='a';i<='z';i++){
                char z=(char) i;
                temp =temp.replace(c, z);

                if(MapData.get(temp)!=null&&!temp.equals(searchText)){
                    content.add(temp);
                }
                temp=searchText;

            }

        }
       for(int i=searchText.length()-1;i>1;i--){
                 String subStr=searchText.substring(0,i);
            for(char c:subStr.toCharArray()){
                temp=subStr;
                for(int e='a';e<='z';e++){
                    char z=(char) e;
                    temp =temp.replace(c, z);

                    if(MapData.get(temp)!=null&&!temp.equals(searchText)){
                        content.add(temp);
                    }
                    temp=subStr;

                }

            }

        }
        return content;


    }








}
