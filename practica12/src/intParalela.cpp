#include <iostream>
#include <random>
#include <thread>
#include <mutex>
using namespace std;

// función a integrar
double func(double x) {
    return x*x;
}

// Variables compartidas
int totalHits = 0;
mutex m;

// Rango de integración
double xMin = 0;
double xMax = 1;
double yMin = 0;
double yMax = 1;

// Número de puntos a lanzar
int nPoints;

// Número de hilos
int nThreads;

void monteCarlo(int id) {
    mt19937 gen(random_device{}());
    uniform_real_distribution<double> xDist(xMin, xMax);
    uniform_real_distribution<double> yDist(yMin, yMax);

    int hits = 0;
    for (int i = 0; i < nPoints/nThreads; i++) {
        double x = xDist(gen);
        double y = yDist(gen);
        if (y <= func(x)) {
            hits++;
        }
    }

    // Seccion critica
    m.lock();
    totalHits += hits;
    m.unlock();
}

int main() {
    // Leer el número de puntos a lanzar y el número de tareas paralelas
    cout << "Introduce el número de puntos a lanzar: ";
    cin >> nPoints;
    cout << "Introduce el número de tareas paralelas: ";
    cin >> nThreads;

    // Crear hilos
    thread threads[nThreads];
    for (int i = 0; i < nThreads; i++) {
        threads[i] = thread(monteCarlo, i);
    }

    // Unir hilos
    for (int i = 0; i < nThreads; i++) {
        threads[i].join();
    }

    // Calcular el área bajo la curva
    double area = (xMax - xMin) * (yMax - yMin) * (double) totalHits / nPoints;

    // Imprimir el resultado
    cout << "El área bajo la curva es: " << area << endl;

    return 0;
}
