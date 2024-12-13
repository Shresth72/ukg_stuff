#pragma once

float get_average(int x, int y, int z);

float snek_score(int num_files, int num_contributors, int num_commits,
                 float avg_bug_criticality);

char *get_temperature_status(int temp);

void print_numbers(int start, int end);
