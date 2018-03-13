$(function () {
    $('form').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            realName: {
                realName: '真实姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    }
                }
            },
            university: {
                validators: {
                    notEmpty: {
                        message: '毕业院校不能为空'
                    }
                }
            },
            degree: {
                validators: {
                    notEmpty: {
                        message: '学位不能为空'
                    }
                }
            },
            self_introduction: {
                validators: {
                    notEmpty: {
                        message: '介绍自我不能为空'
                    }
                }
            }
        }
    });
});