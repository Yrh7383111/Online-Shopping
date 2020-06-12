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



	// Jquery dataTable for "View Products"
	const productListTable = $('#productListTable');

	if (productListTable.length)
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

		productListTable.DataTable({
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
						return '<img src="'+ window.contextRoot +'/resources/images/'+ data +'.jpg" alt="Alternate image" class="dataTableImg" />';
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
					// Quantity
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



	// Jquery dataTable for "Manage Products" for admin
	const adminProductsTable = $('#adminProductsTable');

	if (adminProductsTable.length)
	{
		const jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		adminProductsTable.DataTable({
			lengthMenu: [[10, 30, 50, -1], ['10', '30', '50', 'ALL']],
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id'
				},
				{
					// Image
					data: 'code',
					bSortable: false,
					mRender: function(data) {
						return '<img src="'+ window.contextRoot +'/resources/images/'+ data +'.jpg" alt="Alternate image" class="adminDataTableImg" />';
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
					// Quantity
					data: 'quantity',
					mRender: function(data) {
						if (data < 1)
						{
							return '<span style="color:red">Out of Stock...</span>';
						}

						return data;
					}
				},
				{
					// Price
					data: 'unitPrice',
				},
				{
					// Activate button
					data: 'active',
					bSortable: false,
					mRender: function(data, type, row) {
						let string = '';

						if (data)
						{
							string += '<label class="switch"> <input type="checkbox" checked="checked" value="'+ row.id +'" /> <div class="slider round"></div> </label>'
						}
						else {
							string += '<label class="switch"> <input type="checkbox" value="'+ row.id +'" /> <div class="slider round"></div> </label>'
						}

						return string;
					}
				},
				{
					// Edit button
					data : 'id',
					bSortable : false,
					mRender : function(data) {
						let string = '';

						string += '<a href="" class="btn btn-warning"> <span class="glyphicon glyphicon-pencil"></span> </a>'

						return string;
					}
				}
			],

			initComplete: function () {
				// Boot box support
				const api = this.api();

				api.$('.switch input[type="checkbox"]').on('change' , function() {
					const checkbox = $(this);
					const checked = checkbox.prop('checked');
					const value = checkbox.prop('value');
					const message = (checked)? 'Do you want to activate the product?' : 'Do you want to deactivate the product?';

					bootbox.confirm({
						size: 'medium',
						title: 'Product Activation/Deactivation',
						message: message,
						callback: function (confirmed) {
							if (confirmed) {
								$.ajax({
									type: 'POST',
									url: window.contextRoot + '/manage/products/'+ value +'/activation',
									timeout: 5000,
									success: function(data) {
										bootbox.alert({
											size: 'medium',
											title: 'Confirmation',
											message: data
										});
									},
									error: function() {
										bootbox.alert({
											size: 'medium',
											title: 'Error',
											message: 'Something went wrong'
										});
									}
								});
							}
							else {
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
		});
	}
});