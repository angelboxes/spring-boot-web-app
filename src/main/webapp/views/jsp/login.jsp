<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <font color="red">${errorMessage}</font>
    <form method="post">
        Name: <input name="name" type="text">
        Password:<input name="password" type="password">
        <input type="submit">
    </form>
</div>
<%@ include file="common/footer.jspf" %>
