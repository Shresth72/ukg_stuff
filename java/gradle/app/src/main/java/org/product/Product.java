package org.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
  private long id;
  private String name;
  private double price;

  public Product(long id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Product product = (Product)o;
    return id == product.id;
  }

  @Override
  public int hashCode() {
    return (int)(id ^ (id >> 32));
  }
}
