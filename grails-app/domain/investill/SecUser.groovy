package investill

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class SecUser implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String firstName
	String lastName
	String email
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<SecRole> getAuthorities() {
		SecUserSecRole.findAllBySecUser(this)*.secRole
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static constraints = {
		firstName(size:1..120, nullable: false, blank: false)
		lastName(size:1..120, nullable: false, blank: false)
		email(email: true, size:1..120, nullable: false, blank: false, unique: true)
		username(size: 4..20, blank: false, unique: true, nullable: false)
		password(blank: false, password: true, nullable: false)
	}

	static transients = ['springSecurityService']

	static mapping = {
		password column: '`password`'
	}
}
