import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Barret J. Nobel on 11/26/2016.
 * Class to Map ALL THE THINGS!
 * Contains a HashMap with the key of the word
 * and the value is an array list with the number of occurrences within the document
 * You're able to specify when the range that you'd like to scan within the File sent in
 */

public class Mapper {
    private HashMap< String, ArrayList<Integer> > mapperKeyValue;
    private int start;
    private int end;
    private File fileToProcess;

    // Constructor with parameters
    // Takes the File, starting line and ending line
    public Mapper( File fileToRead, int startReadingAtThisLine, int endAtThisLine ){
        setMapperKeyValue( new HashMap<>() );
        setStart( startReadingAtThisLine );
        setEnd( endAtThisLine );
        setFileToProcess( fileToRead );
    }

    // Method to get the HashMap containing the words and ArrayList of the count
    public HashMap<String, ArrayList<Integer>> getMapperKeyValue() {
        return mapperKeyValue;
    }

    // Method to set the HashMap
    public void setMapperKeyValue(HashMap<String, ArrayList<Integer>> mapperKeyValue) {
        this.mapperKeyValue = mapperKeyValue;
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
    // loops through each row and calls the method processsLine that will actually has the values
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
    private void processLine( String lineToProcess ){

        for( String word : lineToProcess.split(" ") ){ //.................. loop through each line and split by spaces
            word = makeTheWordPretty( word );

            ArrayList< Integer > current = ( mapperKeyValue.containsKey(word) )
                    ? mapperKeyValue.get( word )
                    : new ArrayList<>();

            current.add( 1 );

            mapperKeyValue.put( word, current );
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
