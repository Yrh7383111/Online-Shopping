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
                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#categoryModal">Add New Category</button>
                    </div>


                    <div class="panel-body">
                        <form:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label col-md-4">Name</label>
                                <div class="col-md-5">
                                    <form:input type="text" class="form-control" path="name" placeholder="Product Name" />
                                    <form:errors path="name" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Brand</label>
                                <div class="col-md-5">
                                    <form:input type="text" class="form-control" path="brand" placeholder="Product Brand" />
                                    <form:errors path="brand" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Description</label>
                                <div class="col-md-5">
                                    <form:textarea class="form-control" path="description" rows="4" placeholder="Product Description" />
                                    <form:errors path="description" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Price</label>
                                <div class="col-md-5">
                                    <form:input type="number" class="form-control" path="unitPrice" placeholder="Product Unit Price" />
                                    <form:errors path="unitPrice" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Quantity</label>
                                <div class="col-md-5">
                                    <form:input type="number" class="form-control" path="quantity" placeholder="Product Quality" />
                                    <form:errors path="quantity" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Image</label>
                                <div class="col-md-5">
                                    <form:input type="file" class="form-control" path="file" />
                                    <form:errors path="file" cssClass="help-block" element="em" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Category</label>
                                <div class="col-md-5">
                                    <form:select class="form-control" path="categoryId" items="${categories}" itemLabel="name" itemValue="id" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-5">
                                    <span><input type="submit" name="submit" value="Submit" class="btn btn-primary"/></span>
                                    <c:if test="${product.id != 0}">
                                        <span class="pull-right"><a href="${contextRoot}/manage/products" class="btn btn-primary">Back</a></span>
                                    </c:if>

                                    <form:hidden path="id" />
                                    <form:hidden path="code" />
                                    <form:hidden path="active" />
                                    <form:hidden path="supplierId" />
                                    <form:hidden path="purchases" />
                                    <form:hidden path="views" />
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>



        <%-- Modal to add a new category --%>
        <div id="categoryModal" class="modal fade" role="dialog" tabindex="-1">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <%-- Modal header --%>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title">Add New Category</h4>
                    </div>

                    <%-- Modal body --%>
                    <div class="modal-body">
                        <form:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">
                            <div class="form-group">
                                <label class="control-label col-md-4">Name</label>
                                <div class="col-md-5">
                                    <form:input type="text" class="form-control" path="name" placeholder="Category Name" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Description</label>
                                <div class="col-md-5">
                                    <form:textarea class="form-control" path="description" rows="4" placeholder="Category Description" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Image URL</label>
                                <div class="col-md-5">
                                    <form:input type="text" class="form-control" path="imageURL" placeholder="Category Image URL" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" name="submit" value="Submit" class="btn btn-primary" />
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>



        <br>
        <br>



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