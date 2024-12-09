#pragma once

typedef struct coordinate {
  int x;
  int y;
  int z;
} coordinate_t;

void coordinate_update_x(coordinate_t coor, int new_x);
coordinate_t coordinate_update_and_return_x(coordinate_t coor, int new_x);

typedef struct CodeFile {
  int lines;
  int filetype;
} codefile_t;

codefile_t change_filetype(codefile_t *f, int new_filetype);

void update_file(int filedata[200], int new_filetype, int new_num_lines);
