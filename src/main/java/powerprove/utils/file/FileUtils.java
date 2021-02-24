package powerprove.utils.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class FileUtils {
    public static File getAlreadyExistFile(String path) throws FileNotFoundException {
        File file;

        if ((null == path) || (true == path.isEmpty())) {
            throw new NullPointerException();
        }

        file = new File(path);

        if (false == file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        return file;
    }

    public static File getNotExistFile(String path) throws FileAlreadyExistsException {
        File file;

        if ((null == path) || (true == path.isEmpty())) {
            throw new NullPointerException();
        }

        file = new File(path);

        if (true == file.exists()) {
            throw new FileAlreadyExistsException(file.getAbsolutePath());
        }

        return file;
    }
}
