package org.mawaaw.springbootportadapterarchitecture.infrastructure.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(checkRole)")
    public void checkRoleAspect(CheckRole checkRole) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Utilisateur non authentifié.");
        }

        String requiredRole = checkRole.value();
        boolean hasRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + requiredRole));

        if (!hasRole) {
            throw new AccessDeniedException("Accès refusé. Rôle requis : " + requiredRole);
        }
    }
}