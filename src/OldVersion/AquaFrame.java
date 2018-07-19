package OldVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AquaFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private Machine machine1,machine2,machine3,machine4;
    private JLabel labelmachine1, labelmachine2, labelmachine3, labelmachine4,label1,label2,label3,label4,result1,result2,result3,result4,status1,status2,status3,status4;
    private JButton stop;
    private boolean isStoped = false;

    public AquaFrame(){
        super("My Machines");
        this.setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(5,4,0,0));
        this.stop = new JButton("Stop");
        this.stop.setPreferredSize(new Dimension(95,17));

        this.labelmachine1 = new JLabel("OldVersion.Machine 1: ");
        this.labelmachine2 = new JLabel("OldVersion.Machine 2: ");
        this.labelmachine3 = new JLabel("OldVersion.Machine 3: ");
        this.labelmachine4 = new JLabel("OldVersion.Machine 4: ");
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
        Timer timer = new Timer(12000,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                result1.setText(String.valueOf(machine1.getResult()));
                result2.setText(String.valueOf(machine2.getResult()));
                result3.setText(String.valueOf(machine3.getResult()));
                result4.setText(String.valueOf(machine4.getResult()));
                if (machine1.checkSum()=="ok"){
                    status1.setText("OK");
                    status1.setForeground(Color.GREEN);
                }else if (machine1.checkSum()=="red"){
                    status1.setText("Danger");
                    status1.setForeground(Color.RED);
                }else {
                    status1.setText("Warning");
                    status1.setForeground(Color.YELLOW);
                }
                if (machine2.checkSum()=="ok"){
                    status2.setText("OK");
                    status2.setForeground(Color.GREEN);
                }else if (machine2.checkSum()=="red"){
                    status2.setText("Danger");
                    status2.setForeground(Color.RED);
                }else {
                    status2.setText("Warning");
                    status2.setForeground(Color.YELLOW);
                }
                if (machine3.checkSum()=="ok"){
                    status3.setText("OK");
                    status3.setForeground(Color.GREEN);
                }else if (machine3.checkSum()=="red"){
                    status3.setText("Danger");
                    status3.setForeground(Color.RED);
                }else {
                    status3.setText("Warning");
                    status3.setForeground(Color.YELLOW);
                }
                if (machine4.checkSum()=="ok"){
                    status4.setText("OK");
                    status4.setForeground(Color.GREEN);
                }else if (machine4.checkSum()=="red"){
                    status4.setText("Danger");
                    status4.setForeground(Color.RED);
                }else {
                    status4.setText("Warning");
                    status4.setForeground(Color.YELLOW);
                }
            }
        });
        timer.start();
        start1();
    }

    private void start1() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        try {
        this.machine1 = new Machine("thread1.txt");
        this.machine2 = new Machine("thread2.txt");
        this.machine3 = new Machine("thread3.txt");
        this.machine4 = new Machine("thread4.txt");


                service.execute(this.machine1);
                service.execute(this.machine2);
                service.execute(this.machine3);
                service.execute(this.machine4);

            } catch (IOException e) {
                e.printStackTrace();
            }
        service.shutdown();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.stop){
            Machine.isStoped = true;
            System.exit(0);
        }
    }


}
