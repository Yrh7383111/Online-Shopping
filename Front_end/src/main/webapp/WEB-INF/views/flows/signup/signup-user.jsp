<%@include file="../shared/flow-header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Sign Up - User Info</h4>
                    </div>

                    <div class="panel-body">
                        <form:form method="POST" modelAttribute="user" class="form-horizontal" id="personalForm">
                            <div class="form-group">
                                <label class="control-label col-md-4">First Name</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="firstName" class="form-control" placeholder="First Name" />
                                    <form:errors path="firstName" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Last Name</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="lastName" class="form-control" placeholder="Last Name" />
                                    <form:errors path="lastName" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Email</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="email" class="form-control" placeholder="Email" />
                                    <form:errors path="email" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Contact Number</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="contactNumber" class="form-control" placeholder="Contact Number" maxlength="10" />
                                    <form:errors path="contactNumber" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Password</label>
                                <div class="col-md-5">
                                    <form:input type="password" path="password" class="form-control" placeholder="Password" />
                                    <form:errors path="password" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Confirm Password</label>
                                <div class="col-md-5">
                                    <form:input type="password" path="confirmPassword" class="form-control" placeholder="Confirm password" />
                                    <form:errors path="confirmPassword" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Role</label>
                                <div class="col-md-5">
                                    <label class="radio-inline">
                                        <form:radiobutton path="role" value="USER" checked="checked" /> User
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton path="role" value="SUPPLIER" /> Supplier
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-5">
                                    <button type="submit" name="_eventId_billingAddress" class="btn btn-primary">
                                        Next - Billing
                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="../shared/flow-footer.jsp" %>