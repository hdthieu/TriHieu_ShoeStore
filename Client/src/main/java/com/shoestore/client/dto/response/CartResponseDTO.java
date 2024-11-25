package com.shoestore.client.dto.response;

import com.shoestore.client.dto.request.CartItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartResponseDTO {
  private int totalItems;
  private int totalPages;
  private List<CartItemDTO> items;
}
