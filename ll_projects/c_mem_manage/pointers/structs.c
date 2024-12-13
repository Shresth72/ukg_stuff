#include <string.h>

// Forward Declaration
typedef struct SnekObject snekobject_t;

typedef struct SnekObject {
  char *name;
  snekobject_t *child;
} snekobject_t;

snekobject_t new_node(char *name) {
  snekobject_t node = {
      .name = name,
      .child = NULL,
  };
  return node;
}

// Mutual Structs (Circular Reference)
typedef struct Employee employee_t;
typedef struct Department department_t;

struct Employee {
  int id;
  char *name;
  department_t *department;
};

struct Department {
  char *name;
  employee_t *manager;
};

employee_t create_employee(int id, char *name) {
  employee_t emp = {.id = id, .name = name, .department = NULL};
  return emp;
}

department_t create_department(char *name) {
  department_t dept = {.name = name, .manager = NULL};
  return dept;
}

void assign_employee(employee_t *emp, department_t *department) {
  emp->department = department;
}

void assign_manager(department_t *dept, employee_t *manager) {
  dept->manager = manager;
}
