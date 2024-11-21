package com.shoestore.Server.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CartItemKey implements Serializable {
    private int cartId;
    private int productId;
}
