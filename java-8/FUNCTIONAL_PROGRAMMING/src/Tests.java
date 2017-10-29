import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Elimane on Oct, 2017, at 18:45
 */
public class Tests {


    public static int getErrorLines(String path) throws IOException {
        File file =  new File(path);
        List<String> lines = FileUtils.readLines(file,"UTF-8");
        int cpt=0;

        for(String l : lines)
        {
            if(l.startsWith("ERROR"))
             cpt++;
        }
        return cpt;
    }

}
