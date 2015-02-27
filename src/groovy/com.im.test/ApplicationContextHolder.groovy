package com.im.test

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.codehaus.groovy.grails.commons.GrailsApplication
import javax.servlet.ServletContext

@Singleton
class ApplicationContextHolder implements ApplicationContextAware {

    private ApplicationContext ctx

    @Override
    void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext
    }

    static ApplicationContext getApplicationContext() {
        getInstance().ctx
    }

    static Object getBean(String name) {
        getApplicationContext().getBean(name)
    }

    static GrailsApplication getGrailsApplication() {
        getBean('grailsApplication') as GrailsApplication
    }

    static ConfigObject getConfig() {
        getGrailsApplication().config
    }

    static def getResourceTagBean() {
        grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')
    }

    static String getServerURL() {
        getConfig().grails.serverURL
    }

    static ServletContext getServletContext() {
        getBean('servletContext') as ServletContext
    }

}