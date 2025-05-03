String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};


$(".answerWrite input[type='submit']").click(addAnswer);
function addAnswer(e) {
    e.preventDefault(); // 기본 폼 제출 방지

    var queryString = $("form[name='answer']").serialize();

    $.ajax({
        type: "POST",
        url: "/api/qna/addAnswer", // 실제 동작할 엔드포인트로 수정
        data: queryString,
        error: onError,
        success : onSuccess,
    });
}

function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(json.writer, new Date(json.createDate),
    json.contents, json.answerId);
    $(".qna-comment-slipp-articles").prepend(template);
}