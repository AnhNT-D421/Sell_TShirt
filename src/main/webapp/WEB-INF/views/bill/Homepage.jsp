<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="text-center">
        <div class="mb-3">
            <h2>MANAGE BILLS SUCCESS</h2>
        </div>
    </div>
    <div class="mb-3">
        <div class="text-center" style="height: 50px;">
            <c:if test="${not empty messages}">
                <div class="alert alert-success" role="alert">
                        ${messages}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

            </c:if>
        </div>
    </div>
    <div class="row card mb-3" style="padding: 10px">
        <div class="d-flex">
            <div class="col-7">
                <label></label>
                <a href="/login/home" class="btn btn-success">Create new bill</a>
                <a href="/bill/export-excel" class="btn btn-success">Export excel</a>
            </div>
            <div class="col-5">
                <form action="/bill/search">
                    <div class="d-flex">
                        <label>Search bill: </label>
                        <input type="text" name="key" class="form-control">
                        <button style="margin-left: 20px" type="submit" class="btn btn-warning">Search</button>
                    </div>
                </form>
            </div>
            <div class="col-2">

            </div>
        </div>
    </div>
    <div style="height: 400px">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>ID Staff</th>
                <th>ID Customer</th>
                <th>Name Customer</th>
                <th>Phone number</th>
                <th>Address number</th>
                <th>Total payment</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <c:forEach items="${listBill.content}" var="bill">
                <tbody>
                <tr>
                    <td>${bill.id}</td>
                    <td>${bill.staff.id}</td>
                    <td>${bill.customer.id}</td>
                    <td>${bill.customer.name}</td>
                    <td>${bill.customer.phoneNumber}</td>
                    <td>${bill.customer.address}</td>
                    <td>${bill.totalPayment}</td>
                    <td>${bill.status==1?"Paid":"Unpaid"}</td>
                    <td>
                        <a href="/login/delete-bill-success/${bill.id}" class="btn btn-danger" onclick="return confirm('Do you want to delete this bill?')">Delete</a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <div class="text-center">
        <a href="/bill/success?page=0" class="btn btn-secondary">First</a>
        <a href="/bill/success?page=${listBill.first?0:listBill.number-1}" class="btn btn-secondary">Previous</a>
        <a href="/bill/success?page=${listBill.last?listBill.totalPages-1:listBill.number+1}" class="btn btn-secondary">Next</a>
        <a href="/bill/success?page=${listBill.totalPages-1}" class="btn btn-secondary">Last </a>
    </div>

</div>
</body>
</html>