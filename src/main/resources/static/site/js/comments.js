var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHttp");
var Ajax = {
    post: function (url, data) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-type","application/json;charset=UTF-8");
        xhr.onreadystatechange = function () {
            if (xhr.status == 200) {
                window.location.reload();
            } else {
                window.location.reload();
            }
        };
        xhr.send(JSON.stringify(data));
    }
}
var data = {
    pid: 0,
    cid: 0,
    articleTitle: null,
    articleId: null,
    name: null,
    email: null,
    url: null,
    cName: null,
    content: null,
    type: 0, //评论类型，0->detail 1->link 2->about
};
//提交表单
function vsubmit() {
    data.type = document.getElementsByClassName("type")[0].value
    if (document.getElementsByName("articleId").length) {
        data.articleTitle = document.getElementsByName("articleTitle")[0].value
        data.articleId = document.getElementsByName("articleId")[0].value
    }
    data.name = document.getElementsByName("name")[0].value
    data.email = document.getElementsByName("email")[0].value;
    data.url = document.getElementsByName("url")[0].value;
    data.content = document.getElementById("content").value;
    Ajax.post('/comments/save', data);
    console.log(data);
}


