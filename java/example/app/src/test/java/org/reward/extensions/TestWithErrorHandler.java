package org.reward.extensions;

import java.lang.annotation.*;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({
  IllegalArgumentExceptionHandlerExtension.class,
  RewardByConversionParameterResolver.class,
  DisableTestsIfExceptionThrownExtension.class
})
public @interface TestWithErrorHandler {}

// Add @Test to add to individual tests
