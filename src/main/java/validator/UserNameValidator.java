package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import service.UserService;
import annotation.UserNameValidationAnnotation;
import bean.User;


public class UserNameValidator implements ConstraintValidator<UserNameValidationAnnotation, String> {
	
	@Autowired
	private UserService userService; 
 
	@Override
    public void initialize(UserNameValidationAnnotation annotation) {
    	
    }
 
    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return validateUser(userName);
    }
    
    
    private boolean validateUser(String userName) {
    	boolean result = false;
    	User user = userService.getUser(userName);
    	if(user!=null){
    		result = true;
    	}
    	return result;
    }
 
}