package NewVersion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Machines implements Runnable {

    private static final int MAX = 12;
    private final Random random = new Random();
    private File file;

    public Machines(String nameOfFile){
        this.file = new File(nameOfFile);
    }



    @Override
    public void run() {

        while (true) {
            synchronized (file) {
                try {
                    int number = random.nextInt(MAX + 1);
                    PrintWriter writer = new PrintWriter(new FileWriter(file, true));
                    writer.println(number);
                    writer.close();
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Machines [] machines = new Machines[4];
        Thread[] threads = new Thread[4];
        String[] nameOfFile = new String[4];

        nameOfFile[0] = new String("Thread0");
        nameOfFile[1] = new String("Thread1");
        nameOfFile[2] = new String("Thread2");
        nameOfFile[3] = new String("Thread3");

        machines[0] = new Machines(nameOfFile[0]);
        machines[1] = new Machines(nameOfFile[1]);
        machines[2] = new Machines(nameOfFile[2]);
        machines[3] = new Machines(nameOfFile[3]);

        threads[0] = new Thread(machines[0]);
        threads[1] = new Thread(machines[1]);
        threads[2] = new Thread(machines[2]);
        threads[3] = new Thread(machines[3]);

        threads[0].start();
        threads[1].start();
        threads[2].start();
        threads[3].start();

        ReadFromFile readFromFile = new ReadFromFile(nameOfFile);
        Thread thread = new Thread(readFromFile);
        thread.start();

    }

}
