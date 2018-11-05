package com.bst.backcommon.conditional.liunx;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 *   搬砖于 https://blog.csdn.net/qq_26525215/article/details/53510156
 */
public class LinuxCondition  implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
