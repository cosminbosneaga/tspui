function removeSpan(input){
	input.find("span").remove();
}

function validateForm(formName){
	
	removeErrors();
	var valid = true;
	
	var checkLetters = /^[a-zA-Z']$/;
	var checkEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var checkNumbers = /^(?=.*\d)(?:[\d ]+)$/;
	var checkDate = /^\d{4}-\d{2}-\d{2}$/
	
	// Validate required fields
	$("#"+formName).find(".required").each(function(){
		var input = $(this);
		var value = input.val();
		if(value == "")
		{
			valid = false;
			input.parent().addClass("has-error");
			removeSpan(input.parent());
			input.before("<span class=\"control-label\">This field is required!</span>");
		}
	});
	
	// Validate email fields
	$("#"+formName).find(".email").each(function(){
		var input = $(this);
		var value = input.val();
		if(!checkEmail.test(value) && !input.hasClass("has-error"))
		{
			valid = false;
			input.parent().addClass("has-error");
			removeSpan(input.parent());
			input.before("<span class=\"control-label\">Please provide a valid email!</span>");
		}
	});
	
	// Validate number fields
	$("#"+formName).find(".numbers").each(function(){
		var input = $(this);
		var value = input.val();
		if(!checkNumbers.test(value) && !input.hasClass("has-error"))
		{
			valid = false;
			input.parent().addClass("has-error");
			removeSpan(input.parent());
			input.before("<span class=\"control-label\">Please input only numbers!</span>");
		}
	});
	
	// Validate date fields
	$("#"+formName).find(".date").each(function(){
		var input = $(this);
		var value = input.val();
		if(!checkDate.test(value) && !input.hasClass("has-error"))
		{
			valid = false;
			input.parent().addClass("has-error");
			removeSpan(input.parent());
			input.before("<span class=\"control-label\">Please input a valid date!</span>");
		}
	});

	$('html, body').animate({ scrollTop: 0 }, 'slow');
	return valid;
}

// Clear all errors on page before starting a new check
function removeErrors()
{
	$(".form-group").removeClass("has-error");
	$(".form-group").find("span").remove();
}