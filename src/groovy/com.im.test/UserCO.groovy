package com.im.test
import com.im.test.User
import grails.validation.Validateable

@Validateable
class UserCO {
    String username
    String givenName
    String familyName
    String password
    String confirmPassword

    static constraints = {
        familyName nullable: false, blank: false
        givenName nullable: false, blank: false
        confirmPassword nullable: false, validator: {val, obj->
            if(val!=obj.password){
                'password.confirm.password.do.not.match'
            }
        }
        username nullable: false, blank: false, validator: {val, obj->
            if(User.countByUsername(val)){
                'username.not.unique.error'
            }
        }
    }

}