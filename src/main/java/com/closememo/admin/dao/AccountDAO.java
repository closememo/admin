package com.closememo.admin.dao;

import com.closememo.admin.dto.AccountDTO;
import com.closememo.admin.infra.persistence.SequenceGenerator;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAO {

  private final EntityManager em;
  private final SequenceGenerator sequenceGenerator;

  public AccountDAO(EntityManager em, SequenceGenerator sequenceGenerator) {
    this.em = em;
    this.sequenceGenerator = sequenceGenerator;
  }

  @Transactional
  public String createAccount(String username, String password) {
    String accountId = sequenceGenerator.generate();

    AccountDTO.AccountDTOBuilder builder = AccountDTO.builder()
        .id(accountId)
        .username(username)
        .password(password)
        .createdAt(ZonedDateTime.now())
        .roles(Set.of("ADMIN"));

    em.persist(builder.build());

    return accountId;
  }

  public Optional<AccountDTO> getAccountByUsername(String username) {
    TypedQuery<AccountDTO> query = em.createQuery(
        "SELECT a FROM AccountDTO a WHERE a.username = :username",
        AccountDTO.class);
    query.setParameter("username", username);

    return Optional.ofNullable(query.getSingleResult());
  }
}
