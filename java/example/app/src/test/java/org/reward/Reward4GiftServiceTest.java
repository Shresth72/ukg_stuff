package org.reward;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.product.Product;

public class Reward4GiftServiceTest {
  private RewardByGiftService reward = null;

  @BeforeEach
  void setUp() {
    reward = new RewardByGiftService();
    reward.setGiftProductId(4);
    reward.setNeededPoints(100);
  }

  @Test
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

  private List<Product> buildSampleOrder(int numberOfProducts) {
    return IntStream.range(1, numberOfProducts)
        .mapToObj(i -> new Product(i, "Product " + i, 2.99))
        .collect(toList());
  }
}
