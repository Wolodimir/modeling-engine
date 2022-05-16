package diplom.threads;

import diplom.Space;

import static diplom.Data.*;
import static diplom.library.ExternalFunctions.FPF;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Thread5 extends Thread{
    private int startFrom = 400;
    private int endIn = 500;

    @Override
    public void run() {
        for (int i = startFrom; i < endIn; i++) {
            particles[i].FxPrev = particles[i].Fx;
            particles[i].FyPrev = particles[i].Fy;
            particles[i].FzPrev = particles[i].Fz;

            particles[i].x = particles[i].x + particles[i].Vx * dt + 0.5 * (particles[i].FxPrev * pow(dt, 2) / m);
            particles[i].y = particles[i].y + particles[i].Vy * dt + 0.5 * (particles[i].FyPrev * pow(dt, 2) / m);
            particles[i].z = particles[i].z + particles[i].Vz * dt + 0.5 * (particles[i].FzPrev * pow(dt, 2) / m);

            Space.borderConditions(i);
        }
        double r;
        double f0;

        for (int i = startFrom; i < endIn; ++i) {
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(FPF((particles[i].x - particles[j].x), 2)
                            + FPF((particles[i].y - particles[j].y), 2)
                            + FPF((particles[i].z - particles[j].z), 2));
                    f0 = (48 * EPS / FPF(SIG, 2) * (FPF(SIG / r, 13) - FPF(SIG / r, 7)) * r);
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
        }

        for (int i = startFrom; i < endIn; i++) {//определение скорости частиц
            particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
            particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
            particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
        }
    }
}
