<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create New Product Detail</title>
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
            <h2>Create New Product Detail</h2>
        </div>
    </div>
    <form:form action="/product-detail/create" method="post" modelAttribute="productDetailNew">
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label>Product:</label>
                    <form:select path="product.id" cssClass="form-select">
                        <option selected disabled hidden>--Select product--</option>
                        <form:options items="${products}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="product" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Size:</label>
                    <form:select path="size.id" cssClass="form-select">
                        <option selected disabled hidden="">--Select size--</option>
                        <form:options items="${sizes}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="size" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Color:</label>
                    <form:select path="color.id" cssClass="form-select">
                        <option selected disabled hidden>--Select color--</option>
                        <form:options items="${colors}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="color" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Code:</label>
                    <form:input path="code" class="form-control"/>
                    <form:errors path="code" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Name:</label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Choose file:</label>
                    <form:input path="imageFileName" type="file" cssClass="form-control"/>
                    <div class="text-danger">
                        <form:errors path="imageFileName"/>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Quantity:</label>
                    <form:input path="quantity" class="form-control"/>
                    <form:errors path="quantity" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Price:</label>
                    <form:input path="price" class="form-control"/>
                    <form:errors path="price" class="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Status:</label>
                    <br>
                    <form:radiobutton path="status" value="1" class="form-check-input"/> Active
                    <br>
                    <form:radiobutton path="status" value="0" class="form-check-input"/> Inactive
                    <form:errors path="status" class="text-danger"/>
                </div>
            </div>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-success"
                    onclick="return confirm('Do you want to create this product?')">Create
            </button>
            <a href="/product-detail/home" class="btn btn-secondary">Manage products detail</a>
        </div>
    </form:form>
</div>
</body>
</html>
