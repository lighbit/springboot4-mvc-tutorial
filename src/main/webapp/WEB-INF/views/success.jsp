<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Enrollment Detail Confirmation</title>
    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
</head>
<body>
    <div class="success">
        Confirmation message : ${success}
        <br>
        We have also sent you a confirmation mail to your email address : ${student.email}.
    </div>
</body>
</html>