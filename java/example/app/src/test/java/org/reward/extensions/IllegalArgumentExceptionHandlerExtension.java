package org.reward.extensions;

import static org.reward.extensions.ExtensionUtils.*;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class IllegalArgumentExceptionHandlerExtension implements TestExecutionExceptionHandler {
  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
      throws Throwable {
    if (throwable instanceof IllegalArgumentException) {
      ExtensionContext engineContext = getEngineContext(context);
      engineContext.getStore(NAMESPACE).put(EXCEPTION_KEY, throwable);
      return;
    }

    throw throwable;
  }
}
