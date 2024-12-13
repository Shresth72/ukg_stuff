package org.reward;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.product.Product;

public class ParameterizedTests {
  private RewardByGiftService reward;

  @BeforeEach
  void setUp() {
    reward = new RewardByGiftService();
    reward.setNeededPoints(100);
    System.out.println("BeforeEach");
  }

  // ------------ //
  // ValueSource
  // ------------ //
  @ParameterizedTest(name = "Test #{index}: productId={0}")
  @ValueSource(longs = {1, 2, 3, 4})
  @DisplayName("Display name")
  void discountShouldBeApplied(long productId, TestInfo testInfo, TestReporter testReporter) {
    System.out.println("Display name: " + testInfo.getDisplayName());
    testReporter.publishEntry("ProductID: ", String.valueOf(productId));

    reward.setGiftProductId(productId);
    RewardInformation info = reward.applyReward(getSampleOrder(), 200);

    assertTrue(info.getDiscount() > 0);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1; Small Decaf; 1.99", "2; Big Decaf; 2.49"})
  void discountShouldBeAppliedCustomConverter(
      @ConvertWith(ProductArgumentConverter.class) Product product) {
    System.out.println("Testing product: " + product.getName());
    reward.setGiftProductId(product.getId());
    RewardInformation info = reward.applyReward(getSampleOrder(), 200);

    assertTrue(info.getDiscount() > 0);
  }

  @Nested
  public class ProductArgumentConverter extends TypedArgumentConverter<String, Product> {
    protected ProductArgumentConverter() {
      super(String.class, Product.class);
    }

    @Override
    protected Product convert(String source) {
      String[] productString = source.split(";");

      Product product =
          new Product(
              Long.parseLong(productString[0]),
              productString[1].trim(),
              Double.parseDouble(productString[2]));

      return product;
    }
  }

  // ------------ //
  // EnumSource
  // ------------ //
  @ParameterizedTest
  @EnumSource(SpecialProductsEnum.class)
  void discountShouldBeAppliedEnumSource(SpecialProductsEnum product) {
    reward.setGiftProductId(product.getProductId());
    RewardInformation info = reward.applyReward(getSampleOrder(), 200);

    assertTrue(info.getDiscount() > 0);
  }

  @ParameterizedTest
  @EnumSource(
      value = SpecialProductsEnum.class,
      names = {"BIG_LATTE", "BIG_TEA"})
  void discountShouldBeAppliedEnumSourceNames(SpecialProductsEnum product) {
    reward.setGiftProductId(product.getProductId());
    RewardInformation info = reward.applyReward(getSampleOrder(), 200);

    assertTrue(info.getDiscount() > 0);
  }

  @Nested
  @Getter
  public enum SpecialProductsEnum {
    SMALL_DECAF(1),
    BIG_DECAF(2),
    BIG_LATTE(3),
    BIF_TEA(4),
    ESPRESSO(5);

    private final int productId;

    private SpecialProductsEnum(int productId) {
      this.productId = productId;
    }
  }

  // ------------ //
  // MethodSource
  // ------------ //
  @ParameterizedTest
  @MethodSource("productIds")
  void discountShouldBeAppliedMethodSource(long productId) {
    reward.setGiftProductId(productId);
    RewardInformation info = reward.applyReward(getSampleOrder(), 200);

    assertTrue(info.getDiscount() > 0);
  }

  private static LongStream productIds() {
    return LongStream.range(1, 6);
  }

  @ParameterizedTest
  @MethodSource("productIdsCustomerPoints")
  void discountShouldBeAppliedMethodSourceMultipleParams(long productId, long customerPoints) {
    reward.setGiftProductId(productId);
    RewardInformation info = reward.applyReward(getSampleOrder(), customerPoints);

    assertTrue(info.getDiscount() > 0);
  }

  static Stream<Arguments> productIdsCustomerPoints() {
    return productIds().mapToObj(productId -> Arguments.of(productId, 100 * productId));
  }

  // ------------ //
  // ArgumentsSource
  // ------------ //
  @ParameterizedTest
  @ArgumentsSource(ProductIdArgumentsProvider.class)
  void discountShouldBeAppliedArgumentsSource(long productId, long customerPoints) {
    reward.setGiftProductId(productId);
    RewardInformation info = reward.applyReward(getSampleOrder(), customerPoints);

    assertTrue(info.getDiscount() > 0);
  }

  @Nested
  public class ProductIdArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return LongStream.range(1, 6).mapToObj(productId -> Arguments.of(productId, 200 * productId));
    }
  }

  private List<Product> getSampleOrder() {
    Product smallDecaf = new Product(2, "Small Decaf", 2.49);
    Product bigDecaf = new Product(2, "Big Decaf", 2.49);
    Product bigLatte = new Product(2, "Big Latte", 2.99);
    Product bigTea = new Product(2, "Big Tea", 2.99);
    Product espresso = new Product(2, "Espresso", 2.99);
    return Arrays.asList(smallDecaf, bigDecaf, bigLatte, bigTea, espresso);
  }
}
