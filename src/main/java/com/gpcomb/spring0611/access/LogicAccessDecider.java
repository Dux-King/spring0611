package com.gpcomb.spring0611.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author 王东旭
 * @date 2018-06-19
 */
public abstract class LogicAccessDecider implements AccessDecisionManager {

  protected List<AccessDecisionManager> deciders;

  private boolean allowIfAllAbstainDecisions = false;

  private static final Log logger = LogFactory.getLog(LogicAccessDecider.class);

  public LogicAccessDecider(List<AccessDecisionManager> deciders, boolean allowIfAllAbstainDecisions) {
    this.deciders = deciders;
    this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
  }

  public LogicAccessDecider(List<AccessDecisionManager> deciders) {
    this.deciders = deciders;
  }

  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
    if (CollectionUtils.isEmpty(deciders)) {
      checkAllowIfAllAbstainDecisions();
    }
    switch (decideInternal(authentication, object, configAttributes)) {
      case 1:
        logger.debug("logic access decider : access");
        break;
      case -1:
        checkAllowIfAllAbstainDecisions();
        break;
      default:
        throw new AccessDeniedException("logic access decider : refuse");
    }
  }

  /**
   * 决策执行方法
   * @param authentication Authentication
   * @param object Object
   * @param configAttributes ConfigAttribute
   * @return  1：access
   *         -1: abstain
   *          0: refuse
   */
  protected abstract int decideInternal(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes);

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }

  protected final void checkAllowIfAllAbstainDecisions() {
    if (!this.isAllowIfAllAbstainDecisions()) {
      throw new AccessDeniedException("Access is denied");
    }
  }

  public boolean isAllowIfAllAbstainDecisions() {
    return allowIfAllAbstainDecisions;
  }

  public void setAllowIfAllAbstainDecisions(boolean allowIfAllAbstainDecisions) {
    this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
  }
}
