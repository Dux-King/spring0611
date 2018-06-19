package com.gpcomb.spring0611.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * @author 王东旭
 * @date 2018-06-19
 */
public class AndAccessDecider extends LogicAccessDecider {
  public AndAccessDecider(List<AccessDecisionManager> deciders, boolean allowIfAllAbstainDecisions) {
    super(deciders, allowIfAllAbstainDecisions);
  }

  public AndAccessDecider(List<AccessDecisionManager> deciders) {
    super(deciders);
  }

  @Override
  private int decideInternal(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
    return 0;
  }
}
