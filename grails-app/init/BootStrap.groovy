import investill.SecRole
import investill.SecUser
import investill.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(flush: true)

        def adminUser = SecUser.findByUsername('admin') ?: new SecUser(
              username: 'admin',
              password: 'admin',
              firstName: 'First',
              lastName: 'Admin',
              email: 'admin@admin.com',
              enabled: true).save(validate: false)

        if(!adminUser.authorities.contains(adminRole)) {
          SecUserSecRole.create adminUser, adminRole
        }
    }
    def destroy = {
    }
}
