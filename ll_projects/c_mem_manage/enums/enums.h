#pragma once

typedef enum DaysOfWeek {
  MONDAY,
  TACO_TUESDAY,
  WED,
} days_of_week_t;

typedef struct Event {
  char *title;
  days_of_week_t day;
} event_t;
