package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.User;
import java.util.List;

public interface UserService {
    User findByUserName(String userName);
    void saveUser(User user);
    void add(User user);
    void delete(Long id);
    User get(Long id);
    List<User> getUsers();
    List<User>findAllAdmins();
    void changeStatus(Long id);
    void takeOffPermission(Long id);
    void saveUserPassword(User user);
}
