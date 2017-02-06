(function($) {
    'use strict';
    /*
     tooltip
     =========================== */
    $('.tooltips').tooltip({
        selector: "a[data-toggle^=tooltip]"
    })

    /*
     * selectpicker
     * */
    $('.selectpicker').selectpicker();

    /* Client logo hover
     =========================== */
    $(".logo-hover").css({'opacity':'0','filter':'alpha(opacity=0)'});
    $('.client-link').hover(function(){
        $(this).find('.logo-hover').stop().fadeTo(900, 1);
        $(this).find('.client-logo').stop().fadeTo(900, 0);
    }, function() {
        $(this).find('.logo-hover').stop().fadeTo(900, 0);
        $(this).find('.client-logo').stop().fadeTo(900, 1);
    });

    // Estimate from validation
    $(".modal-dialog button#estimGo").prop('disabled', true);
    $('#estimationForm').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: $('#localeEstimateValidationText1').val()
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: $('#localeEstimateValidationText2').val()
                    },
                    emailAddress: {
                        message: $('#localeEstimateValidationText3').val()
                    }
                }
            },
            message: {
                validators: {
                    notEmpty: {
                        message: $('#localeEstimateValidationText4').val()
                    }
                }
            }
        }
    });

    $(".modal-dialog button#estimGo").click(function(){
        estimate();
    });

    // Autorization from validation
    $('#authorizationForm').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: $('#localeAuthorizationValidationText1').val()
                    },
                    emailAddress: {
                        message: $('#localeAuthorizationValidationText2').val()
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: $('#localeAuthorizationValidationText3').val()
                    },
                    stringLength: {
                        min: 3,
                        max: 20,
                        message: $('#localeAuthorizationValidationText4').val()
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: $('#localeAuthorizationValidationText5').val()
                    }
                }
            }
        }
    })

    // Back to top
    var offset = 300,
        offset_opacity = 1200,
        scroll_top_duration = 700,
        $back_to_top = $('.cd-top');

    $(window).scroll(function(){
        ( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
        if( $(this).scrollTop() > offset_opacity ) {
            $back_to_top.addClass('cd-fade-out');
        }
    });
    $back_to_top.on('click', function(event){
        event.preventDefault();
        $('body,html').animate({
                scrollTop: 0 ,
            }, scroll_top_duration
        );
    });

    var hiddenhVal = $("input[type=hidden]").val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

})(jQuery);

function estimate() {
    var $this = document.forms["estimationForm"];
    var name = $this.name.value;
    var email = $this.email.value;
    var message = $this.message.value;
    //alert(name +" " + email + " " + message);

    $.ajax({
        type: "POST",
        data: {name: name , email: email , message: message},
        url: "estimate",
        dataType: "json",
        success: function(data)	{

            if(data.response == "ok") {
                $(".thanks").show();
                $(".modal-body").hide();
                $(".buttonClose").hide();
            } else if (data.response == ""){
                alert("invalid login or password! please try again.");
             }

           //setTimeout(func, 5000);
        },
        error: function(){
        }
    });
}

$(".modal-dialog button#estimClose").click(function(){
    $(".thanks").hide();
    $(".modal-body").show();
    $(".buttonClose").show();
});


