import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class Main {
    public static void main(String[] args) throws IOException {

        // create a object for constant class
        Constants obj = new Constants();

        if (args == null || args.length != 1) {
            System.out.println("Please provide a, r, ?, + or c as argument");
            return;
        }

        // Every operation requires us to load the student list.
        String fileContents = LoadData(Constants.StudentList);

        if (args[0].equals(obj.ShowAll)) {

            System.out.println("Loading data ...");

            try {
                String[] words = fileContents.split(obj.StudentEntryDelimiter);

                for (String word : words) {
                    System.out.println(word);
                }

            }

            catch (Exception e) {

            }

            System.out.println("Data Loaded.");

        }

        else if (args[0].equals(obj.ShowRandom)) {

            System.out.println("Loading data ...");

            try {
                String[] words = fileContents.split(obj.StudentEntryDelimiter);
                Random random = new Random();
                int randomIndex = random.nextInt(0, words.length);
                System.out.println(words[randomIndex]);
            }

            catch (Exception e) {

            }

            System.out.println("Data Loaded.");

        }

        else if (args[0].contains(obj.AddEntry)) {

            System.out.println("Loading data ...");

            try {
                BufferedWriter fileStream = new BufferedWriter(
                        new FileWriter("students.txt", true));
                String argValue = args[0].substring(1);
                Date date = new Date();
                String dateFormatObj = "dd/mm/yyyy-hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(dateFormatObj);
                String formatDate = dateFormat.format(date);
                fileStream.write(", " + argValue + "\nList last updated on " + formatDate);
                fileStream.close();
            }

            catch (Exception e) {

            }

            System.out.println("Data Loaded.");

        }

        else if (args[0].contains(obj.FindEntry)) {

            System.out.println("Loading data ...");

            String[] words = fileContents.split(obj.StudentEntryDelimiter);
            boolean done = false;
            String argValue = args[0].substring(1);

            for (int idx = 0; idx < words.length && !done; idx++) {

                if (words[idx].equals(argValue)) {
                    System.out.println("We found it!");
                    done = true;
                }

            }

            System.out.println("Data Loaded.");

        }

        else if (args[0].contains(obj.ShowCount)) {

            System.out.println("Loading data ...");

            try {
                char[] characters = fileContents.toCharArray();
                boolean in_word = false;
                int count = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!in_word) {
                            count++;
                            in_word = true;
                        }

                        else {
                            in_word = false;
                        }
                    }
                }

                System.out.println(count + " word(s) found " + characters.length);

            }

            catch (Exception e) {

            }

            System.out.println("Data Loaded.");
        }
    }

    public static String LoadData(String fileName) {

        // create a object for constant class
        Constants obj = new Constants();

        BufferedReader fileStream = null;
        try {
            fileStream = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Constants.StudentList)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String reader = null;
        try {
            reader = fileStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }
}
