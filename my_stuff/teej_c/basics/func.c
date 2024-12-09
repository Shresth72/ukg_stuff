#include "func.h"
#include <stdio.h>

float get_average(int x, int y, int z) { return (float)(x + y + z) / 3; }

float snek_score(int num_files, int num_contributors, int num_commits,
                 float avg_bug_criticality) {
  int size_factor = num_commits * num_files;
  int complexity_factor = num_contributors += size_factor;
  return complexity_factor * avg_bug_criticality;
}

char *get_temperature_status(int temp) {
  if (temp < 70) {
    return "too cold";
  } else if (temp > 90) {
    return "too hot";
  } else {
    return "just right";
  }
}

void get_size() { printf("sizeof(char) = %zu\n", sizeof(char)); }

void print_numbers(int start, int end) {
  for (int i = start; i <= end; i++) {
    printf("%d\n", i);
  }
}
