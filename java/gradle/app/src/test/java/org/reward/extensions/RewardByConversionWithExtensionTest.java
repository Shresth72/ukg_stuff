package org.reward.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.reward.RewardConversionService;
import org.reward.RewardInformation;

// Collection JUnit-5 extensions
// https://github.com/junit-team/junit5-samples/tree/main/junit5-jupiter-extensions

@TestClassWithErrorHandler
public class RewardByConversionWithExtensionTest {
  @Nested
  class OneNestedTest {
    @TestWithErrorHandler
    void changeAmount(RewardConversionService reward) {
      reward.setAmount(-20);

      assertEquals(-20, reward.getAmount());
    }
  }

  @AfterEach
  void tearDown() {
    System.out.println("AfterEach");
  }

  @AfterAll
  void tearDownAll() {
    System.out.println("AfterAll");
  }

  @Test
  void changeAmount(RewardConversionService reward) {
    System.out.println("Test changeAmount");
    reward.setAmount(20);

    assertEquals(20, reward.getAmount());
  }

  @Test
  void rewardNotAppliedEmptyOrder(RewardConversionService reward) {
    System.out.println("Test rewardNotAppliedEmptyOrder");
    RewardInformation info = reward.applyReward(new ArrayList<>(), 500);

    assertEquals(0, info.getPointsRedeemed());
    assertEquals(0, info.getDiscount());
  }
}
