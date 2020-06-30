	<p class="lead">Categories</p>
	<div class="list-group">
		<c:forEach var="category" items="${categories}">
			<a href="${contextRoot}/show/category/${category.id}/products" id="a_${category.name}" class="list-group-item">${category.name}</a>
		</c:forEach>
	</div>