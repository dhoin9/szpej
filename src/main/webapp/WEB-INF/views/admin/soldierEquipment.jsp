<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="header.jsp"%>
<div class="container-fluid">



    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <h1 class="h3 mb-0 text-gray-800">${soldier.firstName} ${soldier.lastName} Unit: ${soldier.warehouse.name}</h1>
        <%--        <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>--%>

        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Equipment List</h6>
                <form action="<c:url value="${soldier.id}/new"/>">
                    <input type="submit" value="Add Szpej" class="btn btn-primary">

                </form>

            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Lp.</th>
                            <th>Name</th>
                            <th>Size</th>
                            <th>Quantity</th>
                            <th>Expire date</th>
<%--                            <th>Enable</th>--%>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        </tfoot>
                        <tbody>
                        <c:set var="count" value="0" scope="page"/>
                        <c:forEach items="${equipmentPass}" var="equipment">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <th>${count}</th>
                                <th>${equipment.product.name}</th>
                                <th>${equipment.product.size}</th>
                                <th>${equipment.quantity}</th>
                                <th>${equipment.expireDate}</th>
                                <th><a href="/admin/equipment/${soldier.id}/delete/${equipment.id}">Delete</a>

                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container-fluid -->
    <!-- End of Main Content -->

    <!-- Footer -->
    <footer class="sticky-footer bg-white">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright CAK&copy; Szpej 2020</span>
            </div>
        </div>
    </footer>
    <!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <sec:authorize access="isAuthenticated()">
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <form action="<c:url value="/logout"/>" method="post">
                        <input type="submit" value="Logout" class="btn btn-primary">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <%--                <a class="btn btn-primary" href="/logout" method="post">Logout</a>--%>
                </div>
            </sec:authorize>
        </div>

    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin-2.min.js" />"></script>

<!-- Page level plugins -->
<script src="<c:url value="/resources/vendor/chart.js/Chart.min.js" />"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="/resources/js/demo/chart-area-demo.js" />"></script>
<script src="<c:url value="/resources/js/demo/chart-pie-demo.js" />"></script>

</body>

</html>

