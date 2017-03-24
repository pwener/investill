package investill

import org.springframework.security.access.annotation.Secured

class SensitiveContentController {
  @Secured(['ROLE_ADMIN'])
  def index() {
    render "Some sensitive content"
  }
}
