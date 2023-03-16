package framework.helpers;

import com.github.javafaker.Faker;

import java.util.Date;

public class SignUpHelper {
    private static final Faker faker = new Faker();
    public static String generateValidFirstName(){
        return faker.name().firstName();
    }
    public static String generateValidLastName(){
        return faker.name().lastName();
    }
    public static String generateValidEmail(){
        return faker.internet().emailAddress();
    }
    public static String generateValidPassword(){
        return faker.internet().password();
    }
    public static Date generateValidBirthday(){
        return faker.date().birthday();
    }

}
