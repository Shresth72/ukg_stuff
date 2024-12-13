package org.reward;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.product.Product;

public class Reward4GiftServiceTest implements TestHelper {
  private RewardByGiftService reward = null;

  @Override
  public RewardService getRewardService() {
    return reward;
  }

  @BeforeEach
  void setUp() {
    reward = new RewardByGiftService();
    reward.setGiftProductId(4);
    reward.setNeededPoints(100);
  }

  @RepeatedTest(value = 5, name = "{displayName} -> {currentRepition}/{totalRepititions}")
  @DisplayName("gift")
  void correctProductID() {
    assertEquals(
        4,
        reward.getGiftProductId(),
        () -> {
          System.out.println("Lazy loaded!");
          return "Error: product ID is incorrect";
        });
  }

  @Test
  void rewardApplied() {
    RewardInformation info = reward.applyReward(buildSampleOrder(10), 200);

    assertAll(
        "Reward info errors",
        () -> assertNotNull(info),
        () -> assertEquals(4, info.getDiscount()),
        () -> assertEquals(100, info.getPointsRedeemed()));
  }

  @Test
  void exceptionThrownWhenInvalidProductID() {
    long productId = 0;

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              reward.setGiftProductId(productId);
            });

    assertTrue(exception.getMessage().contains(String.valueOf(productId)));
  }

  @Test
  @Disabled("Optimization not implemented yet")
  @EnabledOnOs(OS.LINUX)
  void timeoutNotExceeded() {
    int numberOfProducts = 50_000;
    reward.setGiftProductId(numberOfProducts - 1);

    RewardInformation info =
        assertTimeoutPreemptively(
            Duration.ofMillis(4),
            () -> reward.applyReward(buildSampleOrder(numberOfProducts), 200));

    assertEquals(2.99, info.getDiscount());
  }

  private List<Product> buildSampleOrder(int numberOfProducts) {
    return IntStream.range(1, numberOfProducts)
        .mapToObj(i -> new Product(i, "Product " + i, 2.99))
        .collect(toList());
  }

  @Test
  @DisplayName("AssumeTrue")
  void AssumeTRUE() {
    assertEquals(100, reward.getNeededPoints());
    assumeTrue(!"1".equals(System.getenv("TEST_POINTS")));
    assertEquals(1, reward.getGiftProductId());
  }

  @Test
  @DisplayName("AssumingThat")
  void AssumingTHAT() {
    assumingThat(
        !"1".equals(System.getenv("TEST_POINTS")),
        () -> {
          assertEquals(100, reward.getNeededPoints());
        });
  }
}
