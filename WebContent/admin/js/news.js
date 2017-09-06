$(function(){
CKEDITOR.replace('TextArea1');
    //如果是在ASP.NET环境下用的服务器端控 件<TextBox>
    CKEDITOR.replace('tbContent');
    //如 果<TextBox>控件在母版页中，要这样写
    CKEDITOR.replace('<%=tbContent.ClientID.Replace("_","$") %>');
})