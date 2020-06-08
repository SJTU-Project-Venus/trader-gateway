package com.sjtu.trade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String id;
  private String type;
  private String password;
  private String caseId;
  @Version
  private long version;

}
