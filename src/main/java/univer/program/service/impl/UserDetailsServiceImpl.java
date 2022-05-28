package univer.program.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import univer.program.entity.User;
//import univer.program.entity.UserRole;
//import univer.program.repository.UserRoleRepository;
////import univer.program.service.UserService;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.Set;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private UserRoleRepository userRoleRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRoleRepository userRoleRepository) {
//        this.userRoleRepository = userRoleRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserRole> optionalRole = userRoleRepository.findByUsername(username);
//
//        if (optionalRole.isPresent()) {
//            UserRole userRole = optionalRole.get();
//            Set<GrantedAuthority> authorities = new HashSet<>();
//
//            if (Objects.equals(userRole.getRole(),"admin"))
//                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            else
//                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            return new org.springframework.security.core.userdetails.User(userRole.getUsername(), userRole.getPassword(), authorities);
//        } else
//            throw new UsernameNotFoundException(username + " not found!");
//    }
//}
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import univer.program.entity.UserRole;
import univer.program.repository.UserRoleRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRole user = this.userRoleRepository.findByUsername(username).get();
		if(user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		
        List<GrantedAuthority> grantList = new ArrayList<>();

        if (user.getRole() != null) {

                GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                grantList.add(authority);
        }
		UserDetails userDetail = new User(user.getUsername(),user.getPassword(),grantList);

		return userDetail;
	}

//	@Autowired
//	private RoleRepository roleRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AppUser user = this.userRepo.findByUsername(username);
//		if(user == null) {
//			System.out.println("User not found! " + username);
//			throw new UsernameNotFoundException("User " + username + " was not found in the database");
//		}
//		log.info(username);
//		log.info("Found User: " + user.getName());
//		
//        List<GrantedAuthority> grantList = new ArrayList<>();
//
//        if (user.getRoles() != null) {
//            for (AppRole role : user.getRoles()) {
//                //USER, ADMIN,..
//            	log.info(role.getName());
//                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
//                grantList.add(authority);
//            }
//        }
//		UserDetails userDetail = new User(user.getUsername(),user.getPassword(),grantList);
//
//		return userDetail;
//	}

}

