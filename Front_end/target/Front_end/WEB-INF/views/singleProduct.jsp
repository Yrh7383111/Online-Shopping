    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="${contextRoot}/home">Home</a></li>
                    <li><a href="${contextRoot}/show/all/products">All Products</a></li>
                    <li class="active">${product.name}</li>
                </ol>
            </div>
        </div>


        <div class="row">
            <div class="col-xs-12 col-sm-offset-3 col-sm-6">
                <div class="thumbnail">
                    <img src="${images}/${product.code}.jpg" class="img img-responsive"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-offset-3 col-sm-6">
                    <h3>${product.name}</h3>
                    <hr/>

                    <p>${product.description}</p>
                    <hr/>

                    <h4>Price: <strong> &#36; ${product.unitPrice}</strong></h4>
                    <hr/>

                    <c:choose>
                        <c:when test="${product.quantity < 1}">
                            <h6>Quantity: <span style="color:red">Out of Stock...</span></h6>
                        </c:when>
                        <c:otherwise>
                            <h6>Quantity: ${product.quantity}</h6>
                        </c:otherwise>
                    </c:choose>

                    <security:authorize access="hasAuthority('USER')">
                        <c:choose>
                            <c:when test="${product.quantity < 1}">
                                <a href="javascript:void(0)" class="btn btn-success disabled"><strike>
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</strike>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextRoot}/cart/${product.id}/products" class="btn btn-success">
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </security:authorize>

                    <a href="${contextRoot}/show/all/products" class="btn btn-primary">
                        <span class="glyphicon glyphicon-chevron-left"></span> Back
                    </a>

                    <security:authorize access="hasAuthority('ADMIN')">
                        <a href="${contextRoot}/manage/edit/${product.id}/products" class="btn btn-warning">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </a>
                    </security:authorize>
                </div>
        </div>
    </div>