all: echo listen

echo: echo.c 
	gcc -Wall -Wextra -o echo echo.c

listen: listen.c 
	gcc -Wall -Wextra -o listen listen.c

rs_echo:
	cargo run --bin echo

rs_listen:
	cargo run --bin listen
