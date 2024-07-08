<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="text-center">
        <div class="mb-3">
            <h2>${title}</h2>
        </div>
    </div>
    <form:form action="/size/update" method="post" modelAttribute="sizeDetail">
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label>ID: </label>
                    <form:input path="id" class="form-control" readonly="true"/>
                    <form:errors path="id" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Name: </label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Code: </label>
                    <form:input path="code" class="form-control"/>
                    <form:errors path="code" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label class="label-control">Status: </label>
                    <br>
                    <form:radiobutton path="status" value="1" class="form-check-input"/> Active
                    <form:radiobutton path="status" value="0" class="form-check-input"/> Inactive
                    <form:errors path="status" class="text-danger"/>
                </div>
            </div>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-warning" onclick="return confirm('Do you want to update this product?')">Update</button>
            <a href="/product/home" class="btn btn-secondary">Manage sizes</a>
        </div>
    </form:form>
</div>

</body>
</html>