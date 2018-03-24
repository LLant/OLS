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
                        message: '课程名称不能为空'
                    }
                }
            },
            courseContent: {
                validators: {
                    notEmpty: {
                        message: '课程内容不能为空'
                    }
                }
            },
            courseIntroduction: {
                validators: {
                    notEmpty: {
                        message: '课程介绍不能为空'
                    }
                }
            },
            courseTip: {
                validators: {
                    notEmpty: {
                        message: '课程提示不能为空'
                    }
                }
            }
        }
    });
});