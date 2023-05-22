<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>[ProjectName]</title>

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/fontawesome.all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/static/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/static/css/responsive.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/css/adminlte.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/starter.html" class="brand-link">
            <img src="/static/img/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Đặt lịch khám bệnh</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <li class="nav-item">
                        <a href="/khoas" class="nav-link">
                            <span class="nav-icon badge">K</span>
                            <p>[Khoa]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/bac-sis" class="nav-link">
                            <span class="nav-icon badge">B</span>
                            <p>[Bác sĩ]</p>
                        </a>
                    </li>
                    <li class="nav-item option-khoa">
                        <a href="/benh-nhans" class="nav-link">
                            <span class="nav-icon badge">B</span>
                            <p>[Bệnh nhân]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a  onclick="function getLickKham() {
                            let maBn = localStorage.getItem('maBn');
                            window.location = `http://localhost:8080/lich-khams?action=layLichKhamCuaBenhNhan&maBenhNhan=`+maBn;
                        }
                        getLickKham()" class="nav-link active">
                            <span class="nav-icon badge">L</span>
                            <p>[Lịch khám]</p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Danh sách lịch khám</h1>
                        <input value="${message}" id="mess" type="hidden"/>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <button class="btn btn-primary"><a href="lich-khams" class="btn-primary">Tất cả</a>
                                </button>
                                <button id="btnCreate" class="btn btn-primary">Tạo mới</button>
                            </div>
                            <div class="m-2 l-5 option-khoa">
                                <form style="margin-top: 20px; margin-left: 10px;" action="/lich-khams" method="get">
                                    <input type="hidden" name="action" value="getLichKhamInTime">
                                    From: <input type="date" name="ngayBatDau"/>
                                    To: <input type="date" name="ngayKetThuc"/>
                                    <button class="btn-primary" style="margin-right: 100px;" type="submit">Tìm kiếm
                                    </button>
                                </form>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="products" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Mã lịch</th>
                                        <th>Ngày khám</th>
                                        <th>Bác sĩ</th>
                                        <th>Bệnh nhân</th>
                                        <th>Nội dung</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${lichKhams}" var="lich">
                                        <tr>
                                            <td>${lich.id}</td>
                                            <td>${lich.ngaykham}</td>
                                            <td>
                                                <c:forEach items="${bacSis}" var="bacsi">
                                                    <c:if test="${lich.bacSi.mabs == bacsi.mabs}">
                                                        <c:out value="${bacsi.tenbs}"/>
                                                        <span style="color: blue; text-decoration: underline; float: right;  cursor: pointer;">
                                                           <a href="/lich-khams?action=getByBacSi&maBacSi=${bacsi.mabs}">
                                                        Xem các lịch khác
                                                    </a>
                                                        </span>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>

                                                <c:forEach items="${benhNhans}" var="benhnhan">
                                                    <c:if test="${lich.benhNhan.mabn == benhnhan.mabn}">
                                                        <c:out value="${benhnhan.tenbn}"/>
                                                        <span class="option-khoa" style="color: blue; text-decoration: underline; float: right; cursor: pointer;">
                                                    <a href="/lich-khams?action=getLichKhamOfBenhNhan&maBenhNhan=${benhnhan.mabn}">
                                                        Xem các lịch khác
                                                    </a>
                                                </span>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${lich.noidung}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col-md-6 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <!-- Trigger/Open The Modal -->
    <!-- <button id="myBtn">Open Modal</button> -->

    <!-- The Modal -->
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Sửa thông tin khoa</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/khoas" method="post">
                    <div class="input-field">
                        Mã khoa: <input type="text" name="id" readonly id="makhoa"/>
                    </div>
                    <div class="input-field">
                        Tên khoa: <input type="text" name="name" id="khoa"/>
                    </div>
                    <input type="hidden" name="action" value="edit"/>
                    <button type="submit">Sửa</button>
                </form>
            </div>
        </div>

    </div>


    <div id="modalCreate" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Tạo mới lịch khám</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/lich-khams" method="post">
                    <input type="hidden" name="action" value="create"/>

                    <input type="hidden" name="maBn" id="maBnCreate" value="1" />

                    <div class="input-field">
                        <table>
                            <tr>
                                <td>Ngày khám:</td>
                                <td><input type="date" name="ngayKham"/></td>
                            </tr>
                            <tr>
                                <td>Bác sĩ:</td>
                                <td>
                                    <select name="maBacSi" id="idBacSi">
                                        <c:forEach items="${bacSis}" var="bacsi">
                                            <option value="${bacsi.mabs}">
                                                    ${bacsi.tenbs}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td> Bệnh nhân:</td>
                                <td>
                                    <select name="maBenhNhan" id="idBenhNhan">
                                        <c:forEach items="${benhNhans}" var="benhnhan">
                                            <option value="${benhnhan.mabn}">
                                                    ${benhnhan.tenbn}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td> Nội dung:</td>
                                <td><input type="text" name="noiDung"/></td>
                            </tr>
                        </table>
                        <button type="submit">Tạo mới</button>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <div id="modalDelete" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Xóa thông tin khoa</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/lich-khams" method="post">
                    <div class="input-field">
                        Bạn có chắc chắn muốn xóa lịch khám?
                    </div>
                    <input id="maLichKham" name="maLichKham" type="hidden"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="submit">Xóa</button>
                </form>
            </div>
        </div>

    </div>

    <div id="modalDetail" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Xem chi tiết lịch khám</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <div>
                    <p>Mã lịch: <span id="idLichKham"></span></p>
                </div>
                <div>
                    <p>Ngày khám: <span id="idNgayKham"></span></p>
                </div>
                <div>
                    <p>Nội dung: <span id="idNoiDung"></span></p>
                </div>
                <div>
                    <p>Bác sĩ: <span id="idMaBs"></span></p>
                </div>
                <div>
                    <p>Bệnh nhân: <span id="idMaBn"></span></p>
                </div>
            </div>
        </div>

    </div>


    <!-- Main Footer -->
    <footer class="main-footer">
        With
        <a href=""> Đặt lịch khám bệnh </a>.
    </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/static/js/lichkham.js"></script>
<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/js/adminlte.min.js"></script>
<!-- DataTables  & Plugins -->
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="/static/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/dataTables.responsive.min.js"></script>
<script src="/static/js/responsive.bootstrap4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<%--<script>--%>
<%--    $("#products").DataTable({--%>
<%--        "paging": true,--%>
<%--        "lengthChange": false,--%>
<%--        "searching": false,--%>
<%--        "ordering": true,--%>
<%--        "info": true,--%>
<%--        "autoWidth": true,--%>
<%--        "responsive": true,--%>
<%--    });--%>
<%--</script>--%>
</body>

</html>