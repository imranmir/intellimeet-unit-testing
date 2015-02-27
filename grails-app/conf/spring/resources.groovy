// Place your Spring DSL code here
import com.im.test.ApplicationContextHolder
beans = {

	applicationContextHolder(ApplicationContextHolder) { bean ->
        bean.factoryMethod = 'getInstance'
    }
}
