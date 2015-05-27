package com.im.test

import org.springframework.http.HttpStatus

class UserController {

    static allowedMethods = [save: 'POST', 'updatePassword': 'PATCH']

    def userService


    def show() {
        render (view: 'show', model: [user: User.findByUsername(params.username)])
    }

    def showWithImplicitView(){
        [user: User.findByUsername(params.username)]
    }

    def save(UserCO co) {
        if (co.validate()) {
            userService.save(co)
            render(status: HttpStatus.CREATED.value, text: 'User created with role user')
        } else {
            render(status: HttpStatus.BAD_REQUEST, text: 'Error')
        }
    }

    def updatePassword(UpdatePasswordCO co) {
        try {
            userService.updatePassword(co)
            render(status: HttpStatus.OK.value, text: 'Password updated')
        } catch (Throwable t) {
            render(status: HttpStatus.BAD_REQUEST)
        }
    }


}
