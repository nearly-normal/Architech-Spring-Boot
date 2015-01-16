package com.architech.login.domain;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * Created by satish on 15-01-15.
 */
@RunWith(DataProviderRunner.class)
public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }


    @Test
    public void testUserNameNull() {
        User user = new User();
        user.setUserName(null);
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("Apple1234");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("User Name cannnot be left empty", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testUserNameEmpty() {
        User user = new User();
        user.setUserName("");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("Apple1234");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Either the user Name contains invalid (non-alphanumeric) characters or it is not  at least 5 chars long", constraintViolations.iterator().next().getMessage());
    }


    @Test
    public void testUserNameLength() {
        User user = new User();
        user.setUserName("sats");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("Apple1234");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Either the user Name contains invalid (non-alphanumeric) characters or it is not  at least 5 chars long", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testUserNameSpecialChar() {
        User user = new User();
        user.setUserName("sats*");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("Apple1234");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Either the user Name contains invalid (non-alphanumeric) characters or it is not  at least 5 chars long", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testPasswordLength() {
        User user = new User();
        user.setUserName("satsh");
        user.setEmailAddress("satsh@yahoo.com");
        user.setPassword("Apple12");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Invalid format, use atleast 1 lower case, 1 upper case and 1 number and a minimum of 8 chars needed", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void testPasswordWithNoNumeric() {
        User user = new User();
        user.setUserName("satsh");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("AppleXYZ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Invalid format, use atleast 1 lower case, 1 upper case and 1 number and a minimum of 8 chars needed", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void testPasswordWithNoCaps() {
        User user = new User();
        user.setUserName("satsh");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("1applexyz");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Invalid format, use atleast 1 lower case, 1 upper case and 1 number and a minimum of 8 chars needed", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void testPasswordWithNoSmall() {
        User user = new User();
        user.setUserName("satsh");
        user.setEmailAddress("sats@outlook.com");
        user.setPassword("1APPLEXYZ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(constraintViolations.size(), 1);
        assertEquals("Invalid format, use atleast 1 lower case, 1 upper case and 1 number and a minimum of 8 chars needed", constraintViolations.iterator().next().getMessage());

    }

    @DataProvider
    public static Object[][] emails() {
        return new Object[][]{
                {"satsh", false},
                {"satsh@.com.my", false},
                {"satsh123@gmail.a", true},
                {"satsh123@.com", false},
                {"satsh123@.com.com", false},
                {".satsh@satsh.com", false},
                {"satsh()*@gmail.com", false},
                {"satsh..2002@gmail.com", false},
                {"satsh.@gmail.com", false},
                {"satsh@satsh@gmail.com", false},
                {"satsh@gmail.com.1a", true},
                {"satsh@yahoo.com", true},
                {"satsh-100@yahoo.com", true},
                {"satsh.100@yahoo.com", true},
                {"satsh111@satsh.com", true},
                {"satsh-100@satsh.net", true},
                {"satsh.100@satsh.com.au", true},
                {"satsh@1.com", true},
                {"satsh@gmail.com.com", true},
                {"satsh+100@gmail.com", true},
                {"satsh-100@yahoo-test.com", true}


        };
    }


    @Test
    @UseDataProvider("emails")
    public void testEmail(String email, Boolean expected) {
        User user = new User();
        user.setUserName("satsh");
        user.setEmailAddress(email);
        user.setPassword("1APpleEXYZ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if(expected==Boolean.FALSE) {
            assertEquals(constraintViolations.size(), 1);
            assertEquals("Invalid email format, Enter a valid email address", constraintViolations.iterator().next().getMessage());
        }else {
            assertEquals(constraintViolations.size(), 0);
        }

    }


}
