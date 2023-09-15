package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car toyota = new Car("Toyota", 12345);
      Car audi = new Car("Audi", 12156);
      Car lexus = new Car("Lexus", 45465);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", toyota));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", lexus));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", audi));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUserByCar(toyota));

      context.close();
   }
}
