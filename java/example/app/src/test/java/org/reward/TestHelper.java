package org.reward;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public interface TestHelper {
  RewardService getRewardService();

  @BeforeEach
  default void beforeEach() {
    System.out.println("BeforeEach is implemented");
  }

  @Test
  default void correctPoints() {
    Assertions.assertEquals(100, RewardService.class);
  }
}
