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
});