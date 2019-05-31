$(function(){
    $(".mynumber").numInput({
        width: 200,//宽度
        positive: true,//允许输入正数
        negative: false,//允许输入负数
        //faq：positive，negative不能同时false，同时false按同时为true处理
        floatCount: 0,//小数点后保留位数
        //命名空间，防止样式冲突
        wrapperClass: 'num-input-wrap',//最外层容器
        inputClass: 'num-input',//input输入框
        addClass: 'add',//增加按钮
        subtractClass: 'subtract'//减少按钮
    });
});