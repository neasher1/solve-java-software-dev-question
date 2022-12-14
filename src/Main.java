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
            ShowUsage();
            return;
        }

        // Every operation requires us to load the student list.
        String fileContents = LoadData(Constants.StudentList);

        // matches the arguments of a
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

        // identical random numbers.
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

        // add another student
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

        // find student name from file
        else if (args[0].contains(obj.FindEntry)) {

            System.out.println("Loading data ...");

            String[] words = fileContents.split(obj.StudentEntryDelimiter);
            String argValue = args[0].substring(1);
            int indexLocation = -1;

            for (int idx = 0; idx < words.length; idx++) {
                if (words[idx].trim().equals(argValue)) {
                    indexLocation = idx;
                    break;
                }
            }

            if (indexLocation >= 0) {
                System.out.println(String.format("ArgValue =  %s is exist, We found it", argValue));
            } else {

                System.out.println(String.format("ArgValue =  %s is not exist, Not Found", argValue));

            }

            System.out.println("Data Loaded.");

        }

        // find words in a file
        else if (args[0].contains(obj.ShowCount)) {

            System.out.println("Loading data ...");

            String[] words = fileContents.split(obj.StudentEntryDelimiter);
            System.out.println(String.format("%d words found", words.length));

            System.out.println("Data Loaded.");
        }

        else {
            ShowUsage();
        }
    }

    public static String LoadData(String fileName) {

        // create a object for constant class
        Constants obj = new Constants();

        // Reads data from the given file.
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

    static void ShowUsage() {
        System.out.println("Please provide a, r, ?, + or c as argument");
    }
}
