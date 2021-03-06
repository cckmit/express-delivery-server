<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sachindra Rodrigo
  Date: 11/23/2021
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Express Delivery - Transit Shipment</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
    <script>
        function submitHiddenForm3(mailId) {
            document.getElementById('mailId3').value = mailId;
        }

        function submitHiddenForm2(mailId) {
            document.getElementById('mailId2').value = mailId;
        }
    </script>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div>
    <h3 style="padding: 20px 30px 20px 30px">Manage Transit Packages</h3>
</div>
<div>
    <div class="package-stats-agent" style="text-align: center">
        <div>
            <h4 style="float: left">Transit Shipments</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All <i style="margin-left: 10px"
                                                                                           class="fas fa-expand"></i>
            </button>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mail = new ArrayList<>();
                try {
                    mail = (List<MailDto>) request.getAttribute("transit_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mail != null && mail.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Transit Shipments
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${transit_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button"
                                    class="btn btn-outline-success"
                                    data-toggle="modal" data-target="#openChangeDriverModal${mail.mailId}"
                            >
                                Change Driver
                            </button>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/chnage-driver-modal.jsp" %>
                <%@ include file="modals/view-package-agent.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <%@ include file="modals/accept-package.jsp" %>
    <%@ include file="modals/reject-package.jsp" %>
    <%@ include file="utils/script_imports.jsp" %>
</body>
</html>
