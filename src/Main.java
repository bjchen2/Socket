import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        String path = System.clearProperty("user.dir");
        File file = new File(path+"\\resource");
        try {
            System.out.println(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
