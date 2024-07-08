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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="text-center">
        <div class="mb-3">
            <h2>MANAGE CUSTOMERS</h2>
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
                <a href="/customer/create" class="btn btn-success">Create new customer</a>
                <a href="/login/home" class="btn btn-secondary">Home</a>
            </div>
            <div class="col-5">
                <form action="/customer/search">
                    <div class="d-flex">
                        <label>Search customer: </label>
                        <input type="text" name="key" class="form-control">
                        <button style="margin-left: 20px" type="submit" class="btn btn-warning">Search</button>
                    </div>
                </form>
            </div>
            <div class="col-2">

            </div>
        </div>
    </div>
   <div style="height: 350px">
       <table class="table table-bordered" style="margin-bottom: 30px">
           <thead>
           <tr>
               <th>STT</th>
               <th>ID</th>
               <th>Name</th>
               <th>Phone number</th>
               <th>Code</th>
               <th>Status</th>
               <th colspan="2">Action</th>
           </tr>
           </thead>
           <c:forEach items="${listCustomer.content}" varStatus="i" var="customer">
               <tbody>
               <tr>
                   <td>${i.index+1}</td>
                   <td>${customer.id}</td>
                   <td>${customer.name}</td>
                   <td>${customer.phoneNumber}</td>
                   <td>${customer.address}</td>
                   <td>${customer.status==1?"Active":"Inactive"}</td>
                   <td>
                       <a href="/customer/detail/${customer.id}" class="btn btn-warning">Edit</a>
                       <a href="/customer/delete/${customer.id}" class="btn btn-danger"
                          onclick="return confirm('Do you want to delete this customer?')">Delete</a>
                   </td>
               </tr>
               </tbody>
           </c:forEach>
       </table>
   </div>

    <div class="text-center">
        <a href="/customer/home?page=0" class="btn btn-secondary">First</a>
        <a href="/customer/home?page=${listCustomer.first?0:listCustomer.number-1}" class="btn btn-secondary">Previous</a>
        <a href="/customer/home?page=${listCustomer.last?listCustomer.totalPages-1:listCustomer.number+1}" class="btn btn-secondary">Next</a>
        <a href="/customer/home?page=${listCustomer.totalPages-1}" class="btn btn-secondary">Last</a>
    </div>
</div>
</body>
</html>