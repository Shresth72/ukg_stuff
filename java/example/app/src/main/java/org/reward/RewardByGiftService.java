package org.reward;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import org.product.Product;

@Getter
@Setter
public class RewardByGiftService extends RewardService {
  private long giftProductId;

  @Override
  public RewardInformation applyReward(List<Product> order, long customerPoints) {
    RewardInformation rewardInformation = new RewardInformation();

    if (customerPoints >= neededPoints) {
      Optional<Product> result = order.stream().filter(p -> p.getId() == giftProductId).findAny();

      if (result.isPresent()) {
        rewardInformation = new RewardInformation(neededPoints, result.get().getPrice());
      }
    }

    return rewardInformation;
  }
}
