$(document).ready(function () {
    $('#mark').change(function(event) {

        $.ajax({

            url : 'get_all_proposes/get_all_marks',

            type : 'get',

            dataType: 'json',

            success: function (data) {

                var select = $('#mark');

                select.find('option').remove();


                $.each(response, function(index, value) {

                    $('<option>').val(value).text(value).appendTo(select);
                });
            }
        });
    });
});