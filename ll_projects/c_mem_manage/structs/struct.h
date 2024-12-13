#pragma once

// Struct
struct City {
  char *name;
  int lat;
  int lon;
};

struct Coordinate {
  int x;
  int y;
  int z;
};

struct Coordinate new_coord(int x, int y, int z);
struct Coordinate scale_coordinate(struct Coordinate coord, int factor);

// Typedef
typedef struct {
  int x;
  int y;
  int z;
} coordinate_t;

coordinate_t new_coord_t(int x, int y, int z);
coordinate_t scale_coordinate_t(coordinate_t coord, int factor);

// Padding
typedef struct SneklangVar {
  char type;
  char is_constant;
  int value;
  int scope_level;
  char *name;
} sneklang_var_t;
