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
public class OrAccessDecider extends LogicAccessDecider {
  public OrAccessDecider(List<AccessDecisionManager> deciders, boolean allowIfAllAbstainDecisions) {
    super(deciders, allowIfAllAbstainDecisions);
  }

  public OrAccessDecider(List<AccessDecisionManager> deciders) {
    super(deciders);
  }

  @Override
  protected int decideInternal(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
    return 0;
  }
}
