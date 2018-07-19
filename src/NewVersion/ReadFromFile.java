package NewVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReadFromFile extends JFrame implements Runnable,ActionListener {
    private JPanel panel;
    private JLabel labelmachine1, labelmachine2, labelmachine3, labelmachine4,label1,label2,label3,label4,result1,result2,result3,result4,status1,status2,status3,status4;
    private JButton stop;
    private File[] files;
    private int[] array;

    public ReadFromFile(String[] nameOfFiles) {
        super("My Machines");
        this.files = new File[nameOfFiles.length];
        for (int i = 0; i < this.files.length; i++) {
            this.files[i] = new File(nameOfFiles[i]);
        }
        this.array = new int[12];


        this.setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(5,4,0,0));
        this.stop = new JButton("Stop");
        this.stop.setPreferredSize(new Dimension(95,17));

        this.labelmachine1 = new JLabel("Machine 1: ");
        this.labelmachine2 = new JLabel("Machine 2: ");
        this.labelmachine3 = new JLabel("Machine 3: ");
        this.labelmachine4 = new JLabel("Machine 4: ");
        this.label1 = new JLabel("Result: ");
        this.label2 = new JLabel("Result: ");
        this.label3 = new JLabel("Result: ");
        this.label4 = new JLabel("Result: ");
        this.result1 = new JLabel("0");
        this.result2 = new JLabel("0");
        this.result3 = new JLabel("0");
        this.result4 = new JLabel("0");
        this.status1 = new JLabel("");
        this.status2 = new JLabel("");
        this.status3 = new JLabel("");
        this.status4 = new JLabel("");

        this.panel.add(this.labelmachine1);
        this.panel.add(this.label1);
        this.panel.add(this.result1);
        this.panel.add(this.status1);
        this.panel.add(this.labelmachine2);
        this.panel.add(this.label2);
        this.panel.add(this.result2);
        this.panel.add(this.status2);
        this.panel.add(this.labelmachine3);
        this.panel.add(this.label3);
        this.panel.add(this.result3);
        this.panel.add(this.status3);
        this.panel.add(this.labelmachine4);
        this.panel.add(this.label4);
        this.panel.add(this.result4);
        this.panel.add(this.status4);
        this.panel.add(this.stop);
        this.add(this.panel);

        this.stop.addActionListener(this);
        ////////////
        this.add(panel);
        panel.setVisible(true);
        this.setSize(500,600);
        this.setVisible(true);
    }


    @Override
    public void run() {


        while (true){
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = 0;
            String line = null;
            for (File file : this.files){
                int index = 0;
                synchronized (file){
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        while ((line = reader.readLine()) != null){
                            this.array[index] = Integer.parseInt(line);
                            index++;
                            if (index == 12){
                                index = 0;
                            }
                        }
                        result = getSumOfTheLastTwelveNum();
                        checkResult(file,result);
                        System.out.println(file.getName() + ": " + result);
                        reader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void checkResult(File file, int result) {
        if (file.getName().equals("Thread0")) {
            result1.setText(String.valueOf(result));
            String status = checkSum(result);
            setStatus(status, status1);
        }
        if (file.getName().equals("Thread1")) {
            result2.setText(String.valueOf(result));
            String status = checkSum(result);
            setStatus(status, status2);
        }
        if (file.getName().equals("Thread2")) {
            result3.setText(String.valueOf(result));
            String status = checkSum(result);
            setStatus(status, status3);
        }
        if (file.getName().equals("Thread3")) {
            result4.setText(String.valueOf(result));
            String status = checkSum(result);
            setStatus(status, status4);
        }
    }

    private void setStatus(String status, JLabel label) {
        if (status == "ok"){
            label.setText("OK");
            label.setForeground(Color.GREEN);
        }else if (status == "red"){
            label.setText("Danger");
            label.setForeground(Color.RED);
        }else {
            label.setText("Warning");
            label.setForeground(Color.YELLOW);
        }
    }

    private String checkSum(int result) {
        if ((result < 85 && result >= 80) || (result > 115 && result <= 120)) {
            return "yellow";
        } else if (result < 80 || result > 120) {
            return "red";
        }
        return "ok";
    }

    private int getSumOfTheLastTwelveNum() {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result+=array[i];
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.stop){
            System.exit(0);
        }
    }
}
