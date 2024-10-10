package org.mawaaw.springbootportadapterarchitecture.infrastructure.security;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {
    String value();
}