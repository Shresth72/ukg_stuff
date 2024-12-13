package org.reward;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.product.Product;

@Getter
@Setter
public class RewardConversionService extends RewardService {
  private double amount;

  @Override
  public RewardInformation applyReward(List<Product> order, long customerPoints) {
    RewardInformation rewardInformation = new RewardInformation();

    if (customerPoints >= neededPoints) {
      double orderTotal = calculateTotal(order);
      if (orderTotal > amount) {
        rewardInformation = new RewardInformation(neededPoints, amount);
      }
    }

    return rewardInformation;
  }
}
