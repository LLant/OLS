$(function () {
    $('form').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            courseName: {
                validators: {
                    notEmpty: {
                        message: '毕业院校不能为空'
                    }
                }
            },
            courseIntroduction: {
                validators: {
                    notEmpty: {
                        message: '学位不能为空'
                    }
                }
            },
            courseTip: {
                validators: {
                    notEmpty: {
                        message: '介绍自我不能为空'
                    }
                }
            }
        }
    });
});