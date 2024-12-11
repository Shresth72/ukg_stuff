package org.reward;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.product.Product;

@Getter
@Setter
public abstract class RewardService {
  protected long neededPoints;

  public abstract RewardInformation applyReward(List<Product> order, long customerPoints);

  protected double calculateTotal(List<Product> order) {
    double sum = 0;

    if (order != null) {
      sum =
          order.stream()
              .mapToDouble(Product::getPrice)
              .sum(); // Product::getPrice is a method reference
    }

    return sum;
  }
}
