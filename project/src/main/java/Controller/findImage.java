package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class findImage {
    public static ArrayList<String> getLink(String order) {
        ArrayList<String> imageList = new ArrayList<String>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "python", "I:\\ramezon\\data structures\\search-engine-hajmohamad\\project\\src\\main\\java\\Controller\\main.py");
            Process process = processBuilder.start();


            OutputStream outputStream = process.getOutputStream();
            outputStream.write(order.getBytes());
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                imageList.add(line);
            }

// Wait for the process to complete
            int exitCode = process.waitFor();

// Print the exit code
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    return imageList;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String order = sc.nextLine();
        for(String s:getLink(order)) {
            System.out.println(s);
        }


    }
}
