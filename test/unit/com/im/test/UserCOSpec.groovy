package com.im.test

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(ControllerUnitTestMixin)
@Mock(User)
class UserCOSpec extends Specification{
    UserCO co

    def setup() {
        co = mockCommandObject(UserCO)
    }

    @Unroll
    def "UserCO constraints are in place class"() {
        UserCO co = new UserCO()
        when:
        co.username = username
        co.familyName = familyName
        co.givenName = givenName
        co.password = password
        co.confirmPassword = confirmPassword
        Boolean isValid = co.validate()

        then:
        isValid == valid

        where:
        username   | familyName   | givenName   | password   | confirmPassword | valid
        "username" | "familyName" | "givenName" | 'password' | 'password'      | true
        "username" | "familyName" | "givenName" | 'password' | 'password1'     | false
        "username" | "familyName" | "givenName" | 'password' | null            | false
        "username" | "familyName" | "givenName" | 'password' | ""              | false
        "username" | "familyName" | "givenName" | null       | 'password'      | false
        "username" | "familyName" | "givenName" | ""         | 'password'      | false
        "username" | "familyName" | null        | 'password' | 'password'      | false
        "username" | "familyName" | ""          | 'password' | 'password'      | false
        "username" | null         | "givenName" | 'password' | 'password'      | false
        "username" | ""           | "givenName" | 'password' | 'password'      | false
        null       | "familyName" | "givenName" | 'password' | 'password'      | false
        ""         | "familyName" | "givenName" | 'password' | 'password'      | false
        ""         | "familyName" | "givenName" | 'password' | 'password'      | false
    }
}
