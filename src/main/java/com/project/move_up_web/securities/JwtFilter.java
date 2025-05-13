package com.project.move_up_web.securities;

import com.project.move_up_web.services.AccountDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AccountDetailsService accountDetailsService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String token = null;
    String email = null;

    try {
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        email = jwtService.extractEmail(token);
      }

      // Only try to authenticate if email was extracted and user is not already authenticated
      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = accountDetailsService.userDetailsService().loadUserByUsername(email);

        if (jwtService.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    } catch (Exception ex) {
      // Log the exception
      logger.error("JWT Filter Error: ", ex);

      // Optionally, clear the context in case of an invalid or expired token
      SecurityContextHolder.clearContext();

      // Optionally, set a response status code (e.g., 401 Unauthorized)
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid token or unauthorized access.");
      return; // Prevent further processing in case of error
    }

    // Continue the filter chain if everything is fine
    filterChain.doFilter(request, response);
  }
}
