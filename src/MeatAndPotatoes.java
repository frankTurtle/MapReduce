import java.io.*;
import java.util.*;

/**
 * Created by Barret J. Nobel on 11/26/2016.
 */

public class MeatAndPotatoes {

    public static void main( String[] args ){
        try {
            Mapper map1 = new Mapper(new File("data.txt"), 0, 66);
            map1.processFile();

            Mapper map2 = new Mapper(new File("data.txt"), 67, 133);
            map2.processFile();

            Mapper map3 = new Mapper(new File("data.txt"), 134, countLines("data.txt"));
            map3.processFile();

            // For Reducer 1
            ArrayList< Mapper > sort1 = new ArrayList<>();
            sort1.add( map1 );
            sort1.add( map2 );

            SortAndShuffle sortAndShuffle = new SortAndShuffle( sort1 );
            sortAndShuffle.sortAndShuffleData();

            ArrayList< SortAndShuffle > shuffle1 = new ArrayList<>();
            shuffle1.add( sortAndShuffle );

            Reducer reducer1 = new Reducer( shuffle1 );



            // For Reducer 2
            ArrayList< Mapper > sort2 = new ArrayList<>();
            sort2.add( map3 );

            SortAndShuffle sortAndShuffle2 = new SortAndShuffle( sort2 );
            sortAndShuffle2.sortAndShuffleData();

            ArrayList< SortAndShuffle > shuffle2 = new ArrayList<>();
            shuffle2.add( sortAndShuffle2 );

            Reducer reducer2 = new Reducer( shuffle2 );



            // REDUCE AND PRODUCE!
            reducer1.reduce();
            reducer2.reduce();

//            printReducerOutput( reducer1, 1 );
//            printReducerOutput( reducer2, 2 );





            //************ SINGLE MAPPER / REDUCER ***************
            Mapper map4 = new Mapper(new File("data.txt"), 0, countLines("data.txt"));
            map4.processFile();

            ArrayList< Mapper >mapper3 = new ArrayList<>();
            mapper3.add( map4 );

            printMapperOutput( mapper3 );

            // For Reducer 2
            ArrayList< Mapper > sort3 = new ArrayList<>();
            sort3.add( map4 );

            SortAndShuffle sortAndShuffle3 = new SortAndShuffle( sort3 );
            sortAndShuffle3.sortAndShuffleData();

            ArrayList< SortAndShuffle > shuffle3 = new ArrayList<>();
            shuffle3.add( sortAndShuffle3 );

            Reducer reducer3 = new Reducer( shuffle3 );

            // REDUCE AND PRODUCE!
            reducer3.reduce();

//            printReducerOutput( reducer3, 1 );
        }
        catch ( IOException e ){ e.printStackTrace(); }
    }

    private static ArrayList< Mapper > splitUpIntoThisNumberOfMappers( int numberOfMappers, String fileName ){
        ArrayList< Mapper > returnMe = new ArrayList<>();
        File fileToRead = new File( fileName );

        try {
            int splitBy = countLines( fileName ) / numberOfMappers;

            for( int i = 0; i < numberOfMappers; i++ ){
                int start = ( i != 0 )
                        ? i * splitBy + i
                        : i * splitBy;

                int end   = ( (start + splitBy) <= countLines(fileName) )
                        ? start + splitBy
                        : countLines(fileName);

                Mapper addMe = new Mapper( fileToRead, start, end );
                addMe.processFile();

                returnMe.add( addMe );
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return returnMe;
    }

    private static void printMapperOutput( ArrayList< Mapper > mappers ){
        int ct = 1;

        for( Mapper mapper : mappers ){
            if( mapper.getListOfKeyValuePairs().size() == 0 ) continue;

            System.out.printf( "******MAPPER %d*******%n", ct++ );

            for (int a = 0; a < mapper.getListOfKeyValuePairs().size(); a++){
                HashMap<String, Integer> tmpData = (HashMap<String, Integer>) mapper.getListOfKeyValuePairs().get(a);
                Set<String> key = tmpData.keySet();
                Iterator it = key.iterator();

                while (it.hasNext()) {
                    String hmKey = (String)it.next();
                    Integer hmData = (Integer) tmpData.get(hmKey);

                    System.out.println("* Key: "+hmKey +" & Data: "+hmData);
                    it.remove(); // avoids a ConcurrentModificationException
                }
            }

            System.out.println( "******************************************\n\n" );
        }
    }

    private static void printReducerOutput( Reducer reducer, int num ){
        System.out.printf( "******REDUCER %d*******%n", num );
        System.out.println( reducer.getReducedList() );
        System.out.println( "******************************************\n\n" );

    }

    private static int countLines( String filename ) throws IOException {
        InputStream inputStream = new BufferedInputStream( new FileInputStream(filename) );
        try {
            byte[] c = new byte[ 1024 ];
            int count = 0;
            int readChars = 0;
            boolean empty = true;

            while ( (readChars = inputStream.read(c) ) != -1 ) {
                empty = false;
                for ( int i = 0; i < readChars; ++i ) {
                    if ( c[ i ] == '\n' ) {
                        ++count;
                    }
                }
            }

            return (count == 0 && !empty) ? 1 : count;
        }
        finally {
            inputStream.close();
        }
    }
}
