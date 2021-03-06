<%@include file="../../shared/flow-header.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <c:forEach var="cartLine" items="${checkoutModel.cartLines}">
                        <div class="col-xs-12">
                            <div>
                                <h3>${cartLine.product.name}</h3>
                                <hr/>
                                <h4>Quantity - ${cartLine.productCount}</h4>
                                <h5>Buying Price - $ ${cartLine.buyingPrice}</h5>
                            </div>
                            <hr/>
                            <div class="text-right">
                                <h3>Grand Total - $ ${cartLine.total}</h3>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>


            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Payment Details
                        </h3>
                    </div>

                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                <label>CARD NUMBER</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Valid Card Number" required autofocus />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-7 col-md-7">
                                    <div class="form-group">
                                        <label>EXPIRY DATE</label>
                                        <br/>
                                        <div class="col-xs-6 col-lg-6 pl-ziro">
                                            <input type="text" class="form-control" placeholder="MM" required />
                                        </div>
                                        <div class="col-xs-6 col-lg-6 pl-ziro">
                                            <input type="text" class="form-control" placeholder="YY" required /></div>
                                    </div>
                                </div>
                                <div class="col-xs-5 col-md-5 pull-right">
                                    <div class="form-group">
                                        <label>CV CODE</label>
                                        <input type="password" class="form-control" placeholder="CV" required />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href=""><span class="badge pull-right"> $ ${checkoutModel.checkoutTotal}</span> Final Payment</a></li>
                </ul>
                <br/>
                <a href="${flowExecutionUrl}&_eventId_addOrder" class="btn btn-success btn-lg btn-block" role="button">Pay</a>
            </div>
        </div>
    </div>
<%@include file="../../shared/flow-footer.jsp" %>