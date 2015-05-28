class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/ws/users" {
            controller = 'user'
            action = [POST: 'save']
        }
        "/ws/users/$id" {
            controller = 'user'
            action = [PATCH: 'updatePassword', POST: 'save']
        }
        "/"(view:"/index")
        "500"(view:'/error')


	}
}
