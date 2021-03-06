package hotel;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hotel.repository.UserRoleRepository;
import hotel.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private DataSource dataSource;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Setting Service to find User in the database and Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();

    	// Các trang không yêu cầu login
    	http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

    	// Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
    	// Nếu chưa login, nó sẽ redirect tới trang /login.
    	http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

    	// Trang chỉ dành cho ADMIN
    	http.authorizeRequests().antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')");

    	// Khi người dùng đã login, với vai trò XX.
    	// Nhưng truy cập vào trang yêu cầu vai trò YY,
    	// Ngoại lệ AccessDeniedException sẽ ném ra.
    	http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    	// Cấu hình cho Login Form.
    	http.authorizeRequests().and().formLogin()//
    			// Submit URL của trang login
    			 // Submit URL
    			.loginPage("/login")//
    			.defaultSuccessUrl("/index")//
    			.failureUrl("/login?error=true")//
    			.usernameParameter("username")//
    			.passwordParameter("password")

    			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    	http.requestMatchers().and().rememberMe().userDetailsService(userDetailsService).tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(10*60);
 }	
    @Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

}