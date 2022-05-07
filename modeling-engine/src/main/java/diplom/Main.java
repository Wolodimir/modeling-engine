package diplom;

import diplom.library.PowerAlgorithms;
import diplom.output.Output;

import java.io.File;
import java.io.IOException;

import static diplom.Data.*;
import static java.lang.Math.pow;

public class Main {

    public static void start() throws Exception {
        Data.initStaticVariables();
        Space.initialCoords(D);
        PowerAlgorithms.calcPowers();
        simulationInTime();
    }

    public static void simulationInTime() throws IOException {
        File file1 = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        file1.delete();
        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        int k = 0;//счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {
            for (int i = 0; i < N; i++) {
                particles[i].FxPrev = particles[i].Fx;
                particles[i].FyPrev = particles[i].Fy;
                particles[i].FzPrev = particles[i].Fz;
            }

            for (int i = 0; i < N; i++) {
                //sign(x2-x1)*F/m1
                particles[i].x = particles[i].x + particles[i].Vx * dt + particles[i].x - * pow(dt, 2);
                particles[i].y = particles[i].y + particles[i].Vy * dt + (particles[i].FyPrev * pow(dt, 2) / m);
                particles[i].z = particles[i].z + particles[i].Vz * dt + (particles[i].FzPrev * pow(dt, 2) / m);

                Space.borderConditions(i);
            }
            PowerAlgorithms.calcPowers();
            //PowerAlgorithms.staticGrid();

            for (int i = 0; i < N; i++) {//определение скорости частиц
                particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
                particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
                particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
            }

            System.out.println("---------" + k + "---------");
            k++;

            //if (k > 900000) {
                if (k % 1 == 0){
                    Output.csvForGraphics(file, k);
                }
            //}

            if (k == steps) {
                System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - hhhh) / 1000 + "s");
                break;
            }
        }
    }


}
