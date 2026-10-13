import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestFilePrueba {
	public static void main(String[] args) throws IOException {
        FileInputStream file;
        byte b[] = new byte[1024];
        try {
            file = new FileInputStream("src/A4/TestFile.java");
            file.read(b);
            String s = new String(b);
            ViewFile view = new ViewFile(s);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(400, 150);
            view.setVisible(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
