package com.im.test

import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {
    String id
    String password
    String newPassword
    String confirmPassword
    static constraints = {
        password nullable: false, blank: false, validator: {val, obj->
            User existingUser = User.findByUsername(obj.id)
            if(existingUser.password!=password)   {
                return 'username.does.not.exist'
            }
        }
        newPassword nullable: false, minSize: 7, validator: { val, obj ->
            if (val != obj.confirmPassword) {
                'passwords.retypePassword.not.matching'
            }
        }
        confirmPassword nullable: false, blank: false
        id nullable: false, blank: false, validator: { val, obj ->
            if (!User.countByUsername(val)) {
                'username.does.not.exist'
            }
        }
    }
}