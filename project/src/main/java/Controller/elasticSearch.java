package Controller;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class elasticSearch {
    static void trainElasticSearch(String filename) throws IOException {
        File file = new File(filename);
        for (File f : file.listFiles()) {
            String temp = f.getName();
            String txt = Files.readAllLines(f.toPath()).toString();

            txt = txt.toLowerCase();
            txt=filename+" "+txt;
            //  String[] wordList = txt.split("[\\p{Punct}\\s]+");
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"cmd.exe", "/c", "python", "I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Controller\\elasticSearch.py"});
                Process process = processBuilder.start();
                OutputStream outputStream = process.getOutputStream();
                outputStream.write(txt.getBytes());
                outputStream.flush();
                outputStream.close();
                searchinElastic("tree");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while (true) {
                    String line;
                    if ((line = reader.readLine()) == null) {
                        int exitCode = process.waitFor();
                        System.out.println("Exit Code: " + exitCode);
                        break;
                    }

                }
            } catch (IOException var8) {
                throw new RuntimeException(var8);
            } catch (InterruptedException var9) {
                throw new RuntimeException(var9);
            }

        }
    }
    static void searchinElastic(String order) throws  IOException{

            try {
                ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"cmd.exe", "/c", "python", "I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Controller\\elasticSearchGet.py"});
                Process process = processBuilder.start();
                OutputStream outputStream = process.getOutputStream();
                outputStream.write(order.getBytes());
                outputStream.flush();
                outputStream.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while (true) {
                    String line;
                    if ((line = reader.readLine()) == null) {
                        int exitCode = process.waitFor();
                        System.out.println("Exit Code: " + exitCode);
                        break;
                    }

                }
            } catch (IOException var8) {
                throw new RuntimeException(var8);
            } catch (InterruptedException var9) {
                throw new RuntimeException(var9);
            }

        }



}
