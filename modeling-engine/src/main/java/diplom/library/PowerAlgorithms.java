package diplom.library;

import diplom.Space;

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
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(FPF((particles[i].x - particles[j].x), 2)
                            + FPF((particles[i].y - particles[j].y), 2)
                            + FPF((particles[i].z - particles[j].z), 2));
                    f0 = (48 * EPS / FPF(SIG, 2) * (FPF(SIG / r, 13) - FPF(SIG / r, 7)) * r)/* * 10E-8*/;
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
        }
    }

    public static void calcEverything() {

        for (int i = 0; i < N; i++) {
            particles[i].FxPrev = particles[i].Fx;
            particles[i].FyPrev = particles[i].Fy;
            particles[i].FzPrev = particles[i].Fz;

            particles[i].x = particles[i].x + particles[i].Vx * dt + 0.5 * (particles[i].FxPrev * pow(dt, 2) / m);
            particles[i].y = particles[i].y + particles[i].Vy * dt + 0.5 * (particles[i].FyPrev * pow(dt, 2) / m);
            particles[i].z = particles[i].z + particles[i].Vz * dt + 0.5 * (particles[i].FzPrev * pow(dt, 2) / m);

            Space.borderConditions(i);
        }
        PowerAlgorithms.calcPowers();

        for (int i = 0; i < N; i++) {//определение скорости частиц
            particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
            particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
            particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
        }
    }

    /**
     * В этом методе объединены все вычисления, которые проводятся с частицей. Удобен для разделения
     * на потоки, чтения и анализа.
     * */
    @Deprecated
    public static void upTheCycles() {
        double r;
        double f0;

        for (int i = 0; i < N; ++i) {
            //Сохраняем силы, действовавшие на предыдущем шаге
            particles[i].FxPrev = particles[i].Fx;
            particles[i].FyPrev = particles[i].Fy;
            particles[i].FzPrev = particles[i].Fz;
            //Отчищаем место для новых сил
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
            //Рассчитываем положение частиц (Формулу уточнить)
            /*particles[i].x = particles[i].x + particles[i].Vx * dt + 0.5 * (particles[i].FxPrev * FPF(dt, 2) / m);
            particles[i].y = particles[i].y + particles[i].Vy * dt + 0.5 *  (particles[i].FyPrev * FPF(dt, 2) / m);
            particles[i].z = particles[i].z + particles[i].Vz * dt + 0.5 *  (particles[i].FzPrev * FPF(dt, 2) / m);*/
            particles[i].x = particles[i].x + particles[i].Vx * dt + 0.5 * (particles[i].FxPrev * pow(dt, 2) / m);
            particles[i].y = particles[i].y + particles[i].Vy * dt + 0.5 * (particles[i].FyPrev * pow(dt, 2) / m);
            particles[i].z = particles[i].z + particles[i].Vz * dt + 0.5 * (particles[i].FzPrev * pow(dt, 2) / m);
            //Учитываем стенки куба
            Space.borderConditions(i);
            //Проводим анализ взаимодействия со всеми частицами в системе, по очереди
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(FPF((particles[i].x - particles[j].x), 2)
                            + FPF((particles[i].y - particles[j].y), 2)
                            + FPF((particles[i].z - particles[j].z), 2));
                    f0 = (48 * EPS / FPF(SIG, 2) * (FPF(SIG / r, 13) - FPF(SIG / r, 7)) * r)/* * 10E-8*/;
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
            //Расчитываем скорости частиц на шаге (Формулу уточнить)
            /*particles[i].Vx = particles[i].Vx + 0.5 * (particles[i].Fx / m) * dt;
            particles[i].Vy = particles[i].Vy + 0.5 * (particles[i].Fy / m) * dt;
            particles[i].Vz = particles[i].Vz + 0.5 * (particles[i].Fz / m) * dt;*/
            particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
            particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
            particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
        }
    }

    public static void EilerAlg() {
        double r;
        double f0;

        for (int i = 0; i < N; ++i) {
            //Отчищаем место для новых сил
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
            //Учитываем стенки куба
            Space.borderConditions(i);
            //Проводим анализ взаимодействия со всеми частицами в системе, по очереди
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    r = sqrt(FPF((particles[i].x - particles[j].x), 2)
                            + FPF((particles[i].y - particles[j].y), 2)
                            + FPF((particles[i].z - particles[j].z), 2));
                    f0 = (48 * EPS / FPF(SIG, 2) * (FPF(SIG / r, 13) - FPF(SIG / r, 7)) * r)/* * 10E-8*/;
                    particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                    particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                    particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                }
            }
            particles[i].Vx = particles[i].Vx + (particles[i].Fx / m) * dt;
            particles[i].Vy = particles[i].Vy + (particles[i].Fy / m) * dt;
            particles[i].Vz = particles[i].Vz + (particles[i].Fz / m) * dt;
            //Рассчитываем положение частиц (Формулу уточнить)
            particles[i].x = particles[i].x + particles[i].Vx * dt;
            particles[i].y = particles[i].y + particles[i].Vy * dt;
            particles[i].z = particles[i].z + particles[i].Vz * dt;
        }
    }

}
