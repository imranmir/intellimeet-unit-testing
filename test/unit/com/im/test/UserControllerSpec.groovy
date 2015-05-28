package com.im.test

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import jdk.nashorn.internal.ir.annotations.Ignore
import spock.lang.IgnoreRest
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock(User)
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Show renders the correct model and view"() {
        given: "A user in db"
        User user = new User(id: 1, username: 'username').save(validate: false)

        and: "params has username"
        params.username = 'username'

        expect:
        User.findByUsername(user.username)

        when: "Show action is called with username in params"
        controller.show()

        then: "show action should be rendered with the user in the model"
        view == '/user/show'
        model.user.id == user.id
    }


    @Unroll("Save action is able to correclty execute save flow when #description")
    void "Save action is able to correclty execute save flow when "() {
        given: "Initialize UserCO"
        UserCO co = new UserCO(username: 'username', givenName: 'givenName', familyName: 'familyName', password: 'password', confirmPassword: validFlow?'password': 'pwd')

        and: "mock UserService and its save method"
        def userService = Mock(UserService)
        controller.userService = userService

        (validFlow?1:0) * userService.save(_) >> new User(id: 1, username: 'usrename').save(validate: false)

        and: "Request method is post"
        request.method = "POST"

        when:
        controller.save(co)

        then:
        response.contentAsString == contentString
        response.status == status

        where:
        description                | validFlow | contentString                 | status
        "When co has valid data"   | true      | "User created with role user" | 201
        "When co has invalid data" | false     | "Error"                       | 400
    }


}
