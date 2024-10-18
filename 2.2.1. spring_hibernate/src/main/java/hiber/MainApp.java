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


        User user1 = context.getBean(User.class, "User1", "Lastname1", "user1@mail.ru",new Car("Lada", 2024));
        User user2 = context.getBean(User.class, "User2", "Lastname2", "user2@mail.ru",new Car("Haval", 2020));
        User user3 = context.getBean(User.class, "User3", "Lastname3", "user3@mail.ru",new Car("BMW", 2023));
        User user4 = context.getBean(User.class, "User4", "Lastname4", "user4@mail.ru",new Car("Mersedes", 2024));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        List<User> usersByModel = userService.getUserByModelAndSeries("Haval", 2020);
        for (User user : usersByModel) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }
        context.close();
    }
}
