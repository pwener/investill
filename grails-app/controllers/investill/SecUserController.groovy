package investill

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
@Transactional(readOnly = true)
class SecUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SecUser.list(params), model:[secUserCount: SecUser.count()]
    }

    def show(SecUser secUser) {
        respond secUser
    }

    @Secured(['permitAll'])
    def create() {
        respond new SecUser(params)
    }

    @Secured(['permitAll'])
    @Transactional
    def save(SecUser secUser) {
        if (secUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secUser.errors, view:'create'
            return
        }

        secUser.save flush:true, failOnError: true
        SecUserSecRole.create secUser, SecRole.findByAuthority('ROLE_USER')

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message',
                  args: [message(code: 'secUser.label', default: 'SecUser'), secUser.id])
                redirect secUser
            }
            '*' { respond secUser, [status: CREATED] }
        }
    }

    def edit(SecUser secUser) {
        respond secUser
    }

    @Transactional
    def update(SecUser secUser) {
        if (secUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secUser.errors, view:'edit'
            return
        }

        secUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secUser.label', default: 'SecUser'), secUser.id])
                redirect secUser
            }
            '*'{ respond secUser, [status: OK] }
        }
    }

    @Transactional
    def delete(SecUser secUser) {

        if (secUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        secUser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secUser.label', default: 'SecUser'), secUser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secUser.label', default: 'SecUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
