package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDTO {
    private int supplierID;
    private String supplierName;
    private String address;
    private List<String> phoneNumbers;
}
