package com.HotelManagement.HotelManagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.HotelManagement.HotelManagement.Service.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomUserService userDetailsService;


	@Autowired
	public SecurityConfig(CustomUserService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers("/api/**").permitAll()

								.requestMatchers("/pra/**").permitAll()
								.requestMatchers("/admin/**").hasRole("ADMIN")
								.requestMatchers("/user/**").hasRole("USER")
								.anyRequest().authenticated() )
				.httpBasic(Customizer.withDefaults( ))
				. formLogin(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	public AuthenticationManager manager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
System.out.println("  .   ____          _            __ _ _\n" +
		" /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\\n" +
		"( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\\n" +
		" \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )\n" +
		"  '  |____| .__|_| |_|_| |_\\__, | / / / /\n" +
		" =========|_|==============|___/=/_/_/_/\n");
		return new ProviderManager(provider);
	}

}


