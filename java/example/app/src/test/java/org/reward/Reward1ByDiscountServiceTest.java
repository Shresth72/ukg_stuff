package org.reward;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.product.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Reward1ByDiscountServiceTest {

  @Test
  @Order(1)
  void setNeededPoints() {
    RewardByDiscountService reward = new RewardByDiscountService();

    reward.setNeededPoints(100);
    assertEquals(100, reward.getNeededPoints());
  }

  @Test
  @Order(2)
  void setPercentageForPoints() {
    RewardByDiscountService reward = new RewardByDiscountService();

    reward.setPercentage(0.1);
    assertEquals(0.1, reward.getPercentage());
  }

  @Test
  void zeroCustomerPoints() {
    RewardByDiscountService reward = new RewardByDiscountService();
    reward.setPercentage(0.1);
    reward.setNeededPoints(100);

    Product smallDecaf = new Product(1, "Small Decaf", 1.99);
    List<Product> order = Collections.singletonList(smallDecaf);

    RewardInformation info = reward.applyReward(order, 0);

    assertEquals(0, info.getDiscount());
    assertEquals(0, info.getPointsRedeemed());
  }
}