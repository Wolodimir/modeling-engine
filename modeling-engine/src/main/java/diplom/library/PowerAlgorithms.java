package diplom.library;

import static diplom.Data.*;
import static diplom.Data.particles;
import static diplom.library.ExternalFunctions.FPF;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PowerAlgorithms {

    public static void calcPowers() {
        double r;
        double f0;

        for (int i = 0; i < N; ++i) {
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(pow((particles[i].x - particles[j].x), 2)
                            + pow((particles[i].y - particles[j].y), 2)
                            + pow((particles[i].z - particles[j].z), 2));
                    f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / r), 13) - 0.5 * FPF((SIG / r), 7));
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
        }
    }

}
