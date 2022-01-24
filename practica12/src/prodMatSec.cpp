#include <iostream>
#include <cstdlib>
#include <chrono>
#include <ctime>

int main() {

    std::chrono::time_point<std::chrono::system_clock> start, end;
    const int N = 1000;

    double A[N][N];
    double B[N][N];
    double C[N][N];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            A[i][j] = rand() % 10 + 1;
            B[i][j] = rand() % 10 + 1;
        }
    }

    start = std::chrono::system_clock::now();

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                C[i][j] += A[i][k] + B[k][j];
            }
        }
    }

    std::chrono::duration<double> elapsed_seconds = end-start;
    System.out.println("Tiempo: " + elapsed_seconds.count() + " segundos.");
}