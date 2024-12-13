package org.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.product.Product;

// PER_CLASS -> Constructor gets called only once (all methods don't need static
// PER_METHOD -> Constructor gets called after every method

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Reward2InstanceTest {
  private RewardByDiscountService reward = null;

  Reward2InstanceTest() {
    System.out.println("Constructor");
  }

  @BeforeAll
  static void setUpAll() {
    System.out.println("BeforeAll");
  }

  @BeforeEach
  void setUp() {
    reward = new RewardByDiscountService();
    System.out.println("BeforeEach");
  }

  @AfterEach
  void tearDown() {
    System.out.println("AfterEach");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println("AfterAll");
  }

  @Test
  void setNeededPoints() {
    System.out.println("Test setNeededPoints");

    reward.setNeededPoints(100);
    assertEquals(100, reward.getNeededPoints());
  }

  @Test
  void setPercentageForPoints() {
    System.out.println("Test setPercentageForPoints");

    reward.setPercentage(0.1);
    assertEquals(0.1, reward.getPercentage());
  }

  @Test
  void zeroCustomerPoints() {
    System.out.println("Test zeroCustomerPoints");

    reward.setPercentage(0.1);
    reward.setNeededPoints(100);

    Product smallDecaf = new Product(1, "Small Decaf", 1.99);
    List<Product> order = Collections.singletonList(smallDecaf);

    RewardInformation info = reward.applyReward(order, 0);

    assertEquals(0, info.getDiscount());
    assertEquals(0, info.getPointsRedeemed());
  }
}
