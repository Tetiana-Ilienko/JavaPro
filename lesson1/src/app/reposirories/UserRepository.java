package app.reposirories;

import app.domain.User;

public interface UserRepository extends CrudRepository<User>{

   // наследует CrudRepository, имеет все его методы + свои
   User findByEmail(String email); // найти пользователя по email
}
