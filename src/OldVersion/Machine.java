package OldVersion;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Machine extends JPanel implements Runnable {
    private static final int min = 0;
    private static final int max = 12;
    public static boolean isStoped;
    private int result;
    private String fileName;
    private FileWriter writer;
    private int[] listOfNumbers;
    private Random random = new Random();

    Machine(String fileName) throws IOException {
        result = 0;
        this.fileName = fileName;
        this.listOfNumbers = new int[max];
        this.writer = new FileWriter(fileName,true);
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        while (!isStoped) {
            for (int i = 0; i < max; i++) {
                int numBetweenOneToTwelve = random.nextInt(max+1);
                this.listOfNumbers[i] = numBetweenOneToTwelve;
                result = updateResultOfList();

                System.out.println(Thread.currentThread().getName() + ": " + numBetweenOneToTwelve + ", " + result);

                try {
                    this.writer.write(String.valueOf(numBetweenOneToTwelve + "\n"));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /*try {
                Thread.sleep(5000);
                BufferedReader reader0 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), Charset.defaultCharset()));

                int[] list = new int[12];


                *//*setList(reader0, list);
                this.result1 = (String.valueOf(getResultOfList(list)));
                setList(reader1, list);
                this.result2 = (String.valueOf(getResultOfList(list)));
                setList(reader2, list);
                this.result3 = (String.valueOf(getResultOfList(list)));
                setList(reader3, list);
                this.result4 = (String.valueOf(getResultOfList(list)));*//*

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkSum() {
        if ((result<85&& result>=80)|| (result>115 && result<=120)){
            return "yellow";
        }else if (result<80 || result > 120){
            return "red";
        }
        return "ok";

    }

    private int updateResultOfList() {
        int res= 0;
        for (int i = 0; i < this.listOfNumbers.length; i++) {
            res+=this.listOfNumbers[i];
        }
        return res;
    }
}
