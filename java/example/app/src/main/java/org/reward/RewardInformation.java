package org.reward;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardInformation {
  private long pointsRedeemed;
  private double discount;

  public RewardInformation() {}

  public RewardInformation(long pointsRedeemed, double discount) {
    this.pointsRedeemed = pointsRedeemed;
    this.discount = discount;
  }
}
