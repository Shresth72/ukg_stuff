#include <stdio.h>
#include <stdlib.h>

#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>

char buffer[4096];

int main() {
  struct sockaddr_in local;
  local.sin_family = AF_INET;
  inet_pton(AF_INET, "0.0.0.0", &local.sin_addr);
  local.sin_port = htons(6969);

  int sock = socket(AF_INET, SOCK_STREAM, 0);
  bind(sock, (struct sockaddr *)&local, sizeof(local));
  listen(sock, 128);

  int client = accept(sock, NULL, NULL);
  write(client, "220 Welcome\r\n", 13);

  int bytesRead = 0, res;
  for (;;) {
    res = read(client, buffer, sizeof(buffer));
    if (res < 0) {
      perror("reading");
      break;
    }
    if (!res)
      break;
    bytesRead += res;
  }

  printf("%d\n", bytesRead);
  close(sock);

  return 0;
}
