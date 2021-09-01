import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        File config = new File("Config.ini");

        if(!config.exists()) {
            UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 30));
        } else{
            try {
                Scanner scanner = new Scanner(config);
                String in = scanner.nextLine();
                in = in.replace("FontSize=[", "").replace("]", "");
                int fontSize = Integer.parseInt(in);
                UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, fontSize));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        File activeQuestions = new File("Questions\\Active\\");
        File[] dir = activeQuestions.listFiles();

        if (dir == null){
            JOptionPane.showMessageDialog(null, "No files in questions folder");
            System.exit(0);
        }

        //File in = new File("test.txt");

        Vector<Terms> termsVector = new Vector<>();

        for (File f: dir
             ) {


            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_16));


                String str = "";
                while((str = inReader.readLine()) != null) {

                    String jp = str.substring(0, str.indexOf("\t"));
                    String en = str.substring(str.indexOf("\t") + 1);

                    Terms temp = new Terms(jp, en);

                    termsVector.add(temp);

                    //JOptionPane.showMessageDialog(null, str);
                    //System.out.println(str);

                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        JOptionPane.showMessageDialog(null, termsVector.size() + " terms loaded for practice\r\n" +
                "Please enter the number of practice rounds you would like to do on the following screen.");

        int results = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rounds of practice:"));

        Random rand = new Random();

        for(int i = 0; i < results; i++){
            Vector<Integer> questions = new Vector<>();
            for(int jj = 0; jj < 4; jj++){
                int q = rand.nextInt(termsVector.size());
                if (!questions.contains(q)) {
                    questions.add(q);
                }
            }

            int termTested = rand.nextInt(4);

            String result = "";

            JOptionPane.showMessageDialog(null, "Identify the following term on the next screen: " + termsVector.elementAt(questions.elementAt(termTested)).getJpTerm());
            result = JOptionPane.showInputDialog("A: " + termsVector.elementAt(questions.elementAt(0)).getEnTerm() + "\r\n" +
                    "B: "+ termsVector.elementAt(questions.elementAt(1)).getEnTerm() + "\r\n" +
                    "C: " + termsVector.elementAt(questions.elementAt(2)).getEnTerm() + "\r\n" +
                    "D: " + termsVector.elementAt(questions.elementAt(3)).getEnTerm() + "\r\n");

            switch (result.toLowerCase()){
                case "a": if (termTested == 0){
                    JOptionPane.showMessageDialog(null, "Correct!");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect, the correct answer was: " + termsVector.elementAt(questions.elementAt(termTested)).getEnTerm());
                }
                break;
                case "b" : if (termTested == 1){
                    JOptionPane.showMessageDialog(null, "Correct!");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect, the correct answer was: " + termsVector.elementAt(questions.elementAt(termTested)).getEnTerm());
                }
                break;
                case "c": if (termTested == 2){
                    JOptionPane.showMessageDialog(null, "Correct!");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect, the correct answer was: " + termsVector.elementAt(questions.elementAt(termTested)).getEnTerm());
                }
                break;
                case "d": if (termTested == 3){
                    JOptionPane.showMessageDialog(null, "Correct!");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect, the correct answer was: " + termsVector.elementAt(questions.elementAt(termTested)).getEnTerm());
                }
                break;
                default: JOptionPane.showMessageDialog(null, "I'm sorry, I didn't recognize your input");
            }


        }

        /*
        for (Terms t: termsVector
             ) {
            System.out.println("English: " + t.getEnTerm() + " Japanese: " + t.getJpTerm());
        }


         */
    }
}
