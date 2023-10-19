package csc305.gymnasticsApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CardButton {
    private static String[] cardDescription;

    private void main(String[] args) {
        String path = "C:\\csc\\CurlewRepo\\GymnasticsProject\\src\\main\\resources\\GymSoftwarePics\\DEMO1.csv";
        readFile(path);
    }

    public void readFile(String path){
            String line = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                br.readLine();
                while((line = br.readLine()) != null){
                    System.out.println(line);
                    cardDescription = line.split(",");

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
}
