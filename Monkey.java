import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/** 
 * Monkey slapping a keyboard simulator
 */

public class Monkey {
    // Monkey type type with these :)
    static String[] JavaCharArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
        "~", "!", "?", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "{", "}", "[", "]", "|", "/", ":", ";", "'", "<", ">", " ", ",", ".", "`", "9"};

    // file path and title of target type type goal
    static String FILE_PATH = "hamlet.txt";
    static String TitleName = "Hamlet";
    static String slappingNow = "currentlySlapping.txt";
    static String bestSlapped = "bestSlapped.txt";
    static double pastPercent = 0.0;
    public static void main(String[] args) throws IOException {
       run();
    }

    // return num chars in file
    public static int findNumChars(String target) throws IOException {
        int charAmount = 0;
        FileUtil fileUtil = new FileUtil(target);
        charAmount = fileUtil.getCharCount();
        return charAmount;
    }

    // monkey go vroom
    public static void run() throws IOException {
        // logic :sunglasses: :handshake:
        int totalNumberOfCharacters = findNumChars(FILE_PATH);
        if ( allGood(totalNumberOfCharacters) ) {
            System.out.println("okie");
            while ( pastPercent < 50.0 ) {
                for ( int i = 0; i < totalNumberOfCharacters; i++ ) {
                    toFile(slappingNow, randomGenerateKeyStroke());
                }
                System.out.println(pastPercent);
                System.out.println("dokie");
                double monkeyPercent = monkeyPercentChecker(FILE_PATH, slappingNow);
                //double charCount = lineCount(FILE_PATH, slappingNow);
                double calcdPercent = calc(monkeyPercent, totalNumberOfCharacters);
                System.out.println(calcdPercent);
                if ( calcdPercent > pastPercent ) {
                    clearLast(bestSlapped);
                    copyToBest(slappingNow, bestSlapped);
                    pastPercent = calcdPercent;
                }
                System.out.println("pal");
                clearLast(slappingNow);
            }
        } else {
            System.out.println("what the fuck???");
        }
    }

    // quick ol checkeroo for base file not being empty
    public static boolean allGood(int charAmount) {
        if ( charAmount == 0 )  {
            return false;
        }
        return true;
    }

    // sends monkey type type to file
    public static void toFile(String target, String appendThis) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(target, true));
            // 9  means new line :sunglasses:
            if ( appendThis == "9" ) {
                out.newLine();
                out.write("");
            } else {
                out.write(appendThis);
                out.close();
            }
        }
        catch ( IOException e ) {
            System.out.println("uh ohhhh!, stinky :puke:");
        }
    }

    // monkey slapping
    public static String randomGenerateKeyStroke() {
        int rnd = new Random().nextInt(JavaCharArray.length);
        return JavaCharArray[rnd];
    }

    // checks characters of known file vs randomly generated file
    public static double monkeyPercentChecker(String from, String to) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(from));
        BufferedReader reader2 = new BufferedReader(new FileReader(to));
        
        int countEqual = 0;
        int c1 = 0;
        int c2 = 0;
        
        while ( (c1 = reader1.read()) != -1 && (c2 = reader2.read()) != -1 ) {
            char char1 = Character.toLowerCase((char) c1);
            char char2 = Character.toLowerCase((char) c2);
            
            if ( char1 != char2 ) {
               
            } else {
                countEqual++;
            }
        }

        reader1.close();
        reader2.close();
        double doubleCount = countEqual;
        return doubleCount;
        }

        public static boolean copyToBest(String from, String to) throws IOException {
            File a = new File(from);
            File b = new File(to);
            FileInputStream in = new FileInputStream(a);
            FileOutputStream out = new FileOutputStream(b);
  
        try {
  
            int n;
  
            // read() function to read the
            // byte of data
            while ((n = in.read()) != -1) {
                // write() function to write
                // the byte of data
                out.write(n);
            }
        }
        finally {
            if (in != null) {
  
                // close() function to close the
                // stream
                in.close();
            }
            // close() function to close
            // the stream
            if (out != null) {
                out.close();
            }
        }
        //System.out.println("File Copied");
            return true;
        }

        // delets contents of a file
        public static void clearLast(String from) throws IOException {
            FileWriter fwOb = new FileWriter(from, false); 
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        }


    
        // working - counts chars in a file 
        public static double lineCount(String from, String to) throws IOException {
            BufferedReader reader1 = new BufferedReader(new FileReader(from));
            BufferedReader reader2 = new BufferedReader(new FileReader(to));
            
            int lineNum = 0;
            
            int c1 = 0;
            int c2 = 0;

            
            while ( (c1 = reader1.read()) != -1 && (c2 = reader2.read()) != -1 ) {
            char char1 = Character.toLowerCase((char) c1);
            char char2 = Character.toLowerCase((char) c2);
            
            if ( char1 != char2 ) {
                lineNum++;
            } else {
                lineNum++;
            }
        }
             
            reader1.close();
            reader2.close();

            double doubleLineNum = lineNum - 1;
            //System.out.println(doubleLineNum);
            return doubleLineNum + 1;
        }

        // percentage calc for monkey slap completition rate
        public static double calc(double a, double b) {
            return a/b*100;
        }
}