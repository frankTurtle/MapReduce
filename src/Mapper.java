import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Barret J. Nobel on 11/26/2016.
 * Class to Map ALL THE THINGS!
 * Contains an ArrayList full of HashMaps with the key of the word
 * and the value an Integer with value 1
 * You're able to specify when the range that you'd like to scan within the File sent in
 */

public class Mapper {
    private ArrayList< HashMap<String, Integer> > listOfKeyValuePairs;
    private int start;
    private int end;
    private File fileToProcess;

    // Constructor with parameters
    // Takes the File, starting line and ending line
    public Mapper( File fileToRead, int startReadingAtThisLine, int endAtThisLine ){
        setStart( startReadingAtThisLine );
        setEnd( endAtThisLine );
        setFileToProcess( fileToRead );
        setListOfKeyValuePairs( new ArrayList<>() );
    }

    // Method to return the ArrayList of key/value pairs
    public ArrayList<HashMap<String, Integer>> getListOfKeyValuePairs() {
        return listOfKeyValuePairs;
    }

    // Method tos et the ArrayList of key/value pairs
    public void setListOfKeyValuePairs(ArrayList<HashMap<String, Integer>> listOfKeyValuePairs) {
        this.listOfKeyValuePairs = listOfKeyValuePairs;
    }

    // Method to get the starting row
    public int getStart() {
        return start;
    }

    // Method to set the starting row
    public void setStart(int start) {
        this.start = start;
    }

    // Method to get the ending row
    public int getEnd() {
        return end;
    }

    // Method to set the ending row
    public void setEnd(int end) {
        this.end = end;
    }

    // Method to return the file you want to process
    public File getFileToProcess() {
        return fileToProcess;
    }

    // Method to set the file you want to process
    public void setFileToProcess(File fileToProcess) {
        this.fileToProcess = fileToProcess;
    }

    // Method to process the entire file
    // loops through each row and calls the method processLine that will actually has the values
    public void processFile(){
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream( fileToProcess );
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(fileInputStream) );

            for( int i = 0; i < start; i++ ) bufferedReader.readLine(); //................................... ensures it starts at the beginning of the line specified

            for( int i = start; i < end; i++ ){
                processLine( bufferedReader.readLine() );
            }
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    // Method to process the line sent in
    // adds each word to a new HashMap and adds to the ArrayList
    private void processLine( String lineToProcess ){
        for( String word : lineToProcess.split(" ") ){ //.................. loop through each line and split by spaces
            HashMap< String, Integer > addMe = new HashMap<>();
            addMe.put( makeTheWordPretty(word), 1 );

            listOfKeyValuePairs.add( addMe );
        }
    }

    // Method that replaces all ' with nothing ( ie can't == cant )
    // also replaces all . with nothing ( ie cant. == cant )
    // makes all text lowercase as well
    private String makeTheWordPretty( String prettyifyMe ){
        prettyifyMe = prettyifyMe.replaceAll( "\'", "" );
        prettyifyMe = prettyifyMe.replaceAll( "\\.", "" );
        prettyifyMe = prettyifyMe.toLowerCase();

        return prettyifyMe;
    }
}
