package com.craneos.sgv.integration.model.annotations;

public @interface Required {
    boolean isRequired() default true;
}
