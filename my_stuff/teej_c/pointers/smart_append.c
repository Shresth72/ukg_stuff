#include <string.h>

typedef struct {
  char buffer[64];
  size_t length;
} TextBuffer;

int smart_append(TextBuffer *dest, const char *src) {
  if (!dest || !src) {
    return 1;
  }

  size_t bufferSize = sizeof(dest->buffer);
  size_t destLen = dest->length;
  size_t srcLen = strlen(src);

  if (destLen >= bufferSize - 1) {
    return 1;
  }

  size_t rem = bufferSize - 1 - destLen;

  if (srcLen > rem) {
    strncat(dest->buffer, src, rem);
    dest->buffer[bufferSize - 1] = '\0';
    dest->length = bufferSize - 1;
    return 1;
  }

  strcat(dest->buffer, src);
  dest->length += srcLen;
  return 0;
}
