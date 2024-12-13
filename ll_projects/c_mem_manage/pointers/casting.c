#include "casting.h"
#include "main.h"
#include <stdio.h>

void array_casting() {
  coordinate_t points[3] = {
      {5, 4, 1},
      {7, 3, 2},
      {9, 6, 8},
  };

  coordinate_t *points_start = (coordinate_t *)points;

  // cast: array of structs -> array of integers
  int *points_start_int = (int *)points;

  for (int i = 0; i < 9; i++) {
    printf("points_start[%d] = %d\n", i, points_start_int[i]);
  }
}

void dump_graphics(graphics_t gsettings[10]) {
  int *ptr = (int *)gsettings;
  unsigned int len = sizeof(graphics_t) / sizeof(int) * 10;

  for (int i = 0; i < len; i++) {
    printf("settings[%d] = %d\n", i, ptr[i]);
  }
}

void array_decaying() {
  int arr[5] = {1, 2, 3, 4, 5};
  int *ptr = arr; // arr decays to int*
  int value = *(arr + 2);
  int same_value = *ptr + 2;
  printf("value: %d - same_value: %d\n", value, same_value);

  int(*whole_ptr)[5] = &arr; // ptr to whole array
  printf("whole_array: ");
  for (int i = 0; i < 5; i++) {
    printf("%d ", (*whole_ptr)[i]);
  }

  size_t size = sizeof(arr);
  printf("\nsizeof arr: %zu\n", sizeof(arr));      // %zu - size_t
  printf("sizeof arr: %zu\n", sizeof(*whole_ptr)); // gives same result - 20

  printf("sizeof arr: %zu\n", sizeof(*ptr)); // 4
  printf("sizeof arr: %zu\n", sizeof(ptr));  // 8 - size of address
}

int main() { array_decaying(); }
