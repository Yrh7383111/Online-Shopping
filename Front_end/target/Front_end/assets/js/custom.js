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
	else if (menu === 'Manage Products')
	{
		$('#manageProducts').addClass('active');
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
					data: 'quantity',
					mRender: function(data, type, row) {
						if (data < 1)
						{
							return '<span style="color:red">Out of Stock...</span>';
						}

						return data;
					}
				},
				{
					// Buttons
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						let string = '';

						string += '<a href="'+ window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

						if (row.quantity < 1)
						{
							string += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						else {
							string += '<a href="'+ window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}

						return string;
					}
				}
			]
		});
	}


	// Alert fade out animation
	const $alert = $('.alert');

	if ($alert.length)
	{
		setTimeout(function() {
				$alert.fadeOut('slow');
				}, 3000);
	}
});