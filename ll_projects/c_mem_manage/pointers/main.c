#include "main.h"

void func() {
  int age = 37;
  int *pointer_to_age = &age; // address-of operator - &
  // pointer_to_age holds the address of age
}

// In C, structs are passed by value. That's why updating a field in the
// struct does not change the original struct from the main function. To get
// the change to "persist", we needed to return the updated struct from
// the function (a new copy).
void coordinate_update_x(coordinate_t coor, int new_x) { coor.x = new_x; }

coordinate_t coordinate_update_and_return_x(coordinate_t coor, int new_x) {
  coordinate_t new_coord = coor;
  new_coord.x = new_x;
  return new_coord;
}

// Dereferencing Pointers
void deref() {
  int meaning_of_life = 42;
  int *pointer_to_mol = &meaning_of_life;
  int value_at_p = *pointer_to_mol;
}

codefile_t change_filetype(codefile_t *f, int new_filetype) {
  // accept a pointer to code file: change_filetype(&original) -> struct *

  codefile_t new_f = *f;         // deref pointer into a codefile_t struct
  new_f.filetype = new_filetype; // change the field to new_filetype
  return new_f;                  // return the updated codefile_t struct
}

int return_pointer() {
  coordinate_t point = {10, 20, 30};
  coordinate_t *ptrToPoint = &point;
  return ptrToPoint->x; // (*ptrToPoint).x
}

void update_file(int filedata[200], int new_filetype, int new_num_lines) {
  // filedata[1] = new_num_lines;
  // filedata[2] = new_filetype;
  // filedata[199] = 0;

  int *ptr = filedata;
  ptr++;
  *ptr = new_num_lines;
  ptr++;
  *ptr = new_filetype;

  *(filedata + 199) = 0; // arr[4], *(arr + 4)
}
