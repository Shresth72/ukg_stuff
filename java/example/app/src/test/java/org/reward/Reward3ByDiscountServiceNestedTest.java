package org.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.product.Product;

@DisplayName("Reward by Conversion \uD83D\uDE04")
public class Reward3ByDiscountServiceNestedTest {
  private RewardByDiscountService reward = null;

  Reward3ByDiscountServiceNestedTest() {
    System.out.println("Constructor");
  }

  @DisplayName("Given that 100 points are needed for $10")
  @BeforeEach
  void setUp() {
    reward = new RewardByDiscountService();
    reward.setNeededPoints(100);
    reward.setPercentage(0.1);
  }

  @DisplayName("Then 100 points should be needed for 10% discount")
  @Test
  void checkPercentageAndNeededPoints() {
    assertEquals(0.1, reward.getPercentage());
    assertEquals(100, reward.getNeededPoints());
  }

  @DisplayName("Given there's a small order")
  @Nested
  class SmallOrder {
    List<Product> smallOrder = null;

    @BeforeEach
    void setup() {
      Product smallDecaf = new Product(1, "Small Decaf", 1.99);
      smallOrder = Collections.singletonList(smallDecaf);
    }

    @DisplayName("Given the customer has zero points")
    @Nested
    class ZeroPoints {
      private RewardInformation info = null;

      @DisplayName("When the reward is applied")
      @BeforeEach
      void setUp() {
        info = reward.applyReward(smallOrder, 0);
      }

      @DisplayName("Then discount should be zero")
      @Test
      void checkDiscount() {
        assertEquals(0, info.getDiscount());
      }

      @DisplayName("Then points redeemed should be zero")
      @Test
      void checkPointsRedeemed() {
        assertEquals(0, info.getPointsRedeemed());
      }
    }
  }

  @DisplayName("Given there's a big order")
  @Nested
  class BigOrder {
    List<Product> bigOrder = null;

    @BeforeEach
    void setUp() {
      Product bigDecaf = new Product(2, "Big Decaf", 2.49);
      Product bigLatte = new Product(2, "Big Latte", 2.99);
      Product bigTea = new Product(2, "Big Tea", 2.99);
      Product espresso = new Product(2, "Espresso", 2.99);
      bigOrder = Arrays.asList(bigDecaf, bigLatte, bigTea, espresso);
    }

    @DisplayName("When customer has enough points, then reward should be applied")
    @Test
    void enoughCustomerPointsForDiscountInBigOrder() {
      RewardInformation info = reward.applyReward(bigOrder, 200);

      assertEquals(1.14, info.getDiscount(), 0.01);
      assertEquals(100, info.getPointsRedeemed());
    }
  }
}
