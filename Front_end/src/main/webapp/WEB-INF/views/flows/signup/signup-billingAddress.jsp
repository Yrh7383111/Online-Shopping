<%@include file="../shared/flow-header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Sign Up - Billing Address</h4>
                    </div>

                    <div class="panel-body">
                        <form:form method="POST" modelAttribute="billingAddress" class="form-horizontal" id="billingAddressForm">
                            <div class="form-group">
                                <label class="control-label col-md-4">Address Line One</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="addressLineOne" class="form-control" placeholder="Address Line One" />
                                    <form:errors path="addressLineOne" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Address Line Two</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="addressLineTwo" class="form-control" placeholder="Address Line Two" />
                                    <form:errors path="addressLineTwo" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">City</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="city" class="form-control" placeholder="City" />
                                    <form:errors path="city" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">State</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="state" class="form-control" placeholder="State" />
                                    <form:errors path="state" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Country</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="country" class="form-control" placeholder="Country" />
                                    <form:errors path="country" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Postal Code</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="postalCode" class="form-control" placeholder="Postal Code" />
                                    <form:errors path="postalCode" cssClass="help-block" element="em" />
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" name="_eventId_user" class="btn btn-primary">
                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                        Back - User
                                    </button>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" name="_eventId_confirm" class="btn btn-primary">
                                        Next - Confirm
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