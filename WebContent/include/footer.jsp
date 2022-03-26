<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    </section>
   </main>
    <footer class="bg-dark">
      <div class="container text-muted text-center p-5">
        Copyright &copy; ChaDaEun 2022 <br />
        <a href="http://github.com/chadaeun/simple-jsp-practice" target="_blank">GitHub</a>
      </div>
    </footer>

    <div class="modal" id="signInModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Sign In</h4>
          </div>
          <form action="${root }/user" method="post">
            <div class="modal-body">
              <!-- form here -->
              <div class="form-floating mb-3">
              	<input type="hidden" name="action" value="signin" />
                <input type="text" class="form-control" id="id" name="id" />
                <label for="id">ID</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" name="password" />
                <label for="password">Password</label>
              </div>
            </div>
            <div class="modal-footer justify-content-center">
              <!-- btn here -->
              <input type="submit" value="Sign In" class="btn btn-primary" />
            </div>
          </form>
        </div>
      </div>
    </div>

    <div class="modal" id="signUpModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Sign Up</h4>
          </div>
          <form action="${root }/user" method="post">
            <div class="modal-body">
              <!-- form here -->
              <input type="hidden" name="action" value="signup" />
              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="id" name="id" />
                <label for="id">ID</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" name="password" />
                <label for="password">Password</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="password-confirm"
                  name="password-confirm"
                />
                <label for="password-confirm">Confirm Password</label>
              </div>
            </div>
            <div class="modal-footer justify-content-center">
              <!-- btn here -->
              <input type="submit" value="Sign Up" class="btn btn-primary" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
