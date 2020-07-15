    <div class="container">
        <c:if test="${not empty message}">
            <div class="alert alert-info fade in">
                <h3 class="text-center">${message}</h3>
            </div>
        </c:if>


        <c:choose>
            <c:when test="${not empty cartLines}">
                <table id="cart" class="table table-hover table-condensed">
                    <thead>
                        <tr>
                            <th style="width:50%">Product</th>
                            <th style="width:10%">Price</th>
                            <th style="width:8%">Quantity</th>
                            <th style="width:22%" class="text-center">Subtotal</th>
                            <th style="width:10%"></th>
                        </tr>
                    </thead>


                    <tbody>
                        <c:forEach var="cartLine" items="${cartLines}">
<%--                        <c:if test="${cartLine.available == false}">--%>
<%--                            <c:set var="${userModel.cart.cartLines}" value="${userModel.cart.cartLines - 1}"/>--%>
<%--                        </c:if>--%>

                            <tr>
                                <td data-th="Product">
                                    <div class="row">
                                        <div class="col-sm-4 hidden-xs"><img src="${images}/${cartLine.product.code}.jpg" alt="${cartLine.product.name}" class="img-responsive cartImg"/></div>
                                        <div class="col-sm-8">
                                            <h4 class="nomargin">${cartLine.product.name}</h4>
                                            <p>Brand: ${cartLine.product.brand}</p>
                                            <p>Description: ${cartLine.product.description}
                                        </div>
                                    </div>
                                </td>
                                <td data-th="Price">$ ${cartLine.buyingPrice}</td>
                                <td data-th="Quantity">
                                    <input type="number" class="form-control text-center" id="quantity${cartLine.id}" value="${cartLine.productCount}" min="1">
                                </td>
                                <td data-th="Subtotal" class="text-center">$ ${cartLine.total}</td>
                                <td class="actions" data-th="">
                                    <button type="button" name="refreshCart" class="btn btn-info btn-sm" value="${cartLine.id}">
                                        <span class="glyphicon glyphicon-refresh"></span>
                                    </button>
                                    <a href="${contextRoot}/cart/delete/cartlines/${cartLine.id}" class="btn btn-danger btn-sm">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>


                    <tfoot>
                        <tr class="visible-xs">
                            <td class="text-center"><strong>$ ${userModel.cart.grandTotal}</strong></td>
                        </tr>

                        <tr>
                            <td><a href="${contextRoot}/show/all/products" class="btn btn-warning"><span class="glyphicon glyphicon-chevron-left"></span> Continue Shopping</a></td>
                            <td colspan="2" class="hidden-xs"></td>
                            <td class="hidden-xs text-center"><strong>Total $ ${userModel.cart.grandTotal}</strong></td>
                            <c:choose>
                                <c:when test="${userModel.cart.cartLines != 0}">
                                    <td><a href="${contextRoot}/cart/validate/cartlines" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="javascript:void(0)" class="btn btn-success btn-block disabled"><strike>Checkout <span class="glyphicon glyphicon-chevron-right"></span></strike></a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </tfoot>
                </table>
            </c:when>


            <c:otherwise>
                <div class="jumbotron">
                    <h2 class="text-center">Your Cart is Empty...</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>