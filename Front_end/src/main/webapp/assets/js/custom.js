// Active menu
$(function()
{
	if (menu === 'About Us')
	{
		$('#about').addClass('active');
	}
	else if (menu === 'Contact Us')
	{
		$('#contact').addClass('active');
	}
	else if (menu === 'All Products')
	{
		$('#listProducts').addClass('active');
	}
	else {
		if (menu !== "Home")
		{
			$('#listProducts').addClass('active');
			$('#a_' + menu).addClass('active');
		}
	}


	// Jquery dataTable
	const $table = $('#productListTable');

	if ($table.length)
	{
		let jsonUrl = '';

		//  Display different options
		if (window.categoryId === '')
		{
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId + '/products';
		}

		$table.DataTable({
			lengthMenu: [[3, 5, 10, -1], ['3', '5', '10', 'ALL']],
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					// Image
					data: 'code',
					bSortable: false,
					mRender: function(data, type, row) {
						return '<img src="'+ window.contextRoot +'/resources/images/'+ data +'.jpg" alt="Alternate image" class="dataTableImg"/>';
					}
				},
				{
					// Name
					data: 'name'
				},
				{
					// Brand
					data: 'brand'
				},
				{
					// Price
					data: 'unitPrice',
				},
				{
					data: 'quantity'
				},
				{
					// Buttons
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						let string = '';
						
						string += '<a href="'+ window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						string += '<a href="'+ window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

						return string;
					}
				}
			]
		});
	}
});