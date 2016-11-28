import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Barret J. Nobel on 11/27/2016.
 * Method to reduce the SortAndShuffle'd list
 */

public class Reducer {
    private ArrayList< SortAndShuffle > shuffled;
    private ArrayList< HashMap<String, Integer> > reducedList;

    // Constructor with parameters
    // takes in an ArrayList full of SortAndShuffle objects
    public Reducer( ArrayList<SortAndShuffle> shuffledList ){
        setShuffled( shuffledList );
        setReducedList( new ArrayList<>() );
    }

    // Method to reduce everything into a single package
    public void reduce(){
        for( SortAndShuffle list : shuffled ){
            for( String key : list.getSortedKeys() ){
                HashMap< String, Integer > temp = new HashMap<>();
                temp.put( key, list.getKeyValues().get(key).size() );

                if( !reducedList.contains(temp) ) reducedList.add( temp );
            }
        }
    }

    // Method to get the SortAndShuffle object
    public ArrayList<SortAndShuffle> getShuffled() {
        return shuffled;
    }

    // Method to set the ArrayLIst of SortAndShuffle objects
    public void setShuffled(ArrayList<SortAndShuffle> shuffled) {
        this.shuffled = shuffled;
    }

    // Method to get the ArrayList of reduced objects
    public ArrayList<HashMap<String, Integer>> getReducedList() {
        return reducedList;
    }

    // Method to set the ArrayLIst of reduced objects
    public void setReducedList(ArrayList<HashMap<String, Integer>> reducedList) {
        this.reducedList = reducedList;
    }
}
