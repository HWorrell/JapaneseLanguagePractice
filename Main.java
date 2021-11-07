import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        //pull in config file
        File config = new File("Config.ini");
        //default if it doesn't exist
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
        //set target
        File activeQuestions = new File("Questions\\Active\\");
        //populate array with files
        File[] dir = activeQuestions.listFiles();
        //exit gracefully if there are no files
        if (dir == null){
            JOptionPane.showMessageDialog(null, "No files in questions folder");
            System.exit(0);
        }

        //File in = new File("test.txt");
        //vector for terms to be tested on
        Vector<Terms> termsVector = new Vector<>();
        //for each file in the array
        for (File f: dir
             ) {


            try {
                //read the file in
                BufferedReader inReader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_16));

                //init
                String str = "";
                //while we're still getting something from the reader
                while((str = inReader.readLine()) != null) {
                    //split the string on the tab, throw away the tab
                    String jp = str.substring(0, str.indexOf("\t"));
                    String en = str.substring(str.indexOf("\t") + 1);
                    //create a term object
                    Terms temp = new Terms(jp, en);
                    //add it to the vector
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

        //get number of rounds for practice
        int results = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rounds of practice:"));
        //initialize random
        Random rand = new Random();

        for(int i = 0; i < results; i++){
            //store the absolute index numbers of the questions we're testing on it in this vector
            Vector<Integer> questions = new Vector<>();
            //populate four random terms into the vector
            for(int jj = 0; jj < 4; jj++){
                int q = rand.nextInt(termsVector.size());
                if (questionHistoryVector.contains(q)){
                    if (rand.nextInt(10) > 2){
                        jj--;
                        System.out.println("Rerolling due to repeat (number:" + questionHistoryVector.size() + ")" +
                                "" +
                                "" +
                                "");
                    }
                    else{
                        //make sure we're not adding it again
                        if (!questions.contains(q)) {
                            questions.add(q);
                            numHolding[jj] = q;
                        } else {
                            //deincrement if we don't populate something, otherwise we overflow the array size.
                            jj--;
                        }
                    }
                }
                else{
                    //make sure we're not adding it again
                    if (!questions.contains(q)) {
                        questions.add(q);
                        numHolding[jj] = q;
                    } else {
                        //deincrement if we don't populate something, otherwise we overflow the array size.
                        jj--;
                    }
                }
            }

            //pick the term we're testing on randomly
            int termTested = rand.nextInt(4);

            String result = "";

            //get the jp term and display it
            JOptionPane.showMessageDialog(null, "Identify the following term on the next screen: " + termsVector.elementAt(questions.elementAt(termTested)).getJpTerm());
            //get the english terms
            result = JOptionPane.showInputDialog("A: " + termsVector.elementAt(questions.elementAt(0)).getEnTerm() + "\r\n" +
                    "B: "+ termsVector.elementAt(questions.elementAt(1)).getEnTerm() + "\r\n" +
                    "C: " + termsVector.elementAt(questions.elementAt(2)).getEnTerm() + "\r\n" +
                    "D: " + termsVector.elementAt(questions.elementAt(3)).getEnTerm() + "\r\n");

            //set it to lower case to deal with case weirdness
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
                //add default in case another character is used
                default: JOptionPane.showMessageDialog(null, "I'm sorry, I didn't recognize your input\r\n" + "The Correct answer was " + termsVector.elementAt(questions.elementAt(termTested)).getEnTerm());
            }


        }

        //testing garbage.  Useful for checking through
        /*
        for (Terms t: termsVector
             ) {
            System.out.println("English: " + t.getEnTerm() + " Japanese: " + t.getJpTerm());
        }


         */
    }
}
