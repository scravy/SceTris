var toggled = true;

$(window).scroll(function() {
	var cond = ($(window).scrollTop() > 77);
	if (!(!cond || !toggled)) {
		$('#global-navbar-container').toggleClass('fixed');
		toggled = !toggled;
	} else if (!(cond || toggled)) {
		$('#global-navbar-container').toggleClass('fixed');
		toggled = !toggled;		
	}
});

var retrieved = true;
var i = 0;

(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var self = this,
					select = this.element.hide(),
					selected = select.children( ":selected" ),
					value = selected.val() ? selected.text() : "";
				var input = $( "<input>" )
					.insertAfter( select )
					.val( value )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
							response( select.children( "option" ).map(function() {
								var text = $( this ).text();
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "<strong>$1</strong>" ),
										value: text,
										option: this
									};
							}) );
						},
						select: function( event, ui ) {
							ui.item.option.selected = true;
							self._trigger( "selected", event, {
								item: ui.item.option
							});
						},
						change: function( event, ui ) {
							if ( !ui.item ) {
								var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
									valid = false;
								select.children( "option" ).each(function() {
									if ( this.value.match( matcher ) ) {
										this.selected = valid = true;
										return false;
									}
								});
								if ( !valid ) {
									$( this ).val( "" );
									select.val( "" );
									return false;
								}
							}
						}
					})
					.addClass( "input-alternatives text-input" )
					.click(function() {
						if ( input.autocomplete( "widget" ).is( ":visible" ) && false ) {
							input.autocomplete( "close" );
							return false;
						}

						input.autocomplete( "search", "" );
						input.focus();
						return false;
					});
				
				var id = select.attr("id");
				input.attr("id", id + ".replacement");
				var label = $("#label\\." + id.replace(/\./, "\\."));
				label.attr("for", id + ".replacement");

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};
			}
		});
	})( jQuery );

(function($){

	$.fn.alphanumeric = function(p) { 

		p = $.extend({
			ichars: "!@#$%^&*()+=[]\\\';,/{}|\":<>?~`.- ",
			nchars: "",
			allow: ""
		  }, p);	

		return this.each
			(
				function() 
				{
					s = p.allow.split('');
					for ( i=0;i<s.length;i++) if (p.ichars.indexOf(s[i]) != -1) s[i] = "\\" + s[i];
					p.allow = s.join('|');
					
					var reg = new RegExp(p.allow,'gi');
					var ch = p.ichars + p.nchars;
					ch = ch.replace(reg,'');

					$(this).keypress
						(
							function (e)
								{
								
									if (!e.charCode) k = String.fromCharCode(e.which);
										else k = String.fromCharCode(e.charCode);
										
									if (ch.indexOf(k) != -1) e.preventDefault();
									if (e.ctrlKey&&k=='v') e.preventDefault();
									
								}
								
						);
						
					$(this).bind('contextmenu',function () {return false});
									
				}
			);

	};

	$.fn.only = function(p) {
		
		var az = "";
		
		p = $.extend({
			nchars: az
		  }, p);	
		  	
		return this.each (function()
			{
				$(this).alphanumeric(p);
			}
		);
		
	};

	$.fn.numeric = function(p) {
	
		var az = "abcdefghijklmnopqrstuvwxyz";
		az += az.toUpperCase();

		p = $.extend({
			nchars: az
		  }, p);	
		  	
		return this.each (function()
			{
				$(this).alphanumeric(p);
			}
		);
			
	};
	
	$.fn.alpha = function(p) {

		var nm = "1234567890";

		p = $.extend({
			nchars: nm
		  }, p);	

		return this.each (function()
			{
				$(this).alphanumeric(p);
			}
		);
			
	};	

})(jQuery);

$(window).ready(function() {
	$(".js-datepicker").datepicker($.datepicker.regional[$lang]);
	$("select").combobox();
	$(".fancy-box").accordion();
	$(".fancy-tabs").tabs();
	$(".text-input").addClass("ui-corner-all");
	$(".input-numeric").numeric();
	$(".dismissable-message").css("cursor", "pointer");
	$(".dismissable-message").click(function(event) {
		$(this).fadeOut();
	});
/*
	$("#search-field").keyup(function(event) {
		if (retrieved) {
			delay = false;
			if ($("#search-field").val() == "") {
				$("#lectures").css("display", "none");
				$("#lecturers").css("display", "none");
				$("#lectures-results").empty();
				$("#lecturers-results").empty();
			} else {
				$.get("../search/", {ajax: "true", q: $("#search-field").val()}, function (xml) {
					$("#lectures").css("display", $(xml).find("lecture").length > 0 ? "block" : "none");
					$("#lecturers").css("display", $(xml).find("person").length > 0 ? "block" : "none");
					$("#lectures-results").empty();
					$("#lecturers-results").empty();
					$(xml).find("person").each(function () {
						$("#lecturers-results").append("<li>" + $(this).text() + "</li>");
					});
					$(xml).find("lecture").each(function () {
						$("#lectures-results").append("<li>" + $(this).text() + "</li>");
					});
				}, "text/xml");
				retrieved = true;
			}
		}
	});
*/
});
