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

<body onload="ShowMess()" class="hold-transition sidebar-mini">
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
                    <li class="nav-item">
                        <a href="/benh-nhans" class="nav-link active">
                            <span class="nav-icon badge">B</span>
                            <p>[Bệnh nhân]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/lich-khams" class="nav-link">
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
                        <h1 class="m-0">Danh sách bệnh nhân</h1>
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
                                <button class="btn btn-primary"><a href="benh-nhans" class="btn-primary">Tất cả</a>
                                </button>
                                <button id="btnCreate" class="btn btn-primary">Tạo mới</button>
                            </div>
                            <div class="m-2 l-5">
                                <form style="margin-top: 20px; margin-left: 10px;" action="/benh-nhans" method="get">
                                    <input type="hidden" name="action" value="laybenhnhantheoma">
                                    <input type="text" name="maBenhNhan"/>
                                    <button class="btn-primary" style="margin-right: 100px;" type="submit">Tìm kiếm
                                    </button>
                                </form>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="products" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Mã bênh nhân</th>
                                        <th>Tên bệnh nhân</th>
                                        <th>Ngày sinh</th>
                                        <th>Giới tính</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${benhNhans}" var="b">
                                        <tr>
                                            <td>${b.mabn}</td>
                                            <td>
                                                    ${b.tenbn}
                                                <span style="color: blue; text-decoration: underline; float: right; cursor: pointer;">
                                                    <a href="/lich-khams?action=layLichKhamCuaBenhNhan&maBenhNhan=${b.mabn}">
                                                        Xem các lịch khám
                                                    </a>
                                             </span>
                                            </td>
                                            <td>${b.ngaysinh}</td>
                                            <td>${b.gioitinh == 1 ? "Nam" : "Nữ"}</td>
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
            <h3 class="modal-title">Tạo mới thông tin bệnh nhân</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/benh-nhans" method="post">
                    <input type="hidden" name="action" value="create"/>
                    <div class="input-field">
                        <table>
                            <tr>
                                <td>Tên bệnh nhân:</td>
                                <td><input type="text" name="tenBenhNhan"/></td>
                            </tr>
                            <tr>
                                <td>Ngày sinh:</td>
                                <td><input type="date" name="ngaySinh"/></td>
                            </tr>
                            <tr>
                                <td> Giới tính:</td>
                                <td style="position: absolute;">
                                    <input type="radio" style="width:20px; position: relative; top: -20px;" value="1"
                                           name="gioiTinh" checked="checked"/><span
                                        style="width:20px; position: relative; left: 10px;top: -28px;">Nam</span>

                                    <input type="radio" style="width:20px; position: relative; top: -20px; left: 20px;"
                                           value="0" name="gioiTinh"/><span
                                        style="width:20px; position: relative; left: 30px;top: -28px;">Nữ</span>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <button type="submit" style="margin-top: 20px;">Tạo mới</button>
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
                <form action="/khoas" method="post">
                    <div class="input-field">
                        Bạn có chắc chắn muốn xóa khoa <span id="tenK"></span> ?
                    </div>
                    <input id="maK" name="id" type="hidden"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="submit">Xóa</button>
                </form>
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
<script src="/static/js/benhnhan.js"></script>
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
<script>
    $("#products").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": true,
        "responsive": true,
    });
</script>
</body>

</html>