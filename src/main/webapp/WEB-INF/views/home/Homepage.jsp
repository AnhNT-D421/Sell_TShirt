<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Seller</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <style>
        #sidebar {
            width: 190px;
            height: 100vh;
            background-color: #133a62;
            color: #f5f5f5;
            padding: 20px;
            display: flex;
            flex-direction: column;
        }

        .sidebar-link {
            color: #b4bcc2;
            text-decoration: none;
            padding: 10px;
            border-radius: 5px;
            transition: all 0.3s ease;
        }

        .sidebar-link:hover {
            color: #f5f5f5;
            background-color: #5a6a7c;
        }

        .sidebar-toggle {
            font-size: 24px;
            color: #ffffff;
            cursor: pointer;
            margin-bottom: 20px;
        }

        .sidebar-nav {
            flex: 1;
            overflow-y: auto;
        }

        .sidebar-item {
            margin-bottom: 10px;
        }

        .sidebar-dropdown {
            margin-left: 20px;
        }

        .sidebar-link {
            color: #b4bcc2;
            text-decoration: none;
            padding: 15px 10px;
            border-radius: 5px;
            transition: all 0.3s ease;
        }

        .sidebar-item {
            margin-bottom: 20px;
        }

        .top-right-section {
            position: absolute;
            top: 20px;
            right: 20px;
            display: flex;
            align-items: center;
            background-color: #133a62;
            color: #f5f5f5;
            padding: 10px;
            border-radius: 5px;
        }

        .user-icon {
            font-size: 20px;
            margin-right: 10px;
        }

        .top-right-link {
            color: #f5f5f5;
            text-decoration: none;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        .top-right-link:hover {
            color: #b4bcc2;
        }
    </style>
</head>
<body>
<div class="wrapper d-flex" style="background-color: #d9d9d9">
    <div class="top-right-section" style="height:    50px;">
        <div class="user-icon">
            <i class='bx bx-user'></i>
        </div>
        <br>
        <a href="#" class="top-right-link">${username}</a>
    </div>
    <aside id="sidebar">

        <div class="d-flex align-items-center justify-content-between">
            <div class="sidebar-toggle" id="toggle-btn">
                <i class='bx bx-menu'></i>
            </div>
        </div>
        <div class="sidebar-nav">
            <div class="sidebar-item">
                <a href="/login/form" class="sidebar-link" onclick="return confirm('Do you want to log out?')">
                    <i class='bx bx-file'></i>
                    <span>Exist</span>
                </a>
            </div>
            <div class="sidebar-item">
                <a href="/customer/home" class="sidebar-link">
                    <i class='bx bx-file'></i>
                    <span>Customer</span>
                </a>
            </div>
            <c:if test="${roleCheck eq 'admin_anhnt'}">
                <div class="sidebar-item">
                    <a href="/bill/success" class="sidebar-link">
                        <i class='bx bx-file'></i>
                        <span>Bill</span>
                    </a>
                </div>
            </c:if>

            <div class="sidebar-item">
                <a href="" class="sidebar-link has-dropdown collapsed" data-bs-toggle="collapse"
                   data-bs-target="#product"
                   aria-expanded="false" aria-controls="product">
                    <i class='bx bx-package'></i>
                    <span>Product</span>
                </a>
                <div id="product" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar"
                     style="margin-top: 30px">
                    <div class="sidebar-item">
                        <a href="/product/home" class="sidebar-link">Product</a>
                    </div>
                    <div class="sidebar-item">
                        <a href="/product-detail/home" class="sidebar-link">Product detail</a>
                    </div>
                    <div class="sidebar-item">
                        <a href="/color/home" class="sidebar-link">Color</a>
                    </div>
                    <div class="sidebar-item">
                        <a href="/size/home" class="sidebar-link">Size</a>
                    </div>
                </div>
            </div>
            <div class="sidebar-item">
                <c:if test="${roleCheck eq 'admin_anhnt'}">
                    <a href="" class="sidebar-link has-dropdown collapsed" data-bs-toggle="collapse"
                       data-bs-target="#auth"
                       aria-expanded="false" aria-controls="auth">
                        <i class='bx bx-user'></i>
                        <span>Staff</span>
                    </a>
                </c:if>
                <div id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar"
                     style="margin-top: 30px">
                    <div class="sidebar-item">
                        <a href="/staff/home" class="sidebar-link">Manage staff</a>
                    </div>
                    <div class="sidebar-item">
                        <a href="/login/form" class="sidebar-link">Login</a>
                    </div>
                    <div class="sidebar-item">
                        <a href="/staff/create" class="sidebar-link">Register</a>
                    </div>
                </div>
            </div>
        </div>
    </aside>
    <article style="width: 100%;height: 700px;background-color: #f0f0f5">
        <div class="d-flex">
            <div style="width: 50%;height: 730px;background-color: #ffffff">
                <div style="width: 100%;height: 365px; background-color: #fffbfb" class="card">
                    <label>List bill:</label>
                    <a href="/bill/success" class="btn btn-success">GET BILL SUCCESS</a>
                    <table class="table table-secondary">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Staff</th>
                            <th>Customer</th>
                            <th>Phone</th>
                            <th>Total payment</th>
                            <th>Status</th>
                            <th colspan="2">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listBill}" var="bill">
                            <tr>
                                <td>${bill.id}</td>
                                <td>${bill.staff.id}</td>
                                <td>${bill.customer.id}</td>
                                <td>${bill.customer.phoneNumber}</td>
                                <td>${bill.totalPayment}</td>
                                <td>${bill.status == 1 ? "Paid" : "Unpaid"}</td>
                                <td>
                                    <a href="/login/choose-bill/${bill.id}" class="btn btn-success">Choose</a>
                                    <a href="/login/delete-bill/${bill.id}" class="btn btn-danger"
                                       onclick="return confirm('Do you want to delete this bill?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${not empty errorListSize}">
                        <div class="alert alert-danger" role="alert">
                            <p>${errorListSize}</p>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                </div>
                <div style="width: 100%;height: 365px; background-color: #ffffff" class="card">
                    <label>List bill detail:</label>
                    <table class="table table-secondary">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>ID Bill</th>
                            <th>ID Product DT</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Into money</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <c:forEach items="${listBillDetail}" var="billDetail">
                            <tbody>
                            <tr>
                                <td>${billDetail.id}</td>
                                <td>${billDetail.bill.id}</td>
                                <td>${billDetail.productDetail.id}</td>
                                <td>${billDetail.quantity}</td>
                                <td>${billDetail.price}</td>
                                <td>${billDetail.price*billDetail.quantity}</td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                            data-bs-target="#staticBackdrop${billDetail.id}">Update
                                    </button>
                                    <div class="modal fade" id="staticBackdrop${billDetail.id}"
                                         data-bs-backdrop="static"
                                         data-bs-keyboard="false"
                                         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">

                                                <form action="/login/update-bill-detail/${billDetail.id}">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5">Enter
                                                            quantity return product detail</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="number" name="quantityReturn" class="form-control"
                                                               required>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="submit" class="btn btn-warning">Update</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="/login/delete-billdetail/${billDetail.id}"
                                       class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    <c:if test="${not empty error}">
                        <p class="text-danger">
                                ${error}
                        </p>
                    </c:if>
                </div>
            </div>
            <div style="width: 50%;height: 730px;background-color: #ffffff">
                <div style="height: 300px;width: 100%;background-color: #ffffff;margin-left: 10px">
                    <div style="width: 600px; height: auto;background-color: #ffffff">
                        <form action="/login/search-customer" method="post">
                            <div class="d-flex">
                                <input type="number" value="${customerDetail.phoneNumber}"
                                       style="width: 450px; margin-right: 10px;" class="form-control" name="phoneNumber"
                                       placeholder="Enter phone number of customer">
                                <input type="hidden" name="idBill" value="${billDetail.id}">
                                <button type="submit" class="btn btn-secondary">Search</button>
                            </div>
                        </form>
                        <form:form action="/login/payment" method="post" modelAttribute="billDetail">
                            <div class="mb-3"></div>
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-2">
                                        <label>ID Bill:</label>
                                        <input type="text" path="id" value="${billDetail.id}" class="form-control"
                                               readonly>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-2">
                                        <label>ID customer</label>
                                        <input type="text" path="customer.id" value="${customerDetail.id}"
                                               class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-2">
                                        <label>Name customer</label>
                                        <input type="text" path="customer.name" value="${customerDetail.name}"
                                               class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-2">
                                        <label>Phone number</label>
                                        <input type="text" path="customer.phoneNumber"
                                               value="${customerDetail.phoneNumber}" class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="mb-2">
                                        <label>Total payment:</label>
                                        <input type="text" path="totalPayment" value="${totalAmount}"
                                               class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="mb-2">
                                        <button type="submit" style="width: 80px" class="btn btn-warning me-2"
                                                <c:if test="${empty sessionScope.totalAmount || empty sessionScope.customerDetail || empty sessionScope.selectedBillId}">
                                                    disabled
                                                </c:if>onclick="return confirm('Do you want to pay this bill?')"
                                        >Pay
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                        <div style="position: absolute;right: 400px;top: 262px">
                            <form action="/login/create-new-bill" method="post">
                                <input type="hidden" name="IDStaff" value="${IDStaff}">
                                <button type="submit" class="btn btn-success"
                                        onclick="return confirm('Do you want to create new bill?')"
                                        <c:if test="${not empty errorListSize}">disabled</c:if>>New Bill
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <div style="height: 470px;width: 100%;background-color: #ffffff;border:1px solid #a29f9f">
                    <form action="/login/search-product" style="margin-top: 20px">
                        <div class="d-flex">
                            <input type="text" name="key" class="form-control" placeholder="">
                            <button style="margin-left: 20px" type="submit" class="btn btn-warning">Search</button>
                        </div>
                    </form>
                    <div style="height: 290px">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Size</th>
                                <th>Color</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <c:forEach items="${listProductDetail.content}" var="productDetail">
                                <tbody>
                                <tr>
                                    <td>${productDetail.id}</td>
                                    <td>${productDetail.name}</td>
                                    <td>${productDetail.size.name}</td>
                                    <td>${productDetail.color.name}</td>
                                    <td>${productDetail.product.name}</td>
                                    <td>${productDetail.quantity}</td>
                                    <td>${productDetail.price}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                                data-bs-target="#staticBackdrop${productDetail.id}">Buy
                                        </button>
                                        <div class="modal fade" id="staticBackdrop${productDetail.id}"
                                             data-bs-backdrop="static"
                                             data-bs-keyboard="false"
                                             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <form action="/login/create-bill-detail" method="post">
                                                        <input type="hidden" name="idSpct" value="${ctsp.id}">
                                                        <input type="hidden" name="idBill" value="${productDetail.id}">
                                                        <input type="hidden" name="quantityPresent"
                                                               value="${productDetail.quantity}">
                                                        <input type="hidden" name="idProductDt"
                                                               value="${productDetail.id}">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Enter
                                                                quantity buy</h1>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input type="number" name="quantityBuy" class="form-control"
                                                                   required>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-primary">Buy</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="text-center">
                        <a href="/login/home?page" class="btn btn-secondary">First</a>
                        <a href="/login/home?page=${listProductDetail.first?0:listProductDetail.number-1}"
                           class="btn btn-secondary">Previous</a>
                        <a href="/login/home?page=${listProductDetail.last?listProductDetail.totalPages-1:listProductDetail.number+1}"
                           class="btn btn-secondary">Next</a>
                        <a href="/login/home?page=${listProductDetail.totalPages-1}" class="btn btn-secondary">Last</a>
                    </div>
                </div>
            </div>
        </div>
    </article>
</div>
<script>
    const toggleBtn = document.querySelector('.sidebar-toggle');
    const sidebarElements = document.querySelectorAll('#sidebar > *:not(.d-flex)');

    toggleBtn.addEventListener('click', () => {
        sidebarElements.forEach(element => {
            element.classList.toggle('d-none');
        });
        toggleBtn.querySelector('i').classList.toggle('bx-menu');
        toggleBtn.querySelector('i').classList.toggle('bx-x');
    });
</script>

</body>
</html>