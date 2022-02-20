package models;


import java.util.Random;

public class User {
    public String userEmail;
    public String userPassword;
    public String confirmPassword;
    public String userGender;
    public Boolean userVerification;

    public User(String userEmail, String userPassword, String confirmPassword, String userGender, Boolean userVerification){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
        this.userGender = userGender;
        this.userVerification = userVerification;
    }

    public String createUserEmail(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000000);
        String randomUserEmail = "qa_test_email_" + randomNumber + "@example.com";
        return randomUserEmail;
    }

    public User createNewUserAccount(User user){
        if(userEmail == null) user.userEmail = createUserEmail();
        if(userPassword == null) user.userPassword = "userPassword@";
        if(confirmPassword == null) user.confirmPassword = "userPassword@";
        if(userGender == null) user.userGender = "Kobieta";
        if(userVerification == null) user.userVerification = true;

        user = new User(userEmail, userPassword, confirmPassword, userGender, userVerification);

        return user;
    }
}
