package pl.odum.workflowodum.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Role;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.RoleRepository;
import pl.odum.workflowodum.repository.UserRepository;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveUserPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User does not exists"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllAdmins() {
        Role role=roleRepository.findByName("ROLE_ADMIN");
        return  userRepository.findAllByRoleAdmin(role);
    }

    @Override
    public void changeStatus(Long id) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Role>roles = user.getRoles();
        if(user.getRoles().contains(roleUser)){
            roles.remove(roleUser);
            user.setEnabled(false);
        }
        else {
            roles.add(roleUser);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void takeOffPermission(Long id) {
        User user = get(id);
        Role roleTypeAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleTypeUser = roleRepository.findByName("ROLE_USER");

        Set<Role> roles = user.getRoles();
        roles.remove(roleTypeAdmin);
        roles.add(roleTypeUser);
        user.setRoles(roles);

        add(user);
    }

    @Override
    public void addPermission(Long id) {
        User user = get(id);
        Role roleTypeAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleTypeUser = roleRepository.findByName("ROLE_USER");

        Set<Role> roles = user.getRoles();
        roles.remove(roleTypeUser);
        roles.add(roleTypeAdmin);
        user.setRoles(roles);

        add(user);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

}
