<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid" style="width: 40%; margin: auto">

    <div class="card" style="color:#225001;">
        <form action="/login/home" method="post">
            <div class="text-center">
                <h2 style="color: #16169a"> LOGIN ACCOUNT</h2>
            </div>
            <div class="text-center" style="margin-top: 30px">
                <p style="color: #bdbdbe"> Please enter your username and password.</p>
            </div>
            <div class="row" style="margin-top: 50px;padding: 20px">
                <div class="col-12">
                    <div class="mb-5">
                        <input type="text" name="username" class="form-control" placeholder="Username">
                    </div>
                </div>
                <div class="col-12">
                    <div class="mb-3">
                        <input type="password" name="password" id="password" class="form-control" placeholder="Password">
                    </div>
                </div>
                <div class="col-12" style="margin-top: 20px">
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" id="showPasswordCheckbox"
                               onclick="togglePasswordVisibility()">
                        <label class="form-check-label" for="showPasswordCheckbox">
                            Show Password
                        </label>
                    </div>
                </div>
                <div style="margin: auto">
                    <div class="mb-3">
                        <c:if test="${not empty error}">
                            <span class="text-danger">${error}</span>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="text-center" style="margin-bottom: 40px">
                <button class="btn btn-primary">Login</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    function togglePasswordVisibility() {
        var passwordInput = document.getElementById("password");
        var checkbox = document.getElementById("showPasswordCheckbox");
        if (checkbox.checked) {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    }
</script>
</html>
