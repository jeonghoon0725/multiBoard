const CommonScriptFunction = {
    init : function() {
        const _this = this;

        // 게시글 저장
        $('#btn-posts-save').on('click', function () {
            _this.postSave();
        });
        // 게시글 수정
        $('#btn-posts-update').on('click', function () {
            _this.postUpdate();
        });
        // 게시글 삭제
        $('#btn-posts-delete').on('click', function () {
            _this.postDelete();
        });

        // 회원 수정
        $('#btn-user-update').on('click', function () {
            _this.userUpdate();
        });
    },

    /** 글 작성 */
    postSave : function () {
        const data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };
        console.log(data)
        // 공백 및 빈 문자열 체크
        if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts',
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    /** 글 수정 */
    postUpdate : function () {
        const data = {
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val()
        };

        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");
                return false;
            } else {
                $.ajax({
                    type: 'PUT',
                    url: '/api/posts/' + data.id,
                    dataType: 'JSON',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function () {
                    alert("수정되었습니다.");
                    window.location.href = '/posts/read/' + data.id;
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        }
    },

    /** 글 삭제 */
    postDelete : function () {
        const id = $('#id').val();
        const con_check = confirm("정말 삭제하시겠습니까?");

        if(con_check == true) {
            $.ajax({
                type: 'DELETE',
                url: '/api/posts/'+id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8'

            }).done(function () {
                alert("삭제되었습니다.");
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            return false;
        }
    },

    /** 회원 수정 */
    userUpdate : function () {
        const data = {
            id: $('#id').val(),
            modifiedDate: $('#modifiedDate').val(),
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            password: $('#password').val()
        }
        if(!data.nickname || data.nickname.trim() === "" || !data.password || data.password.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)) {
            alert("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            $('#password').focus();
            return false;
        } else if(!/^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/.test(data.nickname)) {
            alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickname').focus();
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: "PUT",
                url: "/api/user",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert("회원수정이 완료되었습니다.");
                window.location.href = "/";

            }).fail(function (error) {
                if (error.status === 500) {
                    alert("이미 사용중인 닉네임 입니다.");
                    $('#nickname').focus();
                } else {
                    alert(JSON.stringify(error));
                }
            });
        } else {
            return false;
        }
    }
};

CommonScriptFunction.init();


