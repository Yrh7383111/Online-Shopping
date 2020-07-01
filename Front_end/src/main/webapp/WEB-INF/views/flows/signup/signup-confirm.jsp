<%@include file="../shared/flow-header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Personal Info</h4>
                    </div>

                    <div class="panel-body">
                        <div class="text-center">
                            <h4><strong>Name: </strong>${signupModel.user.getFirstName()} ${signupModel.user.getLastName()}</h4>
                            <h4><strong>Email: </strong>${signupModel.user.getEmail()}</h4>
                            <h4><strong>Contact: </strong>${signupModel.user.getContactNumber()}</h4>
                            <h4><strong>Role: </strong>${signupModel.user.getRole()}</h4>
                        </div>
                    </div>

                    <div class="panel-footer">
                        <a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Billing Address</h4>
                    </div>
                    <div class="panel-body">
                        <div class="text-center">
                            <h4><strong>Address Line One: </strong>${signupModel.billing.getAddressLineOne()}</h4>
                            <h4><strong>Address Line One: </strong>${signupModel.billing.getAddressLineTwo()}</h4>
                            <h4><strong>City: </strong>${signupModel.billing.getCity()}</h4>
                            <h4><strong>State: </strong>${signupModel.billing.getState()}</h4>
                            <h4><strong>Country: </strong>${signupModel.billing.getCountry()}</h4>
                            <h4><strong>Postal Code: </strong>${signupModel.billing.getPostalCode()}</h4>
                        </div>
                    </div>

                    <div class="panel-footer">
                        <a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <div class="text-center">
                    <a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-lg btn-primary">Submit</a>
                </div>
            </div>
        </div>
    </div>
<%@include file="../shared/flow-footer.jsp" %>