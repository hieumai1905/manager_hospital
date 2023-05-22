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

<body onload="ShowMessage()" class="hold-transition sidebar-mini">
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
            <a href="/bac-sis" class="nav-link active">
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
            <a onclick="function getLickKham() {
                            let maBn = localStorage.getItem('maBn');
                            window.location = `http://localhost:8080/lich-khams?action=layLichKhamCuaBenhNhan&maBenhNhan=`+maBn;
                        }
                        getLickKham()" class="nav-link">
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
            <h1 class="m-0">Danh sách bác sĩ</h1>
            <input id="mess" value="${message}" type="hidden"/>
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
                <button id="btnCreate" class="btn btn-primary option-khoa">Tạo mới</button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="products" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã bác sĩ</th>
                    <th>Tên bác sĩ</th>
                    <th>Ngày sinh</th>
                    <th>Khoa</th>
                    <th style="text-align:center" id="idLuaChon" class="option-khoa">Lựa chọn</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${bacSis}" var="b">
                    <tr id="${b.mabs}">
                      <td>${b.mabs}</td>


                      <td>
                          ${b.tenbs}
                        <span class="option-khoa" style="color: blue; text-decoration: underline; float: right; cursor: pointer;">
                              <a href="/lich-khams?action=getByBacSi&maBacSi=${b.mabs}">
                                Xem các lịch khám
                              </a>
                        </span>
                      </td>


                      <td class="dob">${b.ngaysinh}</td>
                      <c:forEach items="${khoas}" var="k">
                        <c:if test="${b.khoa.maK == k.maK}">
                          <td class="khoa">${k.tenK}</td>
                        </c:if>
                      </c:forEach>

                      <td style="text-align: center;" class="option-khoa">
                        <button class="btn btn-warning btn-sm" onclick="GetDataDoctor('${b.mabs}', '${b.tenbs}', '${b.khoa.maK}')">Sửa
                        </button>
                        |
                        <button class="btn btn-danger btn-sm" onclick="DeleteDoctor('${b.mabs}', '${b.tenbs}')">Xóa</button>
                      </td>
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

  <div id="modalCreate" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <h3 class="modal-title">Tạo mới thông tin bác sĩ</h3>
      <div class="close" onclick="close('modalCreate')">&times;</div>
      <div class="modal-body">
        <form action="/bac-sis" method="post">
          <input type="hidden" name="action" value="create"/>
          <div class="input-field">

            <table>
              <tr>
                <td>Tên bác sĩ:</td>
                <td><input type="text" name="tenBacSi"/></td>
              </tr>

              <tr>
                <td>Ngày sinh:</td>
                <td><input type="date" name="ngaySinh"/></td>
              </tr>

              <tr>
                <td>Khoa:</td>
                <td><select name="maKhoa">
                  <c:forEach items="${khoas}" var="k">
                    <option value="${k.maK}">${k.tenK}</option>
                  </c:forEach>
                </select></td>
              </tr>
            </table>
          </div>
          <button type="submit" style="justify-content: center; margin-top: 10px;">Tạo mới</button>
        </form>
      </div>
    </div>

  </div>

  <!-- The Modal -->
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <h3 class="modal-title">Sửa thông tin bác sĩ</h3>
      <div class="close" onclick="close('myModal')">&times;</div>
      <div class="modal-body">
        <form action="bac-sis" method="post">
          <input type="hidden" name="action" value="edit"/>
          <input type="hidden" name="maBacSi" id="mabs"/>
          <div class="input-field">

            <table>
              <tr>
                <td>Tên bác sĩ:</td>
                <td><input type="text" name="tenBacSi" id="tenbs"/></td>
              </tr>

              <tr>
                <td>Ngày sinh:</td>
                <td><input type="date" name="ngaySinh" id="dob"/></td>
              </tr>

              <tr>
                <td>Khoa:</td>
                <td><select name="maKhoa" id="makhoa">
                  <c:forEach items="${khoas}" var="k">
                    <option value="${k.maK}">${k.tenK}</option>
                  </c:forEach>
                </select></td>
              </tr>
            </table>
          </div>
          <button type="submit">Sửa</button>
        </form>
      </div>
    </div>

  </div>


  <div id="modalDelete" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <h3 class="modal-title">Xóa thông tin bác sĩ</h3>
      <div class="close" onclick="close('modalDelete')">&times;</div>
      <div class="modal-body">
        <form action="/bac-sis" method="post">
          <div class="input-field">
            Bạn có chắc chắn muốn xóa bác sĩ <span id="nameBs"></span> ?
          </div>
          <input id="id" name="maBacSi" type="hidden"/>
          <input type="hidden" name="action" value="delete"/>
          <button type="submit" style="margin-top: 10px;">Xóa</button>
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
<script src="/static/js/bacsi.js"></script>
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
