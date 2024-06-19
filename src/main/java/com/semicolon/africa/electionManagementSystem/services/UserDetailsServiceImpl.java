package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.exceptions.UserNotFoundException;
import com.semicolon.africa.electionManagementSystem.repositories.AdminRepository;
import com.semicolon.africa.electionManagementSystem.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {
    private final VoterRepository voters;
    private final AdminRepository admins;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = voters.findByEmail(username);
        if(userDetails == null)  userDetails = admins.findByEmail(username);
        if(userDetails == null) throw new UserNotFoundException("User Not Found");
        return userDetails;
    }
}
