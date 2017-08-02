$(document).ready(function () {
    //Get marks set.
    $('#mark').focus(function(event) {

        $.ajax({

            url : 'get_all_proposes/get_all_marks',

            type : 'get',

            dataType: 'json',

            success: function (data) {

                var select = $('#mark');

                $.each(data, function(index, value) {

                    var optionMark = $('<option>');

                    optionMark.val(value).text(value).appendTo(select);
                });
            }
        });
    });

    //Get models set.
    $('#mark').change(function() {

        $.ajax({

            url : 'get_all_proposes/get_model_by_mark',

            type : 'get',

            data : {
                'mark' : this.value
            },

            dataType: 'json',

            success : function(data) {

                var optionModel = $('#model');

                $.each(data, function(index, value) {

                    optionModel.append($('<option>').val(value).text(value));
                });
            }
        });
    });
});
