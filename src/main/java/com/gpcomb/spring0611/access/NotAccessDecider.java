package com.gpcomb.spring0611.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * @author 王东旭
 * @date 2018-06-19
 */
public class NotAccessDecider extends LogicAccessDecider {
  public NotAccessDecider(List<AccessDecisionManager> deciders, boolean allowIfAllAbstainDecisions) {
    super(deciders, allowIfAllAbstainDecisions);
  }

  public NotAccessDecider(List<AccessDecisionManager> deciders) {
    super(deciders);
  }

  @Override
  protected int decideInternal(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
    for (AccessDecisionManager adm: deciders) {
      try {
        adm.decide(authentication,object,configAttributes);
      }catch (AccessDeniedException e){
        return 0;
      }
    }
  }
}
