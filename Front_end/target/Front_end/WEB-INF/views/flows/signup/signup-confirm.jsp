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
                    <a href="${flowExecutionUrl}&_eventId_success" class="btn btn-lg btn-primary">Submit</a>
                </div>
            </div>
        </div>
    </div>
<%@include file="../shared/flow-footer.jsp" %>