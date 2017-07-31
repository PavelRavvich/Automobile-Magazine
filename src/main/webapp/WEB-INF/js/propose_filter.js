

$(document).ready(function () {
    $('#mark').focus(function(event) {

        $.ajax({

            url : 'get_all_proposes/get_all_marks',

            type : 'get',

            dataType: 'json',

            success: function (data) {

                var select = $('#mark');

                // select.find('option').remove();

                $.each(data, function(index, value) {

                    var optionMark = $('<option>');

                    optionMark.val(value).text(value).appendTo(select);
                });
            }
        });
    });

    $('#mark').change(function() {
        console.log(this);
        console.log('get_all_proposes/get_model_by_mark/?name='+this.value);
        $.ajax({

            url : 'get_all_proposes/get_model_by_mark',

            type : 'get',

            data : {
                'mark' : this.value
            },

            dataType: 'json',

            success : function(data) {
                console.log(data);
                var optionModel = $('#model');

                $.each(data, function(index, value) {
                    optionModel.append($('<option>').val(value).text(value));
                });
            }
        });
    });
});

