import entity.*;
import service.*;

import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
//        Util util = new Util();
//        util.getConnection();
        BookService bookService = new BookService();
        CustomService customService = new CustomService();
        LocationService positionService = new LocationService();
        RoleService roleService = new RoleService();
        UserService userService = new UserService();

        Book book = new Book();
        book.setId(1L);
        book.setName("Programing");
        book.setAuthor("Lisyuchenko R.V.");
        book.setDescription("Hello World!");
        book.setAttributes("Hello, World.");

        Role role = new Role();
        role.setId(1L);
        role.setAccess("Admin");

        User user = new User();
        user.setId(1L);
        user.setName("Ruslan");
        user.setSurname("Lisyuchenko");
        user.setEmail("ruslan.lisyuchenko@ukr.net");
        user.setPhone(939752488L);
        user.setPassword("asdfgqwert");
        user.setRole_id(role.getId());



        Location location = new Location();
        location.setId(1L);
        location.setLine(17L);
        location.setShelf("AB");
        location.setBook_id(book.getId());

        Custom custom = new Custom();
        custom.setId(1L);
        custom.setPosition_id(location.getId());
        custom.setUser_id(user.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.MAY,1);
        custom.setTime_usage(new java.sql.Date(calendar.getTime().getTime()));

        try {
            bookService.add(book);
            userService.add(user);
            positionService.add(location);
            customService.add(custom);
            roleService.add(role);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
