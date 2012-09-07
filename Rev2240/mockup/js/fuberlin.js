var toggled = true;

$(window).scroll(function() {
	var cond = ($(window).scrollTop() > 77);
	if (!(!cond || !toggled)) {
		$('#subnavigation').toggleClass('fixed');
		toggled = !toggled;
	} else if (!(cond || toggled)) {
		$('#subnavigation').toggleClass('fixed');
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

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};
			}
		});
	})( jQuery );

$(window).ready(function() {
	$(".js-datepicker").datepicker($.datepicker.regional[$lang]);
	$("select").combobox();
	$(".fancy-box").accordion();
	$(".fancy-tabs").tabs();
	$(".text-input").addClass("ui-corner-all");

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
});


                             