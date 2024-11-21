package com.shoestore.client.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartItemDTO {
  private int cartId;
  private int productId;
  private ProductDTO product;
  @JsonProperty("productDetails")
  private List<ProductDetailDTO> productDetails;
  private int quantity;
  private double subTotal;
}
