<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update customer</title>
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
            <h2>${message}</h2>
        </div>
    </div>
    <form:form action="/customer/update" method="post" modelAttribute="customerDetail">
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label>Name: </label>
                    <form:input type="text" path="name" class="form-control"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Phone number: </label>
                    <form:input type="text" path="phoneNumber" class="form-control"/>
                    <form:errors path="phoneNumber" cssClass="text-danger"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label class="form-label">Chọn địa chỉ:</label>
                    <div>
                        <select id="province" class="form-select">
                            <option disabled selected value="">Chọn tỉnh/thành phố</option>
                        </select>
                        <select id="district" class="form-select" disabled>
                            <option disabled selected value="">Chọn quận/huyện</option>
                        </select>
                        <select id="ward" class="form-select" disabled>
                            <option disabled selected value="">Chọn xã/phường</option>
                        </select>
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Địa chỉ khách hàng:</label>
                    <form:input type="text" class="form-control" id="resultAddress" path="address"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label>Status: </label>
                    <div>
                        <form:radiobutton path="status" value="1"/>
                        <label>Active</label>
                        <form:radiobutton path="status" value="0"/>
                        <label>Inactive</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-warning"
                    onclick="return confirm('Do you want to update this customer?')">Update
            </button>
            <a href="/staff/home" class="btn btn-secondary">Manage customer</a>
        </div>
    </form:form>
</div>
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    const host = "https://raw.githubusercontent.com/AnhNT-D421/API_Adress_VietNam/main/";

    const renderData = (data, target) => {
        const selectElement = document.getElementById(target);
        if (!selectElement) return;
        selectElement.innerHTML = "";
        if (data && data.length > 0) {
            data.forEach(item => {
                const option = document.createElement("option");
                option.value = item.code;
                option.text = item.name_with_type;
                selectElement.appendChild(option);
            });
        }
    };
    const fetchData = (url, callback) => {
        axios.get(url)
            .then(response => callback(response.data))
    }

    const renderCities = () => fetchData(host + "cities.json", data => renderData(data, "province"));
    const renderDistricts = provinceId => fetchData(host + "districts.json", data => renderData(data.filter(district => district.parent_code === provinceId), "district"));
    const renderWards = districtCode => fetchData(host + "wards.json", data => renderData(data.filter(ward => ward.parent_code === districtCode), "ward"));

    $(function () {
        renderCities();

        $("#province").change(() => {
            const provinceId = $("#province").val();
            if (provinceId) {
                renderDistricts(provinceId);
                $("#district, #ward").prop("disabled", false);
            } else {
                $("#district, #ward").empty().prop("disabled", true);
            }
        });

        $("#district").change(() => {
            const districtCode = $("#district").val();
            if (districtCode) {
                renderWards(districtCode);
                $("#ward").prop("disabled", false);
            } else {
                $("#ward").empty().prop("disabled", true);
            }
        });

        const renderAddress = () => {
            const provinceName = $("#province option:selected").text()||"";
            const districtName = $("#district option:selected").text()||"";
            const wardName = $("#ward option:selected").text()||"";
            $("#resultAddress").val(provinceName+", "+districtName+", "+ wardName);
        };
        $("#province, #district, #ward").on("change", function () {
            renderAddress();
        });
    });
</script>
</html>