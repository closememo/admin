package com.closememo.admin.dto;

import com.closememo.admin.infra.persistence.converter.StringRolesConverter;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDTO implements Serializable {

  private static final long serialVersionUID = 6499373703095570314L;

  @Id
  private String id;
  private String username;
  private String password;
  private ZonedDateTime createdAt;
  @Convert(converter = StringRolesConverter.class)
  private Set<String> roles;

  @Builder
  public AccountDTO(String id, String username, String password, ZonedDateTime createdAt,
      Set<String> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.createdAt = createdAt;
    this.roles = roles;
  }
}
