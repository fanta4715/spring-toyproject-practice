//button 에 설정해놓은 id를 통해서 버튼을 받아온다.
const commentCreateButton = document.getElementById('comment-create-btn');


if (commentCreateButton) {
    // 등록 버튼을 클릭하면 /api/articles로 요청을 보낸다
    commentCreateButton.addEventListener('click', event => {
        // let params = new URLSearchParams(location.search);
        // let id = params.get('id');
        //여기에 null이 들어가는 경우 발생 -> location이라는 thymeleaf사용
        let articleId = document.getElementById('new-comment-article-id').value;



        body = JSON.stringify({
            //우리는 body에 content만 넣고
            //api/article/{articleId}/comment로 post mapping하면 된다.
            //input or textarea라는 태그의 id에 해당하는 값을 가져온다.
            content: document.getElementById('content').value
        });
        function success() {
            alert('댓글 등록 완료되었습니다.');
            location.replace(`/articles/${articleId}`);
        };
        function fail() {
            alert('댓글 등록 실패했습니다.');
            location.replace(`/articles/${articleId}`);
        };

        httpRequest('POST',`/api/article/${articleId}/comment`, body, success, fail)
    });
}


// 쿠키를 가져오는 함수
function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(';');
    cookie.some(function (item) {
        item = item.replace(' ', '');

        var dic = item.split('=');

        if (key === dic[0]) {
            result = dic[1];
            return true;
        }
    });

    return result;
}

// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        const refresh_token = getCookie('refresh_token');
        if (response.status === 401 && refresh_token) {
            fetch('/api/token', {
                method: 'POST',
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('access_token'),
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    refreshToken: getCookie('refresh_token'),
                }),
            })
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    }
                })
                .then(result => { // 재발급이 성공하면 로컬 스토리지값을 새로운 액세스 토큰으로 교체
                    localStorage.setItem('access_token', result.accessToken);
                    httpRequest(method, url, body, success, fail);
                })
                .catch(error => fail());
        } else {
            return fail();
        }
    });
}
