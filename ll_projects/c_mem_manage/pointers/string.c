#include <stdio.h>
#include <string.h>

void pointers_vs_arrays() {
  char str1[] = "Hi";
  char *str2 = "Snek";
  printf("%s %s\n", str1, str2);

  char first[50] = "Snek";
  char *second = " lang!";
  strcat(first, second);
  printf("Hello, %s\n", first);
}

void concat_string(char *str1, const char *str2) {
  char *ptr = str1;
  while (*ptr != '\0') {
    ptr++;
  }

  const char *ptr2 = str2;
  while (*ptr2 != '\0') {
    *ptr = *ptr2;
    ptr++;
    ptr2++;
  }

  *ptr = '\0';

  printf("final: %s\n", str1);
}

void c_string_lib() {
  // Copy a string to another
  char src[] = "Hello";
  char dest[6];
  strcpy(dest, src);

  // Concatenate
  char dest2[12] = "Helo";
  char src2[] = " World";
  strcat(dest2, src2);

  // Length of string
  size_t len = strlen(dest2);

  // Compare strings
  int result = strcmp(dest, dest2);

  // Copy specified number of chars from one string to another
  char src3[] = "Hello";
  char dest3[6];
  strncpy(dest3, src3, 3);
  dest[3] = '\0'; // ensure null termination

  // Concatenates a specified number of characters from one string to another
  char dest4[12] = "Hello";
  char src4[] = " World";
  strncat(dest4, src4, 3);
  // dest now contains "Hello Wo"

  // Finds the first occurrence of a character in a string
  char str[] = "Hello";
  char *pos = strchr(str, 'l');
  // pos points to the first 'l' in "Hello"

  // Finds the first occurrence of a substring in a string
  char str2[] = "Hello World";
  char *pos2 = strstr(str, "World");
  // pos points to "World" in "Hello World"
}

int main() {
  char str1[100] = "Hello";
  const char *str2 = "Snek";
  concat_string(str1, str2);
  return 0;
}
