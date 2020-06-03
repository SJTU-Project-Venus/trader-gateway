package com.sjtu.trade.aspect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;

import com.sjtu.trade.dto.Request;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.sjtu.trade.exception.exceptions.RequestValidationException;

@Component
@Aspect
public class ValidatorAspect {
  @Resource
  private LocalValidatorFactoryBean localValidatorFactoryBean;

  public ValidatorAspect() {
  }

  /**
   * 方式1：切入点(
   */
  @Pointcut(
      "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
          "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
          "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
          "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
  )
//    @Pointcut("execution(* com.*..controller.*.*(..))")//方式2
  private void parameterPointCut() {
  }

  @Before("parameterPointCut() && args(request,..)")
  public void validateParameter(JoinPoint joinPoint, Request request) {
    Set<ConstraintViolation<Request>> validErrors = this.localValidatorFactoryBean.validate(request,
        Default.class);
    Iterator<ConstraintViolation<Request>> iterator = validErrors.iterator();
    List<String> errorMsg = new ArrayList<>();
    if (validErrors.isEmpty()) {
      return;
    }
    while (iterator.hasNext()) {
      ConstraintViolation<Request> constraintViolation = iterator.next();
      String error = constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage();
      errorMsg.add(error);
    }
    if (!validErrors.isEmpty()) {
      throw new RequestValidationException(errorMsg);
    }
  }
}
