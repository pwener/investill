package investill

class User {
    String firstName
    String lastName
    String login
    String password
    String email

    static constraints = {
    firstName(size:1..120, nullable: false, blank: false)
    lastName(size:1..120, nullable: false, blank: false)
    login(size:4..20, nullable: false, blank: false)
    email(email: true, size:1..120, nullable: false, blank: false)
    password(password: true, size:6..20, nullable: false, blank: false)
  }
}
