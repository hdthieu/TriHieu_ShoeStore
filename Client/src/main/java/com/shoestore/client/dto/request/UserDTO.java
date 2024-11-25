package com.shoestore.client.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
  private int userID;
  private String name;
  private String email;
  private String phoneNumber;
  private String userName;
  private String status;
  private int roleID;
}
