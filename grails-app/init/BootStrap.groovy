import investill.SecRole
import investill.SecUser
import investill.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new SecRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new SecRole(authority: 'ROLE_USER').save(flush: true)

        def testUser = new SecUser(username: 'admin', enabled: true, password: 'admin')
        testUser.save(flush: true)

        SecUserSecRole.create testUser, adminRole

        // assert SecUser.count() == 1
        // assert SecRole.count() == 2
        // assert SecUserSecRole.count() == 1
    }
    def destroy = {
    }
}
