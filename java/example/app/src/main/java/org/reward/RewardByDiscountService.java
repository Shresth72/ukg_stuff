package org.reward;

import java.util.List;
import lombok.Getter;
import org.product.Product;

@Getter
public class RewardByDiscountService extends RewardService {
  double percentage;

  @Override
  public RewardInformation applyReward(List<Product> order, long customerPoints) {
    RewardInformation rewardInformation = new RewardInformation();

    if (customerPoints >= neededPoints) {
      double orderTotal = calculateTotal(order);
      double discount = orderTotal * percentage;
      rewardInformation = new RewardInformation(neededPoints, discount);
    }

    return rewardInformation;
  }

  public void setPercentage(double percentage) {
    if (percentage > 0) {
      this.percentage = percentage;
    } else {
      this.percentage = 0;
    }
  }
}
