import java.io.*;
import java.util.Arrays;

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

            for( String key : map1.getMapperKeyValue().keySet() ){
                System.out.printf( "Value: %s -- Key: %s%n", Arrays.toString( map1.getMapperKeyValue().get(key).toArray() ), key );
            }
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
