import com.im.test.UserController
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.web.UrlMappingsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@Mock([UserController])
@TestFor(UrlMappings)
@TestMixin(UrlMappingsUnitTestMixin)
class UrlMappingsSpec extends Specification {

    @Unroll("Validating #method on #url is forwared to #controller/#action")
    def "Requests are correctly mapping to the corresponding controller actions"() {
        request.method = method
        expect:
        assertForwardUrlMapping(url, controller: controller, action: action)

        where:
        url                        | method   | controller                    | action
        '/ws/users'                | 'POST'   | 'user'                        | 'save'
        '/ws/users/1'              | 'PATCH'  | 'user'                        | 'updatePassword'
    }


}
