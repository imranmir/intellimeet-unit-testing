package com.im.test

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(UserService)
@Mock([User, UserRole, Role])
class UserServiceSpec extends Specification {

    def "Save method is able to save the user and create a default role fo the user"() {
        given: "Initialized UserCO with valid data"
        UserCO co = new UserCO(username: 'username', givenName: 'givenName', familyName: 'familyName', password: 'password', confirmPassword: 'password')

        and: "A Role is present in the database"
        Role role = new Role(authority: "ROLE_USER").save()

        and: "Authority is set in config"
        config.ais.sec.role.user_role="ROLE_USER"

        expect:
        !User.count
        Role.count
        !UserRole.count

        when:
        User user = service.save(co)

        then:
        User.count
        UserRole.count
        User.findByUsername(co.username)
        UserRole.findByUser(user)
    }

}
