<%@include file="../../shared/flow-header.jsp" %>
    <div class="container">
        <div class="alert alert-success">
            <h3 class="text-center">Your order is confirmed...</h3>
        </div>


        <div class="row">
            <div class="col-xs-12">
                <div class="invoice-title">
                    <h2>Invoice</h2>
                    <h3 class="pull-right">Order # ${orderDetail.id}</h3>
                </div>
                <hr>

                <div class="row">
                    <div class="col-xs-6">
                        <address>
                            <strong>Billed to:</strong><br>
                            ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                            ${orderDetail.billing.addressLineOne}<br>
                            ${orderDetail.billing.addressLineTwo}<br>
                            ${orderDetail.billing.city}, ${orderDetail.billing.state}<br>
                            ${orderDetail.billing.country}, ${orderDetail.billing.postalCode}
                        </address>
                    </div>
                    <div class="col-xs-6 text-right">
                        <address>
                            <strong>Shipped to:</strong><br>
                            ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                            ${orderDetail.shipping.addressLineOne}<br>
                            ${orderDetail.shipping.addressLineTwo}<br>
                            ${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
                            ${orderDetail.shipping.state} - ${orderDetail.shipping.country}
                        </address>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6">
                        <address>
                            <strong>Payment Method:</strong><br>
                            Card Payment <br>
                        </address>
                    </div>
                    <div class="col-xs-6 text-right">
                        <address>
                            <strong>Order Date:</strong><br>
                            ${orderDetail.orderDate}<br>
                        </address>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Order Summary</strong></h3>
                    </div>

                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <td><strong>Item</strong></td>
                                        <td class="text-center"><strong>Price</strong></td>
                                        <td class="text-center"><strong>Quantity</strong></td>
                                        <td class="text-right"><strong>Totals</strong></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="orderItem" items="${orderDetail.orderItems}">
                                        <tr>
                                            <td>${orderItem.product.name}</td>
                                            <td class="text-center">$ ${orderItem.buyingPrice}</td>
                                            <td class="text-center">${orderItem.productCount}</td>
                                            <td class="text-right">$ ${orderItem.total}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="text-center">
            <a href="${contextRoot}/show/products" class="btn btn-lg btn-warning">Continue Shopping</a>
        </div>
    </div>
<%@include file="../../shared/flow-footer.jsp" %>