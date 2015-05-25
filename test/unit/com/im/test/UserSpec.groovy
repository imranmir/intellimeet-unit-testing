package com.im.test

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestMixin(GrailsUnitTestMixin)
@Mock(User)
class UserSpec extends Specification {

    def "Canary Test"() {
        expect:
        true
    }

     def "Test all constraints are in place"(){
         setup: "Mock constraints"
         mockForConstraintsTests(User)

         and: "Initialize domain object"
         User user = new User(id: 1, username: 'username', password: 'pwd', givenName: 'givenName', familyName: 'familyName')

         expect:
         !user.validate()
         user.errors['password'] == 'minSize'
     }



    @Unroll
    def "Test all domain constraints are placed correctly for #desc"() {
        setup:
        mockForConstraintsTests(User)

        User existingUser = new User(id: 1, username: 'username').save(validate: false)

        and: "Initialize a user object"
        User user = new User(username: username)
        user.password = password
        user.givenName = givenName
        user.familyName = familyName

        expect:
        !user.validate()
        user.errors[invalidFieldName] == failedConstraint

        where:
        desc                      | invalidFieldName | failedConstraint | username   | password   | givenName   | familyName
        "Username null "          | 'username'       | 'nullable'       | null       | 'password' | 'givenName' | 'familyName'
        "Password  size invalid " | 'password'       | 'minSize'        | 'username' | 'pwd'      | 'givenName' | 'familyName'

    }


}
