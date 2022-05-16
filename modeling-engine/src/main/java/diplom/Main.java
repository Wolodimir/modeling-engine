package diplom;

import diplom.library.PowerAlgorithms;
import diplom.output.Output;
import diplom.threads.*;

import java.io.File;
import java.io.IOException;

import static diplom.Data.*;
import static java.lang.Math.pow;

public class Main {

    public static void start() throws Exception {
        // Начальные действия, создать объекты, дать им начальные координаты и скорости
        // инициализировать переменные
        Data.initStaticVariables();
        Space.initialCoords(D);
        PowerAlgorithms.calcPowers();
        // Запуск разных вычислительных алгоритмов
        //emptySimulation();
        mainCycle();

    }

    public static void mainCycle() throws IOException, InterruptedException {
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

            System.out.println("---------" + k + "---------");
            k++;

            if (k > 49990000) {
                if (k % 1 == 0){
                    Output.csvFor3D(file, k);
                }
            }

            if (k == steps) {
                System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - hhhh) / 1000 + "s");
                break;
            }
        }
    }

    // Метод, запускающий потоки с методом upTheCycles
    static public void threadingCulcPowers() throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        Thread4 thread4 = new Thread4();
        Thread5 thread5 = new Thread5();
        Thread6 thread6 = new Thread6();
        Thread7 thread7 = new Thread7();
        Thread8 thread8 = new Thread8();
        Thread9 thread9 = new Thread9();
        Thread10 thread10 = new Thread10();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
    }

    /**
     * Вычисления скоростей и координат были объединены с силами,
     * в связи с этим понадобился метод, который только шагает по времени.
     * */
    @Deprecated
    public static void emptySimulation() throws IOException, InterruptedException {

        File file1 = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        file1.delete();
        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");
        int k = 0; // счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {

            PowerAlgorithms.upTheCycles();
            //threadingCulcPowers();
            //PowerAlgorithms.el();

            System.out.println("---------" + k + "---------");
            k++;

            //if (k > 100000) {
            if (k % 1 == 0){
                Output.csvFor3D(file, k);
            }
            //}

            if (k == steps) {
                System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - hhhh) / 1000 + "s");
                break;
            }
        }
    }


}
