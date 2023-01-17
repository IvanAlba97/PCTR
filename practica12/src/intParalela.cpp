#include <iostream>
#include <random>
#include <thread>
#include <mutex>

using namespace std;

int totalHits = 0;
Lock m;
int nPuntos;
int nThreads;

void monteCarlo(int id) {
    int hits = 0;
    for (int i = 0; i < nPuntos/nThreads; i++) {
        double x = (static_cast<double>(rand()) / RAND_MAX);
        double y = (static_cast<double>(rand()) / RAND_MAX);
        if (y <= x*x) {
            hits++;
        }
    }

    m.lock();
    totalHits += hits;
    m.unlock();
}

int main() {
    cout << "Introduce el número de puntos a lanzar: ";
    cin >> nPuntos;
    cout << "Introduce el número de tareas paralelas: ";
    cin >> nThreads;

    thread threads[nThreads];
    for (int i = 0; i < nThreads; i++) {
        threads[i] = thread(monteCarlo, i);
    }

    for (int i = 0; i < nThreads; i++) {
        threads[i].join();
    }

    double area = (double) totalHits / nPuntos;

    cout << "El área bajo la curva es: " << area << endl;

    return 0;
}
