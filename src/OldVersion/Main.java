package OldVersion;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try {
            AquaFrame frame = new AquaFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400,400);
            frame.setVisible(true);
          /*  ExecutorService service = Executors.newFixedThreadPool(4);
            for (int i = 0; i < 4; i++) {
                String fileName = "thread" + i + ".txt";
                service.execute(new OldVersion.Machine(fileName));
            }
            service.shutdown();*/
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
