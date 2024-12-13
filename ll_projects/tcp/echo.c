#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>

#define ADDRESS "127.0.0.1"

char buffer[1000000];

int main() {
  memset(buffer, 'a', sizeof(buffer));

  int sock = socket(AF_INET, SOCK_STREAM, 0);

  // struct linger so_linger;
  // so_linger.l_onoff = true;
  // so_linger.l_linger = 30;
  // setsockopt(sock, SOL_SOCKET, SO_LINGER, &so_linger, sizeof(so_linger));

  // sockaddr_in for IPv4
  struct sockaddr_in remote;
  remote.sin_family = AF_INET;
  inet_pton(AF_INET, ADDRESS, &remote.sin_addr);
  remote.sin_port = htons(6969);

  connect(sock, (struct sockaddr *)&remote, sizeof(remote));
  write(sock, buffer, sizeof(buffer));

  shutdown(sock, SHUT_WR);
  int bytesRead = 0, res;
  for (;;) {
    res = read(sock, buffer, 4000);
    if (res < 0) {
      perror("reading");
      exit(1);
    }
    if (!res)
      break;
    bytesRead += res;
  }
  printf("Bytes recieved from client: %d\n", bytesRead);

  close(sock);

  return 0;
}
