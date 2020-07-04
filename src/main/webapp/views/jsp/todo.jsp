<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <form:form method="post" modelAttribute="todo">
        <fieldset class="form-group">
            <form:hidden path="id"/>
            <form:label path="desc">Description</form:label>
            <form:input path="desc" type="text" class="form-control" required="required"></form:input>
            <form:errors path="desc" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">TargetDate</form:label>
            <form:input path="targetDate" type="text" class="form-control" required="required"></form:input>
            <form:errors path="targetDate" cssClass="text-warning"/>
        </fieldset>
        <button type="submit" class="btn btn-success">Add</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf" %>