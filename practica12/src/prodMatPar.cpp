#include <iostream>
#include <cstdlib>
#include <chrono>
#include <ctime>
#include <utility>
#include <functional>
#include <thread>

const int N = 1000;

double C[N][N];

void concurrentFunction1(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                C[i][j] += A[i][k] + B[k][j];
            }
        }
    }
}

void concurrentFunction2(int n) {
    for (int i = n; i < N; i++) {
        for (int j = n; j < N; j++) {
            for (int k = n; k < N; k++) {
                C[i][j] += A[i][k] + B[k][j];
            }
        }
    }
}

int main() {

    std::chrono::time_point<std::chrono::system_clock> start, end;

    double A[N][N];
    double B[N][N];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            A[i][j] = rand() % 10 + 1;
            B[i][j] = rand() % 10 + 1;
        }
    }

    int range = N / 2;

    start = std::chrono::system_clock::now();

    std::thread t1;
    std::thread t2(concurrentFunction1, range);
    std::thread t3(concurrentFunction2, range);
    std::thread t4(std::move(t3));
    t2.join();
    t4.join();

    std::chrono::duration<double> elapsed_seconds = end-start;
    System.out.println("Tiempo: " + elapsed_seconds.count() + " segundos.");
}