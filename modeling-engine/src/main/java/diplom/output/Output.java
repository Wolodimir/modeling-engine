package diplom.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static diplom.Data.*;

public class Output {

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
