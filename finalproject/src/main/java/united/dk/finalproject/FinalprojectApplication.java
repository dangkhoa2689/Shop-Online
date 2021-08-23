package united.dk.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


@SpringBootApplication
public class FinalprojectApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
		String s = new BCryptPasswordEncoder().encode("123456");
		System.err.println(s);
		}
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN").antMatchers("/member/**")
		.authenticated().anyRequest().permitAll()

		.and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/member/home")
		.failureUrl("/login?err").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

		.permitAll().and().exceptionHandling().accessDeniedPage("/login?deny");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		return bCryptPasswordEncoder;
	}
	
	
	

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect dialect = new SpringSecurityDialect();
		return dialect;
	}

}
