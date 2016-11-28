import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Barret J. Nobel on 11/27/2016.
 * Class to deal with sorting and shuffling the Mapper data
 */

public class SortAndShuffle {
    private ArrayList< Mapper > mappers;
    private HashMap< String, ArrayList<HashMap<String, Integer>> > keyValues;
    private ArrayList< String > sortedKeys;

    // Constructor takes in an ArrayLIst full of Mapper objects
    public SortAndShuffle( ArrayList< Mapper > _mappers ){
        setMappers( _mappers );
        setKeyValues( new HashMap<>() );
        setSortedKeys( new ArrayList<>() );
    }

    // Method to do all the data processing
    // loops through each Mapper object and extracts the data
    // puts the extracted data into a Hash with the key's being put into another array after being sorted
    public void populateData(){
        for( Mapper mapper : mappers ){ //............................................................ loop through each mapper object
            for (int i = 0; i < mapper.getListOfKeyValuePairs().size(); i++) { //..................... loop through each hash in the mapper
                String key = (String)mapper.getListOfKeyValuePairs().get(i).keySet().toArray()[0]; //. get they key
                HashMap< String, Integer > tempHash = mapper.getListOfKeyValuePairs().get(i); //...... get the hash
                if( !sortedKeys.contains(key) ){ //................................................... if the key isnt in the array yet
                    sortedKeys.add( key ); //......................................................... add it

                    ArrayList< HashMap<String, Integer> > temp = new ArrayList<>(); //................ create and put a new hash in
                    temp.add( tempHash );
                    keyValues.put( key, temp );
                }
                else{
                    keyValues.get( key ).add( tempHash ); //.......................................... key already exists, add hash
                }
            }
        }

        Collections.sort( sortedKeys ); //............................................................ sort the keys
    }

    // Method to return the mappers used
    public ArrayList<Mapper> getMappers() {
        return mappers;
    }

    // Method to set the mappers
    public void setMappers(ArrayList<Mapper> mappers) {
        this.mappers = mappers;
    }

    // Method to get the key/values hash
    public HashMap<String, ArrayList<HashMap<String, Integer>>> getKeyValues() {
        return keyValues;
    }

    // Method to get set hte key/values hash
    public void setKeyValues(HashMap<String, ArrayList<HashMap<String, Integer>>> keyValues) {
        this.keyValues = keyValues;
    }

    // Method to get the sorted keys array
    public ArrayList<String> getSortedKeys() {
        return sortedKeys;
    }

    // Method to set the sorted keys array
    public void setSortedKeys(ArrayList<String> sortedKeys) {
        this.sortedKeys = sortedKeys;
    }
}
