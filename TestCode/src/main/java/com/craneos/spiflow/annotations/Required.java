package com.craneos.spiflow.annotations;

public @interface Required {
    boolean isRequired() default true;
}
