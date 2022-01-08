package com.closememo.admin.infra.sequencegenerator;

import com.closememo.admin.infra.persistence.SequenceGenerator;
import org.springframework.stereotype.Component;

@Component
public class SequenceGeneratorImpl implements SequenceGenerator {

  @Override
  public String generate() {
    return new ObjectId().toHexString();
  }
}
