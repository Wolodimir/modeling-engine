package diplom.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static diplom.Data.*;

public class Output {

    public static File createFileForDraw() {
        File file1 = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        file1.delete();

        return new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
    }

    public static File createFileForGraphics() throws IOException {
        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/graph.csv");

        FileWriter fw = new FileWriter(file, true);
        fw.write("\"step\",\"Kin\",\"Pot\"" + "\n");
        fw.close();

        return file;
    }

    public static void csvFor3D(File file, int k) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < N; i++) {
            fw.write(particles[i].x + "," + particles[i].y + "," + particles[i].z + "\n");
        }
        fw.write("step \n");
        fw.close();
    }

    public static void csvForISO(File file, int k) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < N; i++) {
            fw.write(particles[i].x + "," + particles[i].y + "," + particles[i].z + "\n");
        }
        fw.write("step \n");
        fw.close();
    }

    public static void csvKinAndPotEnergy(File file, int step) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(step + ","+ kin + "," + pot + "\n");
        //fw.write("step \n");
        fw.close();
    }
}
