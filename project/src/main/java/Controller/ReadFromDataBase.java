package Controller;

import Model.UnsortedTableMap;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ReadFromDataBase {
     public static SortedMap<String, ArrayList<String>> MapData;
     public static Set<String> history;
    public ReadFromDataBase() throws IOException {
        MapData =new TreeMap<>();
        history=new HashSet<>();
    //  reedEnglishDataFile();
     //   saveMapAsFile();
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
            String temp=f.getName();
          String txtInput= Files.readAllLines(f.toPath()).toString();
                addEnglishDataFileToMap(temp,txtInput);
        elasticSearch.trainElasticSearch(temp);}





    }



    private void addEnglishDataFileToMap(String fileName,String txt){
       txt= txt.toLowerCase();

        String[] wordList=txt.split("[\\p{Punct}\\s]+");


        for(String word:wordList){
           word= word.toLowerCase();
            if(MapData.get(word.toLowerCase())==null){
            MapData.put(word.toLowerCase(),new ArrayList<>());
            MapData.get(word.toLowerCase()).add(fileName);
            }
            if(!MapData.get(word.toLowerCase()).contains(fileName)){
            MapData.get(word.toLowerCase()).add(fileName);}
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
    public static Set<String> doAndOrNot(String order) {
      order=  order.toLowerCase();
        String[] words = order.split(" ");
        Set<String> result = new HashSet<>();
        Set<String> and = new HashSet<>();
        Set<String> or = new HashSet<>();
        Set<String> not = new HashSet<>();
        Set<String> andTemp = new HashSet<>();





        for (String word : words) {
            if (word.startsWith("+")) {
                String S=word.substring(1);
                if(MapData.get(S) != null){
                    System.out.println(S);
                    or.addAll(MapData.get(S));

                }


            } else if (word.startsWith("-")) {
                if (MapData.get((word.substring(1))) != null) {
                    String S=word.substring(1);
                    System.out.println(S);
                    not.addAll(MapData.get(S));
                }


            } else {
                if (MapData.get(word) != null) {
                    if(andTemp.isEmpty()){
                    andTemp.addAll(MapData.get(word));
                    }else {
                        for(String s:MapData.get(word)){
                            if(andTemp.contains(s)){
                                and.add(s);
                            }
                        }
                        andTemp.clear();
                        andTemp.addAll(and);
                        and.clear();
                    }
                }


            }
        }
        and.clear();
        and.addAll(andTemp);
        if(or.size()==0){
            result.addAll(and);
        }else{
        for(String s:and){
            if(or.contains(s)){
                result.add(s);
            }
        }}



        result.removeAll(not);


        return result;



    }
    public static String getStringFronFile(String fileName){
        File f=new File("I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Model\\EnglishData\\"+fileName);
        try {
            return Files.readAllLines(f.toPath()).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String findNameForFile(String searchInput,String fileTxt){
        String[] searchInputList=searchInput.split("\\s+[+]\\s+");;
        String[] fileTxtList=fileTxt.split("[\\p{Punct}\\s]+");
        ArrayList<String> arrayList = new ArrayList<>(List.of(fileTxtList));
            int count = 0;
        for(int i=0;i<searchInputList.length;i++){
            if(!searchInputList[i].startsWith("-")){
                count=arrayList.indexOf(searchInputList[i]);
                if(count!=-1){

                    break;
                }


            }
        }
        return fileTxtList[count]+" "+fileTxtList[count+1]+" "+fileTxtList[count+2];



    }

    public static void main(String[] args) throws IOException {
        ReadFromDataBase R=new ReadFromDataBase();
        Scanner sc=new Scanner(System.in);
        System.out.println((doAndOrNot(sc.nextLine())).size());
    }









}
