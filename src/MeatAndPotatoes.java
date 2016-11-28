import java.io.*;
import java.util.*;

/**
 * Created by Barret J. Nobel on 11/26/2016.
 */

public class MeatAndPotatoes {

    public static void main( String[] args ){
//        try {
//            System.out.println( countLines("data.txt") );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Mapper map1 = new Mapper(new File("data2Line.txt"), 0, countLines("data2Line.txt"));
            map1.processFile();

            Mapper map2 = new Mapper(new File("data3.txt"), 0, countLines("data3.txt"));
            map2.processFile();

            Mapper map3 = new Mapper(new File("data.txt"), 0, countLines("data.txt"));
            map3.processFile();

//            for (int a =0; a < map1.getListOfKeyValuePairs().size(); a++){
//                HashMap<String, Integer> tmpData = (HashMap<String, Integer>) map1.getListOfKeyValuePairs().get(a);
//                Set<String> key = tmpData.keySet();
//                Iterator it = key.iterator();
//
//                while (it.hasNext()) {
//                    String hmKey = (String)it.next();
//                    Integer hmData = (Integer) tmpData.get(hmKey);
//
//                    System.out.println("Key: "+hmKey +" & Data: "+hmData);
//                    it.remove(); // avoids a ConcurrentModificationException
//                }
//            }

            ArrayList< Mapper > mappersList = new ArrayList<>();
            mappersList.add( map1 );
            mappersList.add( map2 );
            mappersList.add( map3 );

            SortAndShuffle sortAndShuffle = new SortAndShuffle( mappersList );
            sortAndShuffle.populateData();

            ArrayList< SortAndShuffle > shuffledLIst = new ArrayList<>();
            shuffledLIst.add( sortAndShuffle );

            Reducer reducer = new Reducer( shuffledLIst );
            reducer.reduce();

            System.out.println( reducer.getReducedList() );
        }
        catch ( IOException e ){ e.printStackTrace(); }


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
