package com.im.test

class UserService {

def grailsApplication

    def getConfig() {
        return grailsApplication.config
    }

    User save(UserCO co) {
        User user = new User(givenName: co.givenName, familyName: co.familyName, password: co.password, username: co.username)
        user.save()
        UserRole.create(user, Role.findByAuthority(config.ais.sec.role.user_role))
        return user
    }

    void updatePassword(UpdatePasswordCO co) {
        if(!co.validate()){
            throw new Exception("Invalid data")
        }
        User user = User.findByUsername(co.id)
        user.password = co.newPassword
        user.save(flush: true)
    }

    String calculateSomething(){
        Integer total = CommonUtil.getTotalTokens()
        return "Total count is "+total
    }

    String getUserName(UserCO co){
        try {
            throw new NullPointerException("invalid co")
        }catch (NullPointerException e){

        }
    }


	
}