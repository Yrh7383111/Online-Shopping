    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <div class="container">
        <%-- Pop up message --%>
        <c:if test="${not empty message}">
            <div class="row">
                <div class="col-xs-12 col-md-offset-2 col-md-8">
                    <div class="alert alert-info fade in">${message}</div>
                </div>
            </div>
        </c:if>


        <%-- Form to add a new product --%>
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Product Management</h4>
                    </div>


                    <div class="panel-body">
                        <form:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label col-md-4" for="name">Name</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="name" id="name" placeholder="Product Name" class="form-control" />
                                    <form:errors path="name" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="brand">Brand</label>
                                <div class="col-md-5">
                                    <form:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control" />
                                    <form:errors path="brand" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="description">Description</label>
                                <div class="col-md-5">
                                    <form:textarea path="description" id="description" rows="4" placeholder="Description" class="form-control" />
                                    <form:errors path="description" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="unitPrice">Price</label>
                                <div class="col-md-5">
                                    <form:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price" class="form-control" />
                                    <form:errors path="unitPrice" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="quantity">Quantity</label>
                                <div class="col-md-5">
                                    <form:input type="number" path="quantity" id="quantity" placeholder="Quality" class="form-control" />
                                    <form:errors path="quantity" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="file">Image</label>
                                <div class="col-md-5">
                                    <form:input type="file" path="file" id="file" class="form-control" />
                                    <form:errors path="file" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="categoryId">Category</label>
                                <div class="col-md-5">
                                    <form:select path="categoryId" id="categoryId" class="form-control" items="${categories}" itemLabel="name" itemValue="id" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>

                                    <form:hidden path="id"/>
                                    <form:hidden path="code"/>
                                    <form:hidden path="supplierId"/>
                                    <form:hidden path="active"/>
                                    <form:hidden path="purchases"/>
                                    <form:hidden path="views"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>


        <%-- Table the display all the products for admin --%>
        <div class='col-xs-12'>
            <h1>Available Products</h1>
            <hr/>
        </div>

        <div class="row">
            <div class='col-xs-12'>
                <div style="overflow: auto">
                    <table id="adminProductsTable" class="table table-condensed table-bordered">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Quantity</th>
                                <th>Price ($)</th>
                                <th>Activate</th>
                                <th>Edit</th>
                            </tr>
                        </thead>

                        <tfoot>
                            <tr>
                                <th>Id</th>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Quantity</th>
                                <th>Price ($)</th>
                                <th>Activate</th>
                                <th>Edit</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>